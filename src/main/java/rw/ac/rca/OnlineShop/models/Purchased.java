package rw.ac.rca.OnlineShop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Purchased {
    @Id
    private Long id;
    private UUID productCode;
    @OneToOne
    private Quantity quantity;
    private int total;
    private Date date;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
