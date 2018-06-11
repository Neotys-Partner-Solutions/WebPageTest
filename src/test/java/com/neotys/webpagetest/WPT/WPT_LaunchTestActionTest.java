package com.neotys.webpagetest.WPT;

import static org.junit.Assert.assertEquals;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Charsets;

public class WPT_LaunchTestActionTest {
	@Test
	public void shouldReturnType() {
		final WPT_LaunchTestAction action = new WPT_LaunchTestAction();
		assertEquals("WPT_LaunchTest", action.getType());
	}

	@Test
	public void TestResultParser() throws IOException {
		final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new WptResponse.MyAdapterFactory()).create();
		final String json = Files.toString(new File("/home/hrexed/jsonResult.json"), Charsets.UTF_8);

		final WptResponse response = gson.fromJson(json, WptResponse.class);
		System.out.println(response.toString());
	}
}

