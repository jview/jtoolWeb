package com.jview.thread.sync;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {

	private int maxSize;
	private List<Date> storage;
	
	public EventStorage(){
		maxSize = 10;
		storage = new LinkedList<Date>();
	}
	
	public synchronized void set(){
		while(storage.size() == maxSize){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		((LinkedList<Date>) storage).offer(new Date());
		System.out.println("Set:"+storage.size());
		notifyAll();
	}
	
	public synchronized void get(){
		while(storage.size()==0){
			System.out.println("---------------------------等待中--------------------");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Get:%d:%s",storage.size(),((LinkedList<Date>) storage).poll());
		notifyAll();
	}
}
