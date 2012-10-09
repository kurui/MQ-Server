package com.kurui.kums.message;

public class Message extends org.apache.struts.action.ActionForm implements
		Cloneable {
	private static final long serialVersionUID = 1L;

	private String messageText = "";
	private String method = "";
	private String providerIp="";
	
	

	public String getProviderIp() {
		return providerIp;
	}

	public void setProviderIp(String providerIp) {
		this.providerIp = providerIp;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	private String thisAction = "";

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

	private int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
