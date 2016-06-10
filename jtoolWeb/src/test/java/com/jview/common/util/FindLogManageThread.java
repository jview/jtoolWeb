package com.jview.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLogManageThread implements Runnable{
	
	public FindLogManageThread(){
		
	}
	public FindLogManageThread(String name, CommThreadManage manage){
		this.name=name;
		this.threadManage=manage;
	}
	private String name;
	private CommThreadManage threadManage;
	private ReturnResult<String> ret;

	public void run(){
		ret=new ReturnResult();
		ret.setDataList(new ArrayList<String>());
		this.threadManage.putReturnResult(this.name, ret);
		try {
			
			ReturnResult aaRet=(threadManage.getReturnResult("aa"));
			while(!"aa".equals(this.name) && (aaRet=(threadManage.getReturnResult("aa")))!=null){
				Thread.sleep(1000);
				System.out.println(this.name+"----aa is not null");
				if(aaRet.getDataList().size()>0){
					break;
				}
			}
			
			this.testFindLog();
//			MapCache cache=new MapCache();
//			TimeLogger timeLogger=new TimeLogger(null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret.error(1, "UNKOWN_ERROR", "", e);
					
			
		}finally{
			if(this.threadManage!=null){
				this.threadManage.getLatch().countDown();
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
				ret.getDataList().addAll(lineList);
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
