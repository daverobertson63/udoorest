/*
 * 
 */
package com.dhr.quad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/*
 * Use this class to create a GPIO pin to get or set values
 * 
 * 
 */

import java.util.UUID;


import com.dhr.quad.GpioService;

// TODO: Auto-generated Javadoc
/**
 * The Class GpioPin.
 */
@SuppressWarnings("unused")
public class GpioPin {

	// Map GPIO pins for QUAD/DUAL

	/** The Constant gpioPins. */
	// Pin map for Quad/Dual
	private static final Object[][] gpioPins = new Object[][]{
		{0,116,"/sys/class/gpio/gpio116/"},
		{1,112,"/sys/class/gpio/gpio112/"},
		{2,20,"/sys/class/gpio/gpio20/"},
		{3,16,"/sys/class/gpio/gpio16/"},
		{4,17,"/sys/class/gpio/gpio17/"},
		{5,18,"/sys/class/gpio/gpio18/"},
		{6,41,"/sys/class/gpio/gpio41/"},
		{7,42,"/sys/class/gpio/gpio42/"},
		{8,21,"/sys/class/gpio/gpio21/"},
		{9,19,"/sys/class/gpio/gpio19/"},
		{10,1,"/sys/class/gpio/gpio1/"},
		{11,9,"/sys/class/gpio/gpio9/"},
		{12,3,"/sys/class/gpio/gpio3/"},
		{13,40,"/sys/class/gpio/gpio40/"},
		{14,150,"/sys/class/gpio/gpio150/"},
		{15,162,"/sys/class/gpio/gpio162/"},
		{16,160,"/sys/class/gpio/gpio160/"},
		{17,161,"/sys/class/gpio/gpio161/"},
		{18,158,"/sys/class/gpio/gpio158/"},
		{19,159,"/sys/class/gpio/gpio159/"},
		{20,92,"/sys/class/gpio/gpio92/"},
		{21,85,"/sys/class/gpio/gpio85/"},
		{22,123,"/sys/class/gpio/gpio123/"},
		{23,124,"/sys/class/gpio/gpio124/"},
		{24,125,"/sys/class/gpio/gpio125/"},
		{25,126,"/sys/class/gpio/gpio126/"},
		{26,127,"/sys/class/gpio/gpio127/"},
		{27,133,"/sys/class/gpio/gpio133/"},
		{28,134,"/sys/class/gpio/gpio134/"},
		{29,135,"/sys/class/gpio/gpio135/"},
		{30,136,"/sys/class/gpio/gpio136/"},
		{31,137,"/sys/class/gpio/gpio137/"},
		{32,138,"/sys/class/gpio/gpio138/"},
		{33,139,"/sys/class/gpio/gpio139/"},
		{34,140,"/sys/class/gpio/gpio140/"},
		{35,141,"/sys/class/gpio/gpio141/"},
		{36,142,"/sys/class/gpio/gpio142/"},
		{37,143,"/sys/class/gpio/gpio143/"},
		{38,54,"/sys/class/gpio/gpio54/"},
		{39,205,"/sys/class/gpio/gpio205/"},
		{40,32,"/sys/class/gpio/gpio32/"},
		{41,35,"/sys/class/gpio/gpio35/"},
		{42,34,"/sys/class/gpio/gpio34/"},
		{43,33,"/sys/class/gpio/gpio33/"},
		{44,101,"/sys/class/gpio/gpio101/"},
		{45,144,"/sys/class/gpio/gpio144/"},
		{46,145,"/sys/class/gpio/gpio145/"},
		{47,89,"/sys/class/gpio/gpio89/"},
		{48,105,"/sys/class/gpio/gpio105/"},
		{49,104,"/sys/class/gpio/gpio104/"},
		{50,57,"/sys/class/gpio/gpio57/"},
		{51,56,"/sys/class/gpio/gpio56/"},
		{52,55,"/sys/class/gpio/gpio55/"},
		{53,88,"/sys/class/gpio/gpio88/"}};


		/** The id. */
		private int id;
		
		/** The name. */
		private String name;
		
		/** The value. */
		private int value;
		
		/** The uri. */
		private String uri;	// the URI path of the pin

		/** The Constant GPIO_DIRECTION_PATH. */
		private static final String GPIO_DIRECTION_PATH = "/direction";
		
