package com.neotys.webpagetest.WPT;

import java.util.List;

import com.google.common.base.Strings;
import com.neotys.NLAPIClient.NLClient;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;
import com.neotys.extensions.action.engine.Context;
import com.neotys.extensions.action.engine.SampleResult;

public final class WPT_LaunchTestActionEngine implements ActionEngine {
	private String WPT_URL;
	private String WPT_APIKEY;
	private String WPT_Location;
	private  String WPT_TestingURL;
	private int retry;
	private String NL_APIURL;
	private String NL_APIKEY;

	@Override
	public SampleResult execute(Context context, List<ActionParameter> parameters) {
		final SampleResult sampleResult = new SampleResult();
		final StringBuilder requestBuilder = new StringBuilder();
		final StringBuilder responseBuilder = new StringBuilder();
		WPTTestRunner runner;
		WptResponse response;
		String StRetry = null;
		for(ActionParameter parameter:parameters) {
			switch(parameter.getName()) {


				case WPT_LaunchTestAction.WPT_URL:
					WPT_URL = parameter.getValue();
					break;
				case WPT_LaunchTestAction.WPT_APIKEY:
					WPT_APIKEY = parameter.getValue();
					break;
				case WPT_LaunchTestAction.WPT_TestingURL:
					WPT_Location = parameter.getValue();
					break;
				case WPT_LaunchTestAction.WPT_Location:
					WPT_TestingURL = parameter.getValue();
					break;
				case WPT_LaunchTestAction.WPT_retry:
					StRetry = parameter.getValue();
					break;
				case WPT_LaunchTestAction.NL_DataExchangeAPIKEY:
					NL_APIKEY = parameter.getValue();
					break;
				case WPT_LaunchTestAction.NL_DataExchangeAPIURL:
					NL_APIURL = parameter.getValue();
					break;

			}
		}
		if (Strings.isNullOrEmpty(WPT_URL)) {
			return getErrorResult(context, sampleResult, "Invalid argument: WPT_URL cannot be null "
					+ WPT_LaunchTestAction.WPT_URL + ".", null);
		}
		if (Strings.isNullOrEmpty(WPT_TestingURL)) {
			return getErrorResult(context, sampleResult, "Invalid argument: WPT_TestingURL cannot be null "
					+ WPT_LaunchTestAction.WPT_TestingURL + ".", null);
		}
		if (Strings.isNullOrEmpty(WPT_Location)) {
			return getErrorResult(context, sampleResult, "Invalid argument: WPT_Location cannot be null "
					+ WPT_LaunchTestAction.WPT_Location + ".", null);
		}
		if (Strings.isNullOrEmpty(StRetry)) {
			return getErrorResult(context, sampleResult, "Invalid argument: WPT_retry cannot be null "
					+ WPT_LaunchTestAction.WPT_retry + ".", null);
		}
		else
		{
			try {
				retry=Integer.parseInt(StRetry);
			}
			catch(NumberFormatException e)
			{
				return getErrorResult(context, sampleResult, "Invalid argument: WPT_retry needs to be a digit "
						+ WPT_LaunchTestAction.WPT_retry + ".", null);
			}
		}
		try
		{
			sampleResult.sampleStart();

			runner=new WPTTestRunner(WPT_URL,WPT_APIKEY);
			response=runner.RunTest(WPT_TestingURL,retry,WPT_TestingURL);

			appendLineToStringBuilder(requestBuilder, "WPT_LaunchTest request.");
			appendLineToStringBuilder(responseBuilder, response.toString());
			// TODO perform execution.

			sampleResult.sampleEnd();
			if (!Strings.isNullOrEmpty(NL_APIURL))
			{
				//----------Send data to NL API----------------
				NLClient Nlclient=new NLClient(response,NL_APIURL,NL_APIKEY);
				Nlclient.SendData();
			}
		}
		catch(Exception e)
		{
			return getErrorResult(context, sampleResult, "Technical Error : "
					+ e.getMessage()+ ".", e);

		}


		sampleResult.setRequestContent(requestBuilder.toString());
		sampleResult.setResponseContent(responseBuilder.toString());
		return sampleResult;
	}

	private void appendLineToStringBuilder(final StringBuilder sb, final String line){
		sb.append(line).append("\n");
	}

	/**
	 * This method allows to easily create an error result and log exception.
	 */
	private static SampleResult getErrorResult(final Context context, final SampleResult result, final String errorMessage, final Exception exception) {
		result.setError(true);
		result.setStatusCode("NL-WPT_LaunchTest_ERROR");
		result.setResponseContent(errorMessage);
		if(exception != null){
			context.getLogger().error(errorMessage, exception);
		} else{
			context.getLogger().error(errorMessage);
		}
		return result;
	}

	@Override
	public void stopExecute() {
		// TODO add code executed when the test have to stop.
	}

}
