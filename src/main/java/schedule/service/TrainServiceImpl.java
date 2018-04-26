package schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import schedule.dao.TrainDao;
import schedule.model.Train;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDao trainDao;

    public void addTrain(Train train) {
        trainDao.addTrain(train);
    }

    public void getTrain(int id) {
        trainDao.getTrain(id);
    }

    public void deleteTrain(int id) {
        trainDao.deleteTrain(id);
    }

    public List<Train> getTrains() {
        return trainDao.getTrains();
    }
}
