package com.mendel.challenge.service;

import com.mendel.challenge.entity.Transaction;

public interface TransactionService {
    Transaction get(Long id);
    Transaction getByType(String type);
    Double getTotalAmount(Long id);
}
