package me.zakaria.commonapi.events.category;

import lombok.Getter;
import me.zakaria.commonapi.events.BaseEvent;

public class CategoryCreatedEvent extends BaseEvent<String> {

    @Getter private String description;


    public CategoryCreatedEvent(String id, String description) {
        super(id);
        this.description = description;
    }
}
