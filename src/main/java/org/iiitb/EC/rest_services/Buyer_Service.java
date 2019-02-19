package org.iiitb.EC.rest_services;

import java.sql.Connection;
import org.iiitb.EC.dao.DAO_Buyer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Buyer;

import org.json.JSONObject;

@Path("buyerService")

public class Buyer_Service {
	@Path("buyerInfo")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
	 public Response getBuyerInfo(
	    		@FormDataParam("buyer_id") String buyer_id) throws Exception{
        
//        Connection conn = DatabaseConnection.getConnection();
        Buyer buyer=DAO_Buyer.get_buyer_details(Integer.parseInt(buyer_id));
        JSONObject buy = new JSONObject();
        buy.put("name", buyer.getName());
        buy.put("email", buyer.getEmail());
        buy.put("mobile", buyer.getMobile());
        buy.put("dob", buyer.getDob());
        buy.put("address_1", buyer.getAddress_1());
        buy.put("address_2", buyer.getAddress_2());
        URI uri;
        if(buyer!=null)
        {
        	String url="http://localhost:8080"+"/EC/items.html?name="+buyer.getName();
        	uri=new URI(url);
        	
        }
        else
        {
        	String url="http://localhost:8080"+"/EC/404.html";
        	uri=new URI(url);
//        	return Response.status(Status.MOVED_PERMANENTLY).location(uri).build();
        }
        return Response.status(Status.MOVED_PERMANENTLY).location(uri).build();
        
//        return buy.toString();
        
        
//        return "abc";
        
     }
	
	
	
	
	
	

}
