package com.mendel.challenge.util;

import com.mendel.challenge.common.TransactionTypes;
import com.mendel.challenge.dto.TransactionDTO;
import com.mendel.challenge.entity.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class TransactionsMock {
    private Transaction ONLY_ONE = Transaction.builder()
            .id(1L)
            .amount(1.0)
            .type(TransactionTypes.CARS.getName())
            .build();
    private List<Transaction> COMMON_EXAMPLE = Arrays.asList(
            ONLY_ONE,
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

    private List<Transaction> ONLY_PARENT = Arrays.asList(
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
                    .build()
    );

    private List<Transaction> ONLY_2_CARS = Arrays.asList(
            Transaction.builder()
                    .id(1L)
                    .amount(1.0)
                    .type(TransactionTypes.CARS.getName())
                    .build(),
            Transaction.builder()
                    .id(2L)
                    .amount(2.0)
                    .type(TransactionTypes.CARS.getName())
                    .parentId(1L)
                    .build()
    );


    public List<Transaction> getCommonExample() {
        return COMMON_EXAMPLE;
    }

    public List<TransactionDTO> getOnlyParents() {
        return ONLY_PARENT.stream().map(Transaction::toDto).collect(Collectors.toList());
    }

    public TransactionDTO getOnlyOne() {
        return ONLY_ONE.toDto();
    }

    public List<Transaction> getOnly2CarsTransactions() {
        return ONLY_2_CARS;
    }

}
