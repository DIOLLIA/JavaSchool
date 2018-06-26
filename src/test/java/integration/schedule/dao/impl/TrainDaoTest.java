package integration.schedule.dao.impl;

import integration.schedule.config.DataBaseTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.api.TrainDao;
import schedule.entity.TrainEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {DataBaseTestConfig.class})
@Transactional
public class TrainDaoTest {

    @Autowired
    private TrainDao trainDao;

    @Test
    public void addTrain() {
        int trainNumber = 100;
        TrainEntity train = new TrainEntity();
        train.setNumberOfTrain(trainNumber);
        train.setSeats(1000);

        trainDao.addTrain(train);

        TrainEntity trainFromDb = trainDao.findByNumber(trainNumber);
        Assert.assertNotNull(trainFromDb);
    }
}
