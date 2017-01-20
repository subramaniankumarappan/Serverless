/**
* File  : SeqKey.java
* Description          : This SeqKey is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 18, 2017      	595251  	 Initial version
*/
package com.bid.vo;

/**
 * @author 595251
 *
 */
public class SeqKey {
	
	private String _id;
	private String _rev;
	
	private String Key;
	private String Table;
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
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public String getTable() {
		return Table;
	}
	public void setTable(String table) {
		Table = table;
	}
	@Override
	public String toString() {
		return "SeqKey [_id=" + _id + ", _rev=" + _rev + ", Key=" + Key
				+ ", Table=" + Table + "]";
	}
	
	

}
