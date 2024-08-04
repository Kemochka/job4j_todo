package todo.repository.user;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import todo.model.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {
    private final SessionFactory sf;
    private static final Logger LOGGER = Logger.getLogger(HbmUserRepository.class);

    @Override
    public Optional<User> save(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            LOGGER.error("Exception during save user", e);
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("from User where login = :fLogin and password = :fPassword", User.class)
                    .setParameter("fLogin", login)
                    .setParameter("fPassword", password);
            Optional<User> user = query.uniqueResultOptional();
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            LOGGER.error("Exception during find user by login and password", e);
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            LOGGER.error("Exception during find user by id", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteByLogin(String login) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            session.createQuery("delete from User where login = :fLogin")
                    .setParameter("fLogin", login)
                    .executeUpdate();
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete user by login", e);
        }
        return result;
    }

    @Override
    public Collection<User> findAll() {
        Session session = sf.openSession();
        List<User> users = new ArrayList<>();
        try {
            session.beginTransaction();
            users = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Exception during find all users", e);
        } finally {
            session.close();
        }
        return users;
    }
}
