package schedule.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.api.RouteDao;
import schedule.entity.RouteEntity;
import schedule.model.Route;
import schedule.service.api.RouteService;

import java.util.ArrayList;
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
}
