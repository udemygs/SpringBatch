package com.transaction.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.batch.TransactionBO;
import com.transaction.entity.Transactions;
import com.transaction.repository.TransactionRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@RequestMapping("/v1")
public class TransactionController {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionRepository txnRepo;

	@GetMapping("/transactions/all")
	@ApiOperation(value = "Retrieve transaction", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public List<TransactionBO> retrieveTransactions() {
		List<Transactions> txnList = new ArrayList<>();
		txnRepo.findAll().forEach(list -> txnList.add(list));
		return txnList.stream().map(Transactions::toTxnBO).collect(Collectors.toList());
	}

	@GetMapping("/transactions")
	@ApiOperation(value = "Seach transaction by customer ID or account number(s) or description ", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public Page<Transactions> search(@RequestParam(name = "custId", required = false) String customerId,
			@RequestParam(name = "accNo", required = false) String accountNumber,
			@RequestParam(name = "desc", required = false) String description, Pageable pageable) {
		LOG.info("customerId [{}], accountNumber [{}], description [{}] ", customerId, accountNumber, description);

		return txnRepo.findByCustomerIdOrAccountNumberOrDescription(customerId, accountNumber, description, pageable);

	}

}
