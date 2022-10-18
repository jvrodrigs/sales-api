package com.app.salesapi.repository;

import com.app.salesapi.model.Client;
import com.app.salesapi.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findByClientId(Client client);
}
