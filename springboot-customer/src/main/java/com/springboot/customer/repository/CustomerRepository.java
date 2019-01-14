package com.springboot.customer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springboot.customer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	List<Customer> findByAge(int age);
}
