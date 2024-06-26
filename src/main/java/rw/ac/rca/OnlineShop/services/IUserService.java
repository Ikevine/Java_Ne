package rw.ac.rca.OnlineShop.services;

import rw.ac.rca.OnlineShop.models.User;

import java.util.Optional;

public interface IUserService {
  public User createUser(User user);
  public User getUserById(int id);
  public Optional<User> getUserByEmail(String email);
}
