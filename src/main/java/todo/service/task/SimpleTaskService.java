package todo.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import todo.model.task.Task;
import todo.repository.task.TaskRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private TaskRepository taskRepository;
    @Override
    public Optional<Task> save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public boolean update(Task task) {
        return taskRepository.update(task);
    }

    @Override
    public boolean delete(Task task) {
        return taskRepository.delete(task);
    }

    @Override
    public boolean setDone(int id) {
        return taskRepository.setDone(id);
    }

    @Override
    public Optional<Task> getById(int id) {
        return taskRepository.getById(id);
    }

    @Override
    public List<Task> findByDone(boolean isDone) {
        return taskRepository.findByDone(isDone);
    }

    @Override
    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }
}
