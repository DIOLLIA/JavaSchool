package schedule;

import org.apache.activemq.ActiveMQConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import schedule.messenger.MessageListenerImpl;
import schedule.service.api.ScheduleService;

import javax.annotation.PostConstruct;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@Component
public class StartupListener {

    @Autowired
    private ScheduleService scheduleService;

    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    @PostConstruct
    void init() {
        Hashtable<String, String> props = new Hashtable<>();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", ActiveMQConnection.DEFAULT_BROKER_URL);
        props.put("queue.client-queue", "client-queue");
        props.put("connectionFactoryNames", "timetable");
        Context context = null;
        try {
            context = new InitialContext(props);
            QueueConnectionFactory queueFactory = (QueueConnectionFactory) context.lookup("timetable");
            Queue queueClient = (Queue) context.lookup("client-queue");
            QueueConnection connection = queueFactory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

            QueueReceiver receiver = session.createReceiver(queueClient);

            receiver.setMessageListener(new MessageListenerImpl(scheduleService));
        } catch (NamingException | JMSException e) {
            logger.debug("JMS Listener WARNING {}",e);
        }
    }
}
