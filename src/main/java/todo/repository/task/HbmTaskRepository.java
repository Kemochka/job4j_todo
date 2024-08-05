package todo.repository.task;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import todo.model.task.Task;
import todo.repository.CrudRepository;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmTaskRepository implements TaskRepository {
    private final CrudRepository crudRepository;
    private static final Logger LOGGER = Logger.getLogger(HbmTaskRepository.class);

    @Override
    public Optional<Task> save(Task task) {
        try {
            crudRepository.run(session -> session.save(task));
            return Optional.of(task);
        } catch (HibernateException e) {
            LOGGER.error("Exception during save task", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Task task) {
        boolean result = false;
        try {
            crudRepository.run(session -> session.merge(task));
            result = true;
        } catch (Exception e) {
            LOGGER.error("Exception during update task", e);
        }
        return result;
    }

    @Override
    public boolean delete(Task task) {
        boolean result = false;
        try {
            crudRepository.run("delete from Task where id = :fId", Map.of("fId", task.getId()));
            result = true;
        } catch (HibernateException e) {
            LOGGER.error("Exception during delete task", e);
        }
        return result;
    }

    @Override
    public boolean setDone(int id) {
        boolean result = false;
        try {
            crudRepository.run("update Task set done = true where id = :fId", Map.of("fId", id));
            result = true;
        } catch (Exception e) {
            LOGGER.error("Exception during setDone task", e);
        }
        return result;
    }

    @Override
    public Optional<Task> getById(int id) {
        return crudRepository.optional(
                "from Task where id = :fId", Task.class,
                Map.of("fId", id));
    }

    @Override
    public List<Task> findByDone(boolean isDone) {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = crudRepository.query("from Task where done = :fDone", Task.class, Map.of("fDone", isDone));
        } catch (Exception e) {
            LOGGER.error("Exception during find tasks by done", e);
        }
        return tasks;
    }

    @Override
    public Collection<Task> findAll() {
        return crudRepository.query("from Task", Task.class);
    }
}
