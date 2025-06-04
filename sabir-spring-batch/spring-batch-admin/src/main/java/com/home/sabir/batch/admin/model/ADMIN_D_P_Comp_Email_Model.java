package com.home.sabir.batch.admin.model;

import lombok.Data;

@Data
public class ADMIN_D_P_Comp_Email_Model {
	
	public String getCompCsnNotifyTxnId() {
		return compCsnNotifyTxnId;
	}
	public void setCompCsnNotifyTxnId(String compCsnNotifyTxnId) {
		this.compCsnNotifyTxnId = compCsnNotifyTxnId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmailMessage() {
		return emailMessage;
	}
	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}
	
	public String getAdminEmailTemplInfoId() {
		return adminEmailTemplInfoId;
	}
	public void setAdminEmailTemplInfoId(String adminEmailTemplInfoId) {
		this.adminEmailTemplInfoId = adminEmailTemplInfoId;
	}

	public String getTransNbr() {
		return transNbr;
	}
	public void setTransNbr(String transNbr) {
		this.transNbr = transNbr;
	}

	private String sender;
	private String receiver;
	private String subject;
	private String emailMessage;
	private String compCsnNotifyTxnId;
	private String adminEmailTemplInfoId;	
	private String transNbr;

}
