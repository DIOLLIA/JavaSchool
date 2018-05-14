package schedule.dao.impl;

import org.springframework.stereotype.Repository;
import schedule.dao.api.UserDao;
import schedule.entity.UserEntity;

import java.util.List;

@Repository
public class UserDaoImpl extends GeneralCrudDaoImpl<UserEntity> implements UserDao {

    @Override
    public void addUser(UserEntity user) {

        getCurrentSession().save(user);
    }

    @Override
    public List<UserEntity> listOfUsers() {
        return getCurrentSession().createQuery("from UserEntity ").list();
    }

    @Override
    public void editUser(UserEntity userEntity) {

        UserEntity existUserEntity = (UserEntity) getCurrentSession().get(UserEntity.class, userEntity.getId());
        existUserEntity.setName(userEntity.getName());
        existUserEntity.setSurname(userEntity.getSurname());
        existUserEntity.setBirthDaY(userEntity.getBirthDaY());
        existUserEntity.setEmail(userEntity.getEmail());
        existUserEntity.setRole(userEntity.getRole());
        getCurrentSession().save(existUserEntity);
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        getCurrentSession().delete(userEntity);
    }

}
