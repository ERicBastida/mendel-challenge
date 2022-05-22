package com.mendel.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {
    @JsonInclude(Include.NON_NULL)
    private Long id;
    private Double amount;
    private String type;
    @JsonInclude(Include.NON_NULL)
    private Long parentId;
}
