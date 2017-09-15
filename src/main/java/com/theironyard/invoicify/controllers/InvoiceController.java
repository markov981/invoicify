package com.theironyard.invoicify.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.BillingRecord;
import com.theironyard.invoicify.models.Invoice;
import com.theironyard.invoicify.models.InvoiceLineItem;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;
import com.theironyard.invoicify.repositories.InvoiceRepository;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private BillingRecordRepository recordRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;


	@GetMapping("")
	public ModelAndView list(Authentication auth) {
		User user = (User) auth.getPrincipal();
		ModelAndView mv = new ModelAndView("invoices/list");
		mv.addObject("user", user);
		mv.addObject("invoices", invoiceRepository.findAll());
		return mv;
	}
	
	@GetMapping("/new")
	public ModelAndView selectClient() {
		ModelAndView mv = new ModelAndView("invoices/step-1");
		mv.addObject("companies", companyRepository.findAll());
		return mv;
	}
	
	
	@PostMapping("new")
	public ModelAndView step2(long clientId) {
		ModelAndView mv = new ModelAndView("invoices/step-2");
		mv.addObject("clientId", clientId);
		mv.addObject("records", recordRepository.findByClientId(clientId)); 
		return mv;
	}

	@PostMapping("create")
	public String createInvoice(Invoice invoice, long clientId, long[] recordIds, Authentication auth) {
		
		User user = (User) auth.getPrincipal();
		List<BillingRecord> records = recordRepository.findByIdIn(recordIds);
		long nowA = Calendar.getInstance().getTimeInMillis();
		Date now  = new Date(nowA);
		
		List<InvoiceLineItem> items = new ArrayList<InvoiceLineItem>();
		for(BillingRecord record : records) {
			InvoiceLineItem lineItem = new InvoiceLineItem();
				lineItem.setBillingRecord(record);
				lineItem.setCreatedBy(user);				
				lineItem.setCreatedOn(now);
				lineItem.setInvoice(invoice);
				items.add(lineItem);				
		}
		invoice.setLineItem(items);
		invoice.setCreatedOn(now);
		invoice.setCreatedBy(user);   
		invoice.setCompany(companyRepository.findOne(clientId));
		invoiceRepository.save(invoice);
		
		return "redirect:/invoices";
	}
}

















