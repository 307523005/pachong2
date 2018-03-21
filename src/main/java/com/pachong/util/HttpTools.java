package com.pachong.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpTools {
	/**
	 * 正常GET方式HTTP请求
	 * 
	 * @param client
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getRequest(HttpClient client, String url) {
		HttpGet get = new HttpGet(url);
		get.addHeader("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN");
		String content="";
		try {
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		 content = EntityUtils.toString(entity, "GBK");
		get.releaseConnection();
		} catch (Exception e) {
			System.out.println("-----------response = client.execute(get);错误-----------");
			e.printStackTrace();
		}
		return content;
	}

	public static String getRequest2(HttpClient client, String url) {
	
		HttpGet get = new HttpGet(url);
		get.addHeader("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN");
		HttpResponse response;
		// 设置超时
		RequestConfig requestConfig = RequestConfig.custom() .setConnectTimeout(5000).setConnectionRequestTimeout(1000)  
		        .setSocketTimeout(5000).build();  
		get.setConfig(requestConfig);  
		String content = "";
		try {
			response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, "GBK");
			}else {
				return content = "";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("-----------response = client.execute(get);错误-----------");
			e.printStackTrace();
		}
		get.releaseConnection();
		return content;
	}

	/**
	 * 正常POST方式HTTP请求
	 * 
	 * @param client
	 * @param url
	 * @param parms
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String postRequest(HttpClient client, String url,List<NameValuePair> parms)  {
		String content="";
		try {
		HttpPost post = new HttpPost(url);
		// post.addHeader("User-Agent",
		// "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
		post.addHeader("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN");
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		// post.addHeader(&quot;Referer&quot;,
		// &quot;http://2013.weibo.com/&quot;);
		UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(parms, "GBK");
		post.setEntity(postEntity);
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		 content = EntityUtils.toString(entity, "GBK");
		/* EntityUtils.consume(entity); */
		post.releaseConnection();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("-----------response = client.execute(post);错误-----------");
			e.printStackTrace();
		}
		return content;
	}
}
