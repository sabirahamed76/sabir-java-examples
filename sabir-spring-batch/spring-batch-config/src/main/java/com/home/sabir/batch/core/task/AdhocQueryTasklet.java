package com.home.sabir.batch.core.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AdhocQueryTasklet implements Tasklet {

	private static final Logger logger = LogManager.getLogger(AdhocQueryTasklet.class);

	private DataSource dataSource;

	private String filePath;
//	private String inFileName;
	private String outFileName;
	private FileWriter fw;
	private Properties prop;

//	private String mode;

//	public static void main(String[] args) {
//		System.out.println(getDate());
//	}

	private void readProperties() {
		InputStream is = null;
		try {
			this.prop = new Properties();
			is = new FileInputStream(filePath);
			this.prop.load(is);
			copyFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}

	private void copyFile() {
		File source = new File(filePath);
		File dest = new File(filePath + "_" + getDate());

		try {
			Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
			new FileWriter(source, false).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AdhocQueryTasklet(DataSource dataSource, String filePath) {
		this.filePath = filePath;
		this.dataSource = dataSource;
		readProperties();
	}

	private String getProperty(String key) {
		String value = "";
		try {
			if (this.prop != null) {
				value = this.prop.getProperty(key);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try {
			String steps = getProperty("steps");
			String sql, mode;
			if (steps != null && !"".equals(steps)) {
				String[] split = steps.split(",");
				if (split != null) {
					for (String step : split) {
						this.fw = null;
						sql = getProperty(step + ".sql");
						mode = getProperty(step + ".mode");
						outFileName = getProperty(step + ".outfile.path");
						executeQuery(sql, mode);
						if (fw != null) {
							fw.close();
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
		return RepeatStatus.FINISHED;
	}

	private void executeQuery(String sql, String mode) {
		if (!sql.equals("")) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			Integer count = 0;
			if (null != mode && "update".equalsIgnoreCase(mode)) {
				count = jdbcTemplate.update(sql);
			} else {
				count = jdbcTemplate.query(sql, (ResultSetExtractor<Integer>) rs -> {
					int rowCount = 0;
					int colCount = rs.getMetaData().getColumnCount();
					String data = "";
					for (int i = 1; i <= colCount; i++) {
						data += "\"" + rs.getMetaData().getColumnName(i) + "\",";
					}

//					logger.debug(data);
					write(data);
					write(System.lineSeparator());

					while (rs.next()) {
						data = "";
						for (int i = 1; i <= colCount; i++) {
							data += "\"" + rs.getString(i) + "\",";
						}

//						logger.debug(data);
						write(data);
						write(System.lineSeparator());
						rowCount++;
					}
					return rowCount;
				});
			}
			logger.info("Total Rows : " + count);
		}
	}

	private void write(String content) {
		try {

			if (fw == null) {
				fw = new FileWriter(outFileName);
			}
			fw.write(content);
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

//	private String getQuery() {
//		String fileString = "";
//		try {
//			fileString = new String(Files.readAllBytes(Paths.get(filePath + inFileName)), StandardCharsets.UTF_8);
//			logger.info("Query from file : " + fileString);
//		} catch (IOException ex) {
//			logger.error(ex.getMessage(), ex);
//		}
//		return fileString;
//	}

}