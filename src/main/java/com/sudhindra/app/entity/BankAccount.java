/**
 * 
 */
package com.sudhindra.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sudhindra
 *
 */
@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount {
	private long id;
	private String fullName;
	private Double balance;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Column(name = "full_name", length = 128, nullable = false)
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param name the name to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the balance
	 */
	@Column(name = "balance", nullable = false)
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("BankAccount [id=%s, name=%s, balance=%s]", id, fullName, balance);
	}
}
