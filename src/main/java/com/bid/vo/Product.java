/**
* File  : Product.java
* Description          : This Product is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Jan 18, 2017      	595251  	 Initial version
*/
package com.bid.vo;

/**
 * @author 595251
 *
 */
public class Product {
	
	private String _id;
	private String _rev;
	
	private String ID;
	private String Table;
	private String Name;
	private String Description;
	private String Price;
	private String Category;
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
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		this.Category = category;
	}
	@Override
	public String toString() {
		return "Product [_id=" + _id + ", _rev=" + _rev + ", ID=" + ID
				+ ", Table=" + Table + ", Name=" + Name + ", Description="
				+ Description + ", Price=" + Price + ", category=" + Category
				+ "]";
	}

	

}
