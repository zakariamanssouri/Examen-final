package me.zakaria.customerservice.queries.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zakaria.commonapi.events.customers.CustomerCreatedEvent;
import me.zakaria.commonapi.events.customers.CustomerUpdatedEvent;
import me.zakaria.commonapi.queries.customers.GetAllCustomersQuery;
import me.zakaria.commonapi.queries.customers.GetCustomerQuery;
import me.zakaria.customerservice.queries.entities.Customer;
import me.zakaria.customerservice.queries.repositories.CustomerRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceHandler {
    private CustomerRepository customerRepository;


    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.info("Handling CustomerCreatedEvent for aggregate id : "+event.getId());
        Customer customer = new Customer();
        customer.setId(event.getId());
        customer.setName(event.getName());
        customer.setEmail(event.getEmail());

        customer.setAdresse(event.getAddress());
        customer.setPhone(event.getPhone());
        customerRepository.save(customer);
    }

    @EventHandler
    public void on(CustomerUpdatedEvent event) {
        log.info("Handling CustomerUpdatedEvent for aggregate id : "+event.getId());
        Customer customer = customerRepository.findById(event.getId()).get();
        customer.setName(event.getName());
        customer.setEmail(event.getEmail());
        customer.setAdresse(event.getAddress());
        customer.setPhone(event.getPhone());
        customerRepository.save(customer);
    }

    @QueryHandler
    public List<Customer> on(GetAllCustomersQuery query) {
        return customerRepository.findAll();
    }

    @QueryHandler
    public Customer on(GetCustomerQuery query){
        return customerRepository.findById(query.getId()).get();
    }


}
