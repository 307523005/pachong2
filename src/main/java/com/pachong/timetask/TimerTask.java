package com.pachong.timetask;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * 类名称：TimerTask   
 * 类描述：定时器任务
 * 创建人：niuhao
 * 创建时间：3-15, 2018 10:56:27 AM      
 * @version  V1.0
 *
 */
@Component
public class TimerTask {
  /**
   * 每天22点30启动任务
   */
  @Scheduled(cron = "0 27 11 ? * *")
  public void test1()
  {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		.format(new Date());// new Date()为获取当前系统时间
      System.out.println("job1 开始执行..."+nowTime);
  } 
     @Scheduled(cron = "0/5 * * * * ?")//每隔5秒隔行一次 
  public void test2()
  {
     System.out.println("job2 开始执行");
  } 
     public void test3()
     {
    	 System.out.println("job2 开始执行");
     } 
     public void test4()
     {
    	 System.out.println("job2 开始执行");
     } 
     public void test5()
     {
    	 System.out.println("job2 开始执行");
     } 
}