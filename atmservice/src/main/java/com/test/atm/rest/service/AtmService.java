package com.test.atm.rest.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.test.atm.rest.database.Database;
import com.test.atm.rest.exceptions.ArgumentException;
import com.test.atm.rest.exceptions.DataNotFoundException;
import com.test.atm.rest.exceptions.InsufficientBalanceException;
import com.test.atm.rest.exceptions.ResourceCreationException;
import com.test.atm.rest.model.CustomerAccount;

public class AtmService {
	private Map<UUID,CustomerAccount> data = Database.getData();
	private Map<UUID, Lock> writeMap = new HashMap<>();
	Lock mainLock = new ReentrantLock();
	
	public CustomerAccount createAccount(String customerName){
		CustomerAccount customerAccount = new CustomerAccount(customerName);
		customerAccount.setId(UUID.randomUUID());
		customerAccount.setBalance(new Double(0));
		customerAccount.setName(customerName);
		data.put(customerAccount.getId(), customerAccount);
		if(customerAccount.getId() == null){
			throw new ResourceCreationException("Account not created, please try again");
		}
		return customerAccount;
	}
	
	public CustomerAccount withdrawAmount(UUID id, Double withDrawAmount) {
		if(withDrawAmount<0){
			throw new ArgumentException("Withdraw amount should be greater than zero");
		}
		mainLock.lock();
		Lock lock = writeMap.getOrDefault(id, new ReentrantLock());
		writeMap.put(id, lock);
		mainLock.unlock();
		
		lock.lock();
		if(data.get(id) == null){
			throw new DataNotFoundException("Given account id is not correct"+" "+id);
		}
		CustomerAccount account = data.get(id);
		if (account.getBalance() < withDrawAmount) {
			lock.unlock();
			throw new InsufficientBalanceException("Insufficient balance, withdraw limit is"+" "+account.getBalance());
		} else {
			account.setBalance(account.getBalance()-withDrawAmount);
		}
		lock.unlock();
		return account;
	}

	public CustomerAccount depositAmount(UUID id, Double money) {
		if(money<0){
			throw new ArgumentException("Deposit amount should be greater than zero");
		}
		mainLock.lock();
		Lock lock = writeMap.getOrDefault(id, new ReentrantLock());
		writeMap.put(id, lock);
		mainLock.unlock();
		lock.lock();
		if(data.get(id) == null){
			throw new DataNotFoundException("Given account id is not correct"+" "+id);
		}
		CustomerAccount account = data.get(id);
		account.setBalance(account.getBalance()+money);
		lock.unlock();	
		return account;
	}
	
	public CustomerAccount displayBalance(UUID id){
		if(data.get(id) == null){
			throw new DataNotFoundException("Account with given Id" +id +" "+"not found");
		}
		CustomerAccount customerAccount = data.get(id);
		return customerAccount;
	}
	
	public int getAll(){
		return data.size();
	}
	
}
