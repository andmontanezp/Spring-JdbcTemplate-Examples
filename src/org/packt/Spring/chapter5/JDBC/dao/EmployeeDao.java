package org.packt.Spring.chapter5.JDBC.dao;

import java.util.List;
import java.util.Map;

import org.packt.Spring.chapter5.JDBC.model.Employee;

public interface EmployeeDao {
	void createEmployee();
	void createMultipleEmployees(final List<Employee> employees);
	int getEmployeeCount();
	int insertEmployee(Employee employee);
	int deleteEmployeeById(int empId);
	Employee getEmployeeById(int empId);
	
	List<Employee> getEmployees();
	
}
