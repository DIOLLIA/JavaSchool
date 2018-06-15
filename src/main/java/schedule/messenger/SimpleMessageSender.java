package schedule.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class SimpleMessageSender implements MessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void send(String message) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                
                return session.createTextMessage(message);
            }
        });
    }
}
