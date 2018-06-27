package schedule.service.api;

import org.joda.time.LocalDateTime;
import schedule.model.User;

import java.util.List;

/**
 * @author Rudkov Andrey
 * <p>
 * UserService class basically interacts with {@link User} objects.
 * Class methods can: create, get user,
 * search passengers on train with requested params,
 * search users by login or surname (birth day optionally)
 */

public interface UserService {
    User addUser(User user);

    void editUser(User user);

    void deleteUser(User user);

    List<User> getUsers();

    boolean userExist(String name, String pswd);

    List<User> findPassengersOfTrain(int routeDailyId, LocalDateTime startTime);

    List<User> findByLoginOrSurname(String loginOrSurname);

    boolean simpleUserValidation(String name, String surName, String birthDay);

    User findUserByNameSurnameBirthDay(String name, String surName, String birthDay);
}
