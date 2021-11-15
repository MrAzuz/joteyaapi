package com.joteya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joteya.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Account findOneByUsername(String username);
	

}
