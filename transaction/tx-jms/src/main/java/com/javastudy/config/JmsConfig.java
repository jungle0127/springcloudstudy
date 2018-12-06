package com.javastudy.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

@EnableJms
@Configuration
public class JmsConfig {
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                      DefaultJmsListenerContainerFactoryConfigurer containerFactoryConfigurer,
                                                                      PlatformTransactionManager transactionManager){
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setTransactionManager(transactionManager);
        jmsListenerContainerFactory.setCacheLevelName("CACHE_CONNECTION");//处理事务结束时ConnectionFactory会被关闭
        jmsListenerContainerFactory.setReceiveTimeout(10000L);
        containerFactoryConfigurer.configure(jmsListenerContainerFactory,connectionFactory);
        return jmsListenerContainerFactory;
    }
    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }
    @Bean
    public PlatformTransactionManager platformTransactionManager(ConnectionFactory connectionFactory){
        return new JmsTransactionManager(connectionFactory);

    }
}
