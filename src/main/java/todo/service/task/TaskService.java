package todo.service.task;

import todo.model.task.Task;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> save(Task task);

    boolean update(Task task);

    boolean delete(Task task);

    boolean setDone(int id);

    Optional<Task> getById(int id);

    List<Task> findByDone(boolean isDone);

    Collection<Task> findAll();
}
