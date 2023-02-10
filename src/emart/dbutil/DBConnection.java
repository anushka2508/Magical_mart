
package emart.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class DBConnection {
    private static Connection conn;
    static 
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded!");
		 
       	    conn=DriverManager.getConnection("jdbc:oracle:thin:@//HALL:1521/XE","emart","000");
            System.out.println("Connection opened sucessfully!");
            JOptionPane.showMessageDialog(null, "connection Opened Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(null,"Error in loading driver","Error",JOptionPane.ERROR_MESSAGE);
            cnfe.printStackTrace();
            System.exit(1);
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error in loading driver","Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(1);
        }
    }
        public static Connection getConnection()
        {
            return conn;
        }
        public static void closeConnection()
        {
            try
            {
                conn.close();
                JOptionPane.showMessageDialog(null,"Connection Closed","Sucess",JOptionPane.INFORMATION_MESSAGE);
            }
             catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Error in closing the Connection","DB Error",JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();   
            }
        }
}
