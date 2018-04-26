package schedule.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import schedule.model.Train;

import java.util.List;

@Repository
public class TrainDaoImpl implements TrainDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    public void addTrain(Train train) {
        getCurrentSession().save(train);
    }

    public Train getTrain(int id) {
        Train train = (Train) getCurrentSession().get(Train.class, id);
        return train;
    }

    public void deleteTrain(int id) {

    }

    @SuppressWarnings("unchecked")
    public List<Train> getTrains() {

        return getCurrentSession().createQuery("from Train ").list();
    }
}
