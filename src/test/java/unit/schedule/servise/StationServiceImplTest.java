package unit.schedule.servise;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import schedule.dao.api.StationDao;
import schedule.entity.StationEntity;
import schedule.model.Station;
import schedule.service.impl.StationServiceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class StationServiceImplTest {
    @Mock
    private StationDao stationDao;

    @InjectMocks
    private
    StationServiceImpl stationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        stationService.setModelMapper(new ModelMapper());
    }

    @Test
    public void testFindByName() {
        String name = "notExistedName";
        when(stationDao.findByName(name)).thenReturn(null);
        Station result = stationService.findByName(name);
        assertNull(result);
    }

    @Test
    public void testFindByName2() {
        String name = "existedName";
        when(stationDao.findByName(name)).thenReturn(new StationEntity());
        Station result = stationService.findByName(name);
        assertNotNull(result);
    }
}