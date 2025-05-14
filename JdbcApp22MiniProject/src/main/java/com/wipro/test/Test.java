package com.wipro.test;

import java.util.Scanner;

import com.wipro.bean.Employee;
import com.wipro.factory.EmployeeServiceFactory;
import com.wipro.service.IEmployeeService;

public class Test {

	public static void main(String[] args) {
		Scanner sc=null;
		Employee emp;
		String status=null;
		
		try {

			/* I need a service layer object */
			IEmployeeService empService=EmployeeServiceFactory.getEmploueeService();
			
			
			sc=new Scanner(System.in);
			int eno=0;
			String ename=null;
			float esal=0.0f;
			String eaddr=null;
			
			while(true) {
				
				System.out.println("1.Add Employee");
				System.out.println("2.Search Employee");
				System.out.println("3.Update Employee");
				System.out.println("4.Delete Employee");
				System.out.println("5.Exit");
				
				System.out.println("Enter Your choice: ");
				int choice=sc.nextInt();
				System.out.println();
				switch(choice) {
					case 1: 
						System.out.println("------------ Employee Add Module ------------");
						System.out.println("Employee Number:   ");
						eno=sc.nextInt();
						System.out.println("Employee Name    :   ");
						ename=sc.next();
						System.out.println("Employee Salary   :   ");
						esal=sc.nextFloat();
						System.out.println("Employee Address:   ");
						eaddr=sc.next();
						
						emp=new Employee();
						emp.setEno(eno);
						emp.setEname(ename);
						emp.setEsal(esal);
						emp.setEaddr(eaddr);
						
						/* I need to call a method from Service layer */
						status=empService.addEmployee(emp);
						
						if(status.equalsIgnoreCase("sucess")) {
							System.out.println("Status: Employee Details Inserted Sucessfully");
						}
						else if(status.equalsIgnoreCase("failure")) {
							System.out.println("Status: Employee Details Insertion Failed");
						}
						else if(status.equalsIgnoreCase("existed")) {
							System.out.println("Status: Employee existed already");
						}						
						break;
						
						
					case 2: 						
						System.out.println("------------ Employee Search Module ------------");
						System.out.println("Employee Number:   ");
						eno=sc.nextInt();
						emp=empService.searchEmployee(eno);
						if(emp==null) {
							System.out.println("Status: Employee not exists");
						}
						else {
							System.out.println("Status: Employee Exists.");
							System.out.println("Employee Number:   "+emp.getEno());
							System.out.println("Employee Name    :   "+emp.getEname());
							System.out.println("Employee Salary   :   "+emp.getEsal());
							System.out.println("Employee Address:   "+emp.getEaddr());
							System.out.println();
						}			
						
						break;						
						
						
					case 3: 
						System.out.println("------------ Employee Update Module ------------");
						System.out.println("Employee Number:   ");
						eno=sc.nextInt();
						emp=empService.searchEmployee(eno);
						if(emp==null) {
							System.out.println("Status: Employee not exists");
						}
						else {
							Employee emp1=new Employee(); 
							emp1.setEno(eno);
							System.out.println("Employee Name[old:"+emp.getEname()+"] New: ");
							String val=sc.next();
							if(val==null || val.equals(" ")) {
								emp1.setEname(emp.getEname());
							}
							else {
								emp1.setEname(val);
							}
							
							System.out.println("Employee Salary[old:"+emp.getEsal()+"] New: ");
							val=sc.next();
							if(val==null || val.equals(" ")) {
								emp1.setEsal(emp.getEsal());
							}
							else {
								emp1.setEsal(Float.parseFloat(val));
							}
							
							System.out.println("Employee Address[old:"+emp.getEaddr()+"] New: ");
							val=sc.next();
							if(val==null || val.equals(" ")) {
								emp1.setEaddr(emp.getEaddr());
							}
							else {
								emp1.setEaddr(val);
							}
							
							status=empService.updateEmployee(emp1);
							if(status.equals("success")) {
								System.out.println("Employee details updated successfully :) ");
							}
							else {
								System.out.println("Employee details updation failed :( ");
							}							
						}
						
						System.out.println();
						
						break;
						
						
						
					case 4: 
						System.out.println("------------ Employee Delete Module ------------");
						
						break;
						
						
					case 5: 
						System.exit(0);
					default: 
						System.out.println("Wrong Choice !! Enter a valid choice number");
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}// end of catch
		finally {
			try {
				if(sc!=null) {
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// end of main
}// end of class
 