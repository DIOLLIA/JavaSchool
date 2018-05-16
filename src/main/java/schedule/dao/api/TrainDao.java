package schedule.dao.api;

import schedule.entity.TrainEntity;

import java.util.List;

public interface TrainDao extends GeneralCrudDao<TrainEntity> {
    void addTrain(TrainEntity train);

    TrainEntity getTrain(int id);

    TrainEntity editTrain(int id);

    void deleteTrain(int id);

    List<TrainEntity> getTrains();
}
