package me.zakaria.inventoryservice.query.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zakaria.commonapi.events.products.ProductCreatedEvent;
import me.zakaria.commonapi.events.products.ProductUpdatedEvent;
import me.zakaria.commonapi.queries.inventory.product.GetAllProductsQuery;
import me.zakaria.commonapi.queries.inventory.product.GetProductQuery;
import me.zakaria.inventoryservice.query.entities.Product;
import me.zakaria.inventoryservice.query.repositories.CategoryRepository;
import me.zakaria.inventoryservice.query.repositories.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductQueryHandler {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    @EventHandler
    public void on(ProductCreatedEvent event) {
        log.info("Handling ProductCreatedEvent for aggregate id : "+event.getId());
        Product product = new Product();
        product.setId(event.getId());
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());
        product.setCategory(categoryRepository.findById(event.getCategoryId()).get());
        productRepository.save(product);

    }

    @EventHandler
    public void on(ProductUpdatedEvent event) {
        log.info("Handling ProductUpdatedEvent for aggregate id : "+event.getId());
        Product product = productRepository.findById(event.getId()).get();
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());
        product.setCategory(categoryRepository.findById(event.getCategoryId()).get());
        productRepository.save(product);
    }

    @QueryHandler
    public List<Product> on(GetAllProductsQuery query) {
        return productRepository.findAll();
    }

    @QueryHandler
    public Product on(GetProductQuery query){
        return productRepository.findById(query.getId()).get();
    }


}
