package org.iiitb.EC.dao;
import java.sql.*;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Item;

public class DAO_Item {
	//String query = null;
	//ResultSet rs;
	public static boolean Add_Item(String description , float price, float discount, String name, String pic_location,
			String category, String sub_category, String barcode, String dummy_1, String dummy_2, String dummy_3, String dummy_4) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "insert into item_table(description,price,discount,name,pic_location,category,sub_category,barcode)"+" VALUES "+
			"(?,?,?,?,?,?,?,?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, description);
			preparedStatement.setFloat(2, price);
			preparedStatement.setFloat(3, discount);
			preparedStatement.setString(4, name);
			preparedStatement.setString(5, pic_location);
			preparedStatement.setString(6, category);
			preparedStatement.setString(7, sub_category);
			preparedStatement.setString(8, barcode);
			
			int rs = preparedStatement.executeUpdate();
			if(rs==0) {
				return false;
			}
			else {
				return true;
			}

		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static int Check_Item(String BarCode) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT item_id FROM item_table where barcode=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, BarCode);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				return rs.getInt("item_id");
			}
			else {
				return -1;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public static boolean Delete_Item(int item_id)
	{
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query = "Delete from item_table where item_id=?";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
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
		return false;
		
	}
	public static Item Get_Item(int Item_id) {
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT * FROM item_table where item_id = ?";
			Item item_object = new Item();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, Item_id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				item_object.setDescription(rs.getString("description"));
				item_object.setName(rs.getString("name"));
				item_object.setPic_location(rs.getString("pic_location"));
				item_object.setCategory(rs.getString("category"));
				item_object.setSub_category(rs.getString("sub_category"));
				item_object.setBarcode(rs.getString("barcode"));
				item_object.setPrice(rs.getFloat("price"));
				item_object.setDiscount(rs.getFloat("discount"));
				return item_object;
			}
			else {
				return null;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
