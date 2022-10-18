package com.app.salesapi.repository;

import com.app.salesapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    @Query("select c from Client c left join fetch c.requests where c.id = :id")
    Client findClientFetchRequests(@Param("id") Integer id);
}
