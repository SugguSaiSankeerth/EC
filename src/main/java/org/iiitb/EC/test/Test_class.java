package org.iiitb.EC.test;

import org.iiitb.EC.dao.DAO_Item_Seller;

public class Test_class {
	public static void main(String args[]) {
		boolean b = DAO_Item_Seller.add_Item_Seller(4, 2, 340, 1);
		System.out.println(b);
	}
}
