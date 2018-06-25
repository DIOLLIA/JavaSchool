package schedule.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import schedule.dao.api.StationDao;
import schedule.entity.StationEntity;
import schedule.model.Station;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class StationServiceImplTest {
    @Mock
    private StationDao stationDao;

    @InjectMocks
    StationServiceImpl stationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        stationService.setModelMapper(new ModelMapper());
    }

    @Test
    public void testFindByName() {
        String name = "name";
        when(stationDao.findByName(name)).thenReturn(null);
        Station result = stationService.findByName(name);
        assertNull(result);
    }

    @Test
    public void testFindByName2() {
        String name = "name";
        when(stationDao.findByName(name)).thenReturn(new StationEntity());
        Station result = stationService.findByName(name);
        assertNotNull(result);
    }
}