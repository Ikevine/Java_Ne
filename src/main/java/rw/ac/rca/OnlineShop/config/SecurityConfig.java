package rw.ac.rca.OnlineShop.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rw.ac.rca.OnlineShop.security.JwtAuthenticationFilter;
import rw.ac.rca.OnlineShop.security.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {
private JwtAuthenticationFilter jwtAuthenticationFilter;
private UserDetailsServiceImpl userDetailsService;
private static final String[] WHITELIST = {
        "/v2/API-docs",
        "/v3/API-docs",
        "/api-docs/**",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/api/v1/auth/**",
        "/api/v1/customer/register"};

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeRequests ->
                    authorizeRequests
                            .requestMatchers(WHITELIST).permitAll()
                            .anyRequest().permitAll()
                    //authenticated
            )
            .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return  http.build();
}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        //DAO responsible for fetching user details and setting the password encoder
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
