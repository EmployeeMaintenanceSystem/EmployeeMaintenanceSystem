package com.cg.ems.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.ems.entities.Employee;
import com.cg.ems.entities.GradeMaster;
import com.cg.ems.entities.User;
import com.cg.ems.exception.EmployeeException;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

	@PersistenceContext
	EntityManager entity;

	@Override
	public User isValid(String userId, String password) throws EmployeeException {
		try {
			TypedQuery<User> query = entity.createQuery(
					"SELECT user FROM User user WHERE user.userId=:id AND user.password=:pass", User.class);
			query.setParameter("id", userId);
			query.setParameter("pass", password);
			User bean = query.getSingleResult();
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeException("DOES NOT MATCH WITH THE DATABASE");
		}
	}

	@Override
	public Integer addEmployee(Employee employeebean) throws EmployeeException {
		try {
			entity.persist(employeebean);
			entity.flush();
			return employeebean.getEmployeeId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeException("Unable to Add");
		}
	}

	@Override
	public String generateLogin(Employee employee) {
		User user = new User();
		user.setUserId(employee.getEmployeeId().toString());
		user.setUserName(employee.getFirstName());
		user.setPassword(employee.getFirstName() + "123");
		user.setUserType("employee");
		entity.persist(user);
		return user.getPassword();
	}

	@Override
	public boolean modifyEmp(Employee bean) throws EmployeeException {
		try {
			System.out.println(bean);
			String sql = "UPDATE Employee SET firstName=:firstName,lastName=:lastName,dateOfBirth=:dateOfBirth,dateOfJoining=:dateOfJoining,departmentId=:departmentId,grade=:grade,designation=:designation,salary=:salary,gender=:gender,maritalStatus=:maritalStatus,address=:address,phoneNumber=:phoneNumber WHERE employeeId=:employeeId";
			Query query = entity.createQuery(sql);
			query.setParameter("employeeId", bean.getEmployeeId());
			query.setParameter("firstName", bean.getFirstName());
			query.setParameter("lastName", bean.getLastName());
			query.setParameter("dateOfBirth", bean.getDateOfBirth());
			query.setParameter("dateOfJoining", bean.getDateOfJoining());
			query.setParameter("departmentId", bean.getDepartmentId());
			query.setParameter("grade", bean.getGrade());
			query.setParameter("designation", bean.getDesignation());
			query.setParameter("salary", bean.getSalary());
			query.setParameter("gender", bean.getGender());
			query.setParameter("maritalStatus", bean.getMaritalStatus());
			query.setParameter("address", bean.getAddress());
			query.setParameter("phoneNumber", bean.getPhoneNumber());
			int status = query.executeUpdate();
			if (status > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeException("Unable to modify..");
		}

	}

	@Override
	public Employee getDetailsById(Integer employeeId) throws EmployeeException {
		try {
			return entity.find(Employee.class, employeeId);
		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public List<Employee> displayAll() throws EmployeeException {
		try {
			String sql = "SELECT bean FROM Employee bean";
			TypedQuery<Employee> query = entity.createQuery(sql, Employee.class);
			List<Employee> list = query.getResultList();
			return list;
		} catch (Exception e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public List<Employee> search(Employee bean) throws EmployeeException {

		try {
			String sql = "SELECT bean FROM Employee bean";

			TypedQuery<Employee> query = entity.createQuery(sql, Employee.class);
			List<Employee> list = query.getResultList();
			List<Employee> l = new ArrayList<Employee>();

			for (Employee e : list) {
				if (e.getEmployeeId().equals(bean.getEmployeeId()) || e.getFirstName().equals(bean.getFirstName())
						|| e.getLastName().equals(bean.getLastName()) || e.getGrade().equals(bean.getGrade())
						|| e.getMaritalStatus().equals(bean.getMaritalStatus())
						|| e.getDepartmentId().toString().equals(bean.getDepartmentId().toString()))
					l.add(e);
			}
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeException("Some Error");
		}
	}

	@Override
	public List<String> getGrades() throws EmployeeException {
		try {
			String sql = "SELECT bean FROM GradeMaster bean";

			TypedQuery<GradeMaster> query = entity.createQuery(sql, GradeMaster.class);
			List<GradeMaster> list = query.getResultList();
			List<String> l = new ArrayList<String>();
			for (GradeMaster e : list) {
				String code=e.getGradeCode();
				l.add(code);
			}
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeException("Some Error");
		}
	}
}
