
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.ProductsPojo;
import emart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrdersDAO {
       public static String getNextOrderId()throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("Select max(order_id) from orders");
         rs.next();
         String orderId=rs.getString(1);
         if(orderId==null)
             return "O-101";
         int ordno=Integer.parseInt(orderId.substring(2));
         ordno++;
         return "O-"+ordno;
    }
       public static boolean addOrder(ArrayList<ProductsPojo> al,String ordId)throws SQLException
       {
            Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("Insert into orders values(?,?,?,?)"); 
         int count=0;
         for(ProductsPojo p:al)
         {
             ps.setString(1,ordId);
             ps.setString(2,p.getProductId());
             ps.setInt(3,p.getQuantity());
             ps.setString(4,UserProfile.getUserid());
            count=count+ ps.executeUpdate();
         }
         return count==al.size();     
       }
        public static ArrayList<String> getAllOrdersId()throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("Select order_id from orders order by order_id");  
         ArrayList<String> list=new ArrayList<>();
         while(rs.next())
         {
             list.add(rs.getString(1));
         }
         return list;
    }
         public static ArrayList<String> getProductsId(String id) throws SQLException
     {
         Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("select * from orders where order_id=?");
         ps.setString(1,id);
         ResultSet rs=ps.executeQuery();
         ArrayList<String> pids=new ArrayList<>();
         while(rs.next())
         {
             pids.add(rs.getString(2));
         }
         return pids;
     }
      public static ProductsPojo getOrdersDetails(String id) throws SQLException
     {
         Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("select * from products where p_id=?");
         ps.setString(1,id);
         ResultSet rs=ps.executeQuery();
         ProductsPojo p=new ProductsPojo();
        if( rs.next()){
         p.setProductId(rs.getString(1));
         p.setProductName(rs.getString(2));
         p.setProductCompany(rs.getString(3));
         p.setProductPrice(rs.getDouble(4));
         p.setProductOur(rs.getDouble(5));
         p.setTax(rs.getInt(6));
         
//         p.setQuantity(rs.getInt(7));   
        }
         return p;
     }
      public static int getOrderQuantity(String ord,String pid)throws SQLException
      {
          Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("select * from orders where order_id=? and p_id=?");
         ps.setString(1,ord);
         ps.setString(2, pid);
         ResultSet rs=ps.executeQuery();
         rs.next();
         ProductsPojo p=new ProductsPojo();
         int quantity=rs.getInt(3);
//         p.setQuantity(quantity);
         return quantity;
      }
}
