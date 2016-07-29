/*
 * 
 */
package com.dhr.quad;
/**
 *  Copyright (C) 2014 Ekironji <ekironjisolutions@gmail.com>
 *
 *  This file is part of serial libraries examples for UDOO
 *
 *  Serial libraries examples for UDOO is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This libraries are distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class Serial.
 */
public class Serial {

	/** The m serial port. */
	private SerialPort mSerialPort;
	
	/** The out. */
	private OutputStream out;
	
	/** The in. */
	private InputStream in;

	/**
	 * Instantiates a new serial.
	 */
	public Serial() {
	}

	/**
	 * Connect.
	 *
	 * @param portName the port name
	 * @throws Exception the exception
	 */
	public void connect(String portName) throws Exception {
		CommPortIdentifier mCommPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (mCommPortIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use!");
		} else {
			CommPort mCommPort = mCommPortIdentifier.open(this.getClass().getName(), 2000);
			if(mCommPort instanceof SerialPort) {
				mSerialPort = (SerialPort) mCommPort;
				mSerialPort.setSerialPortParams(115200,
						SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);
				mSerialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

				out = mSerialPort.getOutputStream();
				in = mSerialPort.getInputStream();
			} else {
				System.out.println("Error: Only serial ports are handled by this example!");
			}
		}
	}

	/**
	 * Write byte array.
	 *
	 * @param msg the msg
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeByteArray(byte[] msg) throws IOException {
		out.write(msg);
	}

	/**
	 * Read start buffer thread.
	 */
	public void read_startBufferThread() {
		(new Thread(new SerialReader(in))).start();
	}

	/**
	 * Disconnect.
	 */
	public void disconnect() {
		try {
			if (out != null) {
				out.close();
				out = null;
			}
			if (mSerialPort != null) {
				mSerialPort.close();
				mSerialPort = null;
			}
		} catch (Exception e) {
			System.out.println("Error: Port not closed!");
		} finally {
			out = null;
			mSerialPort = null;
		}
	}

	/**
	 * The Class SerialReader.
	 */
	public static class SerialReader implements Runnable {

		/** The in. */
		InputStream in;

		/**
		 * Instantiates a new serial reader.
		 *
		 * @param in the in
		 */
		public SerialReader(InputStream in) {
			this.in = in;
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			byte[] buffer = new byte[7];
			int len = -1;
			String mStringReceived = "";
			try {
				while((len = this.in.read(buffer)) > -1) {
					mStringReceived += new String(buffer, 0, len);
					if (buffer[len-1] == '\n') {
						System.out.println(mStringReceived);
						mStringReceived = "";
					}
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}
