package me.zakaria.commonapi.events.customers;

import lombok.Getter;

public class CustomerUpdatedEvent {
    @Getter private String id;
    @Getter private String name;
    @Getter private String email;
    @Getter private String phone;
    @Getter private String address;

    public CustomerUpdatedEvent(String id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
