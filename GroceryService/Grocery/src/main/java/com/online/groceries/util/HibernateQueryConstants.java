package com.online.groceries.util;

public interface HibernateQueryConstants {

	public static final String QUERY_DELETE_GROCERY_TABLE = "delete from grocery";
	public static final String QUERY_GROCERY_LIST_BY_NAME = "from Grocery g where g.groceryName like :groceryName order by g.groceryUpdatedOn asc";
	public static final String QUERY_GROCERY_LIST = "from Grocery g order by g.groceryUpdatedOn asc"; 			
	
}
