package me.zakaria.customerservice.queries.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zakaria.commonapi.queries.customers.GetAllCustomersQuery;
import me.zakaria.commonapi.queries.customers.GetCustomerQuery;
import me.zakaria.customerservice.queries.entities.Customer;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/customer")
@AllArgsConstructor
@Slf4j
public class CustomerQueryController {
    private QueryGateway queryGateway;



    @GetMapping("/all")
    private List<Customer> customersList(){
        return queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Customer.class)).join();
    }

    @GetMapping("/{id}")
    private Customer customer(@PathVariable String id){
        return queryGateway.query(new GetCustomerQuery(id), ResponseTypes.instanceOf(Customer.class)).join();
    }



}
