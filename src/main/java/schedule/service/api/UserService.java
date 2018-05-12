package schedule.service.api;

import schedule.model.User;

import java.util.List;

public interface UserService {
    User addUser (User user);
   // deleteUser
            List<User> getUsers ();
}
