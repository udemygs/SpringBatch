package com.transaction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.transaction.batch.TransactionBO;

@Entity
@Table(name = "TRANSACTIONS")
public class Transactions implements Serializable {

	private static final long serialVersionUID = 8255456332916611119L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private long id;

	@Column(name = "ACCOUNTNUMBER")
	private String accountNumber;

	@Column(name = "TRXAMOUNT")
	private BigDecimal trxAmount;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CUSTOMERID")
	private String customerId;

	@Column(name = "TRXDATETIME")
	private LocalDateTime trxDateTime;

	public Transactions() {
		super();
	}

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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getTrxDateTime() {
		return trxDateTime;
	}

	public void setTrxDateTime(LocalDateTime trxDateTime) {
		this.trxDateTime = trxDateTime;
	}

	public TransactionBO toTxnBO() {
		TransactionBO bo = new TransactionBO();
		bo.setId(this.getId());
		bo.setAccountNumber(this.getAccountNumber());
		bo.setTrxAmount(this.getTrxAmount());
		bo.setDescription(this.getDescription());
		bo.setCustomerId(this.getCustomerId());
		bo.setTrxDateTime(this.getTrxDateTime());
		return bo;
	}

	@Override
	public String toString() {
		return "Transactions [id=" + id + ", accountNumber=" + accountNumber + ", trxAmount=" + trxAmount
				+ ", description=" + description + ", customerId=" + customerId + ", trxDateTime=" + trxDateTime + "]";
	}
}
