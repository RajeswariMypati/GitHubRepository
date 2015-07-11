/**
 * 
 */
package com.online.groceries.util;

/**
 * @author ravikumv
 *
 */
public class GroceryServiceException extends Exception {

	private String errorCode;
	private String errorMSG;
    
    public GroceryServiceException(String msg) {
        super(msg);
    }
    
    
	public GroceryServiceException(String errorCode, String errorMSG, Throwable cause) {
    	// Log msg here with a Spring AOP
        super(errorMSG, cause);		
		this.errorCode = errorCode;
    }
	
    public GroceryServiceException(String msg, Throwable cause) {
    	// Log msg here with a Spring AOP 
        super(msg, cause);
    }
    
    public GroceryServiceException(Throwable cause) {
        super(cause);
    }
    
    @Override
    public String toString() {
        return errorMSG;
    }
}
