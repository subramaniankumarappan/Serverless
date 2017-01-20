package com.bid;

import com.bid.common.BidConstants;
import com.bid.common.util.BidUtil;
import com.bid.exception.BidException;
import com.bid.vo.BidOffer;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.CouchDbException;
import com.google.gson.JsonObject;
/**
 * File  : CreateBidOffer.java
 * Description          : This CreateBidOffer is   
 * Revision History :
 * Version      Date            	Author       Reason
 * 0.1          Jan 18, 2017      	595251  	 Initial version
 */

/**
 * @author 595251
 *
 */
public class AddBidOffer {

	public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();
        String status = BidConstants.STATUS_SUCCESS;
        System.out.println("...Creating BidOffer....");
        try{
        	
			Database db = BidUtil.getConnection();
			int key = BidUtil.getKey(db);
			BidOffer bidOffer = getBidOffer(args, key);
			if(!BidUtil.isProductAvailable(db, bidOffer.getProductID()))
				throw new BidException("Not a valid ProductID");
			
			BidUtil.addBidOffer(db, bidOffer);
			
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
	
	
	private static BidOffer getBidOffer(JsonObject args, int key) throws BidException{
		BidOffer bidOffer = new BidOffer();
		if(args.get(BidConstants.PROPERTY_DESCRIPTION) != null)
				bidOffer.setDescription(args.get(BidConstants.PROPERTY_DESCRIPTION).getAsString());
		if(args.get(BidConstants.PROPERTY_STARTDATE) != null 
				&& !args.get(BidConstants.PROPERTY_STARTDATE).equals(""))
			bidOffer.setStartDate(args.get(BidConstants.PROPERTY_STARTDATE).getAsString());
		else
			throw new BidException("No Start Date, Please provide it");
		
		if(args.get(BidConstants.PROPERTY_PRODUCTID) != null 
				&& !args.get(BidConstants.PROPERTY_PRODUCTID).equals(""))
			bidOffer.setProductID(args.get(BidConstants.PROPERTY_PRODUCTID).getAsString());
		else
			throw new BidException("No ProductID, Please provide it");
		
		if(args.get(BidConstants.PROPERTY_ENDDATE) != null 
				&& !args.get(BidConstants.PROPERTY_ENDDATE).equals(""))
			bidOffer.setEndDate(args.get(BidConstants.PROPERTY_ENDDATE).getAsString());
		else
			throw new BidException("No End Date, Please provide it");
		
		
		if(args.get(BidConstants.PROPERTY_BASEPRICE) != null 
				&& !args.get(BidConstants.PROPERTY_BASEPRICE).equals("")
				&& args.get(BidConstants.PROPERTY_BASEPRICE).getAsDouble() > 0)
			bidOffer.setBasePrice(args.get(BidConstants.PROPERTY_BASEPRICE).getAsString());
		else
			throw new BidException("Please provide valid Base Price");
		
		bidOffer.setTable(BidConstants.PROPERTY_TABLE_BIDOFFER);
		bidOffer.setState(BidConstants.PROPERTY_STATE_OPEN);
		String seqKey = BidUtil.createKey(BidConstants.PROPERTY_TABLE_PREFIX_BO, key);
		
		bidOffer.set_id(seqKey);	
		bidOffer.setID(seqKey);
		
		return bidOffer;
		
	}
}
