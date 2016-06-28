package com.jview.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ObjectToMapUtil {
	private static  Logger logger=Logger.getLogger(ObjectToMapUtil.class); 
	/**
	 * 
	 * ��vo����getType���ͣ���vo����ֵ���ҳ�propNames��Ӧ�����Լ�����ֵ����map����
	 * @param obj
	 * @param propNames
	 * @param getType include/ignore
	 * @return
	 */
	public static Map<String, Object> getDataMapByPropName(Object data, String propNames, String getType) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		if(data==null){
			return map;
		}
		
		if(propNames==null){
			propNames="";
		}
		if(getType==null){
			getType="ignore";
		}
		String[] props=propNames.split(",");
		for(int i=0; i<props.length; i++){
			props[i]=props[i].trim();
		}
		
		Field[] fields=data.getClass().getDeclaredFields();
		Method[] methods=data.getClass().getDeclaredMethods();
		
		String pri=null;
		String fieldName=null;
		String fieldNameUp1=null;
		
		String methodName=null;
		
		Object propValue=null;
		boolean isIgnore=false;
		
		for(Field field:fields){
			fieldName=field.getName();
			pri=Modifier.toString(field.getModifiers());
			fieldNameUp1=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
//			System.out.println("----fieldName="+fieldName+" pri="+pri+" fieldName="+fieldName);
			if("private".equals(pri)){
				if("ignore".equals(getType)){
					isIgnore=false;
					for(String prop:props){
						if(fieldName.equals(prop)){
							isIgnore=true;
							break;
						}
					}
					if(!isIgnore){
						for(Method method:methods){
							methodName=method.getName();
							pri=Modifier.toString(method.getModifiers());
							if("public".equals(pri) && methodName.equals("get"+fieldNameUp1)){
								propValue=method.invoke(data, null);
								if(propValue!=null)
									map.put(fieldName, propValue);
								break;
							}
						}
					}
				}
				else if("include".equals(getType)){
					for(String prop:props){
						if(fieldName.equals(prop)){
							for(Method method:methods){
								methodName=method.getName();
								pri=Modifier.toString(method.getModifiers());
								if("public".equals(pri) && methodName.equals("get"+fieldNameUp1)){
									propValue=method.invoke(data, null);
									if(propValue!=null)
										map.put(fieldName, propValue);
									break;
								}
							}
							break;
						}
					}
				}
				
			}
			
		}

		return map;
	}
	
	/**
	 * ��voList�и���ָ����propName���ɸ�propName��Ӧֵ��list
	 * @param list
	 * @param propName
	 * @return
	 */
	public static List getDataPropListByName(List list, String propName) throws Exception{
		List<Object> propDataList=new ArrayList<Object>();
		
		if(list==null||list.size()==0){
			return propDataList;
		}
		
		String propNameUp1=propName.substring(0, 1).toUpperCase()+propName.substring(1);
		String methodName="get"+propNameUp1;
		Object data=list.get(0);
//		Field field=data.getClass().getDeclaredField(propName);//���Ƿ������Ƿ����
		Class<?>[] parameterTypes = new Class<?>[0];
		Method method=data.getClass().getDeclaredMethod(methodName, parameterTypes);
		for(Object obj:list){
			propDataList.add(method.invoke(obj, null));
		}
		return propDataList;
	}
	
	/**
	 * ��voList�и���getType����ȡָ����propNames��Ӧ�����Լ�����ֵ����mapList
	 * @param dataList
	 * @param propNames
	 * @param getType include/ingore
	 * @return
	 */
	public static List<Map<String, Object>> getDataMapListByPropName(List dataList
			, String propNames, String getType) throws Exception{
		List<Map<String, Object>> mapList=new ArrayList<Map<String, Object>>();
		Map<String, Object> map=null;
		for(Object data:dataList){
			map=getDataMapByPropName(data, propNames, getType);
			if(map!=null){
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * ��mapList�е�ÿ��map��ȡһ��key��ֵ��Ϊlist����
	 * @param mapList
	 * @param key
	 * @return
	 */
	public static List getDataMapListByKey(List<Map> mapList, Object key){
		List list=new ArrayList();
		for(Map map:mapList){
			if(map.containsKey(key)){
				list.add(map.get(key));
			}
		}
		return list;
	}
	
	/**
	 * ��mapList�е�ÿ��map��ȡ����key��Ϊ�µ�map��key��value����
	 * @param mapList
	 * @param propKey
	 * @param propValue
	 * @return
	 */
	public static Map getDataMapListByKey(List<Map<String, Object>> mapList, String propKey, String propValue){
		Map dataMap= new HashMap();
		for(Map<String, Object> map:mapList){
			if(map.containsKey(propKey) && map.containsKey(propValue)){
				dataMap.put(map.get(propKey), map.get(propValue));
			}
		}
		return dataMap;
	}
	
	
}
