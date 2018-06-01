package com.neotys.webpagetest.WPT;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WPT_LaunchTestActionTest {
	@Test
	public void shouldReturnType() {
		final WPT_LaunchTestAction action = new WPT_LaunchTestAction();
		assertEquals("WPT_LaunchTest", action.getType());
	}

}
