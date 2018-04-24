package shedule.service;

import org.springframework.stereotype.Service;
import shedule.model.Train;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    public void addTrain(Train train) {

    }

    public void getTrain(int id) {

    }

    public void deleteTrain(int id) {

    }

    public List<Train> getTrains() {
        return null;
    }
}
