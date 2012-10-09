package com.kurui.kums.message.jms.jboss.topics;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.jms.Topic;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Session;
import javax.jms.MessageListener;
import javax.jms.JMSException;

public class HelloSubscriber implements MessageListener {
	TopicConnection topicConnection;
	TopicSession topicSession;
	TopicSubscriber topicSubscriber;
	Topic topic;

	public HelloSubscriber(String factoryJNDI, String topicJNDI)
			throws JMSException, NamingException {
		Context context = new InitialContext();
		TopicConnectionFactory topicFactory = (TopicConnectionFactory) context
				.lookup(factoryJNDI);
		topicConnection = topicFactory.createTopicConnection();
		topicSession = topicConnection.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);
		topic = (Topic) context.lookup(topicJNDI);
		topicSubscriber = topicSession.createSubscriber(topic);
		topicSubscriber.setMessageListener(this);
		System.out.println("建立对Topic " + topicJNDI + "的监听");
		topicConnection.start();
	}

	/**
	 * client
	 */
	public void onMessage(Message m) {
		try {
			String msg = ((TextMessage) m).getText();
			System.out.println("HelloSubscriber got message: " + msg);
		} catch (JMSException ex) {
			System.err.println("Could not get text message: " + ex);
			ex.printStackTrace();
		}
	}

	public void close() throws JMSException {
		topicSession.close();
		topicConnection.close();
	}
}
