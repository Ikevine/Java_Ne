package rw.ac.rca.OnlineShop.auth;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.rca.OnlineShop.DTOs.AuthenticateDTO;
import rw.ac.rca.OnlineShop.Utils.ApiResponse;
import rw.ac.rca.OnlineShop.models.Role;
import rw.ac.rca.OnlineShop.models.User;
import rw.ac.rca.OnlineShop.repositories.IUserRepository;
import rw.ac.rca.OnlineShop.security.UserPrincipal;
import rw.ac.rca.OnlineShop.services.IUserService;
import rw.ac.rca.OnlineShop.services.JwtService;

import java.util.Collections;
import java.util.NoSuchElementException;


@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticatedManager;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticate(@RequestBody AuthenticateDTO authenticateDTO){
       User user = userRepository.findByEmail(authenticateDTO.getEmail()).orElseThrow(() -> new BadCredentialsException("User with given email is not found!"));
        if (user == null || !passwordEncoder.matches(authenticateDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        authenticatedManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateDTO.getEmail(),authenticateDTO.getPassword()));
       String jwtToken = jwtService.generateToken(UserPrincipal.create(user));
       return ResponseEntity.ok(new ApiResponse<>("Login success", HttpStatus.ACCEPTED,jwtToken));
    }
}
