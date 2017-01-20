/**
* File  : PlaceBid.java
* Description          : This PlaceBid is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 19, 2017      	595251  	 Initial version
*/
package com.bid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bid.common.BidConstants;
import com.bid.common.util.BidUtil;
import com.bid.exception.BidException;
import com.bid.vo.Bid;
import com.bid.vo.BidOffer;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.CouchDbException;
import com.google.gson.JsonObject;

/**
 * @author 595251
 *
 */
public class PlaceBid {
	
	public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();
        String status = BidConstants.STATUS_SUCCESS;
        System.out.println("...Placing Bid....");
        try{
        	
			Database db = BidUtil.getConnection();
			int key = BidUtil.getKey(db);
			Bid bid = getBid(args, key);
			
			List <BidOffer> bidOfferList = BidUtil.getBidOffers(db, bid.getBidOfferID());
			
			if(bidOfferList == null || bidOfferList.isEmpty() )
				throw new BidException("No Valid BidOffer");
			
			if (bidOfferList.size() > 1) 
				throw new BidException("More than one BidOffer available, Something wrong!!!");
			
			BidOffer bidOffer = bidOfferList.get(0);
			
			if(Double.parseDouble(bid.getBidPrice()) < Double.parseDouble(bidOffer.getBasePrice()))
				throw new BidException("Bid Price should be equal to or more than Base Price");
			
			if (!BidConstants.PROPERTY_STATE_OPEN.equalsIgnoreCase(bidOffer.getState()))
				throw new BidException("Bid is in " +bidOffer.getState() +"status and placing bid is not possible ");
			
			if(new SimpleDateFormat(BidConstants.BID_DATE_FORMAT).parse(bid.getBidDate()).after(new SimpleDateFormat(BidConstants.BID_DATE_FORMAT).parse(bidOffer.getEndDate())))
					throw new BidException("BidOffer End Date is passed and  placing bid is not possible");		
			BidUtil.addBid(db, bid);
			
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
	
	
	private static Bid getBid(JsonObject args, int key) throws BidException{
		Bid bid = new Bid();
				
		if(args.get(BidConstants.PROPERTY_BIDOFFERID) != null 
				&& !args.get(BidConstants.PROPERTY_BIDOFFERID).equals(""))
			bid.setBidOfferID(args.get(BidConstants.PROPERTY_BIDOFFERID).getAsString());
		else
			throw new BidException("No BidOfferID, Please provide it");
		
		if(args.get(BidConstants.PROPERTY_BIDEMAIL) != null 
				&& !args.get(BidConstants.PROPERTY_BIDEMAIL).equals(""))
			bid.setBidEmail(args.get(BidConstants.PROPERTY_BIDEMAIL).getAsString());
		else
			throw new BidException("No BidEmail Id, Please provide it");
		
		
		if(args.get(BidConstants.PROPERTY_BIDPRICE) != null 
				&& !args.get(BidConstants.PROPERTY_BIDPRICE).equals("")
				&& args.get(BidConstants.PROPERTY_BIDPRICE).getAsDouble() > 0)
			bid.setBidPrice(args.get(BidConstants.PROPERTY_BIDPRICE).getAsString());
		else
			throw new BidException("Please provide valid Bid Price");
		
		
		bid.setBidDate(new SimpleDateFormat(BidConstants.BID_DATE_FORMAT).format(new Date()));
		bid.setTable(BidConstants.PROPERTY_TABLE_BID);
		String seqKey = BidUtil.createKey(BidConstants.PROPERTY_TABLE_PREFIX_B, key);
		
		bid.set_id(seqKey);	
		bid.setID(seqKey);
		
		return bid;
		
	}
}

