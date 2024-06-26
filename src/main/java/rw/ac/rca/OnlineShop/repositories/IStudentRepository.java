package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.OnlineShop.models.Student;

import java.util.UUID;

public interface IStudentRepository extends JpaRepository<Student, UUID> {
}
