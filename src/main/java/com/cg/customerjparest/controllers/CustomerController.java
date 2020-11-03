package com.cg.customerjparest.controllers;



import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import com.cg.customerjparest.dto.CreateCustomerRequest;
import com.cg.customerjparest.dto.CustomerDetails;
import com.cg.customerjparest.dto.UpdateCustomerRequest;
import com.cg.customerjparest.entities.Customer;
import com.cg.customerjparest.service.ICustomerService;

@Validated
@RequestMapping("/customer")
@RestController
public class CustomerController {
	
	 @Autowired
	    private ICustomerService service;
	 
	 @ResponseStatus(HttpStatus.CREATED)
	    @PostMapping("/add")
	 public CustomerDetails add(@RequestBody CreateCustomerRequest requestData) {
		 Customer customer=new Customer(requestData.getName());
		 customer=service.add(customer);
		 CustomerDetails details= toDetails(customer);
		return details;
	 }
	 

	    @PutMapping("/update")
	    public CustomerDetails update(@RequestBody UpdateCustomerRequest requestData) {
	    	Customer customer = new Customer(requestData.getName());
	    	customer.setId(requestData.getId());
	    	customer = service.update(customer);
	        CustomerDetails details = toDetails(customer);
	        return details;
	    }

	    @GetMapping
	    public List<CustomerDetails> fetchAll() {
	        List<Customer> customers = service.findAll();
	        // Using stream  --> List<CustomerDetails>response= customers.stream().map(customer->toDetails(customer)).collect(Collectors.toList());
	        List<CustomerDetails> response = toDetails(customers);
	        return response;
	    }
	    
	    public List<CustomerDetails> toDetails(List<Customer> customers) {
	    	    List<CustomerDetails> desired = new ArrayList<>();
	            for (Customer customer : customers) {
	            	CustomerDetails details = toDetails(customer);
	                desired.add(details);
	            }
	            return desired;
		}


	    @GetMapping("/by/name/{name}")
	    public List<CustomerDetails> findStudentByName(@PathVariable("name") String name) {
	        List<Customer> customers = service.findByName(name);
	        List<CustomerDetails> response = toDetails(customers);
	        return response;
	    }

		@GetMapping("/get/{id}")
	    public CustomerDetails fetchCustomer(@PathVariable("id") long id) {
	    	Customer customer = service.findById(id);
	        CustomerDetails details = toDetails(customer);
	        return details;
	    }
	    
		public CustomerDetails toDetails(Customer customer) {
			CustomerDetails details=new CustomerDetails(customer.getId(),customer.getName());
			return details;
		}
}
