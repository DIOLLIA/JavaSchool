package unit.schedule.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import schedule.dao.api.ScheduleDao;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;
import schedule.model.Train;
import schedule.service.impl.ScheduleServiceImpl;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class ScheduleServiceImplTest {

    @Mock
    private ScheduleDao scheduleDao;

    @InjectMocks
    private ScheduleServiceImpl scheduleServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Schedule schedule = new Schedule();
        Station station =new Station();
        station.setStationName("Moscow");
        schedule.setStationName(station);
        schedule.setRouteName(new Route());
        schedule.setId(1);
        schedule.setTrainNumber(new Train());
    }

    @Test
    public void findScheduleByStation() {
        Station station =new Station();
        station.setStationName("Moscow");
        when(scheduleServiceImpl.findScheduleByStation(station)).thenReturn(new ArrayList<>());
    }

    @Test
    public void getSchedule() {
    }

    @Test
    public void formatSchedule() {
    }

    @Test
    public void showRouteDetails() {
    }

    @Test
    public void addSchedule() {
    }

    @Test
    public void sendAll() {
    }

    @Test
    public void transform() {
    }

    @Test
    public void findTrainById() {
    }

    @Test
    public void setScheduleDao() {
    }

    @Test
    public void setModelMapper() {
    }

    @Test
    public void setTrainDao() {
    }

    @Test
    public void setStationDao() {
    }

    @Test
    public void setRouteDao() {
    }

    @Test
    public void setSessionFactory() {
    }

    @Test
    public void setSimpleMessageSender() {
    }
}