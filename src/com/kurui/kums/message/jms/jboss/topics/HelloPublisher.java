package com.kurui.kums.message.jms.jboss.topics;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicPublisher;
import javax.jms.Topic;
import javax.jms.TextMessage;
import javax.jms.Session;
import javax.jms.JMSException;
import java.util.Hashtable;

public class HelloPublisher {

	TopicConnection topicConnection;
	TopicSession topicSession;
	TopicPublisher topicPublisher;
	Topic topic;

	public HelloPublisher(String providerIp, String factoryJNDI,
			String topicJNDI) throws JMSException, NamingException {
		Hashtable props = new Hashtable();
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");

		props.put(Context.PROVIDER_URL, providerIp + ":1099");
		props.put("java.naming.rmi.security.manager", "yes");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming");

		Context context = new InitialContext(props);
		TopicConnectionFactory topicFactory = (TopicConnectionFactory) context
				.lookup(factoryJNDI);

		topicConnection = topicFactory.createTopicConnection();
		topicSession = topicConnection.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);
		topic = (Topic) context.lookup(topicJNDI);
		topicPublisher = topicSession.createPublisher(topic);
	}

	public void publish(String msg) throws JMSException {
		TextMessage message = topicSession.createTextMessage();
		message.setText(msg);
		topicPublisher.publish(topic, message);
	}

	public void close() throws JMSException {
		topicSession.close();
		topicConnection.close();
	}

	public static void main(String[] args) {
		try {
			HelloPublisher publisher = new HelloPublisher("192.168.150.1",
					"ConnectionFactory", "topic/testTopic");

			for (int i = 1; i < 11; i++) {
				String msg = "Hello World no. " + i;
				System.out.println("Publishing message: " + msg);
				publisher.publish(msg);
			}
			publisher.close();
		} catch (Exception ex) {
			System.err
					.println("An exception occurred while testing HelloPublisher25: "
							+ ex);
			ex.printStackTrace();
		}
	}
}
