package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.OnlineShop.models.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
