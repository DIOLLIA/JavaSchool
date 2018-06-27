package schedule.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.api.RouteDao;
import schedule.entity.RouteEntity;
import schedule.entity.StationEntity;
import schedule.model.Route;
import schedule.model.Station;
import schedule.service.api.RouteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    private RouteDao routeDao;
    private ModelMapper modelMapper;

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

    @Override
    public void addRoute(String routeName) {
        logger.info("Create new route with {} name", routeName);
        routeDao.create(new RouteEntity(routeName));
    }

    @Override
    public List<Station> stationsList(int routeId) {
        List<StationEntity> stationEntity = routeDao.stationsOnRoute(routeId);
        List<Station> stations = new ArrayList<>();
        for (StationEntity station : stationEntity) {
            stations.add(modelMapper.map(station, Station.class));
        }
        return stations;
    }

    @Override
    public List<String> findStationsByRouteName(int routeId) {
        List<StationEntity> stationsList = routeDao.stationsOnRoute(routeId);

        List<String> stationsNamesList = new ArrayList<>();
        for (StationEntity station : stationsList) {
            stationsNamesList.add(modelMapper.map(station, Station.class).getStationName());
        }
        return stationsNamesList;
    }

    @Override
    public Integer findByName(String routeName) {
        return routeDao.findByName(routeName);
    }

    @Override
    public void addStationToRoute(int routeId, Station stationName) {
        RouteEntity route = routeDao.routeById(routeId);
        route.getStationsList().add(modelMapper.map(stationName, StationEntity.class));
        routeDao.update(route);

    }

    @Override
    public String findRouteById(int routeId) {
        String routeName= routeDao.routeById(routeId).getRouteName();
        return routeName ;
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

    @Autowired
    public void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
