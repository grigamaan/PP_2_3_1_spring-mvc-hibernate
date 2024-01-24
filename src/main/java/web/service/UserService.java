package web.service;

import java.util.List;
import web.model.User;

public interface UserService {

    public List getAllUsers();
    public User getUserById(Long id);
    public boolean saveUser(User user);
    public boolean deleteUserById(Long id);

}