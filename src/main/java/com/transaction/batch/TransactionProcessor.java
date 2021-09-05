package com.transaction.batch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.batch.item.ItemProcessor;

import com.transaction.entity.Transactions;

public class TransactionProcessor implements ItemProcessor<TransactionBO, Transactions> {

	@Override
	public Transactions process(TransactionBO bo) throws Exception {
		Transactions entity = new Transactions();
		entity.setAccountNumber(bo.getAccountNumber());
		entity.setTrxAmount(bo.getTrxAmount());
		entity.setDescription(bo.getDescription());
		entity.setCustomerId(bo.getCustomerId());
		entity.setTrxDateTime(parseDateTime(bo.getTrxDate(), bo.getTrxTime()));
		return entity;
	}

	private LocalDateTime parseDateTime(String inputDate, String inputTime) {
		String strDateTime = inputDate + " " + inputTime;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		LocalDateTime dateTime = LocalDateTime.parse(strDateTime, formatter);
		return dateTime;
	}

}
