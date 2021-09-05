package com.transaction.batch;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;

@ApiModel
public class TransactionBO implements Serializable {

	private static final long serialVersionUID = -7065883568373385579L;

	private long id;

	private String accountNumber;

	private BigDecimal trxAmount;

	private String description;

	@JsonInclude(Include.NON_NULL)
	private String trxDate;

	@JsonInclude(Include.NON_NULL)
	private String trxTime;

	private String customerId;

	private LocalDateTime trxDateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getTrxAmount() {
		return trxAmount;
	}

	public void setTrxAmount(BigDecimal trxAmount) {
		this.trxAmount = trxAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}

	public String getTrxTime() {
		return trxTime;
	}

	public void setTrxTime(String trxTime) {
		this.trxTime = trxTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getTrxDateTime() {
		return trxDateTime;
	}

	public void setTrxDateTime(LocalDateTime localDateTime) {
		this.trxDateTime = localDateTime;
	}

}
