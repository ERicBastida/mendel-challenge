package com.mendel.challenge.unit.impl.service;

import com.mendel.challenge.common.TransactionTypes;
import com.mendel.challenge.repository.TransactionRepository;
import com.mendel.challenge.repository.impl.TransactionRepositoryImpl;
import com.mendel.challenge.service.TransactionService;
import com.mendel.challenge.service.impl.TransactionServiceImpl;
import com.mendel.challenge.util.TransactionsMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TransactionServiceTest {


    private static TransactionsMock transactionsMock = new TransactionsMock();
    private TransactionRepository transactionRepository = mock(TransactionRepositoryImpl.class);
    private TransactionService transactionService = new TransactionServiceImpl(transactionRepository);

    private final Long TRANSACTION_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTransaction_OK() {
        when(transactionRepository.get(any())).thenReturn(transactionsMock.getOnlyOne());

        var result = transactionService.get(TRANSACTION_ID);

        assertEquals(result, transactionsMock.getOnlyOne());
    }

    @Test
    void getTransaction_NotFound() {
        when(transactionRepository.get(any())).thenReturn(null);

        var result = transactionService.get(TRANSACTION_ID);

        assertEquals(result, null);
    }

    @Test
    void getTransactionByType_NotFound() {

        when(transactionRepository.getByType(any())).thenReturn(Arrays.asList());

        var result = transactionService.getByType(null);

        assertEquals(result.size(), 0);
    }

    @Test
    void getTransactionByType_OK() {

        when(transactionRepository.getByType(any())).thenReturn(transactionsMock.getOnly2CarsTransactions());

        var result = transactionService.getByType(TransactionTypes.CARS.getName());

        assertEquals(2, result.size());
    }

    @Test
    void getTransactionByType_nullID() {

        when(transactionRepository.getByType(any())).thenReturn(Arrays.asList());

        var result = transactionService.getByType(null);

        assertEquals(0, result.size());
    }

    @Test
    void getTotalAmountTransaction_notFound() {

        when(transactionRepository.getByRelationship(any())).thenReturn(Arrays.asList());

        var totalAmount = transactionService.getTotalAmount(33L);

        assertEquals(totalAmount, null);
    }

    @Test
    void getTotalAmountTransaction_OK() {

        when(transactionRepository.getByRelationship(any())).thenReturn(transactionsMock.getOnlyParents());

        var totalAmount = transactionService.getTotalAmount(1L);

        assertEquals(3.0, totalAmount.getSum());
    }

    @Test
    void getTotalAmountTransaction_OnlyOneOK() {

        when(transactionRepository.getByRelationship(any())).thenReturn(Arrays.asList(transactionsMock.getOnlyOne()));

        var totalAmount = transactionService.getTotalAmount(1L);

        assertEquals(transactionsMock.getOnlyOne().getAmount(), totalAmount.getSum());
    }

    @Test
    void getTotalAmountTransaction_nullFromRepository() {

        when(transactionRepository.getByType(anyString())).thenReturn(null);

        var totalAmount = transactionService.getTotalAmount(33L);

        assertEquals(totalAmount, null);
    }
}
