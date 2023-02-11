package com.darshan.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darshan.springboot.entity.Customer;
import com.darshan.springboot.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	public Customer getCustomer(Long id) {
	    return repository.findById(id).orElse(null);
	  }
	  
	  public Customer createCustomer(Customer customer) {
	    return repository.save(customer);
	  }
	
	public List<Customer> getCustomers() {
		return repository.findAll();
	}
	
	public List<Customer> getCustomersByAge(int age) {
		return repository.findByAge(age);
	}
	
	public boolean deleteCustomer(Long id) {
		Customer existingCustomer = repository.findById(id).orElse(null);
	    if (existingCustomer == null) {
	      return false;
	    }
	   repository.delete(existingCustomer);
	    return true;
	}
	
	public Customer updateCustomer(Customer cus, Long id) {
		Customer e1=repository.findById(id).get();
		e1.setAge(cus.getAge());
		e1.setName(cus.getName());
		e1.setEmail(cus.getEmail());
		return repository.save(e1);
	}
	
	

}
