package com.ge.exercise3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Bank {

    private static final Logger logger = LogManager.getLogger(Bank.class);
    private Map<String, Account> accountMap;

    public Bank() {
        accountMap = new HashMap<>();
    }

    public double getCurrentHoldings()
    {
    	double sum=0;
    	for(Map.Entry<String, Account> holdings : accountMap.entrySet())
    	{
    		sum += holdings.getValue().getBalance();
    	}
    	return sum;
    }
    
    public Account getAccount(String accountNumber) {
        return accountMap.get(accountNumber);
    }

    public void addAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public void depositToAccount(String accountNumber, float amount) {
        getAccount(accountNumber).deposit(amount);
    }

    public void withdrawFromAccount(String accountNumber, float amount) {
        Account account = getAccount(accountNumber);
        if((account.getAccountType().equalsIgnoreCase("Checking") && !(amount > 100)) ||
        		(account.getAccountType().equalsIgnoreCase("Savings") && !(amount > account.getBalance())) )
        	account.withdraw(amount);
    }

    public int numAccounts() {
        return accountMap.size();
    }
}
