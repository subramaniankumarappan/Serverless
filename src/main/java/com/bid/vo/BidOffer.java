package com.bid.vo;
/**
* File  : BidOffer.java
* Description          : This BidOffer is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 10, 2017      	595251  	 Initial version
*/


/**
 * @author 595251
 *
 */
public class BidOffer {
	private String _id;
	private String _rev;
	
	private String ID;
	private String Table;
	private String State;
	private String Description;
	private String ProductID;
	private String StartDate;
	private String EndDate;
	private String BasePrice;
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
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public String getBasePrice() {
		return BasePrice;
	}
	public void setBasePrice(String basePrice) {
		BasePrice = basePrice;
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
	
	public String getProductID() {
		return ProductID;
	}
	public void setProductID(String productID) {
		ProductID = productID;
	}
	@Override
	public String toString() {
		return "BidOffer [_id=" + _id + ", _rev=" + _rev + ", ID=" + ID
				+ ", Table=" + Table + ", State=" + State + ", Description="
				+ Description + ", ProductID=" + ProductID + ", StartDate="
				+ StartDate + ", EndDate=" + EndDate + ", BasePrice="
				+ BasePrice + "]";
	}

	
}
