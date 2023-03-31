package com.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.model.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	/*
	 * jpa is sensitive to "findBy" part and takes the where condition from 
	 * the remaider part of the method name(email in this case). 
	 * 
	 * that is called derived method name query
	 */
	List<Customer> findByEmail(String email);

	List<Customer> findByName(String username);

}