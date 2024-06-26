package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.OnlineShop.models.Message;

import java.util.UUID;

public interface IMessageRepository extends JpaRepository<Message, UUID> {
}
