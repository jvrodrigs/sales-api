package com.app.salesapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderingInfoDto {
    private Integer cod;
    private String cpf;
    private String nameClient;
    private BigDecimal total;
    private String date;
    private String status;
    private List<ItemOrderInformationDto> itemOrderInformationDtoList;
}
