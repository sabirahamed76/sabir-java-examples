package com.home.sabir.batch.ibis.job;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

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
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

import com.home.sabir.batch.core.model.BatchParams;
import com.home.sabir.batch.ibis.config.footer.IBIS_D_S_SEP_Info_Footer;
import com.home.sabir.batch.ibis.config.header.IBIS_D_S_SEP_Info_Header;
import com.home.sabir.batch.ibis.config.mapper.row.IBIS_D_S_SEP_LoadStg_Mapper;
import com.home.sabir.batch.ibis.model.IBIS_D_S_SEP_Info_Model;

@Configuration
@Lazy
public class IBIS_D_S_SEP_Info_FileCreate_Job {
	
	private static final Logger logger = LogManager.getLogger(IBIS_D_S_SEP_Info_FileCreate_Job.class);
	
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
	
	private String filePath ;
	private String fileName ;
	
	@PostConstruct
	public void printQuery() {
		logger.info("filePath  " +  env.getProperty("filePath"));
		logger.info("fileName  " +  env.getProperty("IBIS_D_S_SEP_Info_full_fileName"));
		filePath = env.getProperty("filePath");
		fileName = env.getProperty("IBIS_D_S_SEP_Info_full_fileName");
	}
	
	private String sepStgLoad_Select = "SELECT RECORD_TYPE,SEP_NRIC,CAYE_CON_RATE FROM STG_IBIS_D_S_SEP_CAYE_RATE";
	
	//Load Staging table into Fixed length file
	@Bean
	public Job IBIS_D_S_SEP_Info_FileCreate() {
		logger.info("IBIS_D_S_SEP_Info_FileCreate started...");
		String forceRun = (String) batchParams.get("forceRun");
		LocalDate date = LocalDate.now();
			return jobBuilderFactory.get("IBIS_D_S_SEP_Info_FileCreate")
					.incrementer(new RunIdIncrementer())
					.start(BANKWSSEPInfoFileCreatStep()) //Load Staging Table and Write to file
					.build();
		
	}
	
	@Bean
    public Step BANKWSSEPInfoFileCreatStep(){
        return stepBuilderFactory.get("BANKWSSEPInfoFileCreatStep")
        		.<IBIS_D_S_SEP_Info_Model, IBIS_D_S_SEP_Info_Model> chunk(1000)
        		.reader(BANKWSSepInfoFileCreateReader())
        		.writer(BANKWSSepInfoFileCreateWriter())
//        		.exceptionHandler(exceptionHandler())
                .build();       
    }
	
	@Bean
    public Step BANKWSSEPInfoExitStep(){
		return stepBuilderFactory.get("BANKWSSEPInfoExitStep")
				.tasklet(new Tasklet() {					
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						return RepeatStatus.FINISHED;
					}
				})
				.build();
	}
	
/*	public ExceptionHandler exceptionHandler() {
		return (context, throwable) -> {logger.error("****"+throwable.getMessage(),throwable); throw throwable;};
	}*/

	public ItemStreamReader<IBIS_D_S_SEP_Info_Model> BANKWSSepInfoFileCreateReader() {
		 JdbcCursorItemReader<IBIS_D_S_SEP_Info_Model> reader = new JdbcCursorItemReader<IBIS_D_S_SEP_Info_Model>();
		 reader.setDataSource(dataSource);
		 reader.setSql(sepStgLoad_Select);
		 reader.setRowMapper(new IBIS_D_S_SEP_LoadStg_Mapper());
		 return reader;
	}
	
	@Bean
	public FlatFileItemWriter<IBIS_D_S_SEP_Info_Model> BANKWSSepInfoFileCreateWriter() {

		
		FlatFileItemWriter<IBIS_D_S_SEP_Info_Model> writer = new FlatFileItemWriter<>();
		DelimitedLineAggregator<IBIS_D_S_SEP_Info_Model> lineAggregator = new DelimitedLineAggregator<>();
		FieldExtractor<IBIS_D_S_SEP_Info_Model> fieldExtractor = sepFDFieldExtractor();
		
		writer.setHeaderCallback(BANKWSSepInfoFileHeader());
		writer.setFooterCallback(BANKWSSepInfoFileFooter());
		
		lineAggregator.setDelimiter("");

		lineAggregator.setFieldExtractor(fieldExtractor);
		writer.setLineAggregator(lineAggregator);
		writer.setLineSeparator("\r\n");
		
		writer.setResource(new FileSystemResource(filePath + fileName));		
		
		return writer;
	}
	
	private FieldExtractor<IBIS_D_S_SEP_Info_Model> sepFDFieldExtractor() {
		BeanWrapperFieldExtractor<IBIS_D_S_SEP_Info_Model> extractor = new BeanWrapperFieldExtractor<>();
		extractor.setNames(new String[] { "RECORD_TYPE", "SEP_NRIC", "CAYE_CON_RATE", "Filler"});
		return extractor;
	}
	
	@Bean
	@StepScope
	public FlatFileHeaderCallback BANKWSSepInfoFileHeader() {
		return new IBIS_D_S_SEP_Info_Header();
	}

	@Bean
	@StepScope
	public FlatFileFooterCallback BANKWSSepInfoFileFooter() {
		return new IBIS_D_S_SEP_Info_Footer();
	}

}
