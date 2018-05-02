package schedule.service;

import schedule.entity.TrainEntity;
import schedule.model.Train;

import java.util.List;

public interface TrainService {

        Train addTrain(Train train);

        public void getTrain(int id);

        public void deleteTrain(int id);

        public List<Train> getTrains();

    }

