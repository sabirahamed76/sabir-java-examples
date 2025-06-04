package com.home.java.utils.file;

import java.io.Serializable;



public class FileDownloadFormDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String fileName;
	private String mimeType;
	private byte[] data;
	private int dataLen;
	
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public int getDataLen() {
		return dataLen;
	}
	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	
}
