package schedule.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    public void addStation(Station station) {
        getCurrentSession().save(station);
    }

    @Override
    public Station getStation(int id) {
        Station station = (Station) getCurrentSession().get(Station.class, id);
        return station;
    }

    @Override
    public void deleteStation(int id) {

    }

    @Override
    public List<Station> getStations() {
        return getCurrentSession().createQuery("from Station").list();
    }
}
