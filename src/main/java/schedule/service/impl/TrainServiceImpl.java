package schedule.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.api.TrainDao;
import schedule.entity.TrainEntity;
import schedule.model.Train;
import schedule.service.api.TrainService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private ModelMapper modelMapper;

    public Train addTrain(Train trainDto) {
        TrainEntity trainEntity = modelMapper.map(trainDto, TrainEntity.class);
        trainDao.addTrain(trainEntity);
        return modelMapper.map(trainEntity, Train.class);
    }

    public void editTrain(Train trainDto) {
        TrainEntity trainEntity = modelMapper.map(trainDto, TrainEntity.class);
        trainDao.editTrain(trainEntity);
    }

    public void deleteTrain(int id) {
        trainDao.deleteTrain(id);
    }

    public List<Train> getTrains() {

        List<TrainEntity> all = trainDao.getTrains();
        List<Train> trains = new ArrayList<>();

        for (TrainEntity trainEntity : all) {
            trains.add(modelMapper.map(trainEntity, Train.class));
        }
        return trains;
    }

    @Override
    public Train get(int id) {
        TrainEntity trainEntity = trainDao.getTrain(id);
        Train train=modelMapper.map(trainEntity, Train.class);
        return train;

    }
}
