package rw.ac.rca.OnlineShop.services;


import rw.ac.rca.OnlineShop.models.Customer;

import java.util.List;

public interface ICustomerService {
    public Customer createCustomer(Customer customer);
    public List<Customer> getCustomers();
}
