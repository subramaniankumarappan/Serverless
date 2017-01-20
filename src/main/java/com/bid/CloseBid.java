package com.bid;
/**
* File  : CloseBid.java
* Description          : This CloseBid is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 10, 2017      	595251  	 Initial version
*/


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bid.common.BidConstants;
import com.bid.common.util.BidUtil;
import com.bid.vo.Bid;
import com.bid.vo.BidOffer;
import com.bid.vo.BidsLog;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.CouchDbException;
import com.google.gson.JsonObject;

/**
 * @author 595251
 *
 */
public class CloseBid {
	/*
	public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();
        String user = "b63e7173-a981-4386-a6f0-22636d93ce0b-bluemix";
		String password = "bb26814ef7a0dfc0b18f15eaf08006f2c90cd40d1cfe0b7226f8d566336e6eba";
		String databaseName = "bid_db";
		System.out.println("  ...1....");
		JsonObject res = new JsonObject();
		System.out.println("  ...2....");
		
        try{
        	System.out.println("  ...3....");
    		
			CloudantClient cloudant = ClientBuilder.account(user).username(user).password(password).build();
			System.out.println("  ...4....");
			
			Database db = cloudant.database(databaseName, false);
			
			System.out.println("  ABCD : " +db.info());
			
        }catch (Exception e){
			throw new RuntimeException("others....", e);
		}
        response.addProperty("Status", "Success");
        return response;
    }
	*/	
	
	public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();

		
		System.out.println("  ...Processing CloseBid....");
		
        try{
        	
			Database db = BidUtil.getConnection();
			
			//Bid b = db.find(Bid.class, "B-01");
			List <BidOffer> bidOfferList  = BidUtil.getOpenBidOffers(db);
			BidOffer bidOffer;
			BidsLog bidsLog;
			String bidEndDate;
			
			SimpleDateFormat sdf = new SimpleDateFormat(BidConstants.BID_DATE_FORMAT);
			Date date, currDate;
			Double gain;
			
			for (int i=0; i< bidOfferList.size(); i++){
				bidOffer = bidOfferList.get(i);
				bidEndDate = bidOffer.getEndDate() ;
				date = sdf.parse(bidEndDate);
				currDate = new Date();
				System.out.println("  date..." +date);
				System.out.println("  currDate..." +currDate);
				if (date.before(currDate)){
				
					List <Bid> bidList  = BidUtil.getBids(db, bidOffer.getID());
					Bid selectBid = bidList.get(0);
					
					for (int j=0; j< bidList.size(); j++){
						if (Integer.parseInt(selectBid.getBidPrice()) < Integer.parseInt(bidList.get(j).getBidPrice()))
							selectBid = bidList.get(j);								
					}
					System.out.println("  selected Bid for BidOfferID:" +bidOffer.getID() 
							   + "is : " +selectBid);
					bidOffer.setState(BidConstants.PROPERTY_STATE_CLOSED);
					System.out.println("  updating BidOffer..." +BidUtil.updateBidOffer(db, bidOffer));
					bidsLog = new BidsLog();
					bidsLog.set_id(BidUtil.createKey(BidConstants.PROPERTY_TABLE_PREFIX_BL,BidUtil.getKey(db)));
					bidsLog.set_rev(null);
					bidsLog.setID(bidsLog.get_id());
					bidsLog.setBidFinalisedDate(sdf.format(currDate));
					bidsLog.setBidID(selectBid.getID());
					bidsLog.setBidWinnerEmails(selectBid.getBidEmail());
					bidsLog.setStatus(BidConstants.PROPERTY_STATE_NOTPROCESSED);
					gain = Double.parseDouble(selectBid.getBidPrice()) - Double.parseDouble(bidOffer.getBasePrice());
					bidsLog.setGain(gain.toString());
					
					System.out.println("  creating BidsList..." +BidUtil.createBidsLog(db, bidsLog));
					
					
				}
			}
			
			
		} catch (CouchDbException e) {
			throw new RuntimeException("Unable to connect to repository", e);
		}catch (Exception e){
			throw new RuntimeException("others....", e);
		}
        response.addProperty(BidConstants.STATUS, BidConstants.STATUS_SUCCESS);
        return response;
    }
	
	
	
	
	
	
	
	

	

}
