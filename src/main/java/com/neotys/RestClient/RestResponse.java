package com.neotys.RestClient;

/**
 * Created by hrexed on 31/05/18.
 */
import org.json.JSONObject;

public class RestResponse {
    private final int statusCode;
    private final JSONObject data;

    public RestResponse(int statusCode, JSONObject data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JSONObject getData() {
        return data;
    }
}