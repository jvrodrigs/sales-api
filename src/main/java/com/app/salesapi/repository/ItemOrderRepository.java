package com.app.salesapi.repository;

import com.app.salesapi.model.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {
}
