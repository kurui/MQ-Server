package com.kurui.kums.message;

import com.kurui.kums.base.ListActionForm;

public class MessageListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
}
