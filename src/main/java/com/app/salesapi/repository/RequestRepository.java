package com.app.salesapi.repository;

import com.app.salesapi.model.Client;
import com.app.salesapi.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByClientId(Client client);
    @Query("select  p from Request p left join fetch p.itemOrders where p.id = :id")
    Optional<Request> findByIdFetchitemOrders(@Param("id") Integer id);
}
