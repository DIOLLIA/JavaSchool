package schedule.service.api;

import schedule.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    void editUser(User user);

    void deleteUser(User user);

    List<User> getUsers();

    boolean userExist(String name, String pswd);

}
