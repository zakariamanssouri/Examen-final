package me.zakaria.inventoryservice.commands.commands.aggragates;

import lombok.extern.slf4j.Slf4j;
import me.zakaria.commonapi.commands.products.CreateProductCommand;
import me.zakaria.commonapi.commands.products.UpdateProductCommand;
import me.zakaria.commonapi.events.products.ProductCreatedEvent;
import me.zakaria.commonapi.events.products.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class ProductAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private double price;
    private int quantity;

    private String categoryId;



    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getName(),
                command.getPrice(),
                command.getQuantity(),
                command.getCategoryId()
        ));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.categoryId = event.getCategoryId();
    }



    @CommandHandler
    public void on(UpdateProductCommand command) {
        AggregateLifecycle.apply(new ProductUpdatedEvent(
                command.getId(),
                command.getName(),
                command.getPrice(),
                command.getQuantity(),
                command.getCategoryId()
        ));
    }

    @EventSourcingHandler
    public void on(ProductUpdatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.categoryId = event.getCategoryId();
    }


}
