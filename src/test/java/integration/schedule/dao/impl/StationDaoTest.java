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
import schedule.dao.api.StationDao;
import schedule.entity.StationEntity;

/**
 * @author Rudkov Andrey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {DataBaseTestConfig.class})
@Transactional
public class StationDaoTest {

    private static final String STATION_NAME = "Vladivostok";

    @Autowired
    private StationDao stationDao;

    @Before
    public void setUp() {
        StationEntity vladivostok = new StationEntity();
        vladivostok.setStationName(STATION_NAME);
        stationDao.create(vladivostok);
    }

    @Test
    public void testFindByName() {
        StationEntity station = stationDao.findByName(STATION_NAME);

        Assert.assertEquals(STATION_NAME, station.getStationName());
    }
}
