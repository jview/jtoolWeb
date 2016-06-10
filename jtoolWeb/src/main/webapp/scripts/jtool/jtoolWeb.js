var app = angular.module("JtoolWebModule", ['ngDialog']);

 app.config(['ngDialogProvider', function (ngDialogProvider) {
            ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
                plain: false,
                showClose: true,
                closeByDocument: true,
                closeByEscape: true,
                appendTo: false,
                width:500,
                preCloseCallback: function () {
                    console.log('default pre-close callback');
                }
            });
        }]);
    
app.controller('jtoolDo', function($scope,$rootScope, $http, ngDialog) {
	$rootScope.theme = 'ngdialog-theme-default';
    $scope.types='dbs';
    $scope.datas=[];
    $scope.test1=function(){
    	alert("test1");
    };
    
    
	

    $scope.myKeyup = function(e){
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.doSubmit();
        }
    };
    

    $scope.doSubmit=function(){
    	//alert("doSubmit");
    	console.log($scope.types+" "+$scope.paras);
    	$scope.jtool($scope.types, $scope.paras);
    	console.info(getCurrentTime());
    };

    $scope.showHistory=function(){

    	var datas=$scope.datas;
    	console.log('-------showHistory--'+datas);
    	
    	var datas_desc=[];
		var data;
		for(var i=datas.length-1; i>=0; i--){
			data = datas[i];
			datas_desc.push(data);
		}
		$scope.datas_desc=datas_desc;
		
		//alert(info);
    	
    	
		
		ngDialog.open({ template: 'tpls/operLogs.html',  scope: $scope });
    };
    

    $scope.doClean=function(){
    	$scope.datas=[];
    };

    getCurrentTime=function(){ 
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
    
    successResult=function(data){
    	//console.log(data);
    	//console.log(typeof(data));
        if(data && typeof(data)=='string'){
        	data=data.trim();
        }
        $scope.result=data.result;
        $scope.datas.push(data);
    };
    
    $scope.jtool = function(types, paras) {
    	//console.log('---post--jtool--types='+types+' paras='+paras);
        if(!paras){
            return;
        }
    	var params = {params:{
            types: types,
            paras: paras
        }};
        $http.post('jtoolDo.jsp', params, params).success(function(data){
        	//console.log('---post--data--'+data);
            successResult(data);
        });
       
    };

    

    $scope.jtoolGet = function(types, paras) {
        if(!paras){
            return;
        }
        $http.get('jtoolDo.jsp', {params:{
            types: types,
            paras: paras
        }}).success(function(data){
            //console.log('---get--data--'+data);
            successResult(data);
        });
      
    };
    
});