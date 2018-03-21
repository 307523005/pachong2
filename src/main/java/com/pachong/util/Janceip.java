package com.pachong.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class Janceip {
	 /**
     * 批量代理IP有效检测
     *
     * @param proxyIpMap
     * @param reqUrl
     */
    public static void checkProxyIp(Map<String, Integer> proxyIpMap, String reqUrl) {

          for (String proxyHost : proxyIpMap.keySet()) {
                Integer proxyPort = proxyIpMap.get(proxyHost);

                int statusCode = 0;
                try {
                      HttpClient httpClient = new HttpClient();
                      httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);

                      // 连接超时时间（默认10秒 10000ms） 单位毫秒（ms）
                      int connectionTimeout = 10000;
                      // 读取数据超时时间（默认30秒 30000ms） 单位毫秒（ms）
                      int soTimeout = 30000;
                      httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
                      httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

                      HttpMethod method = new GetMethod(reqUrl);

                      statusCode = httpClient.executeMethod(method);
                } catch (Exception e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                }
                System.out.format("%s:%s-->%s\n", proxyHost, proxyPort, statusCode);
          }
    }

    /**
     * 代理IP有效检测
     *
     * @param proxyIp
     * @param proxyPort
     * @param reqUrl
     */
    public static void checkProxyIp(String proxyIp, int proxyPort, String reqUrl) {
          Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
          proxyIpMap.put(proxyIp, proxyPort);
          checkProxyIp(proxyIpMap, reqUrl);
    }
    
    public static void main(String[] args) {
          
          Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
          proxyIpMap.put("114.80.136.112", 7780);
          proxyIpMap.put("114.80.136.166", 7780);
          proxyIpMap.put("114.80.136.167", 7780);
          proxyIpMap.put("114.80.136.168", 7780);
          proxyIpMap.put("114.80.136.169", 7780);
          proxyIpMap.put("114.80.136.170", 7780);
          proxyIpMap.put("114.80.136.171", 7780);
          proxyIpMap.put("123.129.207.102", 7780);
          proxyIpMap.put("123.129.207.100", 7780);
          proxyIpMap.put("123.129.207.103", 7780);
          proxyIpMap.put("123.129.207.104", 7780);
          proxyIpMap.put("123.129.207.105", 7780);
          proxyIpMap.put("123.129.207.108", 7780);
          proxyIpMap.put("114.80.136.164", 7780);
          proxyIpMap.put("123.129.207.107", 7780);
          proxyIpMap.put("180.153.251.3", 7780);
          proxyIpMap.put("180.153.251.4", 7780);
          proxyIpMap.put("180.153.251.5", 7780);
          proxyIpMap.put("180.153.251.6", 7780);
          proxyIpMap.put("180.153.251.7", 7780);
          proxyIpMap.put("180.153.251.8", 7780);
          proxyIpMap.put("180.153.251.9", 7780);
          proxyIpMap.put("180.153.251.10", 7780);
          proxyIpMap.put("180.153.251.11", 7780);
          proxyIpMap.put("180.153.251.12", 7780);
          proxyIpMap.put("180.153.251.13", 7780);
          proxyIpMap.put("180.153.251.14", 7780);
          proxyIpMap.put("180.153.251.15", 7780);
          proxyIpMap.put("180.153.251.16", 7780);
          proxyIpMap.put("180.153.251.2", 7780);
          checkProxyIp(proxyIpMap, "http://t.sohu.com/new_index");

    }
}
