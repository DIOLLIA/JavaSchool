package schedule.dao.api;

import schedule.entity.ScheduleEntity;
import schedule.entity.TrainEntity;
import schedule.exception.CustomDaoException;

import java.util.List;

public interface TrainDao extends GeneralCrudDao<TrainEntity> {

    void addTrain(TrainEntity train) throws CustomDaoException;

    TrainEntity getTrain(int id);

    TrainEntity findByNumber(int number);

    void editTrain(TrainEntity trainEntity);

    void deleteTrain(int id);

    List<TrainEntity> getTrains();

    List<ScheduleEntity> getScheduleByTrainId(int id);

}
