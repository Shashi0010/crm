package com.durgasoft.crm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.durgasoft.crm.entity.Employee;
import com.durgasoft.crm.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theEmployeeService) {
		this.employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		// get employees from db
		List<Employee> theEmployees = employeeService.findAll();
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		return "employees/list-employees";
	}
	
	//Add Mapping to show add form
	
	@GetMapping("/addForm")
	public String showAddForm(Model theModel) {
		
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}

	@GetMapping("/updateForm")
	public String showUpdateForm(@RequestParam("employeeId") int theId, 
			Model theModel) {
		//Get the Employee From the database
		
		Employee theEmployee = employeeService.findById(theId);
		
		//set the attribute to the Model
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	//Add Mapping for save method
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String showDeleteForm(@RequestParam("employeeId") int theId) {
		
		//Delete the Employee By Id
		employeeService.deleteById(theId);
		
		//Redirect to Employees List
		return "redirect:/employees/list";
	}
	
	@GetMapping("/search")
	public String getEmployee(@RequestParam("employeeName") String theName,
						 Model theModel) {
		
		// delete the employee
		List<Employee> theEmployees = employeeService.searchBy(theName);
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		// send to /employees/list
		return "employees/list-employees";
	}	
}









