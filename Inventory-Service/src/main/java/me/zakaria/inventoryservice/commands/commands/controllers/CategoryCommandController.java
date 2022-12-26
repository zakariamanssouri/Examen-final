package me.zakaria.inventoryservice.commands.commands.controllers;

import lombok.AllArgsConstructor;
import me.zakaria.commonapi.commands.category.CreateCategoryCommand;
import me.zakaria.commonapi.commands.category.UpdateCategoryCommand;
import me.zakaria.commonapi.dtos.category.CreateCategoryRequestDTO;
import me.zakaria.commonapi.dtos.category.UpdateCategoryRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryCommandController {

    private CommandGateway commandGateway;


    @PostMapping("/create")
    public CompletableFuture<String> createCategory(@RequestBody CreateCategoryRequestDTO requestDTO) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateCategoryCommand(
                UUID.randomUUID().toString(),
                requestDTO.getDescription()
        ));
        return commandResponse;
    }


    @PutMapping("/update")
    public CompletableFuture<String> updateCategory(@RequestBody UpdateCategoryRequestDTO requestDTO) {
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateCategoryCommand(
                requestDTO.getId(),
                requestDTO.getDescription()
        ));
        return commandResponse;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), null, 500);
        return responseEntity;
    }
}
