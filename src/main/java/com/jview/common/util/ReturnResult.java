package com.jview.common.util;

import java.util.List;

public class ReturnResult<T> {
	private int flag;
	private String messageInfo;
	private String messageBody;
	private List<T> dataList;
	public void error(int flag, String messageInfo, String messageBody){
		this.error(flag, messageInfo, messageBody, null);
	}
	public void error(int flag, String messageInfo, String messageBody, Exception e){
		this.flag=flag;
		this.messageInfo=messageInfo;
		this.messageBody=messageBody;
		if(e!=null){
			this.messageBody+=" error:"+e.getMessage();
		}
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
	
}
