package schedule.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.api.RouteDao;
import schedule.entity.RouteEntity;
import schedule.entity.StationEntity;
import schedule.model.Route;
import schedule.service.api.RouteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routeDao;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Route> routesList() {
        List<RouteEntity> routeEntities = routeDao.routes();
        List<Route> routeList = new ArrayList<>();
        for (RouteEntity routeEntity : routeEntities) {
            routeList.add(modelMapper.map(routeEntity, Route.class));
        }
        return routeList;
    }

    @Override
    public List<Route> findByStationNames(String... stationNames) {
        List<RouteEntity> routesEntityList = routeDao.findByStationNames(stationNames);
        routesEntityList = filterList(routesEntityList, stationNames);

        List<Route> routeList = new ArrayList<>();
        for (RouteEntity re : routesEntityList) {
            routeList.add(modelMapper.map(re, Route.class));
        }
        return routeList;
    }

    private List<RouteEntity> filterList(List<RouteEntity> routesEntityList, String[] stationNames) {
        List<RouteEntity> resultList = new ArrayList<>();
        for (RouteEntity routeEntity : routesEntityList) {
            List<StationEntity> routeStations = routeEntity.getStationsList();
            List<String> routeStationNames = new ArrayList<>();
            for (StationEntity routeStation : routeStations) {
                routeStationNames.add(routeStation.getStationName());
            }
            if (routeStationNames.containsAll(Arrays.asList(stationNames))) {
                resultList.add(routeEntity);
            }
        }
        return resultList;
    }
}
