package me.zakaria.commonapi.dtos;

import lombok.Data;

@Data
public class UpdateCustomerRequestDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
}
