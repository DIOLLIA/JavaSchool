package schedule.service.impl;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
    public List<User> findByLoginOrSurname(String loginOrSurname) {
        List<UserEntity> userEntity = userDao.findUserByLoginOrSurname(loginOrSurname);
        List<User> userArrayList = new ArrayList<>();
        for (UserEntity userEntityList : userEntity) {
            userArrayList.add(modelMapper.map(userEntityList, User.class));
        }
        return userArrayList;
    }

    /**
     * Method make simple validation of user for not empty fields and for user existing in database by surname param
     *
     * @param name
     * @param surName
     * @param birthDay
     * @return true, if user fields is not empty and surName was found in dataBase
     */
    @Override
    public boolean simpleUserValidation(String name, String surName, String birthDay) {
        if (/*null == name || null == surName || null == birthDay ||*/ name.equals("") || surName.equals("") || birthDay.equals("")) {
            return false;
        } else
            return true;
    }

    @Override
    public User findUserByNameSurnameBirthDay(String name, String surName, String birthDay) {
        DateTimeFormatter birthDayDateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        LocalDate birthDayFormatted = LocalDate.parse(birthDay, birthDayDateFormatter);
        UserEntity userEntity = userDao.findUserByNameSurnameBirthDay(name, surName, birthDayFormatted);
        if (userEntity == null) {
            return null;
        } else {
            User user = modelMapper.map(userEntity, User.class);
            return user;
        }
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
