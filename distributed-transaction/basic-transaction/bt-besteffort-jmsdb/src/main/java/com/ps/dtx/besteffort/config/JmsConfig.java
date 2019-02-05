package com.ps.dtx.besteffort.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.TransactionAwareConnectionFactoryProxy;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfig {
    @Bean
    public ConnectionFactory connectionFactory(){
        ConnectionFactory connectionFactory= new ActiveMQConnectionFactory("tcp://192.168.1.6:61616");
        TransactionAwareConnectionFactoryProxy connectionFactoryProxy = new TransactionAwareConnectionFactoryProxy();
        connectionFactoryProxy.setTargetConnectionFactory(connectionFactory);
        connectionFactoryProxy.setSynchedLocalTransactionAllowed(true);
        return connectionFactoryProxy;
    }
    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }
}
