package schedule.dao.impl;

import org.hibernate.Query;
import org.joda.time.LocalDateTime;
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
        return getCurrentSession().createQuery("FROM UserEntity ").list();
    }

    @Override
    public boolean findUser(String name, String pswd) {
        Query query = getCurrentSession().createQuery("FROM UserEntity ue WHERE ue.email LIKE :name AND ue.password LIKE :pswd");
        query.setParameter("name", name);
        query.setParameter("pswd", pswd);
        boolean userIsFind = false;
        try {
            List userObj = query.list();
            if (userObj != null && userObj.size() > 0) {
                userIsFind = true;
            }
        } catch (Exception e) {
            userIsFind = false;
            //logg
        }
        return userIsFind;
    }

    @Override
    public List<UserEntity> findPassengersOfTrain(int routeDailyId, LocalDateTime startTime) {
        Query query = getCurrentSession().createQuery("SELECT t.userEntity FROM TicketEntity t");
        return query.list();
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
