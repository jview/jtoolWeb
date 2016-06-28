package com.jview.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.jview.common.util.CommCollections;
import com.jview.common.util.CommUtil;

public class testCommCollectionCal {
	@Test
	public void testGenList(){
		List<Integer> vList=CommCollections.genList(10, 100, 2);
		System.out.println(vList);
		String str=CommUtil.concat(vList, ",");
		System.out.println(str);
	}
	
	@Test
	public void testGenArray(){
		Integer[] arrays=CommCollections.genArray(10, 100, 2);
		System.out.println(arrays);
		String str=CommUtil.concat(arrays, ",");
		System.out.println(str);
	}
	
	@Test
	public void testCalInteger(){
		List<Integer> vList=CommCollections.genList(10, 100, 2);
		System.out.println(vList);
		vList=CommCollections.cal(vList, 10);
		String str=CommUtil.concat(vList, ",");
		System.out.println(str);
	}
	
	@Test
	public void testCalDouble(){
		List vList=CommCollections.genList(10, 100, 2);
		System.out.println(vList);
//		vList=CommCollectionCal.cal(vList, 10l);
//		String str=CommUtil.concat(vList, ",");
//		System.out.println(str);
	}
	
	@Test
	public void testSum(){
		Integer[] vList=CommCollections.genArray(10, 100, 2);
		double total=CommCollections.sum("1",2,3,5,"7","7.1", vList);
		System.out.println(total);
		
		
		List<String> strList=new ArrayList();
		strList.add("143");
		strList.add("100");
		total=CommCollections.sum(vList, strList);
		System.out.println(total);
		
		
	}
	
	@Test
	public void testSumAll(){
		Integer[] vList=CommCollections.genArray(10, 100, 2);
		double total=CommCollections.sum("1",2,3,5,"7","7.1",7.5f, vList);
		System.out.println(total);
		
		
		List<String> strList=new ArrayList();
		strList.add("143");
		strList.add("100");
		
		List oList=CommUtil.newList("123",12.0,110,17.2f);
		total=CommCollections.sumAll(vList, strList, oList, 10);
		System.out.println(total);
		
		
	}
	
	@Test
	public void testCalArray(){
		Integer[] array=CommCollections.genArray(10, 100, 2);
		String str=CommUtil.concat(array, ",");
		System.out.println(str);

		array=(Integer[])CommCollections.cal(array, 2);
		str=CommUtil.concat(array, ",");
		System.out.println(str);

		Short[] arrays=new Short[]{1,2,3,13};
		str=CommUtil.concat(arrays, ",");
		System.out.println(str);
		Object[] objs=CommCollections.cal(arrays, 2);
		str=CommUtil.concat(objs, ",");
		System.out.println(str);
		
	}
	
	@Test
	public void testCalList(){
		List<Integer> vList=CommCollections.genList(10, 100, -2);
		String str=CommUtil.concat(vList, ",");
		System.out.println(str);

		vList=(List<Integer>)CommCollections.cal(vList, 2);
		str=CommUtil.concat(vList, ",");
		System.out.println(str);
		
		
		List<Short> sList=new ArrayList();
		Short t=1;
		sList.add(t);
		str=CommUtil.concat(sList, ",");
		System.out.println(str);
		List objs=CommCollections.cal(sList, 2);
		str=CommUtil.concat(objs, ",");
		System.out.println(str);

		
	}
	
	@Test
	public void testFindDataListOrFirst() throws Exception{
		List<RoleVO> roleList=new ArrayList<RoleVO>();
		RoleVO role = new RoleVO(1l, "test1", "dest1");
		roleList.add(role);
		
		role = new RoleVO(2l, "test2", "dest2");
		roleList.add(role);
		
		role = new RoleVO(3l, "test3", "dest3");
		roleList.add(role);
		
		role = new RoleVO(4l, "test3", "dest3");
		roleList.add(role);
		
		RoleVO first=(RoleVO)CommCollections.findDataFirst(roleList, "name", "test3");
		System.out.println("----id="+first.getId());
		TestCase.assertEquals("test findDataFirst", "test3", first.getName());
		
		List<RoleVO> rList=(List<RoleVO>)CommCollections.findDataList(roleList, "name", "test3");
		System.out.println("----size="+rList.size());
		TestCase.assertEquals("test findDataList", 2, rList.size());
		
		
	}
	
	@Test
	public void testFindMapDataListOrFirst() throws Exception{
		List<Map<String, Object>> mapList=new ArrayList<Map<String, Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", 1);
		map.put("name", "test");
		map.put("desc", "test");
		mapList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("id", 2);
		map.put("name", "test2");
		map.put("desc", "test2");
		mapList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("id", 3);
		map.put("name", "test3");
		map.put("desc", "test3");
		mapList.add(map);
		
		map=new HashMap<String, Object>();
		map.put("id", 4);
		map.put("name", "test3");
		map.put("desc", "test3");
		mapList.add(map);
		
		Map<String, Object> first=CommCollections.findMapDataFirst(mapList, "name", "test3");
		System.out.println("----id="+first.get("id"));
		TestCase.assertEquals("test findDataFirst", "test3", first.get("name"));
		
		List<Map<String, Object>> rList=CommCollections.findMapDataList(mapList, "name", "test3");
		System.out.println("----size="+rList.size());
		TestCase.assertEquals("test findDataList", 2, rList.size());
	}
	
}
