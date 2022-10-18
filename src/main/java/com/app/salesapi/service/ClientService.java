package com.app.salesapi.service;

import com.app.salesapi.model.Client;
import com.app.salesapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Page<Client> find(Client filter, Pageable pgRequest){
        Example<Client> example = Example.of(filter,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example, pgRequest);
    }

    public Client save(Client c){
        return this.repository.save(c);
    }

    public Optional<Client> findClientById(Integer id){
        return repository.findById(id);
    }

    public Client update(Integer id, Client client){
        return this.repository.findById(id)
                .map(clientExists -> {
                    client.setId(clientExists.getId());
                    return repository.save(client);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
