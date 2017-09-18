package com.theironyard.invoicify.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.BillingRecord;
import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.models.Invoice;
import com.theironyard.invoicify.models.InvoiceLineItem;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;
import com.theironyard.invoicify.repositories.InvoiceRepository;


@Controller
@RequestMapping("/admin/companies")
public class CompanyController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	
	
	@GetMapping("")
	public ModelAndView list() {		
		ModelAndView mv = new ModelAndView("admin/companies");		
		mv.addObject("companies", companyRepository.findAll(new Sort("name")));		
		return mv;
	}
	
	@PostMapping("")
	public String createInvoice(String name) {		
		Company company = new Company();		
		company.setName(name);		
		companyRepository.save(company);
		
		return "redirect:/admin/companies";
	}	
}















