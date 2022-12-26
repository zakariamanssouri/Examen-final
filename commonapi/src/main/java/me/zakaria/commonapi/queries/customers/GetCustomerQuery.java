package me.zakaria.commonapi.queries.customers;

import lombok.Getter;

public class GetCustomerQuery {
    @Getter private String id;

    public GetCustomerQuery(String id) {
        this.id = id;
    }
}
