package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	// fields for entity manager
	private EntityManager entityManager;

	// set up constructor injection, which is one type of injections
	// entityManager will be auto created by spring boot
	// for newer spring boot, if only one constructor is provided, no need to write
	// the following annotation
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	// handles transaction management so no need to start and commit transaction
	@Transactional
	public List<Employee> findAll() {
		// get current Hibernate session
		Session session = entityManager.unwrap(Session.class);

		// create a query, with native Hibernate API
		Query<Employee> query = session.createQuery("from Employee", Employee.class);

		// execute query and get result list
		List<Employee> employees = query.getResultList();

		return employees;
	}

}
