package com.kurui.kums.message.jms.jboss.example1.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class Test {
	public static void send() {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				"E:\\project\\MQServer\\defaultroot\\WEB-INF\\applicationContext.xml");
		BeanFactory bf = ac;
		JmsTemplate jt = (JmsTemplate) bf.getBean("jmsQueue");
		jt.convertAndSend("2132134");
	}

	public static void main(String[] args) {
		send();
	}

}
