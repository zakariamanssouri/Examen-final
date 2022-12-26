package me.zakaria.customerservice.commands.controllers;

import lombok.AllArgsConstructor;
import me.zakaria.commonapi.commands.customers.CreateCustomerCommand;
import me.zakaria.commonapi.commands.customers.UpdateCustomerCommand;
import me.zakaria.commonapi.dtos.CreateCustomerRequestDTO;
import me.zakaria.commonapi.dtos.UpdateCustomerRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerCommandController {

    private CommandGateway commandGateway;


    @PostMapping("/create")
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequestDTO requestDTO) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                requestDTO.getName(),
                requestDTO.getAddress(),
                requestDTO.getEmail(),
                requestDTO.getPhone()

        ));
        return commandResponse;
    }


    @PutMapping("/update")
    public CompletableFuture<String> updateCustomer(@RequestBody UpdateCustomerRequestDTO requestDTO) {
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateCustomerCommand(
                requestDTO.getId(),
                requestDTO.getName(),
                requestDTO.getAddress(),
                requestDTO.getEmail(),
                requestDTO.getPhone()

        ));
        return commandResponse;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), null, 500);
        return responseEntity;
    }
}
