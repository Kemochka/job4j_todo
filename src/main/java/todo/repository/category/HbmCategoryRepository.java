package todo.repository.category;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import todo.model.category.Category;
import todo.repository.CrudRepository;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmCategoryRepository implements CategoryRepository {
    private final CrudRepository crudRepository;
    private static final Logger LOGGER = Logger.getLogger(HbmCategoryRepository.class);

    @Override
    public Optional<Category> save(Category category) {
        try {
            crudRepository.run(session -> session.save(category));
            return Optional.of(category);
        } catch (Exception e) {
            LOGGER.error("Exception during save category", e);
        }
        return Optional.empty();
    }

    public Optional<Category> findById(int id) {
        return crudRepository.optional("from Category where id = :fId", Category.class, Map.of("fId", id));
    }

    @Override
    public boolean deleteById(int id) {
        boolean result = false;
        try {
            crudRepository.query("delete from Category where id = :fId", Category.class, Map.of("fId", id));
            result = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete category by id");
        }
        return result;
    }

    @Override
    public Collection<Category> findAll() {
        return crudRepository.query("from Category", Category.class);
    }

    @Override
    public Set<Category> findCategoriesById(Set<Integer> id) {
        return new HashSet<>(crudRepository.query(
                "from Category WHERE id in :categories", Category.class,
                Map.of("categories", id)));
    }
}
