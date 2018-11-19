/**
 * 
 */
package com.sudhindra.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sudhindra.app.entity.BankAccount;
import com.sudhindra.app.model.BankAccountInfo;

/**
 * @author Sudhindra
 *
 */
@Repository
@Transactional
public class BankAccountDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public BankAccountDAO() {
	}

	public BankAccount findById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(BankAccount.class, id);
	}

	public List<BankAccountInfo> listBankAccountInfo() {
		String sql = "Select new " + BankAccountInfo.class.getName() + "(e.id,e.fullName,e.balance) " + " from "
				+ BankAccount.class.getName() + " e ";

		Session session = this.sessionFactory.getCurrentSession();
		Query<BankAccountInfo> query = session.createQuery(sql, BankAccountInfo.class);
		
		return query.getResultList();
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void addAmount(Long id, double amount) throws Exception {
		BankAccount bankAccount = this.findById(id);
		if (bankAccount == null) {
			throw new Exception("Account not found " + id);
		}
		double newBalance = bankAccount.getBalance() + amount;
		if (bankAccount.getBalance() + amount < 0) {
			throw new Exception(
					"The money in the account '" + id + "' is not enough (" + bankAccount.getBalance() + ")");
		}
		bankAccount.setBalance(newBalance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws Exception {
 
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }
}
