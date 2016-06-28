package com.jview.comm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.jview.common.util.CommCollections;
import com.jview.common.util.CommUtil;

public class testCommUtil {
	@Test
	public void testObjectEqual(){
		byte v1=1;
		byte v2=1;
		boolean v=CommUtil.isObjectEqual(v1, v2);
		System.out.println("--byte--v="+v);
		TestCase.assertEquals("byte equal check", v, true);
		v2=2;
		v=CommUtil.isObjectEqual(v1, v2);
		System.out.println("--byte--v="+v);
		TestCase.assertEquals("byte unequal check", v, false);
		
		Float f1=0.5f; float f2=0.5f;
		v=CommUtil.isObjectEqual(f1, f2);
		TestCase.assertEquals("float equal check", v, true);
		
		f2=0.6f;
		v=CommUtil.isObjectEqual(f1, f2);
		TestCase.assertEquals("float unequal check", v, false);
		
		short s1=1,s2=1;
		v=CommUtil.isObjectEqual(s1, s2);
		TestCase.assertEquals("short equal check", v, true);
		
		s2=2;
		v=CommUtil.isObjectEqual(s1, s2);
		TestCase.assertEquals("short unequal check", v, false);
		
		String str1="abc"; Object str2="abc";
		v=CommUtil.isObjectEqual(str1, str2);
		TestCase.assertEquals("String equal check", v, true);
		str2="abd";
		v=CommUtil.isObjectEqual(str1, str2);
		TestCase.assertEquals("String unequal check", v, false);
		
		Date date1 = new Date(100000000);
		Date date2 = new Date(100000000);
		v=CommUtil.isObjectEqual(date1, date2);
		TestCase.assertEquals("Date equal check", v, true);
		date2 = new Date(100000002);
		v=CommUtil.isObjectEqual(date1, date2);
		TestCase.assertEquals("Date unequal check", v, false);
	}
	@Test
	public void testDecode(){
		String p="123";
		Object v=CommUtil.decode(p, "123", "123b", "124", "124b", "125", 125);
		System.out.println("p="+p+" v="+v);
		p="124";
		v=CommUtil.decode(p, "123", "123b", "124", "124b", "125", 125);
		System.out.println("p="+p+" v="+v);
		p="125";
		v=CommUtil.decode(p, "123", "123b", "124", "124b", "125", 125);
		System.out.println("p="+p+" v="+v);
		p="126";
		v=CommUtil.decode(p, "123", "123b", "124", "124b", "125", 125);
		System.out.println("p="+p+" v="+v);
	}
	@Test
	public void testNvl(){
		Object p=null;
		Object v=CommUtil.nvl(p, "test");
		System.out.println("p="+p+" v="+v);
		p=123;
		v=CommUtil.nvl(p, "test");
		System.out.println("p="+p+" v="+v); 
	}
	
	@Test
	public void testNewList(){
		List list=CommUtil.newList(1,2,3,4);
		int total=CommCollections.sum(list).intValue();
		System.out.println(total);
		
		
	}
	
	@Test
	public void testNewVoList() throws Exception{
		List list=CommUtil.newVoList(10, 100, 2, "id", RoleVO.class);
		System.out.println(list);
	}
	
	@Test
	public void testNewMap() throws Exception{
		Map map=CommUtil.newMap("id",1
				,"name","test"
				,"age",1);
		System.out.println(map);
	}
}
