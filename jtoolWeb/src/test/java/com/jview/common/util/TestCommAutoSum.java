package com.jview.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TestCommAutoSum {
	@Test
	public void testCount(){
		List<Map<String, Integer>> list=new ArrayList();
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("chenjh", 1);
		map.put("lisse", 2);
		map.put("cady", 5);
		
		
		list.add(map);
		
		map=new HashMap<String, Integer>();
		map.put("chenjh", 2);
		map.put("lisse", 5);
		map.put("cady", 10);
		list.add(map);
		
		map=new HashMap<String, Integer>();
		map.put("chenjh", 4);
		map.put("lisse", 3);
		list.add(map);
		CommAutoSum sum=new CommAutoSum();
		for(Map<String, Integer> m:list){
			Set<String> keys=m.keySet();
			for(String key:keys){
				sum.count(key);
			}
		}
		
		System.out.println(sum.getValue("chenjh"));
		System.out.println(sum.getValue("lisse"));
		System.out.println(sum.getValue("cady"));
		
	}
	
	@Test
	public void testSum(){
		List<Map<String, Integer>> list=new ArrayList();
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("chenjh", 1);
		map.put("lisse", 2);
		map.put("cady", 5);
		
		
		list.add(map);
		
		map=new HashMap<String, Integer>();
		map.put("chenjh", 2);
		map.put("lisse", 5);
		map.put("cady", 10);
		list.add(map);
		
		map=new HashMap<String, Integer>();
		map.put("chenjh", 4);
		map.put("lisse", 3);
		list.add(map);
		
		CommAutoSum sum=new CommAutoSum();
		for(Map<String, Integer> m:list){
			Set<String> keys=m.keySet();
			for(String key:keys){
				sum.sum(key, m.get(key)+0d);
			}
		}
		
		System.out.println(sum.getValue("chenjh"));
		System.out.println(sum.getValue("lisse"));
		System.out.println(sum.getValue("cady"));
	}
	
	public void testList(){
		TimeLogger timeLogger = new TimeLogger(null, "testSet");
		List<Integer> idList=new ArrayList<Integer>();
		for(int i=0; i<1000000; i++){
			idList.add(i);
		}
		timeLogger.time("time1");
		List<Integer> idList2=new ArrayList<Integer>(1000000);
		for(int i=0; i<1000000; i++){
			idList2.add(i);
		}
		timeLogger.time("time2");
		
		timeLogger.printTime(this, "timeList");
	}
	
	@Test
	public void testSet(){
		TimeLogger timeLogger = new TimeLogger(null, "testSet");
		List<Integer> idList=new ArrayList<Integer>();
		for(int i=0; i<100000; i++){
			idList.add(i);
		}
		timeLogger.time("time1");
		List<Integer> idList2=new ArrayList<Integer>(100000);
		for(int i=0; i<100000; i++){
			idList2.add(i);
		}
		timeLogger.time("time2");
		
//		Set<Integer> idSet=new HashSet<Integer>();
//		idSet.addAll(idList);
//		idSet.addAll(idList2);
//		System.out.println("----size="+idSet.size());
		
//		List<Integer> rList=new ArrayList<Integer>();
//		for(Integer v:idList){
//			if(!rList.contains(v)){
//				rList.add(v);
//			}
//		}
//		for(Integer v:idList2){
//			if(!rList.contains(v)){
//				rList.add(v);
//			}
//		}
		
		idList.addAll(idList2);
		List<Integer> rList=new ArrayList<Integer>();
//		for(Integer v:idList){
//			if(!rList.contains(v)){
//				rList.add(v);
//			}
//		}
		int size=idList.size();
		Integer v=null;
		for(int i=0; i<size; i++){
			v=idList.get(i);
			if(!rList.contains(v)){
				rList.add(v);
			}
		}
		System.out.println(rList.size());

		timeLogger.time("time3");
		timeLogger.printTime(this, "timeList");
	}
}
