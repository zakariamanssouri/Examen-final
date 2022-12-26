package me.zakaria.customerservice.queries.repositories;

import me.zakaria.customerservice.queries.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {

}
