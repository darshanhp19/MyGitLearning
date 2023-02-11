package com.darshan.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darshan.springboot.entity.Customer;
import com.darshan.springboot.service.CustomerService;

@RestController
@RequestMapping("/api/customers/")
public class CustomerController {
	
	@Autowired
	private CustomerService service;

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	
	@GetMapping()
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers= service.getCustomers();
    	return ResponseEntity.ok(customers);
    }
	
	 @PostMapping
	  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
	    Customer createdCustomer = service.createCustomer(customer);
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
	  }
	
//	@PostMapping("/employees/addemp")
//	public String addEmployee(@ModelAttribute Customer emp) {
//		service.addEmployee(emp);
//		return "redirect:/employees/list";
//	}
	
//	@GetMapping("/employees")
//	public List<Employee> fetchEmps(){
//		return service.getEmployees();
//	}
	
	  @GetMapping("/{id}")
	  public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
	    Customer customer = service.getCustomer(id);
	    if (customer == null) {
	      return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(customer);
	  }
	  
	  @GetMapping("/byAge/{age}")
	  public ResponseEntity<List<Customer>> getCustomerByAge(@PathVariable int age) {
	    List<Customer> customers = service.getCustomersByAge(age);
	    if (customers == null) {
	      return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(customers);
	  }
	  
	  
	  @PutMapping("/{id}")
	  public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
	    Customer updatedCustomer = service.updateCustomer(customer, id);
	    if (updatedCustomer == null) {
	      return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(updatedCustomer);
	  }
	  
	  @DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
	    if (service.deleteCustomer(id)) {
	      return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.notFound().build();

	  }
	
}
