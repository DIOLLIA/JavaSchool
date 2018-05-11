package schedule.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.UserDao;
import schedule.entity.UserEntity;
import schedule.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User addUser(User userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userDao.addUser(userEntity);
        return modelMapper.map(userEntity, User.class);
    }

    /*
    *  public List<Train> getTrains() {

        List<TrainEntity> all = trainDao.getTrains();
        List<Train> trains = new ArrayList<>();

        for (TrainEntity trainEntity : all) {
            trains.add(modelMapper.map(trainEntity, Train.class));
        }

        return trains;
    }*/
    @Override
    public List<User> getUsers() {
        List<UserEntity> all = userDao.listOfUsers();
        List<User> users = new ArrayList<>();

        for (UserEntity userEntity : all) {
            users.add(modelMapper.map(userEntity, User.class));
        }
        return users;
    }
}
