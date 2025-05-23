package com.home.sabir.batch.common.job;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.home.sabir.batch.core.task.TableOpsTasklet;

@Configuration
@Lazy
public class COMMON_A_P_Email_Test_Job {
	
	private static final Logger logger = LogManager.getLogger(COMMON_A_P_Email_Test_Job.class);
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Environment env;
	
	private String senderAdd;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private final Integer[] count = {0};
	

	String batchJobIdSql = "SELECT BATCH_JOB_ID FROM BATCH_JOB WHERE BATCH_JOB_NAME='SYSTEM'";	
	

	
	@PostConstruct
	public void printQuery() {
		senderAdd = env.getProperty("SENDER_EMAIL_ADDRESS");
		logger.info("ADMIN_Email_Test_Job sender Address:"+senderAdd);
	}
	
	@Bean
	public Job ADMIN_Email_Test (){
		
		return jobBuilderFactory.get("COMMON_A_P_Email_Test").incrementer(new RunIdIncrementer())
				.start(test_email())
				.build();
		
		
	}



	@Bean
	public Step test_email() {
		try{
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo("shabir@ahadsoft.com");
			helper.setFrom(new InternetAddress("shabir@ahadsoft.com"));
			helper.setSubject("Test mail subject from SPRING BATCH PROJECT");
			helper.setText("Test Email Message",true);
			//helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
			mailSender.send(mimeMessage);
			logger.debug("=======================Email Sent Successfully================================");
		}  catch (Exception e) {
			logger.debug("=======================Email failed to send================================");
			logger.error("ADMIN_Email_Test_Job send Email Exception:"+e.getMessage(),e);
		}
	
	
		return stepBuilderFactory.get("test_email")
				.tasklet(tasklet(batchJobIdSql))
				.build();       
	}
	
	public Tasklet tasklet(String sql) {
		return new TableOpsTasklet(batchJobIdSql, dataSource);
	}




}
