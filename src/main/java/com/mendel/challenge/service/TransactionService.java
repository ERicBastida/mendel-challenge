package com.mendel.challenge.service;

import com.mendel.challenge.dto.TransactionSumDTO;
import com.mendel.challenge.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO get(Long id);
    List<Long> getByType(String type);
    TransactionSumDTO getTotalAmount(Long id);
    TransactionDTO setTransaction(TransactionDTO transaction);
}
