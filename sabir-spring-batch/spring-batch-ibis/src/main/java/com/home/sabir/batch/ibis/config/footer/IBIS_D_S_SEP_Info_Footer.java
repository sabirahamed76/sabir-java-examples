package com.home.sabir.batch.ibis.config.footer;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.beans.factory.annotation.Value;

import com.home.sabir.batch.core.util.StringUtils;

public class IBIS_D_S_SEP_Info_Footer implements FlatFileFooterCallback {
	
	@Value("#{StepExecution}")
	private StepExecution stepExecution;
	
	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.write("999" + StringUtils.fillString("left", 12, String.valueOf(stepExecution.getWriteCount()), "0") + StringUtils.fillString("left", 285, " ", " "));	
		
	}


}
	