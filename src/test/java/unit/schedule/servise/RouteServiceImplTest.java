package unit.schedule.servise;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import schedule.dao.api.RouteDao;
import schedule.model.Route;
import schedule.service.impl.RouteServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class RouteServiceImplTest {
    @Mock
    private RouteDao routeDao;

    @InjectMocks
    private RouteServiceImpl routeServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEmptyStationsNames() {
        String station1 = "1";
        String station2 = "2";

        when(routeDao.findByStationNames(station1, station2)).thenReturn(new ArrayList<>());

        List<Route> res = routeServiceImpl.findByStationNames(station1, station2);
        assertTrue(res.isEmpty());
    }

    @Test
    public void testRoutesAsStations(){

    }
}
