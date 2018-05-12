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
    public void deleteUser(UserEntity userEntity) {
    }

    @Override
    public List<UserEntity> listOfUsers() {
        return getCurrentSession().createQuery("from UserEntity ").list();
    }
}
