
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceptionistDAO {
    public static Map<String,String> getNonRegisteredReceptionists() throws SQLException
    {
         Connection conn=DBConnection.getConnection();
        Statement st =conn.createStatement();
        ResultSet rs=st.executeQuery("Select empid,empname from employees where job='Receptionist' and empid not in (select empid from users where usertype='Receptionist')");
        HashMap <String,String> receptionistList=new HashMap<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionistList.put(id,name);
        }
        return receptionistList;
    }
    public static boolean addReceptionist(UserPojo user)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into users values(?,?,?,?,?)");
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getEmpid());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getUsertype());
        ps.setString(5, user.getUsername());
        int result=ps.executeUpdate();
         return result==1;
    }
    public static List<ReceptionistPojo>  getAllReceptionistDetails()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st =conn.createStatement();
        ResultSet rs=st.executeQuery("select users.empid,empname,userid,job,salary from users,employees where usertype='Receptionist' and users.empid=employees.empid");
        ArrayList<ReceptionistPojo> al=new ArrayList<>();
        while(rs.next())
        {
            ReceptionistPojo recp=new ReceptionistPojo();
            recp.setEmpid(rs.getString(1));
            recp.setEmpname(rs.getString(2));
            recp.setUserid(rs.getString(3));
            recp.setJob(rs.getString(4));
            recp.setSalary(rs.getDouble(5));
            al.add(recp);
        }
           return al;
    }  
//        public static List<ReceptionistPojo> getAllReceptionist()throws SQLException
//    {
//        Connection conn=DBConnection.getConnection();
//        Statement st=conn.createStatement();
//     ResultSet rs=st.executeQuery("Select * from users where usertype ='Receptionist'");
//    ArrayList <ReceptionistPojo> fulllist=new ArrayList<>();
//    String job="Receptionist";
////   ResultSet rs1=st.executeQuery("select salary from employees where job='Receptionist'");
//    while(rs.next())
//    {
//          ReceptionistPojo ulist=new  ReceptionistPojo();
//          ulist.setUserid(rs.getString(1));
//          ulist.setEmpid(rs.getString(2));
//          ulist.setJob(job);
//          ulist.setEmpname(rs.getString(5));
////         double sal=rs1.getDouble("Salary");
////         ulist.setSalary(rs.getDouble(5));
//          fulllist.add(ulist);
//    }
//    return fulllist;
//    }
     public static ArrayList<String>getAllReceptionistId()throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("Select empid from users where usertype='Receptionist' order by empid");  
         ArrayList<String> list=new ArrayList<>();
         while(rs.next())
         {
             list.add(rs.getString(1));
         }
         return list;
    }
          public static UserPojo getAllRecep(String id) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select username from users where empid=?");
        ps.setString(1,id);
        ResultSet rs=ps.executeQuery();
        UserPojo e=null;
        while(rs.next())
        {
            e=new UserPojo();
            e.setUsername(rs.getString(1));
          
        }
        return e;
    }
    public static Map<String,String> getAllRecepId()throws SQLException  
    {
        Connection conn=DBConnection.getConnection();
        Statement st =conn.createStatement();
        ResultSet rs=st.executeQuery("Select userid,username from users where usertype='Receptionist' order by userid ");
        HashMap <String,String> receptionistList=new HashMap<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionistList.put(id,name);
        }
        return receptionistList;
    }
     public static boolean updatePassword(String userid,String pwd)throws SQLException
     {
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("update users set password=? where userid=?");
          ps.setString(1,pwd);
          ps.setString(2,userid);
          return ps.executeUpdate()==1;
     }
     public static List<String> getAllReceptionistUserId()throws SQLException
     {
        Connection conn=DBConnection.getConnection();
        Statement st =conn.createStatement();
        ResultSet rs=st.executeQuery("Select userid from users where usertype='Receptionist' order by userid ");
        List <String> receptionistList=new ArrayList<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            receptionistList.add(id);
        }
        return receptionistList;
     }
     public static boolean deleteReceptionist(String userid)throws SQLException
     {
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("delete from users  where userid=?");    
          ps.setString(1,userid);
          return ps.executeUpdate()==1; 
     }
}
