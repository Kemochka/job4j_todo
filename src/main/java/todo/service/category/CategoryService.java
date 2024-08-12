package todo.service.category;

import todo.model.category.Category;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    Optional<Category> save(Category category);
    boolean deleteById(int id);
    Collection<Category> findAll();
    Set<Category> findCategoriesById(Set<Integer> id);
}
