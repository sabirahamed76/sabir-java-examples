package com.home.sabir.batch.admin.job;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.home.sabir.batch.admin.constants.AdminConstants;
import com.home.sabir.batch.admin.model.ADMIN_D_P_Comp_Email_Model;
import com.home.sabir.batch.core.constants.BatchJobConstants;

@Configuration
@Lazy
public class ADMIN_D_P_Comp_Email_Notify_Job {
	
	private static final Logger logger = LogManager.getLogger(ADMIN_D_P_Comp_Email_Notify_Job.class);
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;
	

	@Value("${confPath}")
	String confPath;

	@Autowired
	private Environment env;
	
	private String senderAdd;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private final Integer[] count = {0};
	
	private String emailSelect ="SELECT COMP_CSN_NOTIFY_TXN_ID,EMAIL_TO,EMAIL_SUBJECT,EMAIL_MESSAGE,ADMIN_EMAIL_TEMPL_INFO_ID, TRANS_NBR from COMP_CSN_NOTIFY_TXN where EMAIL_STATUS='TBS' and EMAIL_SENT_DATE is NULL ";
	private String updateSucEmail = "update COMP_CSN_NOTIFY_TXN set EMAIL_STATUS='SNT',EMAIL_SENT_DATE = CURRENT_DATE ,UPDATED_SOURCE='BATCH',UPDATED_TS= CURRENT_TIMESTAMP,UPDATED_BY=(select batch_job_id from Batch_job where batch_job_name ='ADMIN_D_P_Comp_Email_Notify_Job' )  where COMP_CSN_NOTIFY_TXN_ID=";
	private String updateFailEmail = "update COMP_CSN_NOTIFY_TXN set EMAIL_STATUS='FAL',UPDATED_SOURCE='BATCH',UPDATED_TS= CURRENT_TIMESTAMP,UPDATED_BY=(select batch_job_id from Batch_job where batch_job_name ='ADMIN_D_P_Comp_Email_Notify_Job' )  where COMP_CSN_NOTIFY_TXN_ID=";
	
	@PostConstruct
	public void printQuery() {
		senderAdd = env.getProperty("SENDER_EMAIL_ADDRESS");
		logger.info("ADMIN_DPCompEmail sender Address:"+senderAdd);
	}
	
	@Bean
	public Job ADMIN_D_P_Comp_Email_Notify (){
		
		return jobBuilderFactory.get("ADMIN_D_P_Comp_Email_Notify").incrementer(new RunIdIncrementer())
				.start(comp_notify_email())
				//.next(updateNotify())
				.build();
		
		
	}

/*	private Step updateNotify() {
		return stepBuilderFactory.get("updateNotify").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				int updatedCount = jdbcTemplate.update(updateEmail);
				logger.info("ADMIN_DPCompEmail emailsent Count:"+updatedCount);
				return RepeatStatus.FINISHED;
			}
		}).build();
	}*/


	@Bean
	public Step comp_notify_email() {
		return stepBuilderFactory.get("comp_notify_email")
				.<ADMIN_D_P_Comp_Email_Model, ADMIN_D_P_Comp_Email_Model>chunk(1000)
				.reader(read_EmailContent_COMP())
				.writer(send_Email())
				.chunk(20)
				.build();
	}


	private ItemWriter<ADMIN_D_P_Comp_Email_Model> send_Email() {
		return new ItemWriter<ADMIN_D_P_Comp_Email_Model>() {
			@Override
			public void write(List<? extends ADMIN_D_P_Comp_Email_Model> modelList) throws Exception {
				
				if(CollectionUtils.isNotEmpty(modelList)){
					modelList.stream().forEach(model->{
						JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
						logger.info("value of COMP_CSN_NOTIFY_TXN_ID - " + model.getCompCsnNotifyTxnId());
						logger.info("value of TRANS_NBR - " + model.getTransNbr());
						logger.info("value of ADMIN_EMAIL_TEMPL_INFO_ID - " + model.getAdminEmailTemplInfoId());
						try {
							MimeMessage mimeMessage = mailSender.createMimeMessage();
							MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
							String[] receiver = model.getReceiver().split(",");
								helper.setTo(receiver);
							helper.setFrom(new InternetAddress(model.getSender()));
							helper.setSubject(model.getSubject());
							helper.setText(model.getEmailMessage(),true);
							if (model.getAdminEmailTemplInfoId().equals(AdminConstants.SUCCESSFUL_REGISTRATION_OF_CAYE_CSN) ||
								model.getAdminEmailTemplInfoId().equals(AdminConstants.YOUR_COMPANYS_DDA_APPLICATION_FOR_CAYE_WAS_UNSUCCESSFUL)	){
								FileSystemResource file = new FileSystemResource(new File(confPath+"/"+BatchJobConstants.DDA_ATTACH_FORM));
								helper.addAttachment(BatchJobConstants.DDA_ATTACH_FORM, file);
							}
							mailSender.send(mimeMessage);
							
							jdbcTemplate.update(updateSucEmail +  model.getCompCsnNotifyTxnId());
							
						}  catch (Exception e) {
							jdbcTemplate.update(updateFailEmail +  model.getCompCsnNotifyTxnId());
							logger.error("ADMIN_D_P_Comp_Email send Email Exception:"+e.getMessage(),e);
						}
					});
				}
			}
		};
	}

	private JdbcCursorItemReader<ADMIN_D_P_Comp_Email_Model> read_EmailContent_COMP() {
		JdbcCursorItemReader<ADMIN_D_P_Comp_Email_Model> dataItemReader = new JdbcCursorItemReader<ADMIN_D_P_Comp_Email_Model>();
		dataItemReader.setSql(emailSelect);
		dataItemReader.setDataSource(dataSource);
		dataItemReader.setRowMapper(new RowMapper<ADMIN_D_P_Comp_Email_Model>() {
			@Override
			public ADMIN_D_P_Comp_Email_Model mapRow(ResultSet rs, int arg1) throws SQLException {
				ADMIN_D_P_Comp_Email_Model model = new ADMIN_D_P_Comp_Email_Model();
				model.setCompCsnNotifyTxnId(rs.getString("COMP_CSN_NOTIFY_TXN_ID"));
				model.setSubject(rs.getString("EMAIL_SUBJECT"));
				model.setSender(senderAdd);
				model.setEmailMessage(rs.getString("EMAIL_MESSAGE"));
				model.setReceiver(rs.getString("EMAIL_TO"));
				model.setAdminEmailTemplInfoId(rs.getString("ADMIN_EMAIL_TEMPL_INFO_ID"));
				model.setTransNbr(rs.getString("TRANS_NBR"));
				return model;
			}
		});
		return dataItemReader;
	}

}
