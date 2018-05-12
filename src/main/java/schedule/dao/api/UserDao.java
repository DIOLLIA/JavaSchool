package schedule.dao.api;

import schedule.entity.UserEntity;

import java.util.List;

public interface UserDao extends GeneralCrudDao<UserEntity> {
    void addUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);

    List<UserEntity> listOfUsers();


}
