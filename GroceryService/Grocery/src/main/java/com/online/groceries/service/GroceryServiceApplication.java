/**
 * 
 */
package com.online.groceries.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.online.groceries.orm.dao.GroceryDAOImpl;

/**
 * @author U21743
 *
 */
public class GroceryServiceApplication extends ResourceConfig {
	 /**
		* Register JAX-RS application components.
		*/	
		public GroceryServiceApplication(){
			register(RequestContextFilter.class);
			register(GroceryDAOImpl.class);
			register(GroceryServiceImpl.class);
			register(JacksonFeature.class);		
		}
}
