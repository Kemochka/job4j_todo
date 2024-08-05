package todo.repository.user;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import todo.model.user.User;
import todo.repository.CrudRepository;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {
    private final CrudRepository crudRepository;
    private static final Logger LOGGER = Logger.getLogger(HbmUserRepository.class);

    @Override
    public Optional<User> save(User user) {
        try {
            crudRepository.run(session -> session.save(user));
            return Optional.of(user);
        } catch (Exception e) {
            LOGGER.error("Exception during save user", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional("from User where login = :fLogin and password = :fPassword", User.class,
                Map.of("fLogin", login, "fPassword", password));
    }

    @Override
    public Optional<User> findById(int id) {
        try {
            crudRepository.optional("from User where id = :fId", User.class, Map.of("fId", id));
        } catch (Exception e) {
            LOGGER.error("Exception during find user by id", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteByLogin(String login) {
        boolean result = false;
        try {
            crudRepository.run("delete from User where login = :fLogin", Map.of("fLogin", login));
            result = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete user by login", e);
        }
        return result;
    }

    @Override
    public Collection<User> findAll() {
        return crudRepository.query("from User as u", User.class);
    }
}
