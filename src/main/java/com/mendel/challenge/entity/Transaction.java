package com.mendel.challenge.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Transaction {
    private Long id;
    private Double amount;
    private String type;
    private Long parentId;
}
