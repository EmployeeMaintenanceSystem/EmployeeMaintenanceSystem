package com.cg.ems.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.ems.entities.Employee;
import com.cg.ems.entities.User;
import com.cg.ems.exception.EmployeeException;
import com.cg.ems.service.IEmployeeService;

@Controller
public class EmployeeController {
	
	static {
		PropertyConfigurator.configure("C:\\Users\\gakshayk\\git\\EMS\\MiniProject\\WebContent\\resources\\log4j.properties");
	}
	@Autowired
	IEmployeeService service;
	Employee employee = null;
	String name = null;
	List<String> gradeList = null;
	String[] maritalStatuses = new String[] { "Single", "Married", "Divorced", "Widowed", "Separated" };
	static Logger log = Logger.getLogger(EmployeeController.class.getName());
	
	@RequestMapping("index")
	public ModelAndView toIndex() {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("index");
		return mnv;
	}

	@RequestMapping("login")
	public String adminPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping("loginDetails")
	public String validLoginDetails(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "login";
		} else {
			try {
				User bean = service.isValid(user.getUserId(), user.getPassword());
				String type = bean.getUserType();
				gradeList = service.getGrades();
				if (type.equalsIgnoreCase("admin")) {
					model.addAttribute("name", bean.getUserName());
					return "adminPage";
				} else if (type.equalsIgnoreCase("Employee")) {
					model.addAttribute("name", bean.getUserName());
					return "employeePage";
				} else {
					model.addAttribute("msg", "You are not an Admin or an Employee");
					return "login";
				}
			} catch (EmployeeException e) {
				log.error(e.getMessage());
				model.addAttribute("msg", "Invalid UserName or Password");
				return "login";
			}
		}
	}

	@RequestMapping("add")
	public String toAddEmpoyee(Model model) {
		model.addAttribute("temp", 0);
		Employee bean = new Employee();
		model.addAttribute("employee", bean);
		model.addAttribute("grade", gradeList);
		model.addAttribute("maritalStatus", maritalStatuses);
		return "addEmployee";
	}

	@RequestMapping("addEmployee")
	public String addEmp(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("grade", gradeList);
			model.addAttribute("maritalStatus", maritalStatuses);
			model.addAttribute("employee", employee);
			model.addAttribute("temp", 0);
			return "addEmployee";
		} else if (validAge(employee)) {
			try {
				Integer eid = service.addEmployee(employee);
				employee.setEmployeeId(eid);
				String pwd = service.generateLogin(employee);
				model.addAttribute("temp", 1);
				model.addAttribute("id", eid);
				model.addAttribute("pwd", pwd);
				return "addEmployee";
			} catch (EmployeeException e) {
				log.error(e.getMessage());
				model.addAttribute("msg", e.getMessage());
				return "error";
			}
		} else {
			model.addAttribute("grade", gradeList);
			model.addAttribute("maritalStatus", maritalStatuses);
			model.addAttribute("employee", employee);
			model.addAttribute("temp", 0);
			model.addAttribute("message", " Employee Age Should be between 18 and 65..! & Date of Joining Should be Before System date...!!  Try again ");
			return "addEmployee";
		}

	}

	public static boolean validAge(Employee employee) {
		LocalDate empDateOfJoining = employee.getDateOfJoining().toLocalDate();
		LocalDate empDateOfBirth = employee.getDateOfBirth().toLocalDate();
		Period diff = Period.between(empDateOfJoining, empDateOfBirth);
		
		LocalDate today=LocalDate.now();
		 
		if (Math.abs(diff.getYears()) > 18 && Math.abs(diff.getYears()) < 65 && empDateOfJoining.isBefore(today)) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("modify")
	public String modifyPage(Model model) {
		model.addAttribute("temp", 0);
		Employee bean = new Employee();
		model.addAttribute("bean", bean);
		return "modifyPage";
	}

	@RequestMapping("modifyDetails")
	public String modifyDetails(@ModelAttribute("bean") @Valid Employee bean, BindingResult result, Model model) {
		Employee employee=null;
		if (bean.getEmployeeId() == null) {
			model.addAttribute("temp", 0);
			return "modifyPage";
		}
		try {
			 employee = service.getDetailsById(bean.getEmployeeId());
			if (employee != null) {
				model.addAttribute("employee", employee);
				model.addAttribute("temp", 1);
				return "modifyPage";
			} else {
				model.addAttribute("temp", 0);
				model.addAttribute("message", "Enter Valid Employee Id");
				return "modifyPage";
			}
		} catch (EmployeeException e) {
			log.error(e.getMessage());
			model.addAttribute("msg", e.getMessage());
			return "error";
		}

	}

	@RequestMapping("update")
	public String updateDetails(@Valid @ModelAttribute("employee") Employee bean, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("employee", bean);
			model.addAttribute("temp", 1);
			return "modifyPage";
		} else if (validAge(bean)) {
			try {
				if (service.modifyEmp(bean)) {
					model.addAttribute("bean", bean);
					return "updatedEmpDetails";
				} else {
					model.addAttribute("msg", "Could Not Update Employee Details");
					return "error";
				}
			} catch (EmployeeException e) {
				log.error(e);
				model.addAttribute("msg", e.getMessage());
				return "error";
			}
		} else {
			model.addAttribute("employee", bean);
			model.addAttribute("temp", 1);
			model.addAttribute("message", " Employee Age Should be between 18 and 65..! Try again ");
			return "modifyPage";
		}
	}

	@RequestMapping("display")
	public String displayEmployeeDetails(Model model) {
		try {
			List<Employee> list = service.displayAll();
			if (list.isEmpty()) {
				model.addAttribute("msg", "EMPTY LIST");
				return "error";
			} else {
				model.addAttribute("list", list);
				return "displayEmployee";
			}
		} catch (EmployeeException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}

	}

	@RequestMapping("search")
	public String toSearch(Model model) {
		return "searchEmployee";
	}

	@RequestMapping("searchEmployee")
	public String toSearchEmp(@RequestParam("search") String name, Model model) {
		Employee bean = new Employee();
		model.addAttribute("employee", bean);
		model.addAttribute("name", name);
		model.addAttribute("id", "employeeId");
		model.addAttribute("fname", "firstName");
		model.addAttribute("lname", "lastName");
		model.addAttribute("grade", "grade");
		model.addAttribute("maritalStatus", "maritalStatus");
		model.addAttribute("department", "department");
		model.addAttribute("isFirst", "true");
		return "searchEmployee";

	}

	@RequestMapping("searchEmp")
	public String toSearchEmployee(@ModelAttribute("employee") Employee bean, Model model) {
		try {
			System.out.println(bean);
			List<Employee> list = service.search(bean);
			if (list.isEmpty()) {
				String msg = "Invalid Search....! " + "Try Again....:)";
				model.addAttribute("msg", msg);
				return "searchEmployee";
			}
			model.addAttribute("plist", list);
			return "searchEmp";
		} catch (EmployeeException e) {
			log.error(e.getMessage());
			model.addAttribute("message", "Unable to display list");
			return "error";
		}
	}

	@RequestMapping("delete")
	public String toDelete(Model model) {
		return "deleteEmployee";
	}

	@RequestMapping("deleteEmployee")
	public String deleteEmployee(@RequestParam("empId") String empId, Model model) {
		Employee employee = null;
		try {
			Integer id = Integer.parseInt(empId);
			employee = service.getDetailsById(id);
			if (employee != null) {
				model.addAttribute("bean", employee);
				return "deleteEmpPage";
			} else {
				model.addAttribute("message", "Please Enter Valid Employee Id");
				return "deleteEmployee";
			}
		} catch (EmployeeException e) {
			log.error(e);
			model.addAttribute("message", "Please Enter Valid Employee Id");
			return "deleteEmployee";
		}

	}

	@RequestMapping("deleteEmp")
	public String deleteEmp(@RequestParam("empId") String empId, Model model) {
		try {
			Integer id = Integer.parseInt(empId);
			service.getDeleteEmployee(id);
			return "deletedEmpPage";
		} catch (EmployeeException e) {
			log.error(e.getMessage());
			model.addAttribute("message", e.getMessage());
			return "deleteEmployee";
		}

	}

}
