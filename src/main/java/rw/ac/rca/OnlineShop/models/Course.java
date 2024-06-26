package rw.ac.rca.OnlineShop.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID course_id;

    private String courseName;
    private Double weight;
    private String courseCode;
}
