package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.OnlineShop.models.Customer;

import java.util.UUID;

public interface ICustomerRepository extends JpaRepository<Customer, UUID> {
}
