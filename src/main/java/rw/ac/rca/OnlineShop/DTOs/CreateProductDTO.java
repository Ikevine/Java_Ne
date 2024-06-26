package rw.ac.rca.OnlineShop.DTOs;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import rw.ac.rca.OnlineShop.Enumerations.ProductType;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateProductDTO {
    private  String name;
    private  String productType;
    private  float price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inDate;
    private  MultipartFile image;
}
