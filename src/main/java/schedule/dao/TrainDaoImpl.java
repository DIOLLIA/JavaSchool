package schedule.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import schedule.entity.TrainEntity;

import java.util.List;

@Repository
public class TrainDaoImpl implements TrainDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    public void addTrain(TrainEntity train) {
        getCurrentSession().save(train);
    }

    public TrainEntity getTrain(int id) {
        TrainEntity train = (TrainEntity) getCurrentSession().get(TrainEntity.class, id);
        return train;
    }

    public void deleteTrain(int id) {

    }

    @SuppressWarnings("unchecked")
    public List<TrainEntity> getTrains() {

        return getCurrentSession().createQuery("from TrainEntity ").list();
    }
}
