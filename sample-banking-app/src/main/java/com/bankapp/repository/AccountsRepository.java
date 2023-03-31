package com.bankapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.model.Account;



@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
	
	//Accounts findByCustomerId(int customerId);
	List<Account> findByCustomerId(int customerId);

}