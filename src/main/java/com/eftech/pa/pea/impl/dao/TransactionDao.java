package com.eftech.pa.pea.impl.dao;

import com.eftech.pa.pea.impl.persistent.Criteria;
import com.eftech.pa.pea.impl.persistent.Transaction;

import java.util.List;

public interface TransactionDao extends GenericDao<Transaction, String>{

    /**
     * Get all transactions that belong to given criteria
     * @param criteria criteria that the transactions satisfy
     * @return a list of transactions that satisfy given criteria
     */
    List<Transaction> getTransactionsByCriteria(Criteria criteria);

}