		/** The Constant GPIO_VALUE_PATH. */
		private static final String GPIO_VALUE_PATH = "/value";
		
		/** The Constant MAXQUADPIN. */
		private static final int MAXQUADPIN= 53;


		/** The current pin state. */
		private PinState currentPinState = PinState.LOW;
		
		/** The current pin mode. */
		private PinMode currentPinMode = PinMode.OUTPUT;

		/**
		 * The Enum PinState.
		 */
		public enum PinState{
			
			/** The low. */
			LOW,
			
			/** The high. */
			HIGH
		}

		/**
		 * The Enum PinMode.
		 */
		public enum PinMode{
			
			/** The output. */
			OUTPUT,
			
			/** The input. */
			INPUT
		}



		/**
		 * Instantiates a new gpio pin.
		 *
		 * @param gpioPinId the gpio pin id
		 */
		// Basic constructor using board numbers
		public GpioPin(int gpioPinId) {

			// Set the ID
			this.id = gpioPinId;

			// Reference path for the pin
			uri = mkGpioUri(gpioPinId);


		}

		/**
		 * Mk gpio uri.
		 *
		 * @param pinId the pin id
		 * @return the string
		 */
		// Map the pin path.
		private String mkGpioUri(int pinId){

			if ( pinId < 0 || pinId > MAXQUADPIN)
			{
				return "undefined";
			}
			// Get the mapped ID 
			int gpoID = (int) gpioPins[pinId][1];
			// Add it to the base
			uri = (String) gpioPins[pinId][2];
			return uri;

			//return FileUtils.COMMON_GPIO_URI + gpoID;
		}

		/**
		 * Gets the uri.
		 *
		 * @return the uri
		 */
		public String getUri() {
			return uri;
		}
		
		/**
		 * Sets the uri.
		 *
		 * @param uri the new uri
		 */
		public void setUri(String uri) {
			this.uri = uri;
		}
		
		/**
		 * Checks if is exported.
		 *
		 * @param pinId the pin id
		 * @return true, if is exported
		 */
		public boolean isExported(int pinId){
			File gpioDir = new File(mkGpioUri(pinId));
			return gpioDir.exists();
		}



		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * Sets the id.
		 *
		 * @param id the new id
		 */
		public void setId(int id) {
			this.id = id;
		}


		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public int getValue() {
			return value;
		}

		/**
		 * Sets the value.
		 *
		 * @param value the new value
		 */
		public void setValue(int value) {
			this.value = value;
			// Set the pin value
		}


		/**
		 * Gets the current pin mode.
		 *
		 * @return the current pin mode
		 * @throws Exception the exception
		 */
		public PinMode getCurrentPinMode() throws Exception {

			currentPinMode = (Objects.equals(FileUtils.readFile(this.uri + GPIO_DIRECTION_PATH), "in"))? PinMode.INPUT:PinMode.OUTPUT;
			return currentPinMode;
		}

		/**
		 * Sets the current pin mode.
		 *
		 * @param currentPinMode the new current pin mode
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void setCurrentPinMode(PinMode currentPinMode) throws IOException {

			File file = new File(this.uri + GPIO_DIRECTION_PATH);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(currentPinMode == PinMode.INPUT ? "in" : "out");
			bw.close();

			this.currentPinMode = currentPinMode;
		}

		/**
		 * Write.
		 *
		 * @param state the state
		 * @throws Exception the exception
		 */
		public void write(PinState state) throws Exception{
			File file = new File(this.uri + GPIO_VALUE_PATH);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(state == PinState.HIGH ? "1" : "0");
			bw.close();
			currentPinState = state;

		}

		/**
		 * Read.
		 *
		 * @return the pin state
		 * @throws Exception the exception
		 */
		public PinState read() throws Exception{

			PinState state = (Objects.equals(FileUtils.readFile(this.uri + GPIO_VALUE_PATH), "1"))?PinState.HIGH:PinState.LOW;
			currentPinState = state;

			return state;
		}

		/**
		 * High.
		 *
		 * @throws Exception the exception
		 */
		public void high() throws Exception{
			//System.out.println("Set 1 on GPIO" + this.id);
			this.write(PinState.HIGH);
		}

		/**
		 * Low.
		 *
		 * @throws Exception the exception
		 */
		public void low() throws Exception{
			//System.out.println("Set 0 on GPIO" + this.id);
			this.write(PinState.LOW);
		}


}