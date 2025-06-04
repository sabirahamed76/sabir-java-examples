package com.home.sabir.batch.ibis.job;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import com.home.sabir.batch.core.model.BatchJobExceptionModel;
import com.home.sabir.batch.core.task.BatchJobErrorLogsTasklet;
import com.home.sabir.batch.core.util.DateUtils;
import com.home.sabir.batch.core.util.StringUtils;
import com.home.sabir.batch.ibis.config.mapper.field.IBIS_D_R_SEP_MA_Rate_Field;
import com.home.sabir.batch.ibis.config.processor.IBIS_D_R_SEP_MA_Rate_Validation_Processor;
import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_MA_Rate_Mdl;

@Configuration
@Lazy
public class IBIS_D_R_SEP_MA_Rate_FileValidate_Job {

	private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_MA_Rate_FileValidate_Job.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Environment env;

	private String filePath ;
	private String fileName ;

	private static boolean errorExists = true;

	private String startDateTime = "";

	private  SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.TIMESTAMP_FORMAT_DD_MMM_YYYY);

	private Long count = 0L;

	private String batchErrorInsertSql = "INSERT INTO BATCH_JOB_EXCEPTION_SEM " +
			"(BATCH_JOB_EXCEPTION_ID, BATCH_JOB_ID, EXECUTION_DATE, LINE_NBR, COLUMN_NAME, REMARKS,  CREATED_TS, COLUMN_VALUE, REJECTION_TYPE, REF_KEY_NAMES, REF_KEY_VALUES, RECORD_TYPE) " +
			"VALUES(BATCH_JOB_EXCEPTION_SEM_SEQ.NEXTVAL, (SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME='IBIS_D_R_SEP_MA_Rate_FileValidate_Job'), " +
			"?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";	


	@PostConstruct
	public void printQuery() {
		filePath = env.getProperty("filePath");
		fileName = env.getProperty("IBIS_D_R_SEP_MA_Rate_fileName");
		logger.info("IBIS_D_R_SEP_MA_Rate_FileValidate_Job filePath: " + filePath);
		logger.info("IBIS_D_R_SEP_MA_Rate_FileValidate_Job fileName: " + fileName);
	}

	@Bean
	public Job IBIS_D_R_SEP_MA_Rate_FileValidate() throws Exception {
		logger.info("*****IBIS_D_R_SEP_MA_Rate_FileValidate started*****");
		if(null != dataSource) {
			List<String> dateTime = new JdbcTemplate(dataSource).queryForList("SELECT TO_CHAR(CURRENT_TIMESTAMP, 'DD-MON-YY HH.MI.SS.FF9 AM') FROM DUAL" , String.class);
			if(CollectionUtils.isNotEmpty(dateTime))
				startDateTime = dateTime.get(0);
		}	
		logger.info("Job start time - " + startDateTime);

		return jobBuilderFactory.get("IBIS_D_R_SEP_MA_Rate_FileValidate")
				.incrementer(new RunIdIncrementer())
				.start(IBIS_D_R_SEP_MA_Rate_FileValidate_Step())
				.next(SEPMARateErrorLogsTasklet())
				.next(IBIS_D_R_SEP_MA_Rate_FileValidate_ErrorExistsCheck())
				.build();
	}

	@Bean
	public Step IBIS_D_R_SEP_MA_Rate_FileValidate_Step() {
		logger.info("IBIS_D_R_SEP_MA_Rate_FileValidate_Step started...");
		IBIS_D_R_SEP_MA_Rate_Validation_Processor processor = new IBIS_D_R_SEP_MA_Rate_Validation_Processor(dataSource);
		return stepBuilderFactory.get("IBIS_D_R_SEP_MA_Rate_FileValidate_Step")
				.<IBIS_D_R_SEP_MA_Rate_Mdl, IBIS_D_R_SEP_MA_Rate_Mdl>chunk(1000)
				.reader(IBIS_D_R_SEP_MA_Rate_FileValidate_ItemReader())
				.processor(processor)
				.writer(IBIS_D_R_SEP_MA_Rate_FileValidate_ItemWriter())
				.build();				
	}

	@Bean
	public Step IBIS_D_R_SEP_MA_Rate_FileValidate_ErrorExistsCheck() throws Exception {
		logger.info("IBIS_D_R_SEP_MA_Rate_FileValidate_ErrorExistsCheck started...");		

		return stepBuilderFactory.get("IBIS_D_R_SEP_MA_Rate_FileValidate_ErrorExistsCheck")
				.tasklet(new Tasklet() {						
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						if(IBIS_D_R_SEP_MA_Rate_FileValidate_Job.errorExists) {
							if(count == 0)
								throw new Exception("Empty File found!. So it is rejected!!!");
							else throw new Exception("File validation errors exist. So file is rejected!!!");
						}
						return RepeatStatus.FINISHED;
					}
				})
				.build();
	}

	@Bean
	public Step SEPMARateErrorLogsTasklet(){
		logger.info("SEPMARateErrorLogsTasklet started...");
		return stepBuilderFactory.get("SEPMARateErrorLogsTasklet")
				.tasklet(new BatchJobErrorLogsTasklet(dataSource, "IBIS_D_R_SEP_MA_Rate_FileValidate_Job", startDateTime,"BATCH_JOB_EXCEPTION_SEM"))
				.build();       
	}

	@Bean
	public ItemReader<IBIS_D_R_SEP_MA_Rate_Mdl> IBIS_D_R_SEP_MA_Rate_FileValidate_ItemReader() {
		logger.info("IBIS_D_R_SEP_MA_Rate_FileValidate_ItemReader started...");
		FlatFileItemReader<IBIS_D_R_SEP_MA_Rate_Mdl> reader = new FlatFileItemReader<>();
		count = StringUtils.getNoOfLines(filePath + fileName);
		logger.info("num of lines present in file: " + count);

		reader.setResource(new FileSystemResource(filePath + fileName));
		logger.info("IBIS_D_R_SEP_MA_Rate_FileValidate_Job file exists ? " + new FileSystemResource(filePath + fileName).exists());
		reader.setLineMapper(IBIS_D_R_SEP_MA_Rate_FileValidate_LineMapper());
		return reader;
	}

	@Bean
	public LineMapper<IBIS_D_R_SEP_MA_Rate_Mdl> IBIS_D_R_SEP_MA_Rate_FileValidate_LineMapper() {
		logger.info("IBIS_D_R_SEP_MA_Rate_FileValidate_LineMapper started...");
		DefaultLineMapper<IBIS_D_R_SEP_MA_Rate_Mdl> mapper = new DefaultLineMapper<>();
		mapper.setLineTokenizer(lineTokenizerForSEPMARate());
		mapper.setFieldSetMapper(fieldSetMapperForSEPMARate());
		return mapper;
	}

	public FieldSetMapper<IBIS_D_R_SEP_MA_Rate_Mdl> fieldSetMapperForSEPMARate() {
		logger.info("fieldSetMapperForSEPMARate started...");
		return new IBIS_D_R_SEP_MA_Rate_Field(count);
	}

	public LineTokenizer lineTokenizerForSEPMARate() {
		logger.info("lineTokenizerForSEPMARate started...");
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();		
		Map<String, Range> mapper = getFieldMapperSEPMARate();
		Range[] columns = new Range[mapper.size()];
		String[] names = new String[mapper.size()];
		Set<Map.Entry<String, Range>> entries = mapper.entrySet();
		int i = 0;
		for (Map.Entry<String, Range> entry : entries) {
			columns[i] = entry.getValue();
			names[i] = entry.getKey();
			i++;
		}

		tokenizer.setColumns(columns);
		tokenizer.setNames(names);
		tokenizer.setStrict(false);
		return tokenizer;
	}

	public Map<String, Range> getFieldMapperSEPMARate() {
		logger.info("getFieldMapperSEPMARate started...");
		Map<String, Range> fieldMap = new LinkedHashMap<>();
		fieldMap.put("RECORD_TYPE", new Range(1,1));  
		fieldMap.put("NRIC", new Range(2,10));
		fieldMap.put("APPL_YEAR", new Range(11,14));
		fieldMap.put("NTI", new Range(15,27));
		fieldMap.put("APPL_MA_CON_RATE", new Range(28,34));
		fieldMap.put("MAX_APPL_MA_CON_RATE", new Range(35,41));
		fieldMap.put("MAX_APPL_MA_CON_AMOUNT", new Range(42,50));
		return fieldMap;
	}

	@Bean
	@StepScope
	public ItemWriter<IBIS_D_R_SEP_MA_Rate_Mdl> IBIS_D_R_SEP_MA_Rate_FileValidate_ItemWriter() {
		return new ItemWriter<IBIS_D_R_SEP_MA_Rate_Mdl>() {
			@Override
			public void write(List<? extends IBIS_D_R_SEP_MA_Rate_Mdl> items) throws Exception {
				if(IBIS_D_R_SEP_MA_Rate_Field.lastLineCount.equals(IBIS_D_R_SEP_MA_Rate_Validation_Processor.readCount)) {
					logger.info("IBIS_D_R_SEP_MA_Rate_FileValidate_ItemWriter started...");
					List<String> errorList = IBIS_D_R_SEP_MA_Rate_Validation_Processor.errorList;
					JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);		

					logger.info("errorList.isEmpty() ? " + errorList.isEmpty());		
					if(!errorList.isEmpty()) {
						batchJobErrorInsert(jdbcTemplate, errorList);
						logger.error("IBIS_D_R_SEP_MA_Rate_FileValidate_Job error exists - true");
					} else {
						IBIS_D_R_SEP_MA_Rate_FileValidate_Job.errorExists = false;
						logger.info("IBIS_D_R_SEP_MA_Rate_FileValidate_Job error exists - false");
					}
					logger.info("Job end time - " + dateFormat.format(new Timestamp(System.currentTimeMillis())));
					logger.info("*****IBIS_D_R_SEP_MA_Rate_FileValidate_Job ended*****");
				}
			}
		};
	}

	private void batchJobErrorInsert(JdbcTemplate jdbcTemplate, List<String> errorList) {
		logger.info("batchJobErrorInsert started...");
		String[] array;
		List<BatchJobExceptionModel> errorModelList = new ArrayList<>();
		BatchJobExceptionModel model;				

		for(String item : errorList) {
			model = new BatchJobExceptionModel();
			array = item.split("~");
			model.setCOLUMN_NAME((null != array[5] && !array[5].equals("")) ? array[5] : null);
			model.setLINE_NBR(!array[1].equals("") ? Long.valueOf(array[1]) : null);
			model.setREMARKS(array[0]);
			model.setCOLUMN_VALUE(!array[2].equals("") ? array[2] : null);
			model.setREJECTION_TYPE("File");
			model.setREF_KEY_NAMES((!array[3].equals("") && !array[3].equals("null")) ? "NRIC" : null);
			model.setREF_KEY_VALUES((!array[3].equals("") && !array[3].equals("null")) ? array[3] : null);
			model.setRECORD_TYPE(!array[4].equals("") ? array[4] : null);
			errorModelList.add(model);
		}

		jdbcTemplate.batchUpdate(batchErrorInsertSql, errorModelList, errorModelList.size(), new ParameterizedPreparedStatementSetter<BatchJobExceptionModel>() {						               
			public void setValues(PreparedStatement ps, BatchJobExceptionModel argument) 
					throws SQLException {				
				if(null != startDateTime && !startDateTime.equals(""))
					ps.setString(1, startDateTime);
				else ps.setNull(1, java.sql.Types.VARCHAR);
				if(null != argument.getLINE_NBR())
					ps.setBigDecimal(2, new BigDecimal(argument.getLINE_NBR()));
				else ps.setNull(2, java.sql.Types.BIGINT);
				ps.setString(3, argument.getCOLUMN_NAME());
				ps.setString(4, argument.getREMARKS());
				if(null != argument.getCOLUMN_VALUE())
					ps.setString(5, argument.getCOLUMN_VALUE());
				else ps.setNull(5, java.sql.Types.VARCHAR);
				ps.setString(6, argument.getREJECTION_TYPE());
				if(null != argument.getREF_KEY_NAMES())
					ps.setString(7, argument.getREF_KEY_NAMES());
				else ps.setNull(7, java.sql.Types.VARCHAR);
				if(null != argument.getREF_KEY_VALUES())
					ps.setString(8, argument.getREF_KEY_VALUES());
				else ps.setNull(8, java.sql.Types.VARCHAR);
				if(null != argument.getRECORD_TYPE())
					ps.setString(9, argument.getRECORD_TYPE());
				else ps.setNull(9, java.sql.Types.VARCHAR);
			}
		});
		logger.info("batchJobErrorInsert ended...");
	}
}
