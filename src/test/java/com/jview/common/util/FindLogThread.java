package com.jview.common.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class FindLogThread implements Runnable{
	
	public FindLogThread(){
		
	}
	public FindLogThread(String name, CountDownLatch latch){
		this.name=name;
		this.latch=latch;
	}
	private String name;
	private CountDownLatch latch;

	public void run(){
		try {
			this.testFindLog();
//			MapCache cache=new MapCache();
//			TimeLogger timeLogger=new TimeLogger(null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(latch!=null){
				latch.countDown();
			}
		}
		
	}
	
	private void testFindLog() throws Exception{
		File file = new File("d:/log4j/rcp.log");
		String charset="UTF8";
		int count=1000;
		CommMapCache cache=new CommMapCache(5, 10, 2);
		List<String> lineList=null;
		String key=null;
		Map<String, String> filterMap=new HashMap<String, String>();
		
		for(int i=0; i<20; i++){
			try {
				Thread.sleep(1000);
				if(i%5==0){
					filterMap.put("filter", "ERROR");
				}
				else if(i%5==1){
					filterMap.put("filter", "EsbReader");
				}
				else if(i%5==2){
					filterMap.put("filter", "ActivityRestRcpImpl");
				}
				else if(i%5==3){
					filterMap.put("filter", "UserLocationRcpImpl");
				}
				key=filterMap.get("filter");
				cache.cleanOldData();
//			System.out.println("i="+i+ " filter="+key);
				if(cache.isExistCache(key, null)){
					lineList=(List<String>)cache.getData(key);
					System.out.println("--name="+name+" i="+i+ " filter="+key+" data-cache size="+lineList.size());
					continue;
				}
				lineList=FileUtils.readLineFile(file, count, filterMap, charset);
				System.out.println("--name="+name+" i="+i+ " filter="+key+" data size="+lineList.size());
				cache.cache(key, lineList);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cache.cleanCache("User");
		
	}
	
	
}
