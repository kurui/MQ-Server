package com.kurui.kums.message.jms.jboss.example2;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/*******************************************************************************
 * 消息发送者
 * 
 * @作者：kiral
 * @日期：2007-7-3
 ******************************************************************************/
public class MessageProducer {

	public void send(final String message) {
		template.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				Message m = session.createTextMessage(message);
				return m;
			}
		});
	}

	private JmsTemplate template;

	private Destination destination;

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

}
