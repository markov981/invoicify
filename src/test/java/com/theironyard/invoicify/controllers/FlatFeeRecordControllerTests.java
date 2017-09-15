package com.theironyard.invoicify.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.BillingRecord;
import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.models.FlatFeeBillingRecord;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;

public class FlatFeeRecordControllerTests {
	
	private FlatFeeBillingRecordController controller;
	private BillingRecordRepository repo;
	private CompanyRepository companyRepository;
	
	@Before
	public void setup() {
		repo              	= mock(BillingRecordRepository.class);
		
		//auth              	= mock(BillingRecordRepository.class);
		companyRepository 	= mock(CompanyRepository.class);
		
		
		controller 			= new FlatFeeBillingRecordController(repo, companyRepository);
		
		
		Authentication auth;
		//User user = (User) auth.getPrincipal();
	}

//	@Test
//	public void test_list() {
//		List<FlatFeeBillingRecord> records = new ArrayList<FlatFeeBillingRecord>();
//		when(repo.findAll()).thenReturn(records);
//		
//		ModelAndView actual = controller.create(record, clientId, auth);
//		
//		verify(repo).findAll();
//		assertThat(actual.getViewName()).isEqualTo("billing-records");
//		assertThat(actual.getModel().get("records")).isSameAs(records);
//	}
}
//public ModelAndView create(FlatFeeBillingRecord record, long clientId, Authentication auth) {
//	User user = (User) auth.getPrincipal();
//	Company client = companyRepository.findOne(clientId);
//	record.setClient(client);
//	record.setCreatedBy(user);
//	recordRepository.save(record);
//	
//	return new ModelAndView ("redirect:/billing-records");
