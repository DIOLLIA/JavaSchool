package schedule.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;


    @Configuration
    @ComponentScan("schedule.messenger")
    public class JmsCfg {

        @Bean
        public ActiveMQConnectionFactory connectionFactory(){
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            return connectionFactory;
        }


        @Bean
        public JmsTemplate jmsTemplate(){
            JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
            jmsTemplate.setDefaultDestinationName("timetable");
            return jmsTemplate;
        }
    }

