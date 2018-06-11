package schedule.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
@ComponentScan(basePackages = {"schedule.messenger"})
public class JmsConfig {

    private static final String BROKER_URL = "tcp://activemq:61616";
    private static final String BROKER_USERNAME = "admin";
    private static final String BROKER_PASSWORD = "admin";

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_USERNAME);
        connectionFactory.setUserName(BROKER_PASSWORD);
        return connectionFactory;
    }

    //    @Bean
    //    public CachingConnectionFactory connectionFactory() {
    //        return new CachingConnectionFactory(activeMQConnectionFactory());
    //    }

    @Bean
    public ActiveMQQueue activeMQQueue() {
        return new ActiveMQQueue("Send2Recv");
    }

    @Bean
    JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(activeMQConnectionFactory());
        template.setDefaultDestination(activeMQQueue());
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setConcurrency("1-1");
        return factory;
    }
}

