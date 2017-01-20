/**
* File  : AddProduct.java
* Description          : This AddProduct is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 18, 2017      	595251  	 Initial version
*/
package com.bid;

import com.bid.common.BidConstants;
import com.bid.common.util.BidUtil;
import com.bid.exception.BidException;
import com.bid.vo.Product;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.CouchDbException;
import com.google.gson.JsonObject;

/**
 * @author 595251
 *
 */
public class AddProduct {
	
	public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();
        String status = BidConstants.STATUS_SUCCESS;
        System.out.println("...Creating Product....");
        try{
        	
			Database db = BidUtil.getConnection();
			int key = BidUtil.getKey(db);
			Product product = getProduct(args, key);
			
			BidUtil.addProduct(db, product);
			
		} catch (CouchDbException e) {
			throw new RuntimeException("Unable to connect to repository", e);
		}catch (BidException e){
			status =e.getMessage();
		}
        catch (Exception e){
			throw new RuntimeException("others....", e);
		}
	    response.addProperty(BidConstants.STATUS, status);
	    return response;
	}
	
	
	private static Product getProduct(JsonObject args, int key) throws BidException{
		Product product = new Product();
		if(args.get(BidConstants.PROPERTY_DESCRIPTION) != null)
			product.setDescription(args.get(BidConstants.PROPERTY_DESCRIPTION).getAsString());
		if(args.get(BidConstants.PROPERTY_NAME) != null 
				&& !args.get(BidConstants.PROPERTY_NAME).equals(""))
			product.setName(args.get(BidConstants.PROPERTY_NAME).getAsString());
		else
			throw new BidException("No Product Name, Please provide it");
		
		if(args.get(BidConstants.PROPERTY_CATEGORY) != null 
				&& !args.get(BidConstants.PROPERTY_CATEGORY).equals(""))
			product.setCategory(args.get(BidConstants.PROPERTY_CATEGORY).getAsString());
		else
			throw new BidException("No Product Name, Please provide it");
		
		if(args.get(BidConstants.PROPERTY_PRICE) != null 
				&& !args.get(BidConstants.PROPERTY_PRICE).equals("")
				&& args.get(BidConstants.PROPERTY_PRICE).getAsDouble() > 0)
			product.setPrice(args.get(BidConstants.PROPERTY_PRICE).getAsString());
		else
			throw new BidException("Please provide valid Price");
		
		product.setTable(BidConstants.PROPERTY_TABLE_PRODUCT);
		String seqKey = BidUtil.createKey(BidConstants.PROPERTY_TABLE_PREFIX_P, key);
		
		product.set_id(seqKey);	
		product.setID(seqKey);
		
		return product;
		
	}

}
