/**
* File  : GetProduct.java
* Description          : This GetProduct is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 19, 2017      	595251  	 Initial version
*/
package com.bid;

import java.util.List;

import com.bid.common.BidConstants;
import com.bid.common.util.BidUtil;
import com.bid.exception.BidException;
import com.bid.vo.Product;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.CouchDbException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @author 595251
 *
 */
public class GetProduct {
	
	public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();
        String status = BidConstants.STATUS_SUCCESS;
        System.out.println("...Get Product....");
        try{
        	
			Database db = BidUtil.getConnection();
			
			List<Product> productList = BidUtil.getProducts(db);
			
			response.addProperty(BidConstants.PRODUCTLIST, new Gson().toJson(productList));
			
		} catch (CouchDbException e) {
			throw new RuntimeException("Unable to connect to repository", e);
		}
        catch (Exception e){
			throw new RuntimeException("others....", e);
		}
	    response.addProperty(BidConstants.STATUS, status);
	    return response;
	}

}
