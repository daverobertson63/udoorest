/*
 * 
 */
package com.dhr.quad;

import com.google.gson.Gson;

import spark.ResponseTransformer;
import static spark.Spark.*;

// TODO: Auto-generated Javadoc
/**
 * The Class JsonTransformer.
 */
public class JsonTransformer implements ResponseTransformer {

    /** The gson. */
    private Gson gson = new Gson();

    /* (non-Javadoc)
     * @see spark.ResponseTransformer#render(java.lang.Object)
     */
    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}