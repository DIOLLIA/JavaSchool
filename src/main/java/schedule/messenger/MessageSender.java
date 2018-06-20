package schedule.messenger;

import schedule.controller.model.ScheduleToSend;

import java.util.List;

public interface MessageSender {
    void send(String message);

    void sendAll(List<ScheduleToSend> scheduleToSends);

}
