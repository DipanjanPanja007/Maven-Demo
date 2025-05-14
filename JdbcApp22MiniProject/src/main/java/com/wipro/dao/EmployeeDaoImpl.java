package com.wipro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wipro.bean.Employee;
import com.wipro.factory.ConnectionFactory;

public class EmployeeDaoImpl implements IEmployeeDao {
	private static final String EMPLOYEE_SELECT_QUERY="SELECT * FROM EMPLOYEE WHERE ENO=?";
	private static final String EMPLOYEE_INSERT_QUERY="INSERT INTO EMPLOYEE VALUES(?,?,?,?)";
	private static final String EMPLOYEE_UPDATE_QUERY="UPDATE EMPLOYEE SET ENAME=?,ESAL=?,EADDR=? WHERE ENO=?";

	@Override
	public String add(Employee emp) {
		Connection con=null;
		PreparedStatement pst1=null;
		PreparedStatement pst2=null;
		ResultSet rs=null;
		String status=null;
		
		try {
			con=ConnectionFactory.getConnection();
			if(con!=null) {				
				pst1=con.prepareStatement(EMPLOYEE_SELECT_QUERY);       // check for duplicate
				pst2=con.prepareStatement(EMPLOYEE_INSERT_QUERY);
			}
			if(pst1!=null && pst2!=null) {
				
				/* set each employee deatils as pre-compiled SQL-query parmas*/
				pst1.setInt(1, emp.getEno());
				rs=pst1.executeQuery();
				boolean flag=false;
				if(rs!=null) {
					flag=rs.next();					
				}
				if(flag==true) {
					status="existed";
				}
				else {
					
					pst2.setInt(1, emp.getEno());
					pst2.setString(2, emp.getEname());
					pst2.setFloat(3, emp.getEsal());
					pst2.setString(4, emp.getEaddr());
					
					int result=pst2.executeUpdate();
					if(result==0) {
						status="failure";
					}
					else {
						status="sucess";
					}
				}			
								
			}
		} catch (Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Employee search(Integer eno) {
		Employee emp=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			con=ConnectionFactory.getConnection();
			if(con!=null) {
				pst=con.prepareStatement(EMPLOYEE_SELECT_QUERY);
			}
			if(pst!=null) {
				/* set each employee deatils as pre-compiled SQL-query parmas*/
				pst.setInt(1, eno);
				rs=pst.executeQuery();
			}
			
			if(rs!=null) {
				boolean flag=rs.next();
				if(flag==true) {
//					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+rs.getString(4));
					emp=new Employee();
					emp.setEno(rs.getInt(1));;
					emp.setEname(rs.getString(2));
					emp.setEsal(rs.getFloat(3));
					emp.setEaddr(rs.getString(4));
				}// end of inner if				
			}
			else {
				emp=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public String update(Employee emp) {
		Connection con=null;
		PreparedStatement pst=null;
		String status=null;
		
		try {
			con=ConnectionFactory.getConnection();
			if(con!=null) {
				pst=con.prepareStatement(EMPLOYEE_UPDATE_QUERY);
			}
			if(pst!=null) {
				/*set each employee deatils as pre-compiled SQL-query parmas*/
				pst.setString(1, emp.getEname());
				pst.setFloat(2, emp.getEsal());
				pst.setString(3, emp.getEaddr());
				pst.setInt(4, emp.getEno());
				
				int count=pst.executeUpdate();
				if(count==0) {
					status="failure";
				}
				else {
					status="success";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public String delete(Integer eno) {
		// TODO Auto-generated method stub
		return null;
	}

}
