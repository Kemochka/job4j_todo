package todo.repository.task;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import todo.model.task.Task;

import java.util.ArrayList;
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
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            return Optional.of(task);
        } catch (HibernateException e) {
            LOGGER.error("Exception during save task", e);
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Task task) {
        Session session = sf.openSession();
        try {
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
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete Task where id = :fId")
                    .setParameter("fId", task.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            LOGGER.error("Exception during delete task");
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean setDone(int id) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            session.createQuery("update Task set done = true where id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            result = true;
        } catch (HibernateException e) {
            LOGGER.error("Exception during setDone task");
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<Task> getById(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.getTransaction().commit();
            return Optional.of(task);
        } catch (HibernateException e) {
            LOGGER.error("Exception during get task by id");
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public List<Task> findByDone(boolean isDone) {
        Session session = sf.openSession();
        List<Task> tasks = new ArrayList<>();
        try {
            session.beginTransaction();
            var query = session.createQuery("from Task where done = :fDone", Task.class)
                    .setParameter("fDone", isDone);
            tasks = query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Exception during find tasks by done");
        } finally {
            session.close();
        }
        return tasks;
    }

    @Override
    public Collection<Task> findAll() {
        Session session = sf.openSession();
        List<Task> tasks = new ArrayList<>();
        try {
            session.beginTransaction();
            tasks = session.createQuery("from Task", Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Exception during find all tasks");
        } finally {
            session.close();
        }
        return tasks;
    }
}
