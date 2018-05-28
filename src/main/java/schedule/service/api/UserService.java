package schedule.service.api;

import org.joda.time.LocalDateTime;
import schedule.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    void editUser(User user);

    void deleteUser(User user);

    List<User> getUsers();

    boolean userExist(String name, String pswd);

    List<User> findPassengersOfTrain(int routeDailyId, LocalDateTime startTime);

    List<User> findByLoginOrSurname(String loginOrSurname);
}
