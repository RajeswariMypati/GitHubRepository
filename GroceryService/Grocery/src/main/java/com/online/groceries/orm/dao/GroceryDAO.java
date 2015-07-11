/**
 * 
 */
package com.online.groceries.orm.dao;

import java.util.List;

import com.online.groceries.orm.Grocery;

/**
 * @author ravikumv
 *
 */
public interface GroceryDAO {

	public void addGrocery(Grocery grocery);

	public void updateGrocery(Grocery grocery);
	
	public List<Grocery> filterGroceryByName(String name);
	
	public List<Grocery> getGroceryList();
	
	public Grocery getGroceryById(int groceryId);
	
	public void batchGroceryInsert(List<Grocery> groceryList);
}
