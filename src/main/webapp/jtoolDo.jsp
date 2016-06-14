<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.jview.jtool.manager.*,com.jview.common.util.*, com.jview.common.domain.JtoolOper" %>
<%@ page import="java.util.*,java.net.URLDecoder, net.sf.json.*, org.apache.commons.lang.exception.NestableRuntimeException" %>
	<%
	String realPath=request.getRealPath("/");
   String fileName="jtool.log";
   
	String[] args = new String[0];
	TaskManager manager = null;
	Object obj = session.getAttribute("taskManager");
	if(obj==null){
		manager = new TaskManager();
		manager.initTask();
		session.setAttribute("taskManager", manager);
	}
	else{
		manager = (TaskManager)obj;
	}
	String paras = request.getParameter("paras");
	String types = request.getParameter("types");
	String encodeType = request.getParameter("encodeType");
	if("urlEncode".equals(encodeType)){
		paras = URLDecoder.decode(paras);
	}
	String paras_upper_first=paras.substring(0,1).toUpperCase()+paras.substring(1);
	if(paras_upper_first.equals(paras)){
		paras=paras.toLowerCase();
	}
	System.out.println("types="+types);
	List<String> dataList = new ArrayList<String>();
	if(paras!=null && paras.length()!=0){
		if(types==null){
			types="dbs";
		}
		paras = paras.trim();
		if(types.startsWith("dbs")){
			dataList = manager.cmdDbsOper(paras);
		}
		else if(types.startsWith("tools")){
			dataList = manager.cmdToolOper(paras);
		}
		else{		
			dataList.add("Not start by dbs/tools");
		}
	}
	else{
		paras="";
		if(types==null){
			types="tools";
		}
	}
	
	String result="";
	for(String info:dataList){
		result+=info+"\n";
	} 
	result=result.trim();
	
	String times=CommMethod.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	
	JtoolOper oper=new JtoolOper();
	oper.setIp(request.getRemoteHost());
	oper.setTime(times);
	oper.setTypes(types);
	oper.setParas(paras);
	oper.setResult(result);
	
	JSONObject operJson = JSONObject.fromObject(oper);
	String json=operJson.toString();
	out.print(json);
	
	if("help".equals(paras)){
		result="helpInfo";
		oper.setResult(result);
		operJson = JSONObject.fromObject(oper);
		json=operJson.toString();
	}
	
	boolean isAppend=true;
	CommMethod.writeFile(realPath+ ConfigUtil.PATH_ROOT+fileName,null,json, isAppend);
%>