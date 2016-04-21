package org.packt.Spring.chapter5.JDBC.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.packt.Spring.chapter5.JDBC.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createEmployee() {
	}

	@Override
	public int getEmployeeCount() {
		String sql = "select count(*) from employee";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public int insertEmployee(Employee employee) {
		String insertQuery = "insert into employee (id, name) values (?, ?) ";
				Object[] params = new Object[] { employee.getEmpId(),
				employee.getName() };
				int[] types = new int[] { Types.INTEGER, Types.VARCHAR};
				return jdbcTemplate.update(insertQuery, params, types);	
	}

	@Override
	public int deleteEmployeeById(int empId) {
		String delQuery = "delete from employee where EmpId = ?";	
		return jdbcTemplate.update(delQuery, new Object[] {empId}); 
	}

	@Override
	public Employee getEmployeeById(int empId) {
		String query = "select * from employee where id = ?";
		Employee employee = jdbcTemplate.queryForObject(query, new Object[] { empId }, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee(rs.getInt("id"), rs.getString("name"));
				return employee;
			}
		});
		return employee;
	}

	@Override
	public void createMultipleEmployees(final List<Employee> employees) {
		
		jdbcTemplate.batchUpdate("INSERT INTO employee (id, name) VALUES (?,?)", new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setInt(1, employee.getEmpId());
				ps.setString(2, employee.getName());
			}
			
			@Override
			public int getBatchSize() {
				String sql = "select count(*) from employee";
				return jdbcTemplate.queryForInt(sql);
			}
		});
		
		
	}

	@Override
	public List<Employee> getEmployees() {
		String sql = "select * from employee";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<Employee> employees = new ArrayList<Employee>();
		for(Map row : rows) {
			Employee employee = new Employee();
			employee.setEmpId(Integer.parseInt(String.valueOf(row.get("id"))));
			employee.setName(String.valueOf(row.get("name")));
			employees.add(employee);
		}
		return employees;
	}

}
