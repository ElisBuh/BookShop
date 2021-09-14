package com.senla.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senla.model.StatusOrder;
import com.senla.util.utilits.LocalDateDeserializer;
import com.senla.util.utilits.LocalDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDto {

    private Integer id;
    private String nameClient;

    @JsonProperty("book")
    private BookDto bookDTO;
    private Integer idBook;
    private int cost;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateComplete;

    private StatusOrder statusOrder;
}
