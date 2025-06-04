package com.home.sabir.batch.ibis.config.header;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.beans.factory.annotation.Value;

import com.home.sabir.batch.core.util.StringUtils;

public class IBIS_D_S_SEP_Info_Header implements FlatFileHeaderCallback {
	
	@Value("#{StepExecution}")
	private StepExecution stepExecution;
	
	@Override
	public void writeHeader(Writer writer) throws IOException {				
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		writer.write("001" + String.format("%s", dateFormat.format(date)) + "F" + StringUtils.fillString("left", 288, " ", " "));
	}

}