package schedule.config;

import org.apache.activemq.ActiveMQConnection;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import schedule.messenger.MessageListenerImpl;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Hashtable;

@Configuration
public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        Hashtable<String, String> props = new Hashtable<String, String>();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", ActiveMQConnection.DEFAULT_BROKER_URL);
        props.put("queue.js-queue", "client-queue");
        props.put("connectionFactoryNames", "client-queue");
        Context context = null;
        try {
            context = new InitialContext(props);
            QueueConnectionFactory queueFactory = (QueueConnectionFactory) context.lookup("client-queue");
            Queue queue = (Queue) context.lookup("js-queue");
            QueueConnection connection = queueFactory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

            QueueReceiver receiver = session.createReceiver(queue);

            receiver.setMessageListener(new MessageListenerImpl());
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
