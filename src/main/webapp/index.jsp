<%@page import="com.jview.common.util.ConfigUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%if(ConfigUtil.isTest){%>
<html lang="zh-hans">
<%}else{ %>
<html lang="zh-hans" manifest="index.appcache">
<%} %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=0.5" />
	<link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css" />
	
	<script type="text/javascript" src="scripts/lib/jquery-2.0.1.min.js"></script>
	
	<title>jtool/db</title>
	<script type="text/javascript">
	var datas=[];
	function listenerName(ex) {
		if (ex.keyCode == 13) {
			doSubmit();
		}
	}

	function getCurrentTime()
	{ 
		var now = new Date();
		
       // var year = now.getFullYear();       //年
       // var month = now.getMonth() + 1;     //月
       // var day = now.getDate();            //日
       
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        
        var clock="";

        /*
        clock = year + "-";
        if(month < 10)
            clock += "0";
       
        clock += month + "-";
       
        if(day < 10)
            clock += "0";
           
        clock += day + " ";
        */
        
        if(hh < 10)
        	clock += "0";
        
        clock += hh + ":";
        if (mm < 10) clock += '0'; 
        clock += mm; 
        return(clock); 
    };

    $(document).ready(function(){
    	resetInput();
    	
    	$('#paras').keydown(listenerName);
    });
    
    function resetInput(){
    	$('#paras').focus();
    }

    
    
    function doSubmit(){
    	var types=$("#types").val();
    	var paras=$("#paras").val();
    	if(!paras){
    		return;
    	}
    	$.ajax({
    		type: "POST",
    		url: "jtoolDo.jsp",
    		data: "types="+types+"&paras="+paras,
    		success: function(msg){
    			if(msg && typeof(msg)=='string'){
    				msg=msg.trim();
    			}
    			eval("data="+msg);
			     //console.log(data);
			     //console.log(data.result);
			     $("#result").text(data.result);
			     
			     datas.push(data);
			 }
			});
    	return false;
    }
    
    function showHistory(){
    	var info='';
    	for(var i=datas.length-1; i>=0; i--){
    		info+= datas[i].time+' '+datas[i].result+'\n';
    	}
    	alert(info);
    }
    
    function doClean(){
    	datas=[];
    }
    
    </script>
</head>
<%
String paras = request.getParameter("paras");
String types = request.getParameter("types");
if(types==null){
types="dbs";
}
if(paras==null){
paras="";
}
%>

<body>
	
	<div class="container">
		
		<div  class="form-inline" >
			<div class="form-group">
				<select id="types" name="types" >
					<option value="dbs" <%if(types.equals("dbs")){ %>selected<%} %>>dbs</option>
					<option value="tools" <%if(types.equals("tools")){ %>selected<%} %>>tools</option>
				</select>
				<input type="text" id="paras" name="paras" value="<%=paras%>" placeholder="help">
				<button type="button" class="btn btn-default" onclick="doSubmit()">OK</button>
				<button type="button" class="btn btn-default" onclick="showHistory()">History</button>
				<button type="button" class="btn btn-default" onclick="doClean()">Clean</button>
			</div>
		</div>
		<textarea id="result" rows="20" class="form-control">
			
		</textarea>
	</div>
</body>
</html>