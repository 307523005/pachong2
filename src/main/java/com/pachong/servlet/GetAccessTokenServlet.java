package com.pachong.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.pachong.thread.TokenThread;
import com.pachong.thread.TokenThread2;
import com.pachong.thread.TokenThread3;
import com.pachong.thread.TokenThread4;
import com.pachong.thread.TokenThread5;
import com.pachong.thread.TokenThread6;

public class GetAccessTokenServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
@Override
public void init() throws ServletException {
	System.out.println();
//启动线程
new Thread(new TokenThread()).start();//爬微博文章id（第一个）
//new Thread(new TokenThread2()).start();//爬微博文章id（第二个）
//new Thread(new TokenThread3()).start();//爬文章详细数据
new Thread(new TokenThread4()).start();//爬微博文章id（第三个）
//new Thread(new TokenThread5()).start();//爬微博文章id（第4个）
new Thread(new TokenThread6()).start();//爬ip
}
}