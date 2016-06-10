package com.jview.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class CommThreadManage<T> {
	public CommThreadManage(int threadCount){
		this.latch=new CountDownLatch(threadCount);
	}
	private Map<String, ReturnResult<T>> returnMap=new HashMap<String, ReturnResult<T>>();
	private CountDownLatch latch;
	public CountDownLatch getLatch() {
		return latch;
	}
	public ReturnResult<T> getReturnResult(String key) {
		return returnMap.get(key);
	}
	
	public void putReturnResult(String key, ReturnResult<T> returnResult){
		this.returnMap.put(key, returnResult);
	}
	
	public Map<String, ReturnResult<T>> getReturnMap(){
		return this.returnMap;
	}
	
	
}
