package schedule.service;

import schedule.model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findStations(String stationOfDeparture, String stationOfArrival);

    void addSchedule(Schedule schedule);
}
