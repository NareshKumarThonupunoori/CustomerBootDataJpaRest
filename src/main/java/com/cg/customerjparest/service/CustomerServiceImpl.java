package com.cg.customerjparest.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.customerjparest.dao.*;
import com.cg.customerjparest.entities.Customer;
import com.cg.customerjparest.exceptions.CustomerAlreadyExistsException;
import com.cg.customerjparest.exceptions.CustomerNotFoundException;
import com.cg.customerjparest.util.ValidationUtil;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

@Transactional
@Service
public class CustomerServiceImpl implements ICustomerService {
    private static final Logger Log= LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private ICustomerDao dao;


    @Override
    public Customer add(Customer customer) {
        ValidationUtil.checkArgumentNotNull(customer);
        ValidationUtil.checkName(customer.getName());
        
        customer = dao.save(customer);
        return customer;
    }


	@Override
	public Customer update(Customer customer){
		ValidationUtil.checkArgumentNotNull(customer);
        ValidationUtil.checkName(customer.getName());
        
		customer=dao.save(customer);
        return customer;
	}
		
	@Override
	  public List<Customer> findAll() {
	        List<Customer> list = dao.findAll();
	        return list;
	    }
	
	@Override
	public	List<Customer> findByName(String name){
		List<Customer> list = dao.findByName(name);
        return list;
	}
	@Override
	public Customer findById(long id) {
		Optional<Customer> optional = dao.findById(id);
        if(!optional.isPresent()){
            throw new CustomerNotFoundException("customer not found for id="+id);
        }
        Customer customer=optional.get();
        
		return customer;
	}



}
