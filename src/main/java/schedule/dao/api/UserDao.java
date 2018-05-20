package schedule.dao.api;

import schedule.entity.UserEntity;

import java.util.List;

public interface UserDao extends GeneralCrudDao<UserEntity> {
    void addUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);

    void editUser(UserEntity userEntity);

    List<UserEntity> listOfUsers();

    boolean findUser(String name, String pswd);

    List<UserEntity> findPassengersOfTrain(int trainId);
}
