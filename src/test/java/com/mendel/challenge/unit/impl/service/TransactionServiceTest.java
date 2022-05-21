package com.mendel.challenge.unit.impl.service;

import com.mendel.challenge.repository.TransactionRepository;
import com.mendel.challenge.service.TransactionService;
import com.mendel.challenge.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
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
}
