package com.app.salesapi.service;

import com.app.salesapi.exception.CustomException;
import com.app.salesapi.exception.OrderNotFoundException;
import com.app.salesapi.model.Client;
import com.app.salesapi.model.ItemOrder;
import com.app.salesapi.model.Product;
import com.app.salesapi.model.Request;
import com.app.salesapi.model.dto.ItemRequestDto;
import com.app.salesapi.model.dto.RequestDto;
import com.app.salesapi.model.enums.StatusRequest;
import com.app.salesapi.repository.ItemOrderRepository;
import com.app.salesapi.repository.RequestRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository repository;
    private final ClientService clientService;
    private final ProductService productService;
    private final ItemOrderRepository itemOrderRepository;

    @Transactional
    public Request save(RequestDto dto){
        Client client = clientService.findClientById(dto.getClient())
                .orElseThrow(() -> new CustomException("ID client invalid."));

        Request rq = new Request();
        rq.setValueTotal(dto.getTotal());
        rq.setClientId(client);
        rq.setStatus(StatusRequest.REALIZADO);

        List<ItemOrder> itemOrders = convertListItems(rq, dto.getItems());
        repository.save(rq);
        itemOrderRepository.saveAll(itemOrders);
        rq.setItemOrders(itemOrders);
        return rq;
    }

    public Optional<Request> getOrderComplet(Integer id){
        return this.repository.findByIdFetchitemOrders(id);
    }
    @Transactional
    public void updateStatus(Integer id, StatusRequest statusRequest){
        repository.findById(id)
                .map(rq -> {
                    rq.setStatus(statusRequest);
                    return repository.save(rq);
                }).orElseThrow(OrderNotFoundException::new);
    }
    private List<ItemOrder> convertListItems(Request rq, List<ItemRequestDto> items){
        if (items.isEmpty()){
            throw new CustomException("it is not possible to place an order without items.");
        }

        return items.stream()
                .map(dto -> {
                    Product product = productService.findById(dto.getProduct())
                            .orElseThrow(() -> new CustomException("ID product invalid."));
                    ItemOrder itemOrder = new ItemOrder();
                    itemOrder.setCountProduct(dto.getCount());
                    itemOrder.setRequestId(rq);
                    itemOrder.setProductId(product);
                    return itemOrder;
                }).collect(Collectors.toList());
    }
}
