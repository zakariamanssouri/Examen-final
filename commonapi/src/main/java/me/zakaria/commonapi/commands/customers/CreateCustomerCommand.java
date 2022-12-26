package me.zakaria.commonapi.commands.customers;

import lombok.Getter;
import me.zakaria.commonapi.commands.BaseCommand;

public class CreateCustomerCommand extends BaseCommand<String> {
    @Getter
    private String name;
    @Getter
    private String address;
    @Getter
    private String email;
    @Getter
    private String phone;

    public CreateCustomerCommand(String id, String name, String address, String email, String phone) {
        super(id);
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
