package com.mendel.challenge.repository;

import com.mendel.challenge.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    Transaction get(Long id);

    void save(Transaction transaction);

    List<Transaction> getByType(String type);

    List<Transaction> getByRelationship(Long id);

    Transaction getByParentId(Long id);

}
