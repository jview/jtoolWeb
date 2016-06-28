package com.jview.comm;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class test {
	public static void main(String[] args) {
		System.out.println("-----0-----");
		FutureTask<String> futureTask = new FutureTask<>(
				new Callable<String>() {
					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						System.out.println("-----1-----");
						return "�ص����";
					}
				});
		try {
			String str = futureTask.get();
			System.out.println("-----2-----");
			if (str.equals("�ص����"))
				System.out.println("�첽�������!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----1-----");
	}
}
