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

import java.util.List;

/**
 * @author Rudkov Andrey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {DataBaseTestConfig.class})
@Transactional
public class TrainDaoTest {

    @Autowired
    private TrainDao trainDao;

    public static final TrainEntity TRAIN_ENTITY = new TrainEntity();
    private static final int TRAIN_NUMBER = 100;
    private static final int TRAIN_SEATS = 111;
    private static final int TRAIN_ID = 1;

    @Before
    public void setUp() {
        TrainEntity trainEntityOnSetup = new TrainEntity();
        trainEntityOnSetup.setId(TRAIN_ID);
        trainEntityOnSetup.setSeats(TRAIN_SEATS);
        trainEntityOnSetup.setNumberOfTrain(TRAIN_NUMBER);

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
        List trainsBeforeDeletion = trainDao.getTrains();

        trainDao.deleteTrain(TRAIN_ID);

        List trainsAfterDeletion = trainDao.getTrains();
        Assert.assertNotEquals(trainsBeforeDeletion.size(), trainsAfterDeletion.size());
        Assert.assertTrue(trainsBeforeDeletion.size() == trainsAfterDeletion.size() + 1);
    }
}
