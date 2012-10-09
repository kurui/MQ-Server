package com.kurui.kums.message.jms.jboss.example1.spring;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 通过方法来注入ConnectionFactory和Destination这两个对象来支撑jms环境
 * 
 */

public class UserJmsTransactionUtil {
	private String connectionFactoryJndiLookUp;
	private String destinationJndiLookUp;
	private String localConnectionFactoryJndiLookUp;
	private String containerType;

	public ConnectionFactory getConnectionFactory() throws NamingException {
		Context ctx = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory) ctx
				.lookup(connectionFactoryJndiLookUp);
		return cf;
	}

	public Destination getJmsDestination() throws NamingException {
		Context ctx = new InitialContext();
		Destination dest = (Queue) ctx.lookup(destinationJndiLookUp);
		return dest;
	}

	public ConnectionFactory getQueueConnectionFactory() throws NamingException {
		Properties pops = new Properties();
		pops.setProperty("jboss.bind.address", "0.0.0.0");
		pops.setProperty("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		pops.setProperty("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		pops.setProperty("java.naming.provider.url", "localhost");
		Context ctx = new InitialContext(pops);
		ConnectionFactory cf = (ConnectionFactory) ctx
				.lookup(localConnectionFactoryJndiLookUp);
		return cf;
	}

	public Destination getLocalJmsDestination() throws NamingException {
		Properties pops = new Properties();
		pops.setProperty("jboss.bind.address", "0.0.0.0");
		pops.setProperty("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		pops.setProperty("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		pops.setProperty("java.naming.provider.url", "localhost");
		Context ctx = new InitialContext(pops);
		Destination dest = (Destination) ctx.lookup(destinationJndiLookUp);
		return dest;
	}

	public String getConnectionFactoryJndiLookUp() {
		return connectionFactoryJndiLookUp;
	}

	public void setConnectionFactoryJndiLookUp(
			String connectionFactoryJndiLookUp) {
		this.connectionFactoryJndiLookUp = connectionFactoryJndiLookUp;
	}

	public String getDestinationJndiLookUp() {
		return destinationJndiLookUp;
	}

	public void setDestinationJndiLookUp(String destinationJndiLookUp) {
		this.destinationJndiLookUp = destinationJndiLookUp;
	}

	public String getLocalConnectionFactoryJndiLookUp() {
		return localConnectionFactoryJndiLookUp;
	}

	public void setLocalConnectionFactoryJndiLookUp(
			String localConnectionFactoryJndiLookUp) {
		this.localConnectionFactoryJndiLookUp = localConnectionFactoryJndiLookUp;
	}
}
