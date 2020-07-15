package com.org.action;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestControllerServlet 
{
	@Test
	public void getLucky()
	{
		ControllerServlet cs = new ControllerServlet();
		assertEquals(7,cs.getLucky());
	}
}
