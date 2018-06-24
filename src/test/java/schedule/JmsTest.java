package schedule;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import schedule.config.JmsCfg;
import schedule.messenger.MessageSender;

public class JmsTest {

        public static void main(String[] args) {
            ApplicationContext context = new AnnotationConfigApplicationContext(JmsCfg.class);
            MessageSender sender = context.getBean(MessageSender.class);
            sender.send("Hello!!!3");
        }
    }

