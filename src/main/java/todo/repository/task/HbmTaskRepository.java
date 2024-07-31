package todo.repository.task;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import todo.model.task.Task;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmTaskRepository implements TaskRepository {
    private final SessionFactory sf;
    private static final Logger LOGGER = Logger.getLogger(HbmTaskRepository.class);

    @Override
    public Optional<Task> save(Task task) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            return Optional.of(task);
        } catch (HibernateException e) {
            LOGGER.error("Exception during save task", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Task task) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("update Task set description = :fDescription, created = :fCreated, done = :fDone where id = :fId")
                    .setParameter("fDescription", task.getDescription())
                    .setParameter("fCreated", task.getCreated())
                    .setParameter("fDone", task.isDone())
                    .setParameter("fId", task.getId())
                    .executeUpdate();

            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            LOGGER.error("Exception during update task", e);
        }
        return false;
    }

    @Override
    public boolean delete(Task task) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            LOGGER.error("Exception during delete task");
        }
        return false;
    }

    @Override
    public boolean setDone(int id) {
        boolean result = false;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("update Task set done = true where id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            result = true;
        } catch (HibernateException e) {
            LOGGER.error("Exception during setDone task");
        }
        return result;
    }

    @Override
    public Optional<Task> getById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.getTransaction().commit();
            return Optional.of(task);
        } catch (HibernateException e) {
            LOGGER.error("Exception during get task by id");
        }
        return Optional.empty();
    }

    @Override
    public List<Task> findByDone(boolean isDone) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            var query = session.createQuery("from Task as t where t.done = :fDone", Task.class)
                    .setParameter("fDone", true);
            List<Task> tasks = query.getResultList();
            session.getTransaction().commit();
            return tasks;
        }
    }

    @Override
    public Collection<Task> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> tasks = session.createQuery("from Task", Task.class).list();
        session.getTransaction().commit();
        return tasks;
    }
}
