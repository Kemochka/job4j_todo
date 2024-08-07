package todo.service.priority;

import todo.model.priority.Priority;

import java.util.Collection;
import java.util.Optional;

public interface PriorityService {
    Optional<Priority> save(Priority priority);
    boolean deleteById(int id);
    Optional<Priority> findById(int id);
    Collection<Priority> findAll();
}
