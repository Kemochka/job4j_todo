package todo.service.user;

import todo.model.user.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Optional<User> save(User user);
    Optional<User> findByLoginAndPassword(String login, String password);
    boolean deleteByLogin(String login);
    Collection<User> findAll();
}
