package org.iiitb.EC.dao;
import org.iiitb.EC. model.Seller;
import java.sql.*;
import org.iiitb.EC.dbcon.DatabaseConnection;

public class DAO_Seller {
	public static boolean add_Seller(String name,String mobile,String email,String address_1,String address_2)
	{
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "insert into seller_table(name,mobile,email,address_1,address_2) VALUES " +
				 "(?,?,?,?,?);";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, mobile);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, address_1);
			preparedStatement.setString(5, address_2);
			int rs = preparedStatement.executeUpdate();
			if(rs==0) {
				return false;
			}
			else {
				return true;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	public static Seller get_seller_details(int seller_id) {
		Seller sell = new Seller();
		//Connection conn = null;
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from seller_table where seller_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, seller_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				sell.setName(rs.getString("name"));
				sell.setMobile(rs.getString("mobile"));
				sell.setEmail(rs.getString("email"));
				sell.setAddress_1(rs.getString("address_1"));
				sell.setAddress_2(rs.getString("address_2"));
				
			}
			else {
				//item.put("result", "fail");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return sell;
	}
	

}
