package org.packt.Spring.chapter5.JDBC.main;

import java.util.ArrayList;
import java.util.List;

import org.packt.Spring.chapter5.JDBC.dao.EmployeeDao;
import org.packt.Spring.chapter5.JDBC.dao.EmployeeDaoImpl;
import org.packt.Spring.chapter5.JDBC.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HrPayrollSystem {

	public static void main(String[] args) {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		
		
		EmployeeDao employeeDao = context.getBean("employeeDaoImpl", EmployeeDao.class);
		
		
		for(Employee e : employeeDao.getEmployees()) {
			System.out.println(e.getEmpId() + " " + e.getName());
		}
		
		//System.out.println("***Simple Update***");
		
		
		/*Employee employee = (Employee) employeeDao.getEmployeeById(1);
		//Employee e = new Employee(2, "Genesis");
		//employeeDao.insertEmployee(e);
		Employee employee2 = (Employee) employeeDao.getEmployeeById(2);
		System.out.println("The employee with id : 1 = "+ employee.getName());
		System.out.println("The employee with id : 2 = "+ employee2.getName());
		
		System.out.println("We've : " + employeeDao.getEmployeeCount() + " employees");
		
		
		System.out.println("***Batch Update***");
		List<Employee> employees = new ArrayList<Employee>();
		Employee e = new Employee(3, "Brayan");
		Employee e2 = new Employee(4, "Jorge");
		Employee e3 = new Employee(5, "Yorland");
		
		employees.add(e);
		employees.add(e2);
		employees.add(e3);
		
		employeeDao.createMultipleEmployees(employees);
		System.out.println(employeeDao.getEmployeeCount());
		*/
		
		
		

	}

}
