package todo.repository.priority;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import todo.model.priority.Priority;
import todo.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPriorityRepository implements PriorityRepository {
    private final CrudRepository crudRepository;
    private static final Logger LOGGER = Logger.getLogger(HbmPriorityRepository.class);

    @Override
    public Optional<Priority> save(Priority priority) {
        try {
            crudRepository.run(session -> session.save(priority));
            return Optional.of(priority);
        } catch (Exception e) {
            LOGGER.error("Exception during save priority", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(int id) {
        boolean result = false;
        try {
            crudRepository.run("delete from Priority where id = :fId", Map.of("fId", id));
            result = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete priority", e);
        }
        return result;
    }

    @Override
    public Optional<Priority> findById(int id) {
        return crudRepository.optional(
                "from Priority where id = :fId", Priority.class, Map.of("fId", id)
        );
    }

    @Override
    public Collection<Priority> findAll() {
        return crudRepository.query("from Priority", Priority.class);
    }
}
