package rw.ac.rca.OnlineShop.services.ServicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.OnlineShop.models.User;
import rw.ac.rca.OnlineShop.repositories.IUserRepository;
import rw.ac.rca.OnlineShop.services.IUserService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private IUserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found!"));
        return user;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
