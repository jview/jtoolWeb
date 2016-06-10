package com.jview.common.util;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
 
class TaskWithResult implements Callable<String>{
    private int id;
    private static int count =10;
    private final int time =count--;
    public TaskWithResult(int id){
        this.id = id;
    }
     
//    @Override
    public String call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(1000);
//        Thread.sleep(2000);
        return "Result of TaskWithResult : "+ id+", Time= "+time;
    }
     
}
public class CallableDemo {
 
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//    	long time=System.currentTimeMillis();
         ExecutorService exec = Executors.newCachedThreadPool();
         ArrayList<Future<String>> results =new ArrayList<Future<String>>();
//         System.out.println("---time1="+(System.currentTimeMillis()-time));
         for(int i=0;i<10;i++){
             results.add(exec.submit(new TaskWithResult(i)));
         }
//         System.out.println("---time2="+(System.currentTimeMillis()-time));
         for(Future<String> fs : results){
             System.out.println(fs.get());
         }
         exec.shutdown();
//         System.out.println("---time3="+(System.currentTimeMillis()-time));
    }
 
}