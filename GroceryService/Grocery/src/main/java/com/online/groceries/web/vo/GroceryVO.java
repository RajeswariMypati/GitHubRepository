/**
 * 
 */
package com.online.groceries.web.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.online.groceries.orm.Grocery;

/**
 * @author ravikumv
 *
 */
@JsonPropertyOrder({"groceryId", "groceryName", "groceryPrice", "groceryStock","groceryUpdatedOn"})
public class GroceryVO {
	
	private int id;
	private Integer groceryId;
	private String groceryName;
	private Double groceryPrice;
	private Long groceryStock;
	private String groceryUpdatedOn;

	public GroceryVO() {
	}
	
	public GroceryVO(Grocery grocery) {
		this.id = grocery.getGroceryId();		
		this.groceryId = grocery.getGroceryId();
		this.groceryName = grocery.getGroceryName();
		this.groceryPrice = grocery.getGroceryPrice();
		this.groceryStock = grocery.getGroceryStock();
		if(grocery.getGroceryUpdatedOn()!=null) {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			this.groceryUpdatedOn = sdFormat.format(grocery.getGroceryUpdatedOn());			
		}
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroceryUpdatedOn() {
		return groceryUpdatedOn;
	}

	public void setGroceryUpdatedOn(String groceryUpdatedOn) {
		this.groceryUpdatedOn = groceryUpdatedOn;
	}

	public Integer getGroceryId() {
		return groceryId;
	}
	public void setGroceryId(Integer groceryId) {
		this.groceryId = groceryId;
	}
	public String getGroceryName() {
		return groceryName;
	}
	public void setGroceryName(String groceryName) {
		this.groceryName = groceryName;
	}
	public Double getGroceryPrice() {
		return groceryPrice;
	}
	public void setGroceryPrice(Double groceryPrice) {
		this.groceryPrice = groceryPrice;
	}
	public Long getGroceryStock() {
		return groceryStock;
	}
	public void setGroceryStock(Long groceryStock) {
		this.groceryStock = groceryStock;
	}

	@JsonIgnore
	public String toString() {
		return "GroceryVO [groceryId=" + groceryId + ", groceryName="
				+ groceryName + ", groceryPrice=" + groceryPrice
				+ ", groceryStock=" + groceryStock + ", groceryUpdatedOn="
				+ groceryUpdatedOn + "]";
	}

}
