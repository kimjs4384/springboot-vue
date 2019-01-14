package com.springboot.customer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.customer.model.Customer;
import com.springboot.customer.repository.CustomerRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping
	public Customer postCustomer(@RequestBody Customer customer) {
		Customer addedCustomer = customerRepository.save(new Customer(customer.getName(), customer.getAge()));
		return addedCustomer;
	}
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		System.out.println("Get all Customer ... ");
		
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add); // ???
		
		return customers;
	}
	
	@GetMapping("/age/{age}")
	public List<Customer> findByAge(@PathVariable("age") int age) {
		List<Customer> customers = customerRepository.findByAge(age);
		return customers;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id,
			@RequestBody Customer customer) {
		System.out.println("Update Customer with ID = " + id + " ...");
		Optional<Customer> customerData = customerRepository.findById(id);
		
		if (customerData.isPresent()) {
			Customer oldCustomer = customerData.get();
			oldCustomer.setName(customer.getName());
			oldCustomer.setAge(customer.getAge());
			oldCustomer.setActive(customer.isActive()); // ???
			return new ResponseEntity<>(customerRepository.save(oldCustomer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	} 

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
		System.out.println("Delete Customer with ID = " + id + " ...");
		customerRepository.deleteById(id);
		return new ResponseEntity<>("Customer has been deleted!!", HttpStatus.OK);
	}
	
}
