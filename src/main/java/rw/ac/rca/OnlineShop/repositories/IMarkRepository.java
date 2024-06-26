package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.OnlineShop.models.Mark;

import java.util.UUID;

public interface IMarkRepository extends JpaRepository<Mark, UUID> {
}
