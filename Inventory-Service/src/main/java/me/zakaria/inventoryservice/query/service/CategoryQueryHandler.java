package me.zakaria.inventoryservice.query.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zakaria.commonapi.events.category.CategoryCreatedEvent;
import me.zakaria.commonapi.events.category.CategoryUpdatedEvent;
import me.zakaria.commonapi.queries.inventory.category.GetAllCategorysQuery;
import me.zakaria.commonapi.queries.inventory.category.GetCategoryQuery;
import me.zakaria.inventoryservice.query.entities.Category;
import me.zakaria.inventoryservice.query.repositories.CategoryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryQueryHandler {

    private CategoryRepository categoryRepository;


    @EventHandler
    public void on(CategoryCreatedEvent event) {
        log.info("Handling CategoryCreatedEvent for aggregate id : "+event.getId());
        Category category = new Category();
        category.setId(event.getId());
        category.setDescription(event.getDescription());
        category.setProductList(null);
    }

    @EventHandler
    public void on(CategoryUpdatedEvent event) {
        log.info("Handling CategoryUpdatedEvent for aggregate id : "+event.getId());
        Category category = categoryRepository.findById(event.getId()).get();
        category.setDescription(event.getDescription());
        categoryRepository.save(category);
    }

    @QueryHandler
    public List<Category> on(GetAllCategorysQuery query) {
        return categoryRepository.findAll();
    }

    @QueryHandler
    public Category on(GetCategoryQuery query){
        return categoryRepository.findById(query.getId()).get();
    }


}
