package com.mendel.challenge.unit.impl.service;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    void getTransaction() {
    }

    void getTransactionByType() {
    }

    @Test
    void getTotalAmountTransaction_notFound() {

        when(transactionRepository.getByType(anyString())).thenReturn(Arrays.asList());

        Double totalAmount = transactionService.getTotalAmount(33L);

        assertEquals(totalAmount, 0.0);
    }

    @Test
    void getTotalAmountTransaction_OK() {

        when(transactionRepository.getByRelationship(any())).thenReturn(transactionsMock.getOnlyParents());

        Double totalAmount = transactionService.getTotalAmount(1L);

        assertEquals(3.0, totalAmount);
    }
}
