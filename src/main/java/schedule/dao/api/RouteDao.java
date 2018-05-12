package schedule.dao.api;

import schedule.entity.RouteEntity;

import java.util.List;

public interface RouteDao extends GeneralCrudDao<RouteEntity> {
    List<RouteEntity> routes();
}
