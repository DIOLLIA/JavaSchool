package schedule.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import schedule.dao.api.TrainDao;
import schedule.entity.ScheduleEntity;
import schedule.entity.TrainEntity;
import schedule.exception.CustomDaoException;

import java.util.List;

@Repository
public class TrainDaoImpl extends GeneralCrudDaoImpl<TrainEntity> implements TrainDao {

    public void addTrain(TrainEntity train) throws CustomDaoException{
        try {
            getCurrentSession().save(train);
            //logger.info("Train {} was successfuly created")
        } catch (HibernateException e){
            //logger.warn("Train {} was not created. Exception cause: {e.getCause()}")
            throw new CustomDaoException("Message", e.getCause());
        }
    }

    public TrainEntity getTrain(int id) {
        TrainEntity trainEntity = (TrainEntity) getCurrentSession().get(TrainEntity.class, id);
        return trainEntity;
    }

    @Override
    public TrainEntity findByNumber(int number) {
        Query query = getCurrentSession().createQuery("FROM TrainEntity WHERE numberOfTrain =:number");
        query.setParameter("number", number);
        TrainEntity trainEntity = (TrainEntity) query.uniqueResult();

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

    @Override
    public List<ScheduleEntity> getScheduleByTrainId(int id) {
        Query query = getCurrentSession().createQuery("SELECT sch FROM ScheduleEntity sch WHERE sch.trainNumber.id =:id ");

        query.setParameter("id", id);

        return query.list();
    }
}
