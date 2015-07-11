/**
 * 
 */
package com.online.groceries.orm;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.util.StringUtils;

import com.online.groceries.web.vo.GroceryVO;

/**
 * @author ravikumv
 *
 */

@Entity
@Table(name="GROCERY")
public class Grocery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GROCERY_ID", nullable = false)
	/*@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GROCERY_SEQ")
	@SequenceGenerator(name="GROCERY_SEQ", sequenceName="GROCERY_SEQ", allocationSize = 2)*/
	private Integer groceryId;
	
	@Column(name = "GROCERY_NAME", nullable = false)
	private String groceryName;
	
	@Column(name = "GROCERY_PRICE")
	private Double groceryPrice;
	
	@Column(name = "GROCERY_STOCK_LEVEL")
	private Long groceryStock;
	
	@Column(name = "GROCERY_UPDATED_ON")
	@Temporal(TemporalType.DATE)	
	private Date groceryUpdatedOn;

	public Grocery() {
	}
	
	public Grocery(GroceryVO vo) {
		if(vo.getGroceryId() !=null && vo.getGroceryId() > 0) {
			this.groceryId = vo.getGroceryId();			
		}
		this.groceryName = vo.getGroceryName();
		this.groceryPrice = vo.getGroceryPrice();
		this.groceryStock = vo.getGroceryStock();
		if(StringUtils.hasText(vo.getGroceryUpdatedOn())) {
			try {
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");				
				this.groceryUpdatedOn = sdFormat.parse(vo.getGroceryUpdatedOn());				
			} catch (Exception e) {
				this.groceryUpdatedOn = new Date();
			}
		}else {
			this.groceryUpdatedOn = new Date();			
		}
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


	public Date getGroceryUpdatedOn() {
		return groceryUpdatedOn;
	}

	public void setGroceryUpdatedOn(Date groceryUpdatedOn) {
		this.groceryUpdatedOn = groceryUpdatedOn;
	}

	@Override
	public String toString() {
		return "Grocery [groceryId=" + groceryId + ", groceryName="
				+ groceryName + ", groceryPrice=" + groceryPrice
				+ ", groceryStock=" + groceryStock + ", groceryUpdatedOn="
				+ groceryUpdatedOn + "]";
	}

}
