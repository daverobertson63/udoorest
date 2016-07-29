/*
 * 
 */
package com.dhr.quad;

import static spark.Spark.*;

import java.io.IOException;
import java.util.Properties;

import static com.dhr.quad.JsonUtil.*;

// TODO: Auto-generated Javadoc
/**
 * The Class App.
 */
public class App {
	
	/** The Constant CUSTOM_CHAR. */
	private final static char[] CUSTOM_CHAR = {
	        0b00000,
	        0b10010,
	        0b00000,
	        0b01100,
	        0b01100,
	        0b00000,
	        0b10010,
	        0b00000};
	
	/** The properties. */
	private static Properties properties;
	
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

    	properties = new Properties();
    	try {
			properties.load(App.class.getResourceAsStream("/version.properties"));
			System.out.println(
	                 App.getVersionString()
	                         + " (Java platform tools for "
	                         + BoardInfo.getBoardModel() + ")");
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	 
    	new GpioController(new GpioService());
        
    	get("/helloquad", (request, response) -> "We are alive and well here at Udoo Quad",json());
    	
    	// Test here for the pins - see if we can do something
    	
    	//GpioPin pin = new GpioPin(13,)
        
    }
    
    /**
     * Gets the version string.
     *
     * @return the version string
     */
    //@Override
    public static  String getVersionString() {
        return String.format("%s %s", properties.getProperty("app.name"),properties.getProperty("app.version"));
    }
}


