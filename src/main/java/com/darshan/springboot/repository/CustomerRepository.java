package com.darshan.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.darshan.springboot.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query(value = "SELECT e FROM Customer e WHERE e.age = :age")
	public List<Customer> findByAge(@Param("age") int age);

}
