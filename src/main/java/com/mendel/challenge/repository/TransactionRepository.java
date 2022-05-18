package com.mendel.challenge.repository;

import com.mendel.challenge.entity.Transaction;

public interface TransactionRepository {
    Transaction get(Long id);
    void save(Transaction transaction);
}
