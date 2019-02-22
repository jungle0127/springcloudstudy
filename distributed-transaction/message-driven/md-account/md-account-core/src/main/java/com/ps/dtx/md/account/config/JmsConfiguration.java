package com.ps.dtx.md.account.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.TransactionAwareConnectionFactoryProxy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfiguration {
    @Value("${spring.activemq.url}")
    private String brokerUrl;
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.brokerUrl);
        TransactionAwareConnectionFactoryProxy transactionAwareConnectionFactoryProxy = new TransactionAwareConnectionFactoryProxy();
        transactionAwareConnectionFactoryProxy.setTargetConnectionFactory(connectionFactory);
        transactionAwareConnectionFactoryProxy.setSynchedLocalTransactionAllowed(true);
        // 做事务同步
        return transactionAwareConnectionFactoryProxy;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter jacksonJmsMessageConverter) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                      PlatformTransactionManager transactionManager,
                                                                      DefaultJmsListenerContainerFactoryConfigurer jmsListenerContainerFactoryConfigurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactoryConfigurer.configure(factory, connectionFactory);
        factory.setReceiveTimeout(10000L);
//        factory.setCacheLevelName("CACHE_CONNECTION"); 独立的服务器不需要此行配置
        factory.setTransactionManager(transactionManager);
        factory.setConcurrency("10");
        return factory;
    }

    @Bean
    /**
     * 转换Java对象到JSON数据
     */
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTargetType(MessageType.TEXT);
        messageConverter.setTypeIdPropertyName("_type");
        return messageConverter;
    }
}
