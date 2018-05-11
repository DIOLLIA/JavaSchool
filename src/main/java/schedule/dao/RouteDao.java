package schedule.dao;

import schedule.entity.RouteEntity;
import schedule.model.Route;

import java.util.List;

public interface RouteDao {
    List<RouteEntity> routes();
}
