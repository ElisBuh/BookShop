package com.senla.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDto {

    private Integer id;

    @JsonProperty("book")
    private BookDto bookDTO;

    private Integer countRequest;
}
