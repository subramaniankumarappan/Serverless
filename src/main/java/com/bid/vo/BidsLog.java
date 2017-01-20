/**
* File  : BidsLog.java
* Description          : This BidsLog is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 17, 2017      	595251  	 Initial version
*/
package com.bid.vo;

/**
 * @author 595251
 *
 */
public class BidsLog {
	
	private String _id;
	private String _rev;
	
	
	private String ID;
	private String Table = "BidsLog";
	private String BidID;
	private String BidWinnerEmails;
	private String BidFinalisedDate;
	private String Gain;
	private String Status;
	
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
	public String getBidID() {
		return BidID;
	}
	public void setBidID(String bidID) {
		BidID = bidID;
	}
	public String getBidWinnerEmails() {
		return BidWinnerEmails;
	}
	public void setBidWinnerEmails(String bidWinnerEmails) {
		BidWinnerEmails = bidWinnerEmails;
	}
	public String getBidFinalisedDate() {
		return BidFinalisedDate;
	}
	public void setBidFinalisedDate(String bidFinalisedDate) {
		BidFinalisedDate = bidFinalisedDate;
	}
	public String getGain() {
		return Gain;
	}
	public void setGain(String gain) {
		Gain = gain;
	}
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	@Override
	public String toString() {
		return "BidsLog [_id=" + _id + ", _rev=" + _rev + ", ID=" + ID
				+ ", Table=" + Table + ", BidID=" + BidID
				+ ", BidWinnerEmails=" + BidWinnerEmails
				+ ", BidFinalisedDate=" + BidFinalisedDate + ", Gain=" + Gain
				+ ", Status=" + Status + "]";
	}
	
	

}
