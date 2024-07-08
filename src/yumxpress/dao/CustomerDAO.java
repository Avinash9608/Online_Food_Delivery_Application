/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yumxpress.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import yumxpress.dbutil.DBConnection;
import yumxpress.pojo.CustomerPojo;

/**
 *
 * @author HP
 */
public class CustomerDAO {
      public static String getNewId()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(customer_id) from customers");
        rs.next();
        String id=rs.getString(1);
        String custId="";
        if(id!=null)
        {
            id=id.substring(4);
            custId="CUS-"+(Integer.parseInt(id)+1);
        }else
        {
         custId="CUS-101";   
        }
        return custId;
    }
      
      public static boolean addCustomer(CustomerPojo customer)throws SQLException
     {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into customers values(?,?,?,?,?,?)");
        customer.setCustomerId(getNewId());
        ps.setString(1, customer.getCustomerId());
        ps.setString(2, customer.getCustomerName());
        ps.setString(3, customer.getEmailId());
        ps.setString(4, customer.getPassword());
        ps.setString(5, customer.getMobileNo());
        ps.setString(6, customer.getAddress());
        return(ps.executeUpdate()==1);    
     }
     
}
