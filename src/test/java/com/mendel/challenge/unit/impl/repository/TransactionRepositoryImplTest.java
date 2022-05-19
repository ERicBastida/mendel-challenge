package com.mendel.challenge.unit.impl.repository;

import com.mendel.challenge.common.TransactionTypes;
import com.mendel.challenge.entity.Transaction;
import com.mendel.challenge.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TransactionRepositoryImplTest {

    @Autowired
    private TransactionRepository transactionRepository;

    private void prepareMocks() {
        transactionRepository.save(
                Transaction.builder()
                        .id(1L)
                        .amount(1.0)
                        .type(TransactionTypes.CARS.getName())
                        .build()
        );
        transactionRepository.save(
                Transaction.builder()
                        .id(2L)
                        .amount(2.0)
                        .type(TransactionTypes.GROCERIES.getName())
                        .parentId(1L)
                        .build()
        );
        transactionRepository.save(
                Transaction.builder()
                        .id(3L)
                        .amount(3.0)
                        .type(TransactionTypes.CARS.getName())
                        .build()
        );
        transactionRepository.save(
                Transaction.builder()
                        .id(4L)
                        .amount(4.0)
                        .type(TransactionTypes.SHOPPING.getName())
                        .build()
        );
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        prepareMocks();
    }

    @Test
    void getTransactionByType() {

        List<Transaction> carsTransactions = transactionRepository.getByType(TransactionTypes.CARS.getName());

        assertEquals(2, carsTransactions.size());
    }

    @Test
    void getTransactionByRelationship() {

        List<Transaction> carsTransactions = transactionRepository.getByRelationship(1L);

        assertEquals(2, carsTransactions.size());
    }
}
