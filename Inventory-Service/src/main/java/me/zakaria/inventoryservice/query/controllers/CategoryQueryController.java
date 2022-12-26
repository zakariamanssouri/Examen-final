package me.zakaria.inventoryservice.query.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zakaria.commonapi.queries.inventory.category.GetAllCategorysQuery;
import me.zakaria.commonapi.queries.inventory.category.GetCategoryQuery;
import me.zakaria.inventoryservice.query.entities.Category;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/inventory/category")
@AllArgsConstructor
@Slf4j
public class CategoryQueryController {

    private QueryGateway queryGateway;



    @GetMapping("/all")
    private List<Category> customersList(){
        return queryGateway.query(new GetAllCategorysQuery(), ResponseTypes.multipleInstancesOf(Category.class)).join();
    }

    @GetMapping("/{id}")
    private Category customer(@PathVariable String id){
        return queryGateway.query(new GetCategoryQuery(id), ResponseTypes.instanceOf(Category.class)).join();
    }

}
