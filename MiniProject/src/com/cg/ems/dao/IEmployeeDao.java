package com.cg.ems.dao;

import java.util.List;

import com.cg.ems.entities.Employee;
import com.cg.ems.entities.User;
import com.cg.ems.exception.EmployeeException;

public interface IEmployeeDao {

	User isValid(String userId, String password) throws EmployeeException;

	Integer addEmployee(Employee employee) throws EmployeeException;

	String generateLogin(Employee employee);


	boolean modifyEmp(Employee bean) throws EmployeeException;

	Employee getDetailsById(Integer employeeId) throws EmployeeException;

	List<Employee> displayAll() throws EmployeeException;

	List<Employee> search(Employee bean) throws EmployeeException;

	List<String> getGrades() throws EmployeeException;

	void getDeleteEmployee(Integer id) throws EmployeeException;

}
