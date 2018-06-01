package com.neotys.webpagetest.WPT;

import com.google.gson.GsonBuilder;
import com.neotys.RestClient.RestClient;
import com.neotys.RestClient.RestResponse;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.core.MediaType;

/**
 * Created by hrexed on 30/05/18.
 */
public class WPTTestRunner {
    private final String serverUrl;
    private final String apiKey;

    public WPTTestRunner( String serverUrl, String apiKey) {
        this.serverUrl = serverUrl;
        this.apiKey = apiKey;
    }

    public WptResponse RunTest(String targetUrl,
                           int retryCount,
                           String location) throws URISyntaxException, InterruptedException {

        WptResponse runTestResponse= null;
        String testId = Run(targetUrl, retryCount, location);
        runTestResponse=WaitForTestToComplete(testId);
        return runTestResponse;
    }

    public WptResponse WaitForTestToComplete(String testId) throws InterruptedException {
        String url = serverUrl + "/" + WptConst.TestStatus +"?f=json&test=" + testId;
        RestResponse restResponse = null;
        WptResponse runTestResponse= null;
        if(apiKey != null && !apiKey.isEmpty()){
            url += "&k=" + apiKey;
        }

        String status = new String();
         while (!status.equals(WptConst.TestCompleted)) {
            restResponse = ExecuteGet(url);
            status = restResponse.getData().get("statusText").toString();
            TimeUnit.SECONDS.sleep(WptConst.TestStatusCheckIntervalSeconds);
        }
        if(restResponse!=null) {
            JSONObject responseData = restResponse.getData();
            runTestResponse = new GsonBuilder()
                    .create()
                    .fromJson(responseData.toString(), WptResponse.class);
        }
        return runTestResponse;
    }

    private String Run(String targetUrl,
                       int retryCount,
                       String location) throws URISyntaxException {
        WptUrl.Builder wptBuilder = WptUrl.builder(serverUrl, WptConst.RunTest)
                .WithNumberOfRuns(retryCount)
                .WithTargetUrl(targetUrl);

        if(apiKey != null && !apiKey.isEmpty()){
            wptBuilder.WithApiKey(apiKey);
        }

        if(location!= null && !location.isEmpty()){
            wptBuilder.WithLocation(location);
        }

        String url = wptBuilder.buildUrl();


        RestResponse restResponse = ExecuteGet(url);

        JSONObject responseData = restResponse.getData();
        WptResponse runTestResponse = new GsonBuilder()
                .create()
                .fromJson(responseData.toString(), WptResponse.class);

         return runTestResponse.data.testId;
    }

    private RestResponse ExecuteGet(String url) {
        RestResponse restResponse = RestClient.Get(url,
                MediaType.APPLICATION_JSON_TYPE);

        if (restResponse.getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + restResponse.getStatusCode()
                    + restResponse.getData().toString());
        }

        Response response = new GsonBuilder().create()
                .fromJson(restResponse.getData().toString(), Response.class);

        if (response.statusCode != 200 &&
                response.statusCode != 101 &&
                response.statusCode != 100) {
            throw new RuntimeException("Failed : WPT returned an error : "
                    + response.statusCode + ", "
                    + response.statusText);
        }
        return restResponse;
    }

    private class Response {
        private int statusCode;
        private String statusText;
    }
}
