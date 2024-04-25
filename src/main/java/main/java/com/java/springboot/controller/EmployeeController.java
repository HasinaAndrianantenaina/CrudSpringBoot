package com.java.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.springboot.Bean.CommuneBean;
import com.java.springboot.Bean.EmployeeBean;
import com.java.springboot.model.Employee;
import com.java.springboot.repository.EmployeeRepository;
import com.java.springboot.service.CommuneService;
import com.java.springboot.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository empDAO;

	@Autowired
	private CommuneService communeService;

	
	  @RequestMapping("/") public String viewIndexPage(Model model) {
		  List<EmployeeBean> employeeList = employeeService.listAll();
		  model.addAttribute("getAllEmployee", employeeList);
	  return "index"; }
	 

	/*@GetMapping("/")
	public String viewHomePage(Model model) {

		return findPaginated(1, model);
	} */

	@RequestMapping("/table")
	public String test(Model model) {
		List<EmployeeBean> employeeList = employeeService.listAll();
		model.addAttribute("getAllEmployee", employeeList);
		return "tables";
	}

	@RequestMapping("/new_add")
	public String viewNewEmployeeForm(Model model) {
		EmployeeBean employee = new EmployeeBean();
		List<CommuneBean> commune = communeService.getCommune();
		model.addAttribute("employee", employee);
		model.addAttribute("communes", commune);
		return "insert";
	}

	@RequestMapping(value = "/save_employee", method = RequestMethod.POST)
	public String addNewEmployee(@ModelAttribute("employee") EmployeeBean employee) {
		employeeService.create(employee);

		return "redirect:/";
	}

	@RequestMapping(value = "/update_employee", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute("employee") EmployeeBean employee) {
		employeeService.update(employee);
		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView viewEditEmployeeForm(@PathVariable(name = "id") long id) {
		ModelAndView mav = new ModelAndView("update");
		EmployeeBean employee = employeeService.getEmployeById(id);
		mav.addObject("employee", employee);
		List<CommuneBean> commune = communeService.getCommune();
		mav.addObject("communes", commune);
		return mav;
	}

	@GetMapping("/getById/{id}")
	public String getEmpById(@PathVariable(name = "id") long id, Model model) {
		EmployeeBean employee = employeeService.getEmployeById(id);
		model.addAttribute("employee", employee);
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable(name = "id") long id) {
		employeeService.delete(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		// int id = 0;
		viewNewEmployeeForm(model);
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
		List<Employee> listEmployees = page.getContent();
		// viewEditEmployeeForm(id);
		int totalPage = page.getTotalPages();
		int totalElement = (int) page.getTotalElements();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", totalPage);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmployees", listEmployees);

		return "index";
	}

	@GetMapping("/edit")
	@ResponseBody
	public Optional<Employee> update(Long id) {
		Optional<Employee> listEmp = empDAO.findById(id);
		return empDAO.findById(id);
	}
}
