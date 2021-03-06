package com.cg.banking.daoservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.cg.banking.beans.Transaction;

public class TransactionDaoImpl implements TransactionDAO{

	private EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("JPA-PU");
	@Override
	public Transaction save(Transaction transaction) {
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
		entityManager.close();
		return transaction;
	}

	@Override
	public boolean update(Transaction transaction) {
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(transaction);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public Transaction findOne(int transactionId) {
		return entityManagerFactory.createEntityManager().find(Transaction.class, transactionId);
	}

	@Override
	public List<Transaction> findAll(long accountNo) {
		return entityManagerFactory.createEntityManager().createQuery("from Transaction t where ACCOUNT_ACCOUNTNO="+accountNo).getResultList();
	}

}
