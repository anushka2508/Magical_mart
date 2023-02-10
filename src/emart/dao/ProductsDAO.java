
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.ProductsPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductsDAO {
       public static String getNextProductId()throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("Select max(p_id) from products");
         rs.next();
         String productId=rs.getString(1);
         if(productId==null)
             return "P101";
         int productno=Integer.parseInt(productId.substring(1));
         productno=productno+1;
         return "P"+productno;
    }
    public static boolean addProduct(ProductsPojo p)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("Insert into products values(?,?,?,?,?,?,?,'Y')");
         ps.setString(1,p.getProductId());
                  ps.setString(2,p.getProductName());
         ps.setString(3,p.getProductCompany());
         ps.setDouble(4,p.getProductPrice());
          ps.setDouble(5,p.getProductOur());
          ps.setInt(6,p.getTax());
          ps.setInt(7,p.getQuantity());
          return ps.executeUpdate()==1;
          
    }
    
      public static List<ProductsPojo> getProductsDetails() throws SQLException{
            Connection conn=DBConnection.getConnection();
             Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from products where status='Y' order by p_id");
     ArrayList<ProductsPojo> productsList=new ArrayList<>();
     while(rs.next())
     {
         ProductsPojo p=new ProductsPojo();
         p.setProductId(rs.getString(1));
          p.setProductName(rs.getString(2));
         p.setProductCompany(rs.getString(3));
            p.setProductPrice(rs.getDouble(4));
               p.setProductOur(rs.getDouble(5));
         p.setTax(rs.getInt(6));
                     p.setQuantity(rs.getInt(7));
         productsList.add(p);       
     }
     return productsList;
   }
      public static boolean deleteProduct(String productId)throws SQLException
      {
         Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("update products set status='N' where p_id=?");
         ps.setString(1,productId);
        return ps.executeUpdate()==1;
     }
      public static boolean updateProduct(ProductsPojo p)throws SQLException{
         Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("update products set p_name=?,p_companyname=?,p_price=?,our_price=?,p_tax=?,quantity=? where p_id=?");
         ps.setString(1,p.getProductName());
         ps.setString(2,p.getProductCompany());
         ps.setDouble(3,p.getProductPrice());
         ps.setDouble(4,p.getProductOur());
         ps.setInt(5,p.getTax());
         ps.setInt(6,p.getQuantity());
         ps.setString(7,p.getProductId());
         return ps.executeUpdate()==1;
      }
     public static ProductsPojo getProductsDetails(String id) throws SQLException
     {
         Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("select * from products where p_id=? and status='Y'");
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
         p.setQuantity(rs.getInt(7));   
        }
         return p;
     }
      public static boolean updateStocks(List<ProductsPojo> p)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update products set quantity=quantity-? where p_id=?");
        int x=0;
        for(ProductsPojo pro:p){
            ps.setInt(1,pro.getQuantity());
            ps.setString(2,pro.getProductId());
            int rows=ps.executeUpdate();
            if(rows!=0)
                x++;
        }
        return x==p.size();
      }
}
