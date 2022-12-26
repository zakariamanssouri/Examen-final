package me.zakaria.commonapi.commands.category;

import lombok.Getter;
import me.zakaria.commonapi.commands.BaseCommand;

public class UpdateCategoryCommand extends BaseCommand<String> {

    @Getter private String description;

    public UpdateCategoryCommand(String id, String description) {
        super(id);
        this.description = description;
    }
}
