//package com.practice.functional.messaging;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.junit.Test;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//
///**
// *
// */
//public class MessagingTest {
//
//	@Test
//	public void testActiveMQConnection() {
//		String userName = "Intuit.ems.iop";
//		String password = "prdCtgnlzheBbYBlGKPqcUKq6HdiWgJg1A27cNU3";
//		String brokerUrl = "failover:(ssl://message-sbg.platform.intuit.net:61617)?maxReconnectAttempts=3&timeout=5000";
//		Connection connection = null;
//		try {
//			ConnectionFactory healthCheckConnectionFactory = new ActiveMQConnectionFactory(brokerUrl);
//			connection = healthCheckConnectionFactory.createConnection(userName, password);
//			connection.start();
//		} catch (JMSException je) {
//			je.printStackTrace();
//		}
//	}
//}
