package com.wipro.factory;

import com.wipro.service.EmployeeServiceImpl;
import com.wipro.service.IEmployeeService;

public class EmployeeServiceFactory {
	private static IEmployeeService empService;
	static {
		empService=new EmployeeServiceImpl();
	}
	public static IEmployeeService getEmploueeService() {
		return empService;
	}
}
