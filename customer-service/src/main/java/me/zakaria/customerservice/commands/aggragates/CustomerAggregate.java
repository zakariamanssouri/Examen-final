package me.zakaria.customerservice.commands.aggragates;

import lombok.extern.slf4j.Slf4j;
import me.zakaria.commonapi.commands.customers.CreateCustomerCommand;
import me.zakaria.commonapi.commands.customers.UpdateCustomerCommand;
import me.zakaria.commonapi.events.customers.CustomerCreatedEvent;
import me.zakaria.commonapi.events.customers.CustomerUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class CustomerAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    public CustomerAggregate() {
    }
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getName(),
                command.getEmail(),
                command.getPhone(),
                command.getAddress()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.address = event.getAddress();

    }



    @CommandHandler
    public void on(UpdateCustomerCommand command) {
        AggregateLifecycle.apply(new CustomerUpdatedEvent(
                command.getId(),
                command.getName(),
                command.getEmail(),
                command.getPhone(),
                command.getAddress()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerUpdatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.address = event.getAddress();
    }


}
