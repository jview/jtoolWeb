package com.jview.comm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.jview.common.util.ObjectToMapUtil;

public class testObjectToMapUtil {
	@Test
	public void testGetDataMapByPropName() throws Exception{
		RoleVO role = new RoleVO(1l, "test1", "dest1");
		Map map=ObjectToMapUtil.getDataMapByPropName(role, "id,name","include");
		System.out.println(map);
		
		 map=ObjectToMapUtil.getDataMapByPropName(role, "id , name ","include");
			System.out.println(map);
		
		map=ObjectToMapUtil.getDataMapByPropName(role, "id,name","ignore");
		System.out.println(map);
	}
	
	@Test
	public void testGetDataMapListByPropName() throws Exception{
		List<RoleVO> roleList=new ArrayList<RoleVO>();
		RoleVO role = new RoleVO(1l, "test1", "dest1");
		roleList.add(role);
		
		role = new RoleVO(2l, "test2", "dest2");
		roleList.add(role);
		
		role = new RoleVO(3l, "test3", "dest3");
		roleList.add(role);
		
		role = new RoleVO(4l, "test4", "dest4");
		roleList.add(role);
		
		List<Map<String, Object>> mapList=ObjectToMapUtil.getDataMapListByPropName(roleList, "id,name", "include");
		System.out.println("----mapList="+mapList);
	
	}
	
	@Test
	public void testGetDataPropListByName() throws Exception{
		List<RoleVO> roleList=new ArrayList<RoleVO>();
		RoleVO role = new RoleVO(1l, "test1", "dest1");
		roleList.add(role);
		
		role = new RoleVO(2l, "test2", "dest2");
		roleList.add(role);
		
		role = new RoleVO(3l, "test3", "dest3");
		roleList.add(role);
		
		role = new RoleVO(4l, "test4", "dest4");
		roleList.add(role);
		
		List<Long> idList=ObjectToMapUtil.getDataPropListByName(roleList, "id");
		System.out.println("----idList="+idList);
		
		List<String> nameList=ObjectToMapUtil.getDataPropListByName(roleList, "name");
		System.out.println("----nameList="+nameList);
	
	}
}
