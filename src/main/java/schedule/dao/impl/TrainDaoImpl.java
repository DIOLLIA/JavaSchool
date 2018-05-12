package schedule.dao.impl;

import org.springframework.stereotype.Repository;
import schedule.dao.api.TrainDao;
import schedule.entity.TrainEntity;

import java.util.List;

@Repository
public class TrainDaoImpl extends GeneralCrudDaoImpl<TrainEntity> implements TrainDao {

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
