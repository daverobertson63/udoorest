package com.dhr.quad;

import java.io.IOException;
import java.util.*;

import com.dhr.quad.GpioPin.PinMode;
import com.dhr.quad.GpioPin.PinState;
import com.dhr.quad.Serial;

public class GpioService {

	/// Create a list of pins we want to use 
	private Map<String, GpioPin> GpioPins = new HashMap<>();
	static Serial UDOOserial;


	// Get all pint details
	public List<GpioPin> getAllGpioPins() {
		//TODO - Need to load these pin details
		return new ArrayList<>(GpioPins.values());
	}

	public void  SerialMessageService(String Message)
	{
		
		UDOOserial = new Serial();

		System.out.println("connecting to serial port...");
		try {
			UDOOserial.connect("/dev/ttyS0");
			System.out.println("Connected!");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		
		}
		
		try{
			
			UDOOserial.read_startBufferThread();
			byte[] b = Message.getBytes();
			UDOOserial.writeByteArray(b);

			Thread.sleep(100);
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
		UDOOserial.disconnect();
		return;	
		
	}
	
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

	public String getPinUri(int Id)
	{

		GpioPin pin = new GpioPin(Id);

		return pin.getUri();



	}


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




public Map<String, GpioPin> getGpioPins() {
	return GpioPins;
}

public void setGpioPins(Map<String, GpioPin> gpioPins) {
	GpioPins = gpioPins;
}

private void failIfInvalid(int id) {
	if (id < 0) {
		throw new IllegalArgumentException("Parameter is wrong");
	}

}
}