package com.test.atm.rest.database;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.test.atm.rest.model.CustomerAccount;

public class Database {
	static Map<UUID,CustomerAccount> data = new HashMap<>();
	
	public static Map<UUID,CustomerAccount> getData(){
		return data;
	}
	
}
