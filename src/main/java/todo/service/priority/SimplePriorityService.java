package todo.service.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import todo.model.priority.Priority;
import todo.repository.priority.PriorityRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePriorityService implements PriorityService {
    private PriorityRepository priorityRepository;

    @Override
    public Optional<Priority> save(Priority priority) {
        return priorityRepository.save(priority);
    }

    @Override
    public boolean deleteById(int id) {
        return priorityRepository.deleteById(id);
    }

    @Override
    public Optional<Priority> findById(int id) {
        return priorityRepository.findById(id);
    }

    @Override
    public Collection<Priority> findAll() {
        return priorityRepository.findAll();
    }
}
