package com.cg.customerjparest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.customerjparest.entities.Customer;

public interface ICustomerDao  extends JpaRepository<Customer, Long> {
	
	 List<Customer> findByName(String name);
}
