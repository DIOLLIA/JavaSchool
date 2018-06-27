package integration.schedule.dao.impl;

import integration.schedule.config.DataBaseTestConfig;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.api.RouteDao;
import schedule.dao.api.ScheduleDao;
import schedule.dao.api.StationDao;
import schedule.dao.api.TrainDao;
import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;
import schedule.entity.StationEntity;
import schedule.entity.TrainEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Rudkov Andrey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {DataBaseTestConfig.class})
@Transactional

public class ScheduleDaoTest {

    private static final String VLADIVOSTOK_MOSCOW = "VLADIVOSTOK-MOSCOW";
    private static final String VLADOVOSTOK_TULA_MOSCOW = "VLADOVOSTOK-TULA-MOSCOW";
    private static final String MOSCOW_TULA_SPB_HELSINKI = "MOSCOW-TULA-SPB-HELSINKI";
    private static final String MOSCOW_STATION = "Moscow";
    private static final String VLADIVOSTOK_STATION = "Vladivostok";

    @Autowired
    private
    TrainDao trainDao;
    @Autowired
    private
    StationDao stationDao;
    @Autowired
    private
    RouteDao routeDao;
    @Autowired
    private
    ScheduleDao scheduleDao;

    @Before
    public void setUp() {
        TrainEntity train1 = new TrainEntity();
        train1.setNumberOfTrain(10);
        train1.setSeats(100);

        TrainEntity train2 = new TrainEntity();
        train2.setNumberOfTrain(20);
        train2.setSeats(200);

        TrainEntity train3 = new TrainEntity();
        train3.setNumberOfTrain(30);
        train3.setSeats(300);


        trainDao.addTrain(train1);
        trainDao.addTrain(train2);
        trainDao.addTrain(train3);


        StationEntity moscow = new StationEntity();
        moscow.setStationName(MOSCOW_STATION);

        StationEntity tula = new StationEntity();
        tula.setStationName("Tula");

        StationEntity vladivostok = new StationEntity();
        vladivostok.setStationName(VLADIVOSTOK_STATION);

        StationEntity spb = new StationEntity();
        spb.setStationName("Spb");

        StationEntity helsinki = new StationEntity();
        helsinki.setStationName("Helsinki");

        stationDao.create(moscow);
        stationDao.create(tula);
        stationDao.create(vladivostok);
        stationDao.create(spb);
        stationDao.create(helsinki);

        ArrayList list = new ArrayList();
        list.add(vladivostok);
        list.add(moscow);

        ArrayList list2 = new ArrayList();
        list2.add(vladivostok);
        list2.add(tula);
        list2.add(moscow);

        ArrayList list3 = new ArrayList();
        list3.add(moscow);
        list3.add(tula);
        list3.add(spb);
        list3.add(helsinki);

        RouteEntity vm = new RouteEntity();
        vm.setStationsList(list);

        RouteEntity vtm = new RouteEntity();
        vtm.setStationsList(list2);

        RouteEntity mtsh = new RouteEntity();
        mtsh.setStationsList(list3);

        routeDao.create(vm);
        routeDao.create(vtm);
        routeDao.create(mtsh);

        ScheduleEntity schedule1 = new ScheduleEntity();
        schedule1.setArrivalTime(LocalTime.parse("10:10"));
        schedule1.setDepartureTime(LocalTime.parse("10:30"));
        schedule1.setStationName(vladivostok);
        schedule1.setTrainNumber(train1);
        schedule1.setRouteName(vm);

        ScheduleEntity schedule2 = new ScheduleEntity();
        schedule2.setArrivalTime(LocalTime.parse("12:00"));
        schedule2.setStationName(moscow);
        schedule2.setTrainNumber(train1);
        schedule2.setRouteName(vm);

        ScheduleEntity schedule3 = new ScheduleEntity();
        schedule3.setArrivalTime(LocalTime.parse("11:00"));
        schedule3.setDepartureTime(LocalTime.parse("11:15"));
        schedule3.setStationName(vladivostok);
        schedule3.setTrainNumber(train2);
        schedule3.setRouteName(vtm);

        ScheduleEntity schedule4 = new ScheduleEntity();
        schedule4.setArrivalTime(LocalTime.parse("06:00"));
        schedule4.setDepartureTime(LocalTime.parse("06:12"));
        schedule4.setStationName(tula);
        schedule4.setTrainNumber(train2);
        schedule4.setRouteName(vtm);

        ScheduleEntity schedule5 = new ScheduleEntity();
        schedule5.setArrivalTime(LocalTime.parse("16:00"));
        schedule5.setStationName(moscow);
        schedule5.setTrainNumber(train2);
        schedule5.setRouteName(vtm);

        ScheduleEntity schedule6 = new ScheduleEntity();
        schedule6.setArrivalTime(LocalTime.parse("13:00"));
        schedule6.setDepartureTime(LocalTime.parse("13:15"));
        schedule6.setStationName(moscow);
        schedule6.setTrainNumber(train3);
        schedule6.setRouteName(mtsh);

        ScheduleEntity schedule7 = new ScheduleEntity();
        schedule7.setArrivalTime(LocalTime.parse("20:20"));
        schedule7.setDepartureTime(LocalTime.parse("20:30"));
        schedule7.setStationName(tula);
        schedule7.setTrainNumber(train3);
        schedule7.setRouteName(mtsh);

        scheduleDao.create(schedule1);
        scheduleDao.create(schedule2);
        scheduleDao.create(schedule3);
        scheduleDao.create(schedule4);
        scheduleDao.create(schedule5);
        scheduleDao.create(schedule6);
        scheduleDao.create(schedule7);
    }

    @Test
    public void testFindByRoute() {
 /*       Integer routeIdVm = routeDao.findByName(VLADIVOSTOK_MOSCOW);
        Integer routeIdVtm = routeDao.findByName(VLADOVOSTOK_TULA_MOSCOW);*/

        List<RouteEntity> routeList = routeDao.findByStationNames(MOSCOW_STATION, VLADIVOSTOK_STATION);

        Assert.assertTrue(routeList.size() == 3);

        List<ScheduleEntity> schedule = scheduleDao.findByStationsAndRoutes(routeList, VLADIVOSTOK_STATION, MOSCOW_STATION);

        Assert.assertTrue(schedule.size() == 5);
    }
}
