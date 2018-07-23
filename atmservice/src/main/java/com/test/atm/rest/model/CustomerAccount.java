package com.test.atm.rest.model;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerAccount {
    private UUID id;
    private String name;
    private Double balance;
    
    public CustomerAccount(){
    	
    }
    
	public CustomerAccount(String name) {
		super();
		this.name = name;
	}

	public CustomerAccount(String name, Double balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	public CustomerAccount(UUID id, String name, Double balance) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
    
	
}
