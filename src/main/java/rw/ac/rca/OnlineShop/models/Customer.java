package rw.ac.rca.OnlineShop.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Customer{
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;
    @Column(nullable = false)
    private double balance;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate lastUpdateTime;
    @OneToMany
    private Set<Account> account = new HashSet<>();
    @OneToOne
    private User profile;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID customer_id;

    public void setCustomer_id(UUID customerId) {
        this.customer_id = customerId;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }
}
