/**
* File  : BidUtil.java
* Description          : This BidUtil is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 18, 2017      	595251  	 Initial version
*/
package com.bid.common.util;

import java.util.List;

import com.bid.common.BidConstants;
import com.bid.vo.Bid;
import com.bid.vo.BidOffer;
import com.bid.vo.BidsLog;
import com.bid.vo.Product;
import com.bid.vo.SeqKey;
import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.google.gson.JsonObject;

/**
 * @author 595251
 *
 */
public class BidUtil {
	
	
	
	public static Database getConnection(){		
		
		System.out.println("...Creating Connection...");
		CloudantClient cloudant = ClientBuilder.account(BidConstants.user).username(BidConstants.user).password(BidConstants.password).build();
		Database db = cloudant.database(BidConstants.databaseName, false);
		System.out.println("...Connection Created...");
		
		return db;
	}
	
	public static List<BidOffer> getOpenBidOffers(Database db){
		
		List <BidOffer> bidOfferList ;
		JsonObject selector = new JsonObject();
		selector.addProperty(BidConstants.PROPERTY_TABLE, BidConstants.PROPERTY_TABLE_BIDOFFER);
		selector.addProperty(BidConstants.PROPERTY_STATE, BidConstants.PROPERTY_STATE_OPEN);
		String selectorJson = selector.toString();
		System.out.println("  selectorJson is : " +selectorJson);
		bidOfferList =  (List<BidOffer>) db.findByIndex(selectorJson, BidOffer.class);
		System.out.println("  bidOfferList b is : " +bidOfferList);
		return bidOfferList;
		
	}
	
	public static List<BidOffer> getBidOffers(Database db, String bidOfferID){
		
		List <BidOffer> bidOfferList ;
		JsonObject selector = new JsonObject();
		selector.addProperty(BidConstants.PROPERTY_TABLE, BidConstants.PROPERTY_TABLE_BIDOFFER);
		selector.addProperty(BidConstants.PROPERTY_ID, bidOfferID);
		String selectorJson = selector.toString();
		System.out.println("  selectorJson is : " +selectorJson);
		bidOfferList =  (List<BidOffer>) db.findByIndex(selectorJson, BidOffer.class);
		System.out.println("  bidOfferList b is : " +bidOfferList);
		return bidOfferList;
		
	}

	public static List<Product> getProducts(Database db){
		
		List <Product> productList ;
		JsonObject selector = new JsonObject();
		selector.addProperty(BidConstants.PROPERTY_TABLE, BidConstants.PROPERTY_TABLE_PRODUCT);
		String selectorJson = selector.toString();
		System.out.println("  selectorJson is : " +selectorJson);
		productList =  (List<Product>) db.findByIndex(selectorJson, Product.class);
		System.out.println("  productList b is : " +productList);
		return productList;
		
	}
	
	
	public static List<Bid> getBids(Database db, String BidOfferId){
		
		List <Bid> bidList ;
		JsonObject selector = new JsonObject();
		selector.addProperty(BidConstants.PROPERTY_TABLE, BidConstants.PROPERTY_TABLE_BID);
		selector.addProperty(BidConstants.PROPERTY_BIDOFFERID, BidOfferId);
		String selectorJson = selector.toString();
		System.out.println("  selectorJson is : " +selectorJson);
		bidList =  (List<Bid>) db.findByIndex(selectorJson, Bid.class);
		System.out.println("  bidList b is : " +bidList);
		return bidList;
		
	}
	
	public static boolean createBidsLog(Database db, BidsLog bidsLog){
		
		System.out.println("  bidsLog is : " +bidsLog);
		Response response = db.save(bidsLog);
		System.out.println("  response is : " +response);
		if(response.getError() == null)
				return true;
		return false;
		
	}
	
	public static boolean updateBidOffer(Database db, BidOffer bidOffer){
		System.out.println("  bidOffer in update is : " +bidOffer);
		
		Response response = db.update(bidOffer);
		System.out.println("  response is : " +response);
		if(response.getError() == null)
				return true;
		return false;
		
		
	}
	
