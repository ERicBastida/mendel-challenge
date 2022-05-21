package com.mendel.challenge.service;

import com.mendel.challenge.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction get(Long id);
    List<Long> getByType(String type);
    Double getTotalAmount(Long id);
}
