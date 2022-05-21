package com.mendel.challenge.unit.impl.repository;

import com.mendel.challenge.common.TransactionTypes;
import com.mendel.challenge.entity.Transaction;
import com.mendel.challenge.repository.impl.TransactionRepositoryImpl;
import com.mendel.challenge.util.TransactionsMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TransactionRepositoryImplTest {

    private TransactionRepositoryImpl transactionRepository;
    private static TransactionsMock transactionsMock = new TransactionsMock();
    private void prepareMocks() {
        this.transactionRepository = new TransactionRepositoryImpl();
        transactionsMock.getCommonExample().forEach(transaction -> transactionRepository.save(transaction));
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        prepareMocks();
    }

    @Test
    void getTransactionByType_OK() {

        List<Transaction> carsTransactions = transactionRepository.getByType(TransactionTypes.CARS.getName());

        assertEquals(2, carsTransactions.size());
    }

    @Test
    void getTransactionByType_NotFound() {

        List<Transaction> transactions = transactionRepository.getByType("Testing");

        assertEquals(0, transactions.size());
    }

    @Test
    void getTransactionByRelationship_OK() {

        List<Transaction> transactions = transactionRepository.getByRelationship(1L);

        assertEquals(2, transactions.size());
    }

    @Test
    void getTransactionByRelationship_NotFound() {

        List<Transaction> transactions = transactionRepository.getByRelationship(33L);

        assertEquals(0, transactions.size());
    }

    @Test
    void getTransactionByRelationship_OnlyOne() {

        List<Transaction> transactions = transactionRepository.getByRelationship(4L);

        assertEquals(1, transactions.size());
    }
}
