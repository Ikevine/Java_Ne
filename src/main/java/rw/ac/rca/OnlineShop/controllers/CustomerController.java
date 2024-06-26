package rw.ac.rca.OnlineShop.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.OnlineShop.DTOs.CreateCustomerDTO;
import rw.ac.rca.OnlineShop.DTOs.SaveOrWithdrawMoneyDTO;
import rw.ac.rca.OnlineShop.Enumerations.ERole;
import rw.ac.rca.OnlineShop.Utils.ApiResponse;
import rw.ac.rca.OnlineShop.exceptions.UserAlreadyExists;
import rw.ac.rca.OnlineShop.models.Account;
import rw.ac.rca.OnlineShop.models.BankingRecord;
import rw.ac.rca.OnlineShop.models.Customer;
import rw.ac.rca.OnlineShop.models.User;
import rw.ac.rca.OnlineShop.repositories.IAccountRepository;
import rw.ac.rca.OnlineShop.repositories.ICustomerRepository;
import rw.ac.rca.OnlineShop.repositories.IUserRepository;
import rw.ac.rca.OnlineShop.services.IBankingService;
import rw.ac.rca.OnlineShop.services.ICustomerService;
import rw.ac.rca.OnlineShop.services.IUserService;
import rw.ac.rca.OnlineShop.DTOs.TransferMoneyDTO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final ICustomerService customerService;
    private final IUserService userService;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final IAccountRepository accountRepository;
    private final IBankingService bankingService;
    private final ICustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<?> getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok(new ApiResponse<List<Customer>>("Customers here", HttpStatus.OK,customers));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody CreateCustomerDTO dto){
        Customer newCustomer = new Customer();
        modelMapper.map(dto,newCustomer);
        newCustomer.setLastUpdateTime(LocalDate.now());
        newCustomer.setBalance(0.0);
        Optional<User> existingUser = userService.getUserByEmail(dto.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExists("Email already in use");
        }
        User user = new User(dto.getEmail(),passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Collections.singleton(ERole.CUSTOMER));
        user = userService.createUser(user);
        newCustomer.setProfile(user);
        Account account = new Account();
        account.setNumber("2500423212" + accountRepository.findAll().size() + 1);
        account = accountRepository.save(account);
        Set<Account> accounts = newCustomer.getAccount();
        accounts.add(account);
        newCustomer.setAccount(accounts);
        newCustomer = customerService.createCustomer(newCustomer);
        return ResponseEntity.ok(new ApiResponse<Customer>("Customer created succesfully", HttpStatus.CREATED,newCustomer));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransferMoneyDTO transferMoneyDTO) throws BadRequestException {
        Optional<Customer> receiver = customerRepository.findById(transferMoneyDTO.getReceiver_id());
        Optional<Customer> sender = customerRepository.findById(transferMoneyDTO.getSender_id());
        if(sender.get().getBalance() < transferMoneyDTO.getAmount()){
            throw new BadRequestException("Balance not enough");
        }
        receiver.get().setBalance(receiver.get().getBalance() + transferMoneyDTO.getAmount());
        sender.get().setBalance(sender.get().getBalance() - transferMoneyDTO.getAmount());
        customerRepository.save(receiver.get());
        customerRepository.save(sender.get());
        return ResponseEntity.ok(new ApiResponse<>("Transfer Complete!",HttpStatus.OK,null));
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> saveMoney(@RequestBody SaveOrWithdrawMoneyDTO dto) throws Exception {
        BankingRecord record = bankingService.newRecord(dto);
        return ResponseEntity.ok(new ApiResponse<>("Transaction completed!",HttpStatus.CREATED,record));
    }
}
