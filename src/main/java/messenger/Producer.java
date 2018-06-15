package messenger;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
/*    @Autowired
    private static ScheduleService scheduleService;
    @Autowired
    private static StationService stationService;

    public static List<Schedule> getShceduleItem() {
        List<Schedule> listOfTrainsByStation = scheduleService.findScheduleByStation(
                stationService.findByName("devyatkino"));
        return listOfTrainsByStation;
    }*/

    // URL of the JMS server. DEFAULT_BROKER_URL will just mean
// that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
// default broker URL is : tcp://localhost:61616"

    private static String subject = "customerQueue"; //Queue Name
// You can create any/many queue names as per your requirement.

    public static void main(String[] args) throws JMSException {
// Getting JMS connection from the server and starting it
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
// JMS messages are sent and received using a Session. We will
// create here a non-transactional session object. If you want
// to use transactions you should set the first parameter to 'true'
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
// Destination represents here our queue 'VALLYSOFTQ' on the
// JMS server. You don't have to do anything special on the
// server to create it, it will be created automatically.
        Destination destination = session.createQueue(subject);
// MessageProducer is used for sending messages (as opposed
// to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);

// We will send a small text message saying 'Hello' in Japanese
        TextMessage message = session.createTextMessage("message from part I suka");
// Here we are sending the message!
        producer.send(message);
        System.out.println("Sentage '" + message.getText() + "'");

        connection.close();
    }

}
