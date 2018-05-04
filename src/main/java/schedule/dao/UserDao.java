package schedule.dao;

import schedule.entity.UserEntity;

import java.util.List;

public interface UserDao {
    void addUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);

    List<UserEntity> listOfUsers(String userName);


}
