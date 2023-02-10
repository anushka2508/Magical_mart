/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.EmployeePojo;
import emart.pojo.ReceptionistPojo;
import emart.pojo.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {
    public static String getNextEmpId()throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("Select max(empid) from employees");
         rs.next();
         String empid=rs.getString(1);
         int empno=Integer.parseInt(empid.substring(1));
         empno=empno+1;
         return "E"+empno;
    }
    public static boolean addEmployee(EmployeePojo emp)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into employees values(?,?,?,?)");
        ps.setString(1,emp.getEmpid());
         ps.setString(2,emp.getEmpname());
          ps.setString(3,emp.getEmpjob());
           ps.setDouble(4,emp.getSalary());
        int result=ps.executeUpdate();
        return result==1;
    }
    
    public static List<EmployeePojo> getAllEmployees()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from employees order by empid");
    ArrayList <EmployeePojo> emplist=new ArrayList<>();
    while(rs.next())
    {
        EmployeePojo emp=new EmployeePojo();
        emp.setEmpid(rs.getString(1));
         emp.setEmpname(rs.getString(2));
          emp.setEmpjob(rs.getString(3));
           emp.setSalary(rs.getDouble(4));
           emplist.add(emp);
    }
    return emplist;
    }
     public static boolean updateEmployee(EmployeePojo e) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update employees set empname=?,job=?,salary=? where empid=?");
        ps.setString(1,e.getEmpname());
        ps.setString(2,e.getEmpjob());
        ps.setDouble(3,e.getSalary());
        ps.setString(4,e.getEmpid());
        int result=ps.executeUpdate();
       if(result==0)
           return false;
       else 
       {
           boolean x=UserDAO.isUserPresent(e.getEmpid());
           if(x==false)
               return true; 
      ps=conn.prepareStatement("update users set username=?,usertype=? where empid=? ");
      ps.setString(1,e.getEmpname());
      ps.setString(2,e.getEmpjob());
      ps.setString(3,e.getEmpid());
      int y=ps.executeUpdate();
      return y==1;     
  }
  }
     public static ArrayList<String>getAllId()throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("Select empid from employees order by empid");  
         ArrayList<String> list=new ArrayList<>();
         while(rs.next())
         {
             list.add(rs.getString(1));
         }
         return list;
    }
       public static EmployeePojo getAllEmp(String id) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select empname,job,salary from employees where empid=?");
        ps.setString(1,id);
        ResultSet rs=ps.executeQuery();
        EmployeePojo e=null;
        while(rs.next())
        {
            e=new EmployeePojo();
            e.setEmpname(rs.getString(1));
            e.setEmpjob(rs.getString(2));
            e.setSalary(rs.getDouble(3));
        }
        return e;
    }
    public static boolean deleteEmp(String id)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("delete from employees where empid=?");
       ps.setString(1,id);  
       return ps.executeUpdate()==1;
    
    }

}
