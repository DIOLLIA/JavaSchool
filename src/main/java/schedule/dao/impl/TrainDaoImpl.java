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
        TrainEntity trainEntity = (TrainEntity) getCurrentSession().get(TrainEntity.class, id);
        return trainEntity;
    }

    @Override
    public void editTrain(TrainEntity trainEntity) {
        getCurrentSession().update(trainEntity);
    }

    public void deleteTrain(int id) {
        TrainEntity trainEntity = (TrainEntity) getCurrentSession().get(TrainEntity.class, id);
        getCurrentSession().delete(trainEntity);
    }

    @SuppressWarnings("unchecked")
    public List<TrainEntity> getTrains() {

        return getCurrentSession().createQuery("from TrainEntity ").list();
    }
}
