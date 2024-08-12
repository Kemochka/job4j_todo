package todo.service.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import todo.model.category.Category;
import todo.repository.category.CategoryRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean deleteById(int id) {
        return categoryRepository.deleteById(id);
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Set<Category> findCategoriesById(Set<Integer> id) {
        return categoryRepository.findCategoriesById(id);
    }
}
