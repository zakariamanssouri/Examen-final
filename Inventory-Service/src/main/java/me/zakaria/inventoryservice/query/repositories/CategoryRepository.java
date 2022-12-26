package me.zakaria.inventoryservice.query.repositories;

import me.zakaria.inventoryservice.query.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
