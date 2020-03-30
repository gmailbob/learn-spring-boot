package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public List<Employee> findAll() {
		// get current Hibernate session
		Session session = entityManager.unwrap(Session.class);

		// create a query, with native Hibernate API
		Query<Employee> query = session.createQuery("from Employee", Employee.class);

		// execute query and get result list
		List<Employee> employees = query.getResultList();

		return employees;
	}

	@Override
	public Employee findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Employee employee = session.get(Employee.class, id);
		return employee;
	}

	@Override
	public void save(Employee employee) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(employee);// if id=0 || undefined do the insert, else update
	}

	@Override
	public void deleteById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", theId);
		query.executeUpdate();
	}

}
