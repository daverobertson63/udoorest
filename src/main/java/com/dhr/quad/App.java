package com.dhr.quad;

import static spark.Spark.*;
import static com.dhr.quad.JsonUtil.*;

public class App {
    public static void main(String[] args) {
    	
    	new GpioController(new GpioService());
        
    	get("/helloquad", (request, response) -> "We are alive and well here at Udoo Quad",json());
    	
    	// Test here for the pins - see if we can do something
    	
    	//GpioPin pin = new GpioPin(13,)
    	
    	
        
        
    }
}


