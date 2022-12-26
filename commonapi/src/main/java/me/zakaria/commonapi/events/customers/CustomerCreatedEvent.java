package me.zakaria.commonapi.events.customers;

import lombok.Getter;
import me.zakaria.commonapi.events.BaseEvent;

public class CustomerCreatedEvent extends BaseEvent<String> {

    @Getter private String name;
    @Getter private String email;
    @Getter private String phone;
    @Getter private String address;


    public CustomerCreatedEvent(String id, String name, String email, String phone, String address) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
