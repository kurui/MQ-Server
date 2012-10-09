package com.kurui.kums.message.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.message.Message;
import com.kurui.kums.message.MessageListForm;
import com.kurui.kums.message.biz.MessageBiz;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.exception.AppException;

public class MessageListAction extends BaseAction {
	private MessageBiz messageBiz;
	
	public ActionForward listBaseCase(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		MessageListForm messageListForm = (MessageListForm) form;
		if (messageListForm == null) {
			messageListForm = new MessageListForm();
		}
		try {
			Message message=new Message();
			request.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("messageListForm", messageListForm);
		return mapping.findForward("listBaseCase");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		MessageListForm messageListForm = (MessageListForm) form;
		if (messageListForm == null) {
			messageListForm = new MessageListForm();
		}
		try {
			messageListForm.setList(new ArrayList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("messageListForm", messageListForm);
		return mapping.findForward("listMessage");
	}
	
	

	public void setMessageBiz(MessageBiz messageBiz) {
		this.messageBiz = messageBiz;
	}

	

}