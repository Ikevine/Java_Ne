package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.OnlineShop.config.AppConfig;
import rw.ac.rca.OnlineShop.models.Account;

import java.util.UUID;

public interface IAccountRepository extends JpaRepository<Account, UUID> {
}
