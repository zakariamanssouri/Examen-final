package me.zakaria.inventoryservice.query.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zakaria.commonapi.queries.inventory.product.GetAllProductsQuery;
import me.zakaria.commonapi.queries.inventory.product.GetProductQuery;
import me.zakaria.inventoryservice.query.entities.Product;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/inventory/products")
@AllArgsConstructor
@Slf4j
public class ProductQueryController {

    private QueryGateway queryGateway;



    @GetMapping("/all")
    private List<Product> productsList(){
        return queryGateway.query(new GetAllProductsQuery(), ResponseTypes.multipleInstancesOf(Product.class)).join();
    }

    @GetMapping("/{id}")
    private Product product(@PathVariable String id){
        return queryGateway.query(new GetProductQuery(id), ResponseTypes.instanceOf(Product.class)).join();
    }

}
