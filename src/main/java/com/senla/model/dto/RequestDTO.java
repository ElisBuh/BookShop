package com.senla.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDTO {

    private Integer id;

    @JsonProperty("book")
    private BookDTO bookDTO;

    private Integer countRequest;
}
