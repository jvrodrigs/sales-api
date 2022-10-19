package com.app.salesapi.service;

import com.app.salesapi.repository.ItemOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemOrderService {

    @Autowired
    private ItemOrderRepository repository;


}
