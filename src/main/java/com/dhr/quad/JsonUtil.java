/*
 * 
 */
package com.dhr.quad;

import com.google.gson.Gson;
import spark.ResponseTransformer;

// TODO: Auto-generated Javadoc
/**
 * The Class JsonUtil.
 */
public class JsonUtil {

	/**
	 * To json.
	 *
	 * @param object the object
	 * @return the string
	 */
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	/**
	 * Json.
	 *
	 * @return the response transformer
	 */
	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}
}