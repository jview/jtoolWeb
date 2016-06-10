package com.jview.thread.sync;

import org.junit.Test;

public class TestThead {
	@Test
	public void test() throws Exception{
		EventStorage storage=new EventStorage();
		Thread aa = new Thread(new Producer(storage));
		aa.start();
		Thread bb = new Thread(new Consumer(storage));
		bb.start();
		Thread.sleep(5000);
	}
}
