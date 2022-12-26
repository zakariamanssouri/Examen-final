package me.zakaria.inventoryservice.commands.commands.aggragates;

import lombok.extern.slf4j.Slf4j;
import me.zakaria.commonapi.commands.category.CreateCategoryCommand;
import me.zakaria.commonapi.commands.category.UpdateCategoryCommand;
import me.zakaria.commonapi.events.category.CategoryCreatedEvent;
import me.zakaria.commonapi.events.category.CategoryUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class CategoryAggregate {

    @AggregateIdentifier
    private String id;
    private String description;


    public CategoryAggregate() {
    }

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        AggregateLifecycle.apply(new CategoryCreatedEvent(
                command.getId(),
                command.getDescription()
        ));
    }

    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.id = event.getId();
        this.description = event.getDescription();
    }



    @CommandHandler
    public void on(UpdateCategoryCommand command) {
        AggregateLifecycle.apply(new CategoryUpdatedEvent(
                command.getId(),
                command.getDescription()
        ));
    }

    @EventSourcingHandler
    public void on(CategoryUpdatedEvent event) {
        this.id = event.getId();
        this.description = event.getDescription();
    }


}
