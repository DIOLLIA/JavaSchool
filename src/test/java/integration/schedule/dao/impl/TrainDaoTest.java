package integration.schedule.dao.impl;

import integration.schedule.config.DataBaseTestConfig;
import org.junit.Assert;
import org.junit.Before;
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

    public static final TrainEntity TRAIN_ENTITY=new TrainEntity();

    @Before
    public void setUp() {
        int trainNumber=100;
        int seats=111;
        int id=1;
        TrainEntity trainEntityOnSetup = new TrainEntity();
        trainEntityOnSetup.setId(id);
        trainEntityOnSetup.setSeats(seats);
        trainEntityOnSetup.setNumberOfTrain(trainNumber);

        trainDao.addTrain(trainEntityOnSetup);

    }

    @Test
    public void testAddTrain() {
        int trainNumber = 100;
        TrainEntity train = new TrainEntity();
        train.setNumberOfTrain(trainNumber);
        train.setSeats(1000);

        trainDao.addTrain(train);

        TrainEntity trainFromDb = trainDao.findByNumber(trainNumber);
        Assert.assertNotNull(trainFromDb);
    }

    @Test
    public void testGetTrain() {
        TrainEntity train = new TrainEntity();
        int id = 1;
        train.setId(id);
        trainDao.addTrain(train);

        TrainEntity trainFromDb = trainDao.getTrain(id);
        Assert.assertNotNull(trainFromDb);
    }

    @Test
    public void testFindByNumber() {
        TrainEntity train = new TrainEntity();
        int number = 111;
        train.setNumberOfTrain(number);
        trainDao.addTrain(train);

        TrainEntity trainFromDb = trainDao.findByNumber(number);
        Assert.assertNotNull(trainFromDb);
    }

    @Test
    public void testDeleteTrain() {
 /*       TrainEntity train = new TrainEntity();
        int id = 0;
        train.setId(id);
        train.setSeats(0);
        train.setNumberOfTrain(12);
        trainDao.addTrain(train);*/
//        Assert.assertNotNull(trainDao.getTrains());
        trainDao.getTrains();
        trainDao.delete(TRAIN_ENTITY);
        trainDao.getTrains();
        Assert.assertTrue(trainDao.getTrains().isEmpty());
    }
}
