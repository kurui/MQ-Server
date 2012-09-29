package com.kurui.kums.message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.kurui.kums.message.Message;
import com.kurui.kums.message.biz.MessageBiz;
import com.kurui.kums.message.jms.jboss.queues.HelloQueue;
import com.kurui.kums.message.jms.jboss.queues.HelloReceQueue;
import com.kurui.kums.message.jms.jboss.topics.HelloPublisher;
import com.kurui.kums.message.jms.jboss.topics.HelloSubscriber;
import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.http.ServletUtil;

public class MessageAction extends BaseAction {
	private MessageBiz messageBiz;

	public ActionForward executeBaseCase(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();

		try {
			Message message = (Message) form;

			String method = message.getMethod();
			String providerIp = message.getProviderIp();

			ServletUtil.printResult("executeBaseCase,method:" + method,
					response);

			String messageText = message.getMessageText();

			String JNDIName = "topic/testTopic";
			if ("topicPublisher".equals(method)) {
				HelloPublisher topicPublisher = new HelloPublisher(providerIp,
						"ConnectionFactory", JNDIName);
				topicPublisher.publish(messageText);
				inf.setMessage("向" + JNDIName + "发送消息成功");
			}

			if ("topicSublisher".equals(method)) {
				HelloSubscriber subscriber = new HelloSubscriber(
						"ConnectionFactory", JNDIName);
				inf.setMessage(JNDIName + "监听建立成功");
			}

			JNDIName = "queue/kuruiQueue";
			if ("queuePublisher".equals(method)) {
				HelloQueue queueBean = new HelloQueue(providerIp,
						"ConnectionFactory", JNDIName);
				queueBean.send(messageText);
				inf.setMessage("向" + JNDIName + "发送消息成功");
			}

			if ("queueSublisher".equals(method)) {
				HelloReceQueue receQueueBean = new HelloReceQueue(
						"ConnectionFactory", JNDIName);
				inf.setMessage(JNDIName + "监听建立成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常信息：" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setMessageBiz(MessageBiz messageBiz) {
		this.messageBiz = messageBiz;
	}
}