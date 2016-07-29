/*
 * 
 */
package com.dhr.quad;

import java.io.IOException;
import java.util.*;

import com.dhr.quad.GpioPin.PinMode;
import com.dhr.quad.GpioPin.PinState;
import com.dhr.quad.Serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

// TODO: Auto-generated Javadoc
/**
 * The Class GpioService.
 */
public class GpioService {

	
	
	/** The Gpio pins. */
	/// Create a list of pins we want to use 
	private Map<String, GpioPin> GpioPins = new HashMap<>();
	
	/** The UDOO serial for messages*/
	static Serial UDOOserial;


	/**
	 * Gets the all gpio pins.
	 *
	 * @return the all gpio pins
	 */
	// Get all pint details
	public List<GpioPin> getAllGpioPins() {
		//TODO - Need to load these pin details
		return new ArrayList<>(GpioPins.values());
	}

	/**
	 * Serial message service.  Sends a single string message to the main listening port
	 *
	 * @param Message the message
	 */
	
	public void  SerialMessageService(String Message)
	{
		
		//String buffer = Message + System.lineSeparator();
		
		String buffer = new String(Message) +  System.lineSeparator();

		System.out.println("connecting to serial port...");
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("/dev/ttyS0");  
			
			System.out.println("Connected!");
			
			if (portIdentifier.isCurrentlyOwned()) {  
	            System.out.println("Port in use!");  
	        } else {  
	            // points who owns the port and connection timeout  
	            SerialPort serialPort = (SerialPort) portIdentifier.open("RS232Example", 2000);  
	              
	            // setup connection parameters  
	            serialPort.setSerialPortParams(  
	                9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	            
	            serialPort.getOutputStream().write(buffer.getBytes());
	            serialPort.getOutputStream().flush();
	            
	            serialPort.close();
	              
	            // setup serial port reader  
	            //new CommPortReceiver(serialPort.getInputStream()).start();  
	        }  
		}
		
				
		catch(Exception ioe) {
			ioe.printStackTrace();
		}
		
		
		
		// Message sent - no need to worry
		return;	
		
	}
	
	/**
	 * Gets the gpio pin.
	 *
	 * @param id the id
	 * @return the gpio pin
	 */
	// Get the pins value based on the ID
	public GpioPin getGpioPin(int id) {

		GpioPin pin = new GpioPin(id);

		try {
			PinMode val = pin.getCurrentPinMode();
			PinState state = pin.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Cannot get the pinmode ID '" + id + "' pin");
		}
		return pin;
	}

	/**
	 * Gets the pin uri.
	 *
	 * @param Id the id
	 * @return the pin uri
	 */
	public String getPinUri(int Id)
	{

		GpioPin pin = new GpioPin(Id);

		return pin.getUri();



	}


	/**
	 * Update gpio pin.
	 *
	 * @param id the id
	 * @param value the value
	 * @return the gpio pin
	 */
	public GpioPin updateGpioPin(int id, int value) {

		// Get the relevent  pin
		GpioPin pin = new GpioPin(id);

		if (pin == null) {
			throw new IllegalArgumentException("No GpioPin with id '" + id + "' found");
		}


		try {
			// Need to ensure the output mode is set here
			pin.setCurrentPinMode(PinMode.OUTPUT);
			if (value == 0 ) {
				pin.write(PinState.LOW);
			}
			else
			{
				pin.write(PinState.HIGH);
			}

			return pin;

		} catch (Exception e) {
			
			
			e.printStackTrace();
		}	
		
		return null;
	}




/**
 * Gets the gpio pins.
 *
 * @return the gpio pins
 */
public Map<String, GpioPin> getGpioPins() {
	return GpioPins;
}

/**
 * Sets the gpio pins.
 *
 * @param gpioPins the gpio pins
 */
public void setGpioPins(Map<String, GpioPin> gpioPins) {
	GpioPins = gpioPins;
}

/**
 * Fail if invalid.
 *
 * @param id the id
 */
private void failIfInvalid(int id) {
	if (id < 0) {
		throw new IllegalArgumentException("Parameter is wrong");
	}

}
}