package shedule.dao;

import shedule.model.Train;

import java.util.List;

public interface TrainDao {
    public void addTrain(Train train);

    public void getTrain(int id);

    public void deleteTrain(int id);

    public List<Train> getTrains();
}