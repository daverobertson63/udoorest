/*
 * 
 */
package com.dhr.quad;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponseError.
 */
public class ResponseError {

	/** The message. */
	private String message;

	/**
	 * Instantiates a new response error.
	 *
	 * @param message the message
	 * @param args the args
	 */
	public ResponseError(String message, String... args) {
		this.message = String.format(message, args);
	}

	/**
	 * Instantiates a new response error.
	 *
	 * @param e the e
	 */
	public ResponseError(Exception e) {
		this.message = e.getMessage();
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}
}