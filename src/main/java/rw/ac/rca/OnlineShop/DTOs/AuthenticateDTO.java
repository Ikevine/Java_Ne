package rw.ac.rca.OnlineShop.DTOs;

import lombok.Data;

@Data
public class AuthenticateDTO {
    private String email;
    private String password;
}
