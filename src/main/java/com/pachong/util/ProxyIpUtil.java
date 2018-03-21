package com.pachong.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.conn.params.ConnRoutePNames;

import com.pachong.dao.ProxyIpDao;
import com.pachong.entity.ProxyIp;
import com.pachong.servlet.AllBean;


/**
 * 一种代理ip解决方案
 */
public class ProxyIpUtil {
	/**
	 * 设置代理ip   ProxyIpUtil.setProxyIp();
	 * @throws IOException
	 */
	
	public static void setProxyIp() {
		try {
			/*List<String> ipList = new ArrayList<>();
			BufferedReader proxyIpReader = new BufferedReader(new InputStreamReader(ProxyIpUtil.class.getResourceAsStream("/proxyip.txt")));
			
			String ip = "";
			while((ip = proxyIpReader.readLine()) != null) {
				ipList.add(ip);
			}*/
			ProxyIpDao proxyIpDao = (ProxyIpDao) AllBean.getBean("proxyIpDao");
			Random random = new Random();
			//0－n的随机数
			int randomInt = random.nextInt(98);
			/*String ipport = ipList.get(randomInt);
			String proxyIp = ipport.substring(0, ipport.lastIndexOf(":"));
			String proxyPort = ipport.substring(ipport.lastIndexOf(":") + 1, ipport.length());*/
			String randomNum = String.valueOf(randomInt+1);
			List<ProxyIp> ipById = proxyIpDao.getIpById(randomNum);
			if (ipById.size()>0) {
			String proxyIp = ipById.get(0).getPi_ip();
			String proxyPort =ipById.get(0).getPi_port();
			System.setProperty("http.maxRedirects", "50");
	        System.getProperties().setProperty("proxySet", "true");
	        System.getProperties().setProperty("http.proxyHost", proxyIp);  //"http.proxyPort"
	        System.getProperties().setProperty("http.proxyPort", proxyPort);//"http.proxyHost"
			}else {
				System.out.println("重新设置代理ip");
				setProxyIp();
			}
		} catch (Exception e) {
			System.out.println("重新设置代理ip");
			setProxyIp();
		}
	}  
	public static void main(String[] args) {
		setProxyIp();

	}
}	