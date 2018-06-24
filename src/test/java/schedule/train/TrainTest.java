package schedule.train;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import schedule.config.Component;
import schedule.config.DataBaseConfig;
import schedule.dao.api.TrainDao;
import schedule.entity.TrainEntity;

import javax.annotation.Resource;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        DataBaseConfig.class,
        Component.class})
public class TrainTest {

    @Resource
    private TrainDao trainDao;

    @Before
    public void setUp() throws Exception {
        TrainEntity trainEntity = new TrainEntity();
        trainEntity.setId(1);
        trainEntity.setNumberOfTrain(1);
        trainEntity.setSeats(10);
        trainDao.addTrain(trainEntity);
    }
    @Test
    public void testCreate() throws Exception {
    TrainEntity trainEntity = trainDao.findByNumber(1);
        Assert.assertNotNull(trainEntity);
    }

}
