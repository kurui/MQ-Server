package com.kurui.kums.message.jms.jboss.queues;

import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.QueueReceiver;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloReceQueue implements MessageListener {
	QueueConnection queueConnection;
	QueueSession queueSession;
	QueueReceiver queueReceiver;
	Queue queue;

	public HelloReceQueue(String factoryJNDI, String topicJNDI)
			throws JMSException, NamingException {
		Context context = new InitialContext();
		QueueConnectionFactory queueFactory = (QueueConnectionFactory) context
				.lookup(factoryJNDI);
		queueConnection = queueFactory.createQueueConnection();
		queueSession = queueConnection.createQueueSession(false,
				Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) context.lookup(topicJNDI);
		queueReceiver = queueSession.createReceiver(queue);
		queueReceiver.setMessageListener(this);
		System.out.println("HelloReceQueue receiver to queue: " + topicJNDI);
		queueConnection.start();
	}

	public void onMessage(Message m) {
		try {
			String msg = ((TextMessage) m).getText();
			System.out.println("HelloReceQueue got message: " + msg);
		} catch (JMSException ex) {
			System.err.println("Could not get text message: " + ex);
			ex.printStackTrace();
		}
	}

	public void close() throws JMSException {
		queueSession.close();
		queueConnection.close();
	}
}
