package com.mendel.challenge.repository;

import com.mendel.challenge.dto.TransactionDTO;
import com.mendel.challenge.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    TransactionDTO get(Long id);

    Boolean save(TransactionDTO transaction);

    List<Transaction> getByType(String type);

    List<TransactionDTO> getByRelationship(Long id);

    Transaction getByParentId(Long id);

}
