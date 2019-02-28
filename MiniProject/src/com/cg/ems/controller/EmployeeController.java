package com.cg.ems.controller;

import java.util.List;

import javax.validation.Valid;

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

	@Autowired
	IEmployeeService service;
	Employee employee = null;
	String name = null;
	List<String> gradeList=null;
	String[] maritalStatuses=new String[] { "Single", "Married", "Divorced", "Widowed", "Separated" };
	

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
				gradeList=service.getGrades();
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
			model.addAttribute("maritalStatus",maritalStatuses);
			model.addAttribute("employee", employee);
			model.addAttribute("temp", 0);
			return "addEmployee";
		} else {
			try {
				Integer eid = service.addEmployee(employee);
				employee.setEmployeeId(eid);
				String pwd = service.generateLogin(employee);
				model.addAttribute("temp", 1);
				model.addAttribute("id", eid);
				model.addAttribute("pwd", pwd);
				return "addEmployee";
			} catch (EmployeeException e) {
				model.addAttribute("msg", e.getMessage());
				return "error";
			}
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

		if (bean.getEmployeeId()==null) {
			model.addAttribute("temp", 0);
			return "modifyPage";
		}
		try {
			Employee employee = service.getDetailsById(bean.getEmployeeId());
			model.addAttribute("employee", employee);
			model.addAttribute("temp", 1);
			return "modifyPage";
		} catch (EmployeeException e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}

	}

	@RequestMapping("update")
	public String updateDetails(@Valid @ModelAttribute("employee") Employee bean, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("employee", bean);
			model.addAttribute("temp", 1);
			return "modifyPage";
		} else {
			try {
				if (service.modifyEmp(bean)) {
					model.addAttribute("bean", bean);
					return "updatedEmpDetails";
				} else {
					model.addAttribute("msg", "COULD NOT UPDATE EMPLOYEE DETAILS");
					return "error";
				}
			} catch (EmployeeException e) {
				model.addAttribute("msg", e.getMessage());
				return "error";
			}
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
		System.out.println(name);
		model.addAttribute("id", "employeeId");
		model.addAttribute("fname", "firstName");
		model.addAttribute("lname", "lastName");
		model.addAttribute("grade", "grade");
		model.addAttribute("maritalStatus", "maritalStatus");
		model.addAttribute("department","department");
		model.addAttribute("isFirst", "true");
		return "searchEmployee";

	}
	@RequestMapping("searchEmp")
	public String toSearchEmployee(@ModelAttribute("employee") Employee bean, Model model) {
		try {
			System.out.println(bean);
			List<Employee> list = service.search(bean);
			if (list.isEmpty()) {
				String msg = "The field should not be Empty or doesn't exist exist or it is invalid " + "Search Again";
				model.addAttribute("msg", msg);
				return "searchEmployee";
			}
			model.addAttribute("plist", list);
			return "searchEmp";
		} catch (EmployeeException e) {
			model.addAttribute("msg", "Unable to display list");
			return "error";
		}
	}

	

}
