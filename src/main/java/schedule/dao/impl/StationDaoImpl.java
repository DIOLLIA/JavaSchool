package schedule.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import schedule.dao.api.StationDao;
import schedule.entity.StationEntity;

import java.util.List;

@Repository
public class StationDaoImpl extends GeneralCrudDaoImpl<StationEntity> implements StationDao {

    @Override
    public void addStation(StationEntity station) {
        getCurrentSession().save(station);
    }

    @Override
    public StationEntity getStation(int id) {
        StationEntity station = (StationEntity) getCurrentSession().get(StationEntity.class, id);
        return station;
    }

    @Override
    public void deleteStation(int id) {

    }

    @Override
    public List<StationEntity> getStations() {
        return getCurrentSession().createQuery("from StationEntity").list();
    }

    @Override
    public StationEntity findByName(String name) {
        Query query = getCurrentSession().createQuery("SELECT sch FROM StationEntity sch WHERE sch.stationName = ?");
        query.setParameter(0, name);
        List result = query.list();
        if (!result.isEmpty()) {
            return (StationEntity) result.get(0);
        }
        return null;
    }
}
