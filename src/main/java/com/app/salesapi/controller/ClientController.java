package com.app.salesapi.controller;

import com.app.salesapi.model.Client;
import com.app.salesapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    public ClientService service;

    @GetMapping
    public Page<Client> getAllClients(Client filter, Pageable pgRequest){
        Page<Client> result = service.find(filter, pgRequest);

        List<Client> transfListClient = result.getContent().stream().collect(Collectors.toList());
        return new PageImpl<Client>(transfListClient, pgRequest, result.getTotalElements());
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client){
        Client clientCreated = service.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id){
        Client result = service.findClientById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(result);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<Client> getUpdateClientById(@PathVariable("id") Integer id, @RequestBody Client client){
        service.findClientById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Client result = service.update(id, client);
        return ResponseEntity.ok(result);
    }
}
