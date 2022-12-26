package me.zakaria.commonapi.queries.inventory.category;

import lombok.Getter;

public class GetCategoryQuery {

    @Getter
    private String id;


    public GetCategoryQuery(String id) {
        this.id = id;
    }
}
