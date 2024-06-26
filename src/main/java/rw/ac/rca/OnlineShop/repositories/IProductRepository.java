package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.OnlineShop.models.Product;

import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {
}
