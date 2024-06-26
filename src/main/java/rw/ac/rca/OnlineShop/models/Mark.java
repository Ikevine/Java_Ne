package rw.ac.rca.OnlineShop.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID mark_id;
    @ManyToOne
    private Student student;
    private Double score;
    @ManyToOne
    private Course course;
}
