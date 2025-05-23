package com.home.sabir.batch.ibis.utils;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;


import com.home.sabir.batch.ibis.constants.IBISConstants;
import com.home.sabir.batch.core.model.BatchJobExceptionModel;

public class JobUtils {
	private static final Logger log = LogManager.getLogger(JobUtils.class);
	public static long getNoOfLines(String fileName) {
		long totalRecordCount = 0;
		try(Stream<String> totalFileStream =Files.lines(Paths.get(fileName)) ) {
            totalRecordCount = totalFileStream.count();
        } catch (Exception e) {
            totalRecordCount = 0;
        }
		return totalRecordCount;
	}

	public static List<BatchJobExceptionModel> getExceptionModelList(List<String> errorList) {
		List<BatchJobExceptionModel> exceptionModelList = new ArrayList<BatchJobExceptionModel>();
		if (errorList != null) {
			errorList.forEach(error -> {
				String errors[] = error.split(IBISConstants.Splitter);
				BatchJobExceptionModel model = new BatchJobExceptionModel();
				model.setCOLUMN_NAME(errors[0]);
				model.setREMARKS(errors[1]);
				model.setLINE_NBR(Long.valueOf(errors[2]));
				model.setCOLUMN_VALUE(StringUtils.stripEnd(errors[3],IBISConstants.SPACE));
				exceptionModelList.add(model);
			});
		}
		return exceptionModelList;
	}
	
	public static void setBatchJobException(List<BatchJobExceptionModel> errorModelList,JdbcTemplate jdbcTemplate,String sql, String startDateTime) {
		jdbcTemplate.batchUpdate(sql, errorModelList, errorModelList.size(),
				new ParameterizedPreparedStatementSetter<BatchJobExceptionModel>() {
					public void setValues(PreparedStatement ps, BatchJobExceptionModel argument) throws SQLException {
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
	}
	
	public static void truncateSTG(String sql,JdbcTemplate jdbcTemplate) {
		jdbcTemplate.execute(sql);
	}
	
	public static BatchJobExceptionModel formExceptionModel(String columnName,String remarks,Long lineNumber,String columnValue, String refKeyNames, String typeOfRecord){
		BatchJobExceptionModel batchJobExceptionModel = new BatchJobExceptionModel();
		batchJobExceptionModel.setCOLUMN_NAME(columnName);
		batchJobExceptionModel.setREMARKS(remarks);
		batchJobExceptionModel.setLINE_NBR(lineNumber);
		batchJobExceptionModel.setCOLUMN_VALUE(columnValue);
		batchJobExceptionModel.setREJECTION_TYPE("File");
		batchJobExceptionModel.setRECORD_TYPE(typeOfRecord);
		batchJobExceptionModel.setREF_KEY_NAMES(refKeyNames);
		batchJobExceptionModel.setREF_KEY_VALUES(typeOfRecord.equals("DETAIL") ? columnValue : null);
		return batchJobExceptionModel;
		
	}
	
	public static boolean publicHoliday(JdbcTemplate jdbcTemplate){
		boolean publicHoliday = false ;
		List<String> holiday = jdbcTemplate.queryForList("SELECT 'Y' FROM TB_CPP_HDY WHERE HDY_DTE = TRUNC(CURRENT_DATE)", String.class);
		if(CollectionUtils.isNotEmpty(holiday)) {
			publicHoliday = true;
		}
		log.info("SEPInfoAck CompDDAInfoAck publicHoliday:"+publicHoliday);
		return publicHoliday;
	}
	
	public static boolean todayMonday(){
		log.info("SEPInfoAck CompDDAInfoAck todayMonday:"+LocalDate.now().getDayOfWeek().equals(DayOfWeek.MONDAY));
		return LocalDate.now().getDayOfWeek().equals(DayOfWeek.MONDAY);
	}

}
