package com.neotys.webpagetest.WPT;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

import com.google.common.base.Optional;
import com.neotys.extensions.action.Action;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;

public final class WPT_LaunchTestAction implements Action{
	private static final String BUNDLE_NAME = "com.neotys.webpagetest.WPT.bundle";
	private static final String DISPLAY_NAME = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("displayName");
	private static final String DISPLAY_PATH = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("displayPath");
	public static final String WPT_URL="WPT_URL";
	public static final String WPT_APIKEY="WPT_APIKEY";
	public static final String WPT_TestingURL="WPT_TestingURL";
	public static final String WPT_Location="WPT_Location";
	public static final String WPT_retry="WPT_retry";
	public static final String NL_DataExchangeAPIURL="NL_DataExchangeAPIURL";
	public static final String NL_DataExchangeAPIKEY="NL_DataExchangeAPIKEY";
	private static final ImageIcon LOGO_ICON;

	@Override
	public String getType() {
		return "WPT_LaunchTest";
	}

	@Override
	public List<ActionParameter> getDefaultActionParameters() {
		final List<ActionParameter> parameters = new ArrayList<ActionParameter>();
		parameters.add(new ActionParameter(WPT_URL,"URL of your WPT Server"));
		parameters.add(new ActionParameter(WPT_APIKEY,"WPT API KEY"));
		parameters.add(new ActionParameter(WPT_TestingURL,"www.amazon.com"));
		parameters.add(new ActionParameter(WPT_Location,"location:browser.connectivity"));
		parameters.add(new ActionParameter(WPT_retry,"2"));

		// TODO Add default parameters.
		return parameters;
	}
	static {
		final URL iconURL = WPT_LaunchTestAction.class.getResource("wpt.png");
		if (iconURL != null) {
			LOGO_ICON = new ImageIcon(iconURL);
		}
		else {
			LOGO_ICON = null;
		}
	}
	@Override
	public Class<? extends ActionEngine> getEngineClass() {
		return WPT_LaunchTestActionEngine.class;
	}

	@Override
	public Icon getIcon() {
		// TODO Add an icon
		return LOGO_ICON;
	}

	@Override
	public boolean getDefaultIsHit(){
		return false;
	}

	@Override
	public String getDescription() {
		final StringBuilder description = new StringBuilder();
		// TODO Add description
		description.append("WPT_LaunchTest will launch a test on a WPT server.\n");
		description.append("WPT_LaunchTest requires the following parameters\n");
		description.append("\tWPT_URL : Url of your WPT Server\n");
		description.append("\tWPT_APIKEY : Optionnal WPT api Key\n");
		description.append("\tWPT_TestingURL : testing url to send to WPT\n");
		description.append("\tWPT_Location : WPT Location used for the test execution\n");
		description.append("\tWPT_retry : number of execution\n");
		description.append("\tNL_DataExchangeAPIURL : (Optionnal) Nl DataExchange URL http://localhost:7400/DataExchange/v1/Service.svc/\n");
		description.append("\tNL_DataExchangeAPIKEY : (Optionnal) API KEY ");


		return description.toString();
	}

	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	@Override
	public String getDisplayPath() {
		return DISPLAY_PATH;
	}

	@Override
	public Optional<String> getMinimumNeoLoadVersion() {
		return Optional.of("6.3");
	}

	@Override
	public Optional<String> getMaximumNeoLoadVersion() {
		return Optional.absent();
	}
}
