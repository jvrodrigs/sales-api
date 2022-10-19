package com.app.salesapi.controller;

import com.app.salesapi.exception.InforNotFoundException;
import com.app.salesapi.model.ItemOrder;
import com.app.salesapi.model.Request;
import com.app.salesapi.model.dto.ItemOrderInformationDto;
import com.app.salesapi.model.dto.OrderStatusUpdateDto;
import com.app.salesapi.model.dto.OrderingInfoDto;
import com.app.salesapi.model.dto.RequestDto;
import com.app.salesapi.model.enums.StatusRequest;
import com.app.salesapi.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class RequestController {

    @Autowired
    private RequestService service;

    @PostMapping
    public Integer save(@RequestBody @Valid RequestDto dto){
        Request requestSave = service.save(dto);
        return requestSave.getId();
    }

    @GetMapping("/{id}")
    public OrderingInfoDto getById(@PathVariable Integer id){
        return service
                .getOrderComplet(id)
                .map(this::convertEntityToDto)
                .orElseThrow(() -> new InforNotFoundException("Order not found."));
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatusRequest(@PathVariable("id") Integer id,
                                    @RequestBody OrderStatusUpdateDto dto){
        String statusUpdate = dto.getStatusUpdate();
        service.updateStatus(id, StatusRequest.valueOf(statusUpdate));
    }

    private OrderingInfoDto convertEntityToDto(Request rq){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        return OrderingInfoDto.builder()
                .cod(rq.getId())
                .date(dateFormat.format(rq.getCreateAt()))
                .nameClient(rq.getClientId().getName())
                .cpf(rq.getClientId().getCpf())
                .total(rq.getValueTotal())
                .status(rq.getStatus().name())
                .itemOrderInformationDtoList(convertEntityItemOrderToDto(rq.getItemOrders()))
                .build();
    }

    private List<ItemOrderInformationDto> convertEntityItemOrderToDto(List<ItemOrder> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens
                .stream()
                .map(item -> ItemOrderInformationDto
                        .builder()
                        .description(item.getProductId().getDescription())
                        .priceUnit(item.getProductId().getPriceUnit())
                        .count(item.getCountProduct())
                        .build()
                ).collect(Collectors.toList());
    }
}
