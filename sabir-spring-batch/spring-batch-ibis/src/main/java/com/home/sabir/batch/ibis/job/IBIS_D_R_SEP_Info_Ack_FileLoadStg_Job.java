package com.home.sabir.batch.ibis.job;


import static com.home.sabir.batch.ibis.utils.JobUtils.setBatchJobException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.home.sabir.batch.core.model.BatchJobExceptionModel;
import com.home.sabir.batch.core.model.BatchParams;
import com.home.sabir.batch.core.task.BatchJobErrorLogsTasklet;
import com.home.sabir.batch.core.util.DateUtils;
import com.home.sabir.batch.ibis.config.mapper.field.IBIS_D_R_SEP_Info_Ack_Field_Mapper;
import com.home.sabir.batch.ibis.config.processor.IBIS_D_R_SEP_Info_Ack_Val_Processor;
import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_Info_Ack_Model;
import com.home.sabir.batch.ibis.utils.JobUtils;

@Configuration
@Lazy
public class IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job {

	private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Environment env;

	@Autowired
	private BatchParams batchParams;

	private JdbcTemplate jdbcTemplate;

	private String filePath;
	private String fileName;
	public long totalrecordCount;

	public static boolean errorExists;

	private String startDateTime = "";

	private  SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.TIMESTAMP_FORMAT_DD_MMM_YYYY);

	@PostConstruct
	public void printQuery() {
		filePath = env.getProperty("filePath");
		fileName = env.getProperty("IBIS_D_R_SEP_Info_Ack_full_fileName");
		totalrecordCount = JobUtils.getNoOfLines(filePath + fileName);
	}

	private String BANKBatchErrorInsert = "INSERT INTO BATCH_JOB_EXCEPTION_BANK "
			+ "(BATCH_JOB_EXCEPTION_ID, BATCH_JOB_ID, EXECUTION_DATE, LINE_NBR, COLUMN_NAME, REMARKS, COLUMN_VALUE, CREATED_TS, REJECTION_TYPE, REF_KEY_NAMES, REF_KEY_VALUES, RECORD_TYPE)"
			+ "VALUES(BATCH_JOB_EXCEPTION_BANK_SEQ.nextval,(select batch_job_id from batch_job where batch_job_name='IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job'),?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?)";

	@Bean
	public Job IBIS_D_R_SEP_Info_Ack_FileLoadStg() throws Exception {
		logger.info("*****IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job started*****");
		if(null != dataSource) {
			List<String> dateTime = new JdbcTemplate(dataSource).queryForList("SELECT TO_CHAR(CURRENT_TIMESTAMP, 'DD-MON-YY HH.MI.SS.FF9 AM') FROM DUAL" , String.class);
			if(CollectionUtils.isNotEmpty(dateTime))
				startDateTime = dateTime.get(0);
		}
		logger.info("Job start time - " + startDateTime);

		jdbcTemplate = new JdbcTemplate(dataSource);
		RunIdIncrementer runinc = new RunIdIncrementer();
		String forceRun = (String) batchParams.get("forceRun");
		logger.info("IBIS_D_R_SEP_Info_Ack_FileLoadStg forceRun:"+forceRun);
		//if (BANKConstants.FORCE_RUN.equals(forceRun) || BANKJobUtils.todayMonday()) {
			return jobBuilderFactory.get("IBIS_D_R_SEP_Info_Ack_FileLoadStg").incrementer(runinc)
					.start(BANKWRSepInfoAckFileLoadStgStep())
					.next(BANKWRSepInfoAckFileValErrorLogsTasklet())
					.next(BANKWRSepInfoAckFileValErrorExistsCheckStep())
					.build();
		/*}else{
			return jobBuilderFactory.get("IBIS_D_R_SEP_Info_Ack_FileLoadStg").incrementer(runinc)
					.start(BANKWRSepInfoAckExitStep())
					.build();
		}*/


	}
	@Bean
	public Step BANKWRSepInfoAckExitStep() {
		return stepBuilderFactory.get("BANKWRSepInfoAckExitStep").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step BANKWRSepInfoAckFileLoadStgStep() {
		return stepBuilderFactory.get("BANKWRSepInfoAckFileLoadStgStep")
				.<IBIS_D_R_SEP_Info_Ack_Model, IBIS_D_R_SEP_Info_Ack_Model>chunk(1000).reader(BANKWRSepInfoAckFileLoadStgReader())
				.processor(BANKWRSepInfoAckFileLoadStgValidator()).writer(BANKWRSepInfoAckFileLoadStgCompositeItemWriter()).build();
	}

	@Bean
	public Step BANKWRSepInfoAckFileValErrorLogsTasklet(){
		logger.info("BANKWRSepInfoAckFileValErrorLogsTasklet started...");
		return stepBuilderFactory.get("BANKWRSepInfoAckFileValErrorLogsTasklet")
				.tasklet(new BatchJobErrorLogsTasklet(dataSource, "IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job", startDateTime,"BATCH_JOB_EXCEPTION_BANK"))
				.build();       
	}

	@Bean
	public Step BANKWRSepInfoAckFileValErrorExistsCheckStep() throws Exception {
		logger.info("BANKWRSepInfoAckFileValErrorExistsCheckStep started...");

		return stepBuilderFactory.get("BANKWRSepInfoAckFileValErrorExistsCheckStep")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						if(IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job.errorExists) {
							if(totalrecordCount == 0)
								throw new Exception("Empty File found!. So it is rejected!!!");
							else throw new Exception("File validation errors exist. So file is rejected!!!");
						}

						return RepeatStatus.FINISHED;
					}
				})
				.build();
	}

	@Bean
	public ItemReader<IBIS_D_R_SEP_Info_Ack_Model> BANKWRSepInfoAckFileLoadStgReader() {
		FlatFileItemReader<IBIS_D_R_SEP_Info_Ack_Model> reader = new FlatFileItemReader<>();
		reader.setStrict(false);
		logger.info("SEPInfoAck IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job.sepFileReader fileName:" + fileName);
		logger.info("SEPInfoAck IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job.sepFileReader file exists ? :" + new FileSystemResource(filePath + fileName).exists());
		reader.setResource(new FileSystemResource(filePath + fileName));
		reader.setLineMapper(BANKWRSepInfoAckFileLoadStgLineMapper());

		return reader;
	}

	@Bean
	public LineMapper<IBIS_D_R_SEP_Info_Ack_Model> BANKWRSepInfoAckFileLoadStgLineMapper() {
		DefaultLineMapper<IBIS_D_R_SEP_Info_Ack_Model> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(BANKWRSepInfoAckFileLoadStgLineTokenizer());
		lineMapper.setFieldSetMapper(new IBIS_D_R_SEP_Info_Ack_Field_Mapper(totalrecordCount));
		return lineMapper;
	}

	@Bean
	public LineTokenizer BANKWRSepInfoAckFileLoadStgLineTokenizer() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		tokenizer.setStrict(false);

		tokenizer.setNames("Record_Type", "Self_Employed_Person_NRIC", "CAYE_Contribution_Rate", "Error_Code",
				"Error_Description", "Filler");

		tokenizer.setColumns(new Range(1, 3), new Range(4, 12), new Range(13, 16), new Range(17, 19),
				new Range(20, 169), new Range(170, 300));

		return tokenizer;
	}

	@Bean
	public ItemProcessor<IBIS_D_R_SEP_Info_Ack_Model, IBIS_D_R_SEP_Info_Ack_Model> BANKWRSepInfoAckFileLoadStgValidator() {
		return new IBIS_D_R_SEP_Info_Ack_Val_Processor(totalrecordCount);
	}

	@Bean
	public CompositeItemWriter<IBIS_D_R_SEP_Info_Ack_Model> BANKWRSepInfoAckFileLoadStgCompositeItemWriter() {
		CompositeItemWriter<IBIS_D_R_SEP_Info_Ack_Model> compositeItemWriter = new CompositeItemWriter<>();
			List<ItemWriter<? super IBIS_D_R_SEP_Info_Ack_Model>> delgatesList = new ArrayList<>();
			delgatesList.add(0, BANKWRLoadErrorMsg());
			compositeItemWriter.setDelegates(delgatesList);

			logger.info("Job end time - " + dateFormat.format(new Timestamp(System.currentTimeMillis())));
			logger.info("*****IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job ended*****");
		return compositeItemWriter;
	}

	@StepScope
	@Bean
	public ItemWriter<? super IBIS_D_R_SEP_Info_Ack_Model> BANKWRLoadErrorMsg() {
		return new ItemWriter<IBIS_D_R_SEP_Info_Ack_Model>() {
			@Override
			public void write(List<? extends IBIS_D_R_SEP_Info_Ack_Model> items) throws Exception {
				if(IBIS_D_R_SEP_Info_Ack_Field_Mapper.totalRowCount.equals(IBIS_D_R_SEP_Info_Ack_Val_Processor.currentRowNumber)) {
					List<BatchJobExceptionModel> exceptionModelList = IBIS_D_R_SEP_Info_Ack_Val_Processor.exceptionModelList;
					logger.info("SEPInfoAck  exceptionModel size:"+exceptionModelList.size());
					setBatchJobException(exceptionModelList, jdbcTemplate, BANKBatchErrorInsert, startDateTime);

					if(CollectionUtils.isNotEmpty(exceptionModelList))
						IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job.errorExists = true;
					logger.info("value of IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job.errorExists - " + IBIS_D_R_SEP_Info_Ack_FileLoadStg_Job.errorExists);
				}
			}
		};
	}

}
