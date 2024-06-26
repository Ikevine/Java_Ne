package rw.ac.rca.OnlineShop.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID student_id;

    private String fullNames;
    private String grade;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name="student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;
}
