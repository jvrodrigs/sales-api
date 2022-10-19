package com.app.salesapi.model.dto;

import com.app.salesapi.exception.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    @NotNull(message = "identifier client is required.")
    private Integer client;
    @NotNull(message = "Value price is required.")
    private BigDecimal total;
    @NotEmptyList(message = "The order cannot be placed without items.")
    private List<ItemRequestDto> items;

}
