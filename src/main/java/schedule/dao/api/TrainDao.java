package schedule.dao.api;

import schedule.entity.TrainEntity;

import java.util.List;

public interface TrainDao extends GeneralCrudDao<TrainEntity> {

    void addTrain(TrainEntity train);

    TrainEntity getTrain(int id);

    TrainEntity findByNumber(int number);

    void editTrain(TrainEntity trainEntity);

    void deleteTrain(int id);

    List getTrains();

    List getScheduleByTrainId(int id);

}
