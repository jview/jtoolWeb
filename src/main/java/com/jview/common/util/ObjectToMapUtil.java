package com.jview.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ObjectToMapUtil {
	private static  Logger logger=Logger.getLogger(ObjectToMapUtil.class); 
	/**
	 * 对象转map
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Map<String, Object> objectToMap(Object bean) 
            throws IntrospectionException, IllegalAccessException, InvocationTargetException { 
        Class type = bean.getClass(); 
        Map<String, Object> returnMap = new HashMap<>(); 
        BeanInfo beanInfo = Introspector.getBeanInfo(type); 

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
            if (!propertyName.equals("class")) { 
                Method readMethod = descriptor.getReadMethod(); 
                Object result = readMethod.invoke(bean, new Object[0]); 
                if (result != null) { 
                    returnMap.put(propertyName, result); 
                } else { 
//                    returnMap.put(propertyName, ""); 
                } 
            } 
        } 
        return returnMap; 
    } 
	
	/**
	 * map转对象
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	 public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
	        if (map == null)   
	            return null;    
	  
	        Object obj = beanClass.newInstance();  
	  
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
	        for (PropertyDescriptor property : propertyDescriptors) {  
	            Method setter = property.getWriteMethod();    
	            if (setter != null) {  
	                setter.invoke(obj, map.get(property.getName()));   
	            }  
	        }  
	  
	        return obj;  
	    }
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
		
		BeanInfo beanInfo = Introspector.getBeanInfo(data.getClass()); 

		boolean isIgnore=false;
		
		PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) { 
                Method readMethod = descriptor.getReadMethod(); 
                Object result = readMethod.invoke(data, new Object[0]);
                if (result != null) {
                	//忽略模式
                	if("ignore".equals(getType)){
    					isIgnore=false;
    					for(String prop:props){
    						if(propertyName.equals(prop)){
    							isIgnore=true;
    							break;
    						}
    					}
    					if(!isIgnore){
    						map.put(propertyName, result); 
    					}
                	}
                	//包含模式
                	else if("include".equals(getType)){
                		for(String prop:props){
                			if(propertyName.equals(prop)){
                				map.put(propertyName, result);
                				break;
                			}
                		}
                	}
                    
                } else { 
//                    returnMap.put(propertyName, ""); 
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
