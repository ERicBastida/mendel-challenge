package com.mendel.challenge.entity;

import com.mendel.challenge.dto.TransactionDTO;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Transaction {
    private Long id;
    private Double amount;
    private String type;
    private Long parentId;
    public TransactionDTO toDto(){
        return TransactionDTO.builder()
                .id(this.id)
                .amount(this.amount)
                .parentId(this.parentId)
                .type(this.type)
                .build();
    }
    public void fromDto(TransactionDTO transactionDTO){
        this.id = transactionDTO.getId();
        this.amount = transactionDTO.getAmount();
        this.type = transactionDTO.getType();
        this.parentId = transactionDTO.getParentId();
    }
}
