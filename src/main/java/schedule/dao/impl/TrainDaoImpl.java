package schedule.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import schedule.dao.api.TrainDao;
import schedule.entity.ScheduleEntity;
import schedule.entity.TrainEntity;
import schedule.exception.CustomDaoException;

import java.util.List;

@Repository
public class TrainDaoImpl extends GeneralCrudDaoImpl<TrainEntity> implements TrainDao {

    private static final Logger logger = LoggerFactory.getLogger(TrainDaoImpl.class);

    public void addTrain(TrainEntity train) {

        try {
            getCurrentSession().save(train);
            logger.info("Train {} was successfully created", train);
        } catch (HibernateException e) {
            logger.warn("Train {} was not created. Exception cause: {}", train, e.getCause());
            throw new CustomDaoException("save(train) fail", e.getCause());
        }

    }

    public TrainEntity getTrain(int id) {
        TrainEntity trainEntity;
        try {
            trainEntity = (TrainEntity) getCurrentSession().get(TrainEntity.class, id);
            logger.info("Train {} was successfully gotten", trainEntity);
        } catch (HibernateException e) {
            logger.warn("Got train by id {} fail. Exception cause: {}", id, e.getCause());
            throw new CustomDaoException("getTrain(id) fail", e.getCause());
        }

        return trainEntity;
    }

    @Override
    public TrainEntity findByNumber(int number) {
        TrainEntity trainEntity;
        try {
            Query query = getCurrentSession().createQuery("FROM TrainEntity WHERE numberOfTrain =:number");
            query.setParameter("number", number);
            trainEntity = (TrainEntity) query.uniqueResult();
            logger.info("Train by number {} was successfully found", number);
        } catch (HibernateException e) {
            logger.warn("Search train by number {} fail. Exception cause: {}", number, e.getCause());
            throw new CustomDaoException("findByNumber(number) fail", e.getCause());
        }

        return trainEntity;
    }

    @Override
    public void editTrain(TrainEntity trainEntity) {
        try {
            getCurrentSession().update(trainEntity);
            logger.info("Edit train {} success", trainEntity);
        }
        catch (HibernateException e){
            logger.warn("Edit train {} fail. Exception cause: {}", trainEntity, e.getCause());
            throw new CustomDaoException("editTrain(train) fail", e.getCause());
        }
    }

    public void deleteTrain(int id) {
        try {
            TrainEntity trainEntity = (TrainEntity) getCurrentSession().get(TrainEntity.class, id);
            getCurrentSession().delete(trainEntity);
            logger.info("Delete train with id {} success", id);
        }
        catch (HibernateException e){
            logger.warn("Delete train with id {} fail. Exception cause: {}", id, e.getCause());
            throw new CustomDaoException("deleteTrain(id) fail", e.getCause());
        }
    }

    public List<TrainEntity> getTrains() {
        List<TrainEntity> resultList;
        try{
            resultList=getCurrentSession().createQuery("from TrainEntity ").list();
            logger.info("Trains List success");
        }
        catch (HibernateException e){
            logger.warn("Get trains List fail. Exception cause: {}", e.getCause());
            throw new CustomDaoException("getTrains fail", e.getCause());
        }
        return resultList ;
    }

    @Override
    public List<ScheduleEntity> getScheduleByTrainId(int id) {
        Query query;
        try {
             query = getCurrentSession().createQuery("SELECT sch " +
                    "FROM ScheduleEntity sch WHERE sch.trainNumber.id =:id ");
            query.setParameter("id", id);
            logger.info("Schedule items for train id {} found",id);
        }
        catch (HibernateException e){
            logger.warn("Schedule items for train id {} fail. Exception cause: {}",id, e.getCause());
            throw new CustomDaoException("getScheduleByTrainId fail", e.getCause());
        }

        return query.list();
    }
}
