package schedule.messenger;

import schedule.service.api.ScheduleService;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MessageListenerImpl implements MessageListener {

    private ScheduleService scheduleService;

    public MessageListenerImpl(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Remote app stated! Sending schedule...");
        scheduleService.sendAll(scheduleService.transform(scheduleService.getSchedule()));
    }
}
