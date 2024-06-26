package rw.ac.rca.OnlineShop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rw.ac.rca.OnlineShop.Enumerations.EOperationType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private UUID productCode;
    @Enumerated(EnumType.STRING)
    private EOperationType operation;
    private LocalDateTime date;
    private int quantity;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
