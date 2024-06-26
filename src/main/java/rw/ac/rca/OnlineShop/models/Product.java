package rw.ac.rca.OnlineShop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import rw.ac.rca.OnlineShop.Enumerations.ProductType;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID code;
    private String name;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private float price;
    private LocalDate inDate;
    private String filePath;

    public Product(String name, ProductType productType, float price, LocalDate inDate){
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.inDate = inDate;
    }

}


