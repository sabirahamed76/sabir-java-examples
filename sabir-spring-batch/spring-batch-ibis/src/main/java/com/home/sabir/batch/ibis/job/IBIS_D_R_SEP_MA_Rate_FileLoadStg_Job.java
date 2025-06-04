package com.home.sabir.batch.ibis.job;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

import com.home.sabir.batch.core.task.TableOpsTasklet;
import com.home.sabir.batch.core.util.StringUtils;
import com.home.sabir.batch.ibis.config.mapper.field.IBIS_D_R_SEP_MA_Rate_Field;
import com.home.sabir.batch.ibis.config.processor.IBIS_D_R_SEP_MA_Rate_Validation_Processor;
import com.home.sabir.batch.ibis.config.setter.IBIS_D_R_SEP_MA_Rate_Setter;
import com.home.sabir.batch.ibis.model.IBIS_D_R_SEP_MA_Rate_Mdl;

@Configuration
@Lazy
public class IBIS_D_R_SEP_MA_Rate_FileLoadStg_Job {
	
	private static final Logger logger = LogManager.getLogger(IBIS_D_R_SEP_MA_Rate_FileLoadStg_Job.class);

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
	
	private Long count = 0L;

	private String sepMaRateTruncateStgSql = "CALL PRC_TRUNCATE_TABLE('IBIS_D_R_SEP_MA_RATE')";
	
	private String sepMaRateLoadStgSql = "INSERT INTO STG_IBIS_D_R_SEP_MA_RATE ( RECORD_TYPE, NRIC, APPL_YEAR, NTI, APPL_MA_CON_RATE, MAX_APPL_MA_CON_RATE,  MAX_APPL_MA_CON_AMOUNT,  PROCESS_FLG, CREATE_TS ) " + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, 'N', CURRENT_TIMESTAMP) ";
	
	@PostConstruct
	public void printQuery() {
		logger.info("filePath  " +  env.getProperty("filePath"));
		logger.info("fileName  " +  env.getProperty("IBIS_D_R_SEP_MA_Rate_fileName"));
		filePath = env.getProperty("filePath");
		fileName = env.getProperty("IBIS_D_R_SEP_MA_Rate_fileName");
	}

	@Bean
	public Job IBIS_D_R_SEP_MA_Rate_FileLoadStg() {
		logger.info("IBIS_D_R_SEP_MA_Rate_FileLoadStg started");
		return jobBuilderFactory.get("IBIS_D_R_SEP_MA_Rate_FileLoadStg")
				.incrementer(new RunIdIncrementer())
				.start(IBIS_D_R_SEP_MA_Rate_FileLoadStg_TrunStep()) 	//TRUNCATE STAGING TABLE
				.next(IBIS_D_R_SEP_MA_Rate_FileLoadStg_LoadStep())	//READ INPUT FILE AND INSERT INTO STAGING TABLE
				.build();
	}

	@Bean
	public Step IBIS_D_R_SEP_MA_Rate_FileLoadStg_TrunStep(){
		return stepBuilderFactory.get("IBIS_D_R_SEP_MA_Rate_FileLoadStg_TrunStep")
				.tasklet(tasklet(sepMaRateTruncateStgSql))
				.build();       
	}

	public Tasklet tasklet(String sql) {
		return new TableOpsTasklet(sql, dataSource);
	}

	@Bean
	public Step IBIS_D_R_SEP_MA_Rate_FileLoadStg_LoadStep() {
		return stepBuilderFactory.get("IBIS_D_R_SEP_MA_Rate_FileLoadStg_LoadStep")
				.<IBIS_D_R_SEP_MA_Rate_Mdl, IBIS_D_R_SEP_MA_Rate_Mdl>chunk(1000)
				.reader(IBIS_D_R_SEP_MA_Rate_FileLoadStg_ItemReader())
				.processor(itemProcessor())
				.writer(IBIS_D_R_SEP_MA_Rate_FileLoadStg_ItemWriter())
				.build();
	}	

	@Bean
	public ItemReader<IBIS_D_R_SEP_MA_Rate_Mdl> IBIS_D_R_SEP_MA_Rate_FileLoadStg_ItemReader() {
		FlatFileItemReader<IBIS_D_R_SEP_MA_Rate_Mdl> reader = new FlatFileItemReader<>();
		count = StringUtils.getNoOfLines(filePath + fileName);
		logger.info("num of lines present in file: " + count);
		
		reader.setResource(new FileSystemResource(filePath + fileName));
		logger.info("IBIS_D_R_SEP_MA_Rate_FileLoadStg_Job file exists ? " + new FileSystemResource(filePath + fileName).exists());
		reader.setLineMapper(IBIS_D_R_SEP_MA_Rate_FileLoadStg_LineMapper());
		return reader;
	}

	@Bean
	public ItemProcessor<IBIS_D_R_SEP_MA_Rate_Mdl, IBIS_D_R_SEP_MA_Rate_Mdl> itemProcessor () {
		return new ItemProcessor<IBIS_D_R_SEP_MA_Rate_Mdl, IBIS_D_R_SEP_MA_Rate_Mdl>() {			
			@Override
			public IBIS_D_R_SEP_MA_Rate_Mdl process(IBIS_D_R_SEP_MA_Rate_Mdl item) throws Exception {
				//skip header and footer
				if(!IBIS_D_R_SEP_MA_Rate_Validation_Processor.isNull(item) && null == item.getHeader() && null == item.getFooter()) {
					return item;
				}
				else return null;
			}
		};		
	}
	
	@Bean
	public LineMapper<IBIS_D_R_SEP_MA_Rate_Mdl> IBIS_D_R_SEP_MA_Rate_FileLoadStg_LineMapper() {
		DefaultLineMapper<IBIS_D_R_SEP_MA_Rate_Mdl> mapper = new DefaultLineMapper<>();
		mapper.setLineTokenizer(lineTokenizerForSEPMARate());
		mapper.setFieldSetMapper(fieldSetMapperForSEPMARate());
		return mapper;
	}

	public FieldSetMapper<IBIS_D_R_SEP_MA_Rate_Mdl> fieldSetMapperForSEPMARate() {
		return new IBIS_D_R_SEP_MA_Rate_Field(count);
	}

	public LineTokenizer lineTokenizerForSEPMARate() {
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
	public ItemWriter<IBIS_D_R_SEP_MA_Rate_Mdl> IBIS_D_R_SEP_MA_Rate_FileLoadStg_ItemWriter() {	
		JdbcBatchItemWriter<IBIS_D_R_SEP_MA_Rate_Mdl> writer = new JdbcBatchItemWriter<>();        
		writer.setSql(sepMaRateLoadStgSql);        

		writer.setDataSource(dataSource);	
		writer.setItemPreparedStatementSetter(new IBIS_D_R_SEP_MA_Rate_Setter());
		return writer;
	}

}
