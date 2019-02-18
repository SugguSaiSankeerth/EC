package org.iiitb.EC.rest_services;

import java.sql.Connection;
import org.iiitb.EC.dao.DAO_Seller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Seller;

import org.json.JSONObject;


@Path("sellerService")

public class Seller_Service {
	@Path("sellerInfo")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
	 public String getSellerInfo(
	    		@FormDataParam("seller_id") String seller_id) throws Exception{
        
        Connection conn = DatabaseConnection.getConnection();
        Seller seller=DAO_Seller.get_seller_details(Integer.parseInt(seller_id));
        JSONObject sell = new JSONObject();
        sell.put("name", seller.getName());
        sell.put("email", seller.getEmail());
        sell.put("mobile", seller.getMobile());
//        sell.put("dob", seller.getDob());
        sell.put("address_1", seller.getAddress_1());
        sell.put("address_2", seller.getAddress_2());
        return sell.toString();
        
        
//        return "abc";
        
     }
	

}
