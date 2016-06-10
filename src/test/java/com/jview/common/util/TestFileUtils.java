package com.jview.common.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class TestFileUtils {
	@Test
	public void testFindLog() throws Exception{
		File file = new File("d:/log4j/rcp.log");
		int count=1000;
		Map<String, String> filterMap=new HashMap<String, String>();
		filterMap.put("filter", "ERROR");
		String charset="UTF8";
		
		List<String> lineList=FileUtils.readLineFile(file, count, filterMap, charset);
		CommMethod.print(lineList);
	}
	
	@Test
	public void testFindLogByCache() throws Exception{
		CommMapCache cache=new CommMapCache(5, 10, 2);
		
		File file = new File("d:/log4j/rcp.log");
		String charset="UTF8";
		int count=1000;
		
		List<String> lineList=null;
		String key=null;
		Map<String, String> filterMap=new HashMap<String, String>();
		for(int i=0; i<40; i++){
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
				System.out.println("i="+i+ " filter="+key+" data-cache size="+lineList.size());
				continue;
			}
			lineList=FileUtils.readLineFile(file, count, filterMap, charset);
			System.out.println("i="+i+ " filter="+key+" data size="+lineList.size());
			cache.cache(key, lineList);
		}
		
		cache.cleanCache("User");
	}
	
	@Test
	public void testFindLogByCache2() throws Exception{
		CountDownLatch latch=new CountDownLatch(2);//两个工人的协作 
		System.out.println("-----testFindLogByCache2--");
		Thread aa = new Thread(new FindLogThread("aa", latch));
		aa.start();
		Thread bb = new Thread(new FindLogThread("bb", latch));
		bb.start();
		latch.await();
//		Thread.sleep(5000);
		
	}
	
	@Test
	public void testFindLogByCache3() throws Exception{
		CommThreadManage<String> manage=new CommThreadManage<String>(2);//两个工人的协作 
		System.out.println("-----testFindLogByCache2--");
		Thread aa = new Thread(new FindLogManageThread("aa", manage));
		aa.start();
		Thread bb = new Thread(new FindLogManageThread("bb", manage));
		bb.start();
		manage.getLatch().await();
		System.out.println("aa size="+manage.getReturnResult("aa").getDataList().size());
		System.out.println("bb size="+manage.getReturnResult("bb").getDataList().size());
//		Thread.sleep(5000);
		
	}
	
}
