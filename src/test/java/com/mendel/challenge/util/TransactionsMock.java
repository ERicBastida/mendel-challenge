package com.mendel.challenge.util;

import com.mendel.challenge.common.TransactionTypes;
import com.mendel.challenge.entity.Transaction;

import java.util.Arrays;
import java.util.List;

public final class TransactionsMock {
    private List<Transaction> COMMON_EXAMPLE = Arrays.asList(
            Transaction.builder()
                .id(1L)
                .amount(1.0)
                .type(TransactionTypes.CARS.getName())
                .build(),
            Transaction.builder()
                .id(2L)
                .amount(2.0)
                .type(TransactionTypes.GROCERIES.getName())
                .parentId(1L)
                .build(),
            Transaction.builder()
                .id(3L)
                .amount(3.0)
                .type(TransactionTypes.CARS.getName())
                .build(),
            Transaction.builder()
                .id(4L)
                .amount(4.0)
                .type(TransactionTypes.SHOPPING.getName())
                .build()
    );


    public List<Transaction> getCommonExample(){
        return COMMON_EXAMPLE;
    }

}