	public static boolean addBidOffer(Database db, BidOffer bidOffer){
		System.out.println("  bidOffer in addBidOffer is : " +bidOffer);
		
		Response response = db.save(bidOffer);
		System.out.println("  response is : " +response);
		if(response.getError() == null)
				return true;
		return false;
		
		
	}
	
	public static boolean addBid(Database db, Bid bid){
		System.out.println("  bid in addBid is : " +bid);
		
		Response response = db.save(bid);
		System.out.println("  response is : " +response);
		if(response.getError() == null)
				return true;
		return false;
		
		
	}
	
	public static boolean addProduct(Database db, Product product){
		System.out.println("  Product in update is : " +product);
		
		Response response = db.save(product);
		System.out.println("  response is : " +response);
		if(response.getError() == null)
				return true;
		return false;
		
		
	}
	
	public static boolean isProductAvailable(Database db, String productID){
		System.out.println("  productID in isProductAvailable is : " +productID);
		
		
		List <Product> productList;
		Product product ;
		JsonObject selector = new JsonObject();
		selector.addProperty(BidConstants.PROPERTY_TABLE, BidConstants.PROPERTY_TABLE_PRODUCT);
		selector.addProperty(BidConstants.PROPERTY_ID, productID);
		String selectorJson = selector.toString();
		System.out.println("  selectorJson is : " +selectorJson);
		productList =  (List<Product>) db.findByIndex(selectorJson, Product.class);
		
		if (!productList.isEmpty() && productList.size()>0)
			return true;
		
		return false;
		
		
	}
	
	public static int getKey(Database db){
		
		List <SeqKey> SeqKeyList;
		SeqKey key ;
		int sequence = 0;
		JsonObject selector = new JsonObject();
		selector.addProperty(BidConstants.PROPERTY_TABLE, BidConstants.PROPERTY_TABLE_SEQUENCE);
		String selectorJson = selector.toString();
		System.out.println("  selectorJson is : " +selectorJson);
		SeqKeyList =  (List<SeqKey>) db.findByIndex(selectorJson, SeqKey.class);
		
		key =  (SeqKey) SeqKeyList.get(0);
		System.out.println("  SeqKey  is : " +key);
		
		if (key.getKey() != null && !key.getKey().equals(""))
				sequence = Integer.parseInt(key.getKey());
		
		sequence = sequence+1;
		key.setKey(String.valueOf(sequence));
		//key.set_id(String.valueOf(sequence));
		//key.set_rev(null);
		updateSeqKey(db, key);	
		
		return sequence;
		
	}
	
	/**
	 * @param @return 
	 * @return List<BidsLog>
	 *
	 */
	public static List<BidsLog> getBidsLogList(Database db) {
		
		
		List <BidsLog> bidsLogList ;
		JsonObject selector = new JsonObject();
		selector.addProperty(BidConstants.PROPERTY_TABLE, BidConstants.PROPERTY_TABLE_BIDSLOG);
		selector.addProperty("Status", "NotProcessed");
		String selectorJson = selector.toString();
		System.out.println("  selectorJson is : " +selectorJson);
		bidsLogList =  (List<BidsLog>) db.findByIndex(selectorJson, BidsLog.class);
		System.out.println("  BidsLogList b is : " +bidsLogList);
		return bidsLogList;
		
		
	}
	
	/**
	 * @param @param bidsLog 
	 * @return void
	 *
	 */
	public static boolean updateBidsLog(Database db, BidsLog bidsLog) {
		System.out.println("  BidsLog in updateBidsLog is : " +bidsLog);
		
		Response response = db.update(bidsLog);
		System.out.println("  response is : " +response);
		if(response.getError() == null)
				return true;
		return false;
		
	}
	
	public static boolean updateSeqKey(Database db, SeqKey key){
		System.out.println("  SeqKey in update is : " +key);
		
		Response response = db.update(key);
		System.out.println("  response is : " +response);
		if(response.getError() == null)
				return true;
		return false;
		
		
	}
	
	public static String createKey(String table, int sequence){
		
		return table+"-"+sequence;
		
	}

}
