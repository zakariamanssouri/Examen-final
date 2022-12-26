package me.zakaria.commonapi.queries.inventory.product;

import lombok.Getter;

public class GetProductQuery {

    @Getter
    private String id;

    public GetProductQuery(String id) {
        this.id = id;
    }
}
