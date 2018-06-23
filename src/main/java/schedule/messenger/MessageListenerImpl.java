package schedule.messenger;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MessageListenerImpl implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("x");
    }
}
