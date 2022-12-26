package me.zakaria.commonapi.events.products;

import lombok.Getter;
import me.zakaria.commonapi.events.BaseEvent;

public class ProductUpdatedEvent extends BaseEvent<String> {
    @Getter private String name;
    @Getter private double price;
    @Getter private int quantity;

    @Getter private String categoryId;


    public ProductUpdatedEvent(String id, String name, double price, int quantity, String categoryId) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }
}
