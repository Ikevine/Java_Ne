package rw.ac.rca.OnlineShop.services.ServicesImpl;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.OnlineShop.mailHandling.EmailHandlerService;
import rw.ac.rca.OnlineShop.models.Customer;
import rw.ac.rca.OnlineShop.repositories.ICustomerRepository;
import rw.ac.rca.OnlineShop.services.ICustomerService;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository;
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
