package me.zakaria.commonapi.events.category;

import lombok.Getter;
import me.zakaria.commonapi.events.BaseEvent;

public class CategoryUpdatedEvent extends BaseEvent<String> {
    @Getter private String description;

    public CategoryUpdatedEvent(String id, String description) {
        super(id);
        this.description = description;
    }
}
