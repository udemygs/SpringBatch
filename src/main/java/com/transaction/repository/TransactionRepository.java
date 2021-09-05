package com.transaction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.transaction.entity.Transactions;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transactions, String> {

	Page<Transactions> findByCustomerIdOrAccountNumberOrDescription(String customerId, String accountNumber,
			String description, Pageable pageable);
}
