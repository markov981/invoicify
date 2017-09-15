package com.theironyard.invoicify.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theironyard.invoicify.models.BillingRecord;
import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.models.InvoiceLineItem;

public interface BillingRecordRepository extends JpaRepository<BillingRecord, Long> {
	
	List<BillingRecord> findByClientId(long clientId);
	//List<BillingRecord> findByClientIdAndIdNotIn(long clientId, long[] recordIds);
	List<BillingRecord> findByIdIn(long[] recordIds);
}
