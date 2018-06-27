package integration.schedule.dao.impl;

import integration.schedule.config.DataBaseTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.api.RouteDao;
import schedule.dao.api.StationDao;
import schedule.entity.RouteEntity;
import schedule.entity.StationEntity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Rudkov Andrey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {DataBaseTestConfig.class})
@Transactional

public class RouteTest {

    private static final String VLADIVOSTOK_MOSCOW = "VLADIVOSTOK-MOSCOW";
    private static final String VLADOVOSTOK_TULA_MOSCOW = "VLADOVOSTOK-TULA-MOSCOW";

    @Autowired
    private StationDao stationDao;
    @Autowired
    private RouteDao routeDao;

    @Before
    public void setUp() {
        StationEntity moscow = new StationEntity();
        moscow.setStationName("Moscow");

        StationEntity tula = new StationEntity();
        tula.setStationName("Tula");

        StationEntity vladivostok = new StationEntity();
        vladivostok.setStationName("Vladivostok");

        StationEntity spb = new StationEntity();
        spb.setStationName("Spb");

        StationEntity helsinki = new StationEntity();
        helsinki.setStationName("Helsinki");

        stationDao.create(moscow);
        stationDao.create(tula);
        stationDao.create(vladivostok);
        stationDao.create(spb);
        stationDao.create(helsinki);

        ArrayList<StationEntity> list = new ArrayList<>(Arrays.asList(vladivostok, moscow));
        RouteEntity vm = new RouteEntity();
        vm.setRouteName(VLADIVOSTOK_MOSCOW);
        vm.setStationsList(list);

        ArrayList<StationEntity> list2 = new ArrayList<>(Arrays.asList(vladivostok, tula, moscow));
        RouteEntity vtm = new RouteEntity();
        vtm.setRouteName(VLADOVOSTOK_TULA_MOSCOW);
        vtm.setStationsList(list2);

        ArrayList<StationEntity> list3 = new ArrayList<>(Arrays.asList(moscow, tula, spb, helsinki));
        RouteEntity mtsh = new RouteEntity();
        mtsh.setStationsList(list3);

        routeDao.create(vm);
        routeDao.create(vtm);
        routeDao.create(mtsh);
    }

    @Test
    public void testCreate() {
        Integer route1 = routeDao.findByName(VLADIVOSTOK_MOSCOW);
        Integer route2 = routeDao.findByName(VLADOVOSTOK_TULA_MOSCOW);

        Assert.assertNotNull(route1);
        Assert.assertNotNull(route2);
    }
}