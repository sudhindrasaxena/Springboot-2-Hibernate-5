/**
 * 
 */
package com.sudhindra.app.model;

/**
 * @author Sudhindra
 *
 */
public class BankAccountInfo {
	private Long id;
	private String fullName;
	private double balance;

	/**
	 * 
	 */
	public BankAccountInfo() {
	}

	/**
	 * @param id
	 * @param fullName
	 * @param balance
	 */
	public BankAccountInfo(Long id, String fullName, double balance) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.balance = balance;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("BankAccountInfo [id=%s, fullName=%s, balance=%s]", id, fullName, balance);
	}
}
