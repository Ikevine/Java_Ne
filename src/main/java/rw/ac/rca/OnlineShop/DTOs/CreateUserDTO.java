package rw.ac.rca.OnlineShop.DTOs;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String firstName;
    private String email;
    private String password;
    private String phone;

}
