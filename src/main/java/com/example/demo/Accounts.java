package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Accounts {
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getLoan() {
		return loan;
	}

	public void setLoan(String loan) {
		this.loan = loan;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Id
	private  String loan;
	private    int amount;
	private    String user;
}
