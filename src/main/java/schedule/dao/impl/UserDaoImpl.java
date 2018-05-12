package schedule.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import schedule.dao.api.UserDao;
import schedule.entity.UserEntity;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

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
