package me.zakaria.inventoryservice.query.repositories;

import me.zakaria.inventoryservice.query.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
