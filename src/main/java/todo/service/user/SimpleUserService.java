package todo.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import todo.model.user.User;
import todo.repository.user.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {
    private UserRepository userRepository;

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public boolean deleteByLogin(String login) {
        return userRepository.deleteByLogin(login);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}
