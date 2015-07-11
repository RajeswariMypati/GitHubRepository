/**
 * 
 */
package com.online.groceries.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.groceries.orm.Grocery;
import com.online.groceries.orm.dao.GroceryDAO;
import com.online.groceries.util.GroceryServiceException;
import com.online.groceries.web.vo.GroceryVO;

/**
 * @author ravikumv
 *
 */

@Service
public class GroceryServiceImpl implements GroceryService {
	
	
	@Autowired
	GroceryDAO groceryDAO;

	private static final Logger logger = LoggerFactory.getLogger(GroceryServiceImpl.class);

	private String loggerPrString = "GroceryServiceImpl.class ";
	/* (non-Javadoc)
	 * @see com.online.groceries.service.GroceryService#addGrocery(com.online.groceries.web.GroceryVO)
	 */
	@Transactional
	public void addGrocery(GroceryVO groceryVO) throws GroceryServiceException {
		// TODO Auto-generated method stub
		try {
			logger.debug(loggerPrString+" addGrocery: groceryVO =  "+groceryVO);
			Grocery grocery = new Grocery(groceryVO);
			//BeanUtils.copyProperties(groceryVO, grocery);
			logger.debug(loggerPrString+" addGrocery: grocery =  "+grocery);
			groceryDAO.addGrocery(grocery);			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(loggerPrString+" addGrocery, Exception is: "+e);
			throw new GroceryServiceException("Error from DAO during addGrocery",e);
		}
	}
	
	@Transactional
	public void batchGroceryInsert(List<GroceryVO> groceryVOList) throws GroceryServiceException {
		// TODO Auto-generated method stub
		List<Grocery> groceryList = new ArrayList<Grocery>();
		try {
			for (Iterator iterator = groceryVOList.iterator(); iterator
					.hasNext();) {
				//BeanUtils.copyProperties(groceryVO, grocery);
				GroceryVO groceryVO = (GroceryVO) iterator.next();
				logger.debug(loggerPrString+" addGrocery: groceryVO =  "+groceryVO);
				Grocery grocery = new Grocery(groceryVO);
				logger.debug(loggerPrString+" addGrocery: grocery =  "+grocery);
				groceryList.add(grocery);
			}
			groceryDAO.batchGroceryInsert(groceryList);			
		} catch(Exception e) {
			logger.error(loggerPrString+" addGrocery, Exception is: "+e);
			throw new GroceryServiceException("Error from DAO during addGrocery",e);
		}
	}
	
	@Transactional
	public GroceryVO getGroceryById(int groceryId) throws GroceryServiceException {
		// TODO Auto-generated method stub
		Grocery grocery = groceryDAO.getGroceryById(groceryId);
		GroceryVO vo = new GroceryVO(grocery);
		return vo;
	}


	/* (non-Javadoc)
	 * @see com.online.groceries.service.GroceryService#updateGrocery(com.online.groceries.web.GroceryVO)
	 */
	@Transactional
	public void updateGrocery(GroceryVO groceryVO)  throws GroceryServiceException {
		try {
			/*
			 * As we are separating VO and entity objects by restricting to the layer that they belongs to (to achieve loose coupling)
			 * we need to load the entity object and update manually to save it back to data base.
			 * In case of Entity objects is share across layers, we can load it and can call merge directly. 
			 */
			logger.debug(loggerPrString+",  updateGrocery for = "+groceryVO.getGroceryId());  
			Grocery grocery = groceryDAO.getGroceryById(groceryVO.getGroceryId());
			grocery.setGroceryName(groceryVO.getGroceryName());
			grocery.setGroceryPrice(groceryVO.getGroceryPrice());
			grocery.setGroceryStock(groceryVO.getGroceryStock());
			groceryDAO.updateGrocery(grocery);			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(loggerPrString+",  updateGrocery :  Exception e = "+e.getMessage());
			throw new GroceryServiceException("Error from DAO during updateGrocery",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.online.groceries.service.GroceryService#filterGroceryByName(java.lang.String)
	 */
	@Transactional
	public List<GroceryVO>  filterGroceryByName(String groceryName)  throws GroceryServiceException {
		// TODO Auto-generated method stub
		List<GroceryVO> groceryVOList = new ArrayList<GroceryVO>();
		try {
			List<Grocery> groceryList = groceryDAO.filterGroceryByName(groceryName);
			for (Grocery grocery : groceryList) {
				GroceryVO vo = new GroceryVO(grocery);
				groceryVOList.add(vo);
			}
		} catch(Exception e) {
			logger.error("getGroceriesList Exception is: "+e);			
			throw new GroceryServiceException("Error from DAO during filterGroceryByName",e);
		}
		return groceryVOList;
	}

	/* (non-Javadoc)
	 * @see com.online.groceries.service.GroceryService#getGroceriesList()
	 */
	@Transactional
	public List<GroceryVO> getGroceriesList() throws GroceryServiceException {
		// TODO Auto-generated method stub
		List<GroceryVO> groceryVOList = new ArrayList<GroceryVO>();
		try {
			logger.debug(loggerPrString+" getGroceriesList:");
			List<Grocery> groceryList = groceryDAO.getGroceryList();
			logger.debug(loggerPrString+" getGroceriesList, Lst:   size = "+groceryList.size());
			for (Grocery grocery : groceryList) {
				GroceryVO vo = new GroceryVO(grocery);
				//BeanUtils.copyProperties(grocery, vo);
				groceryVOList.add(vo);
			}
			logger.debug(loggerPrString+" getGroceriesList:  "+groceryVOList.size());
			groceryDAO.getGroceryList();			
		} catch(Exception e) {
			logger.error(loggerPrString+" getGroceriesList Exception is: "+e);
			throw new GroceryServiceException("Error from DAO during getGroceriesList",e);
		}
		return groceryVOList;
	}

}
