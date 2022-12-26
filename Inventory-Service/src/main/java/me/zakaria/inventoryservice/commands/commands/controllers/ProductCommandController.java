package me.zakaria.inventoryservice.commands.commands.controllers;

import lombok.AllArgsConstructor;
import me.zakaria.commonapi.commands.products.CreateProductCommand;
import me.zakaria.commonapi.commands.products.UpdateProductCommand;
import me.zakaria.commonapi.dtos.products.CreateProductRequestDTO;
import me.zakaria.commonapi.dtos.products.UpdateProductRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductCommandController {

    private CommandGateway commandGateway;


    @PostMapping("/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDTO requestDTO) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateProductCommand(
                UUID.randomUUID().toString(),
                requestDTO.getName(),
                requestDTO.getPrice(),
                requestDTO.getQuantity(),
                requestDTO.getCategoryId()
        ));
        return commandResponse;
    }


    @PutMapping("/update")
    public CompletableFuture<String> updateProduct(@RequestBody UpdateProductRequestDTO requestDTO) {
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateProductCommand(
                requestDTO.getId(),
                requestDTO.getName(),
                requestDTO.getPrice(),
                requestDTO.getQuantity(),
                requestDTO.getCategoryId()

        ));
        return commandResponse;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), null, 500);
        return responseEntity;
    }
}
