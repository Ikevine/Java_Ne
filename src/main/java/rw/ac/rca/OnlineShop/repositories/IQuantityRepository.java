package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.OnlineShop.models.Quantity;

public interface IQuantityRepository extends JpaRepository<Quantity, Integer> {
}
