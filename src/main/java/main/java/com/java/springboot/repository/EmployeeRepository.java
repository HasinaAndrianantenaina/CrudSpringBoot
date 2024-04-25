package com.java.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("from Employee e inner join Commune c ON e.commune = c.id")
	List<Employee> getAllEmployee();

	@Query("from Employee where id = ?1")
	Employee getEmployeeById(Long id);

}
