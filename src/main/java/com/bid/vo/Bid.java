package com.bid.vo;
/**
* File  : Bid.java
* Description          : This Bid is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 10, 2017      	595251  	 Initial version
*/


/**
 * @author 595251
 *
 */
public class Bid {
	
	private String _id;
	private String _rev;
	
	private String ID;
	private String Table;
	private String BidOfferID;
	private String BidDate;
	private String BidPrice;
	private String BidEmail;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTable() {
		return Table;
	}
	public void setTable(String table) {
		Table = table;
	}
	public String getBidOfferID() {
		return BidOfferID;
	}
	public void setBidOfferID(String bidOfferID) {
		BidOfferID = bidOfferID;
	}
	public String getBidDate() {
		return BidDate;
	}
	public void setBidDate(String bidDate) {
		BidDate = bidDate;
	}
	public String getBidPrice() {
		return BidPrice;
	}
	public void setBidPrice(String bidPrice) {
		BidPrice = bidPrice;
	}
	
	public String getBidEmail() {
		return BidEmail;
	}
	public void setBidEmail(String bidEmail) {
		BidEmail = bidEmail;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_rev() {
		return _rev;
	}
	public void set_rev(String _rev) {
		this._rev = _rev;
	}
	@Override
	public String toString() {
		return "Bid [_id=" + _id + ", _rev=" + _rev + ", ID=" + ID + ", Table="
				+ Table + ", BidOfferID=" + BidOfferID + ", BidDate=" + BidDate
				+ ", BidPrice=" + BidPrice + ", BidEmail=" + BidEmail + "]";
	}
	
	

}
