package schedule.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import schedule.entity.StationEntity;
import schedule.model.Station;

import java.util.List;

@Repository
public class StationDaoImpl implements StationDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

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
}
