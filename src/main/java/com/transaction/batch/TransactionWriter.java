package com.transaction.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.transaction.entity.Transactions;
import com.transaction.repository.TransactionRepository;

public class TransactionWriter implements ItemWriter<Transactions> {

	@Autowired
	private TransactionRepository txnRepo;

	@Override
	public void write(List<? extends Transactions> items) throws Exception {

		for (Transactions item : items) {
			txnRepo.save(item);
		}
	}

}
