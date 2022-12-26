package me.zakaria.commonapi.dtos.category;

import lombok.Data;

@Data
public class UpdateCategoryRequestDTO {
    private String id;
    private String description;
}
