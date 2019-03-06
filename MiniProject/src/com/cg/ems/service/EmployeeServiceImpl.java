package com.cg.ems.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ems.dao.IEmployeeDao;
import com.cg.ems.entities.Employee;
import com.cg.ems.entities.User;
import com.cg.ems.exception.EmployeeException;


@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	IEmployeeDao dao;

	@Override
	public User isValid(String userId, String password) throws EmployeeException {
		return dao.isValid(userId,password);
	}
	
	@Transactional
	@Override
	public Integer addEmployee(Employee employee) throws EmployeeException {
		return dao.addEmployee(employee);
	}

	@Override
	public String generateLogin(Employee employee) {
		return dao.generateLogin(employee);
	}

	@Override
	public boolean modifyEmp(Employee bean) throws EmployeeException {
		return dao.modifyEmp(bean);
	}

	@Override
	public Employee getDetailsById(Integer employeeId) throws EmployeeException {
		return dao.getDetailsById(employeeId);
	}

	@Override
	public List<Employee> displayAll() throws EmployeeException {
		return dao.displayAll();
	}

	@Override
	public List<Employee> search(Employee bean) throws EmployeeException {
		return dao.search(bean);
	}

	@Override
	public List<String> getGrades() throws EmployeeException {
		return dao.getGrades();
	}

	@Override
	public void getDeleteEmployee(Integer id) throws EmployeeException {
		 dao.getDeleteEmployee(id);
	}

}
