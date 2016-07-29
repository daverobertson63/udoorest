/*
 * 
 */
package com.dhr.quad;

import static spark.Spark.*;

import com.dhr.quad.BoardInfo;

import static com.dhr.quad.JsonUtil.*;


// TODO: Auto-generated Javadoc
/**
 * The Class GpioController.
 */
public class GpioController {

	/**
	 * Instantiates a new gpio controller.
	 *
	 * @param gpioService the gpio service
	 */
	public GpioController(final GpioService gpioService) {

		get("/gpio", (req, res) -> gpioService.getAllGpioPins(), json());
		
		get("/message/:message", (req, res) -> {
			
			String message = req.params(":message");
			
			// Send message to serial port
			
			try {
				gpioService.SerialMessageService(message);
			}
			catch (Exception e)
			{
				res.status(400);
				return new ResponseError("Message has failed");
	
			}
			return message;
			
						
		}, json());


		get("/gpioread/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			
			GpioPin pin = gpioService.getGpioPin(id);
			
			if (pin != null) {
				return pin;
			}
			
			res.status(400);
			return new ResponseError("No pin with id '%s' found", Integer.toString(id));
		}, json());

		get("/gpiowrite/:id/:value", (req, res) -> {
			
			int id = Integer.parseInt(req.params(":id"));
			int value = Integer.parseInt(req.params(":value"));
			
			GpioPin pin = gpioService.updateGpioPin(id,value);
			
			if (pin != null) {
				return value;
			}
			res.status(400);
			return new ResponseError("No pin with id '%s' found", Integer.toString(id));
		}, json());

		get("/board","application/json", (req, res) -> {

			String id = req.queryParams("id");
			
			if (id == null )
				id = "Null ID";
			
			return("UDOO Quad/Dual Java RESTful platform tools for: "+ BoardInfo.getBoardModel() + " - Query Param test: " + id);
			
			
		}, json());
		
		get("/gpiouri/:id", (req, res) -> {

			int id = Integer.parseInt(req.params(":id"));
			
			String uri = gpioService.getPinUri(id);
			
			return(uri);
			
			
		}, json());

		

		
		after((req, res) -> {
			res.type("application/json");
		});

		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});
	}
}