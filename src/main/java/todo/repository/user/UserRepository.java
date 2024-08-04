package todo.repository.user;

import todo.model.user.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);
    Optional<User> findByLoginAndPassword(String login, String password);
    Optional<User> findById(int id);
    boolean deleteByLogin(String login);
    Collection<User> findAll();
}
