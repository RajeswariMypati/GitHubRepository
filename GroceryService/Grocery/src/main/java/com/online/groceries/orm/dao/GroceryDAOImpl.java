package com.online.groceries.orm.dao;

import java.util.Iterator;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.online.groceries.orm.Grocery;
import com.online.groceries.util.HibernateQueryConstants;

@Repository
public class GroceryDAOImpl implements GroceryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(GroceryDAOImpl.class);

	private String loggPreString = "GroceryDAOImpl.class ";
	
	@Override
	public void addGrocery(Grocery grocery) {
		// TODO Auto-generated method stub
		logger.debug(loggPreString+" addGrocery: grocery =  "+grocery);
		sessionFactory.getCurrentSession().save(grocery);
	}
	
	
	@Override
	public void batchGroceryInsert(List<Grocery> groceryList) {
		// TODO Auto-generated method stub
		Query deleteQuery = sessionFactory.getCurrentSession().createSQLQuery(HibernateQueryConstants.QUERY_DELETE_GROCERY_TABLE);
		deleteQuery.executeUpdate();
		for (int i=0; i<= groceryList.size(); i++) {
			Grocery grocery =  groceryList.get(i);
			logger.debug(loggPreString+" batchGroceryInsert: grocery =  "+grocery);
			sessionFactory.getCurrentSession().save(grocery);
			if(i==groceryList.size()-1) {
				sessionFactory.getCurrentSession().flush();
			}
		}
	}

	@Override
	public void updateGrocery(Grocery grocery) {
		/*
		 * As it is simple update, we can directly go for update, 
		 * whereas if transaction involves business logic across multiple entity objects
		 * then we need to merge depending on the need and how we want to control Transaction & it's overhead.  
		 */
		sessionFactory.getCurrentSession().update(grocery);
	}

	@Override
	public Grocery getGroceryById(int groceryId) {
		// TODO Auto-generated method stub
		logger.debug(loggPreString+" getGroceryById, Lst:  "+groceryId);
		return (Grocery)sessionFactory.getCurrentSession().get(Grocery.class, groceryId);
	}

	@Override
	public List<Grocery> filterGroceryByName(String groceryName) {
		// TODO Auto-generated method stub
		Query hQuery = sessionFactory.getCurrentSession().createQuery(HibernateQueryConstants.QUERY_GROCERY_LIST_BY_NAME);
		hQuery.setParameter("groceryName", '%'+groceryName+'%');
		return hQuery.list();
	}

	@Override
	public List<Grocery> getGroceryList() {
		// TODO Auto-generated method stub
		List<Grocery> groceryList = sessionFactory.getCurrentSession().createQuery(HibernateQueryConstants.QUERY_GROCERY_LIST).list();
		logger.debug(loggPreString+" getGroceryList, Lst:  "+groceryList);
		return groceryList;
	}

}
