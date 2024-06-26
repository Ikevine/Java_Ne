package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.OnlineShop.models.BankingRecord;

import java.util.UUID;

public interface IBankingRepository extends JpaRepository<BankingRecord, UUID> {
}
