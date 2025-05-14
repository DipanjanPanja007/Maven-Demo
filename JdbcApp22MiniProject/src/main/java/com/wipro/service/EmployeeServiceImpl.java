package com.wipro.service;

import com.wipro.bean.Employee;
import com.wipro.dao.IEmployeeDao;
import com.wipro.factory.EmployeeDaoFactory;

public class EmployeeServiceImpl implements IEmployeeService {

	@Override
	public String addEmployee(Employee emp) {
		IEmployeeDao empDao=EmployeeDaoFactory.getEmployeeDao();
		String status=empDao.add(emp);
		return status;
	}

	@Override
	public Employee searchEmployee(Integer eno) {
		IEmployeeDao empDao=EmployeeDaoFactory.getEmployeeDao();
		Employee emp=empDao.search(eno);
		return emp;
	}

	@Override
	public String updateEmployee(Employee emp) {
		IEmployeeDao empDao=EmployeeDaoFactory.getEmployeeDao();
		String status=empDao.update(emp);
		return status;
	}

	@Override
	public String deleteEmployee(Integer eno) {
		// TODO Auto-generated method stub
		return null;
	}

}
