package com.pachong.timetask;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pachong.thread.TokenThread;
import com.pachong.thread.TokenThread3;
import com.pachong.thread.TokenThread4;

public class myJob {
	int a = 0;
	 public void execute1(){
		 
		 String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());// new Date()为获取当前系统时间
	      System.out.println("--execute1 开始执行..."+nowTime);
	      a++;
	      System.out.println("---execute1---的第"+a+"次");
	      System.out.println("---execute1---的第"+a+"次");
	      System.out.println("---execute1---的第"+a+"次");
	      System.out.println("---execute1---的第"+a+"次");
	      System.out.println("---execute1---的第"+a+"次");
	      System.out.println("---execute1---的第"+a+"次");
	     //new Thread(new TokenThread()).start();//爬微博文章id（第一个）

	    }
	 public void execute2(){
		 String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		 .format(new Date());// new Date()为获取当前系统时间
		 System.out.println("---execute2 开始执行..."+nowTime);
		 a++;
	      System.out.println("---execute2---的第"+a+"次");
	      System.out.println("---execute2---的第"+a+"次");
	      System.out.println("---execute2---的第"+a+"次");
	      System.out.println("---execute2---的第"+a+"次");
	      System.out.println("---execute2---的第"+a+"次");
	      System.out.println("---execute2---的第"+a+"次");
		//new Thread(new TokenThread4()).start();
		 
	 }
	 public void execute3(){
		 String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		 .format(new Date());// new Date()为获取当前系统时间
		 System.out.println("---execute3 开始执行..."+nowTime);
		 new Thread(new TokenThread3()).start();
		 
	 }

}
