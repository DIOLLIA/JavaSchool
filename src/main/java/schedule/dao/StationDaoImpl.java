package schedule.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import schedule.entity.ScheduleEntity;
import schedule.entity.StationEntity;
import schedule.model.Station;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

    @Override
    public StationEntity findByName(String name) {
        Query query = getCurrentSession().createQuery("SELECT sch FROM StationEntity sch WHERE sch.StationName = ?");
        query.setParameter(0, name);
        List result = query.list();
        if (!result.isEmpty()) {
            return (StationEntity) result.get(0);
        }
        return null;
    }
}
