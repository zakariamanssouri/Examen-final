package me.zakaria.commonapi.commands.category;

import lombok.Getter;
import me.zakaria.commonapi.commands.BaseCommand;

public class CreateCategoryCommand extends BaseCommand<String> {

    @Getter private String description;


    public CreateCategoryCommand(String id, String description) {
        super(id);
        this.description = description;
    }
}
