package rw.ac.rca.OnlineShop.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateCustomerDTO {
  private String firstName;
  private String lastName;
  @Email
  private String email;
  private String phoneNumber;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dob;
  private String password;

}
