package schedule.dao.api;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import schedule.entity.UserEntity;

import java.util.List;

public interface UserDao extends GeneralCrudDao<UserEntity> {
    void addUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);

    void editUser(UserEntity userEntity);

    List<UserEntity> listOfUsers();

    boolean findUser(String name, String pswd);

    List<UserEntity> findPassengersOfTrain(int routeDailyId, LocalDateTime startTime);

    List<UserEntity> findUserByLoginOrSurname(String loginOrSurname);

    UserEntity findUserByNameSurnameBirthDay(String name, String surName, LocalDate birthDay);
}
