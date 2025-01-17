package schedule.service.impl;

import org.joda.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.dao.api.UserDao;
import schedule.entity.UserEntity;
import schedule.model.User;
import schedule.service.api.UserService;

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

    @Override
    public List<User> getUsers() {
        List<UserEntity> all = userDao.listOfUsers();
        List<User> users = new ArrayList<>();

        for (UserEntity userEntity : all) {
            users.add(modelMapper.map(userEntity, User.class));
        }
        return users;
    }

    @Override
    public boolean userExist(String name, String pswd) {
        boolean validUser = userDao.findUser(name, pswd);
        return validUser;
    }

    @Override
    public List<User> findPassengersOfTrain(int routeDailyId, LocalDateTime startTime) {
        List<UserEntity> passengeersOnTrainEntity = userDao.findPassengersOfTrain(routeDailyId, startTime);
        List<User> passengersOnTrain = new ArrayList<>();
        for (UserEntity passenger : passengeersOnTrainEntity) {
            passengersOnTrain.add(modelMapper.map(passenger, User.class));
        }
        return passengersOnTrain;
    }

    @Override
    public void editUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userDao.editUser(userEntity);
    }

    @Override
    public void deleteUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userDao.deleteUser(userEntity);
    }

}
