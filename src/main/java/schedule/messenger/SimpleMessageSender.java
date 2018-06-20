package schedule.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import schedule.controller.model.ScheduleToSend;
import schedule.service.api.ScheduleService;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

@Component
public class SimpleMessageSender implements MessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ScheduleService scheduleService;

    /*    @Override
        public void send(String message) {
            jmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(message);
                }
            });
        }*/
    @Override
    public void send(String message) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                return mapMessage;

            }
        });
    }

/*    MessageCreator messageCreator = new MessageCreator() {
        @Override
        public Message createMessage(Session session) throws JMSException {
            List<Schedule> scheduleItem = scheduleService.getSchedule();
            for (Schedule schedule :scheduleItem) {
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("station", schedule.getStationName().getStationName());
            mapMessage.setInt("train", schedule.getTrainNumber().getNumberOfTrain());
            mapMessage.setInt("dailyId", schedule.getRouteDailyId());
            mapMessage.setInt("orderNumber", schedule.getRouteStationIndex());
            mapMessage.setString("arrTime", schedule.getArrivalTime().toString());
            mapMessage.setString("depTime", schedule.getDepartureTime().toString());
            return mapMessage;
            }
        }
    };*/

    @Override
    public void sendAll(List<ScheduleToSend> scheduleToSends) {
        for (ScheduleToSend schedule : scheduleToSends) {
            jmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    MapMessage mapMessage = session.createMapMessage();
                    mapMessage.setString("station", schedule.getStation());
                    mapMessage.setString("arrTime", schedule.getArrTime());
                    mapMessage.setString("depTime", schedule.getDepTime());
                    mapMessage.setInt("train", schedule.getTrain());
                    mapMessage.setInt("dailyId", schedule.getRouteDailyId());
                    mapMessage.setInt("orderNumber", schedule.getStationOrder());
                    return mapMessage;
                }
            });
        }
    }
}

/*    ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject((Serializable) scheduleToSends);
                return objectMessage;*/