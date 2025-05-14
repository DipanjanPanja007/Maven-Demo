package com.wipro.service;

import com.wipro.bean.Employee;

public interface IEmployeeService {
	public String addEmployee(Employee emp);
	public Employee searchEmployee(Integer eno);
	public String updateEmployee(Employee emp);
	public String deleteEmployee(Integer eno);
}
