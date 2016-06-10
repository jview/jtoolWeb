package com.jview.common.util;

import org.junit.Test;

public class TestTimeLogger {
	@Test
	public void testTime() throws Exception{
		TimeLogger timeLogger = new TimeLogger(null, "testTime");
		Thread.sleep(1000l);
		timeLogger.time("time1");
		Thread.sleep(2000l);
		timeLogger.time("time2");
		Thread.sleep(3000l);
		timeLogger.time("time3");
		timeLogger.printTime(this, "timeMap");
	}
}
