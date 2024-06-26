package rw.ac.rca.OnlineShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.OnlineShop.models.Course;

import java.util.UUID;

public interface ICourseRepository extends JpaRepository<Course,UUID> {
}
