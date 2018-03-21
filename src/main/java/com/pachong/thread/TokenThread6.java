package com.pachong.thread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.impl.client.DefaultHttpClient;
import com.pachong.ProxyIp.ProxyIp;
import com.pachong.dao.ProxyIpDao;
import com.pachong.main.HttpUtils;
import com.pachong.servlet.AllBean;
import com.pachong.util.ProxyIpUtil;

public class TokenThread6 implements Runnable {
	/*
	 * 爬取ip的线程
	 */
	ProxyIpDao proxyIpDao = (ProxyIpDao) AllBean.getBean("proxyIpDao");
	int d = 0;
	int a = 0;
	@Override
	public void run() {
		System.out.println("线程6启动");
		while (true) {
			try {
			Thread.sleep(1000 * 10 );
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List listIp = new ArrayList<>();
			List listPort = new ArrayList<>();
			a = 0;
			try {
				String url = "http://www.xicidaili.com/";// nt,wn.wt

				if (d == 0) {
					url = url + "nn/";
					d = 1;
				}else if(d == 1) {
					url = url + "nt/";
					d = 2;
				}else if(d == 2) {
					url = url + "wn/";
					d = 3;
				}else if(d == 3) {
					url = url + "wt/";
					d = 0;
				}

				String ajaxCotnent = HttpUtils.getAjaxCotnent(url);
				while(ajaxCotnent.equals("")){
					System.out.println("获取代理ip错误");
					ajaxCotnent = HttpUtils.getAjaxCotnent(url);
					try {
						Thread.sleep(1000 * 10 );
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(!ajaxCotnent.equals("")){
						break;
					}
				}
				// 取出需要的ip字段
				String ip = "n.png\"alt=\"Cn\"></td><td>.*?<";
				Matcher m = Pattern.compile(ip).matcher(ajaxCotnent);
				String endIp = null;
				// 循环得到需要的i
				/*
				 * while (m.find()) { // 去除空格和斜杠 endIp =
				 * m.group().replaceAll("<td data-title=\"IP\">", "")
				 * .replaceAll("<", ""); System.out.println("-----" + endIp);
				 * listIp.add(endIp); }
				 */
				while (m.find()) {
					// 去除空格和斜杠
					endIp = m.group()
							.replaceAll("n.png\"alt=\"Cn\"></td><td>", "")
							.replaceAll("<", "");
					System.out.println("-----" + endIp);
					listIp.add(endIp);
				}
				// 取出需要的PORT字段
				String PORT = "</td><td>[0-9]{2,6}</td><td><ahref=\"/";
				Matcher n = Pattern.compile(PORT).matcher(ajaxCotnent);
				String endPORT = null;
				// 循环得到需要的i
				while (n.find()) {
					// 去除空格和斜杠
					endPORT = n.group().replaceAll("</td><td>", "")
							.replaceAll("<ahref=\"/", "");

					listPort.add(endPORT);
				}
				System.out.println("-----" + listPort.size() +listIp.size());
				if (listPort.size() == listIp.size()) {
					com.pachong.entity.ProxyIp proxyIp = new com.pachong.entity.ProxyIp();
					// 循环放入数据库
					for (int j = 0; j < listPort.size(); j++) {
						proxyIp.setPi_id(String.valueOf(j + 1));
						String ips = listIp.get(j).toString();
						proxyIp.setPi_ip(ips);
						String ports = listPort.get(j).toString();
						proxyIp.setPi_port(ports);
						if (!ips.equals("") && !ports.equals("")) {
							String nowTime = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss").format(new Date());// new
																				// Date()为获取当前系统时间
							proxyIp.setPi_time(nowTime);
							proxyIpDao.update(proxyIp);
						}
						a++;
					}
				
					if (a == listPort.size()&& listPort.size()!=0) {
						// 之后重新进行获取
						System.out.println("-ipipipip--成功--3分钟之后尝试重新获取");
						// 让出CPU，给其他线程执行
						Thread.sleep(1000 * 60 );
					} else {
						// 获取失败时，10秒之后尝试重新获取
						System.out.println("-3333- 获取失败时，10秒之后尝试重新获取");
						Thread.sleep(10 * 1000);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// 获取失败时10 之后尝试重新获取
				try {
					Thread.sleep(10 * 1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		}
	}
}
