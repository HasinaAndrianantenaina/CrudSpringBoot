package com.java.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.java.springboot.Bean.EmployeeBean;
import com.java.springboot.model.Employee;

public interface EmployeeService {
	List<EmployeeBean> listAll();

	void create(EmployeeBean employee);

	EmployeeBean getEmployeById(Long id);

	void delete(Long id);

	void update(EmployeeBean employee);

	Page<Employee> findPaginated(int pageNo, int pageSize);

}
