package com.pachong.util;



import com.pachong.page.Page;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  本类主要是 下载那些已经访问过的文件*/
public class FileTool {

    private static String dirPath;


    /**
     * getMethod.getResponseHeader("Content-Type").getValue()
     * 根据 URL 和网页类型生成需要保存的网页的文件名，去除 URL 中的非文件名字符
     */
    private static String getFileNameByUrl(String url, String contentType) {
        //去除 http://
        url = url.substring(7);
        //text/html 类型
        if (contentType.indexOf("html") != -1) {
            url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
            return url;
        }
        //如 application/pdf 类型
        else {
            return url.replaceAll("[\\?/:*|<>\"]", "_") + "." +
                    contentType.substring(contentType.lastIndexOf("/") + 1);
        }
    }

    /*
    *  生成目录
    * */
    private static void mkdir() {
       /* if (dirPath == null) {
            //dirPath = Class.class.getClass().getResource("/").getPath() + "temp\\";
        	dirPath="D:/temp\\";
        }
        File fileDir = new File(dirPath);
        System.out.println(dirPath+"-------dirPath------");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }*/
    	 // 得到二维码路径,生成二维码图片前要先创建空白文件
    }

    /**
     * 保存数据到本地文件，filePath 为要保存的文件的相对地址
     */

    public static void saveToLocal(Page page,String filePath,String a) {
        mkdir();
        //String fileName =  getFileNameByUrl(page.getUrl(), page.getContentType()) ;
       // byte[] data = page.getContent();
        byte[] data = a.getBytes();
        try {
            //Files.lines(Paths.get("D:\\jd.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
            for (int i = 0; i < data.length; i++) {
                out.write(data[i]);
            }
            out.flush();
            out.close();
            System.out.println("文件：" + "已经被存储在"+ filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 保存数据到本地文件，filePath 为要保存的文件的相对地址
     */
    
    public static void saveToLocal(String filePath,String a) {
    	mkdir();
    	//String fileName =  getFileNameByUrl(page.getUrl(), page.getContentType()) ;
    	// byte[] data = page.getContent();
    	byte[] data = a.getBytes();
    	try {
    		//Files.lines(Paths.get("D:\\jd.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
    		DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
    		for (int i = 0; i < data.length; i++) {
    			out.write(data[i]);
    		}
    		out.flush();
    		out.close();
    		System.out.println("文件：" + "已经被存储在"+ filePath);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 去除非中文
     * @param buff
     * @return
     */
    public static String clearNotChinese(String buff){
     	String tmpString =buff.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]","");//去掉所有中英文符号
    	char[] carr = tmpString.toCharArray();
    	for(int i = 0; i<tmpString.length();i++){
    		if(carr[i] < 0xFF){
    			carr[i] = ' ' ;//过滤掉非汉字内容
    		}
    	}
    	return String.copyValueOf(carr).trim();
    }
    /**
     * 将数据分行读取
     * @param name
     * @return
     * @throws Exception
     */
    	public static List readLine(String name)throws Exception{
    	String[] a= null; 
    	List list = new ArrayList();
    	File file = new File(name);//Text文件
    	BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
    	String s = null;
    	while((s = br.readLine())!=null){//使用readLine方法，一次读一行
    		s= "\""+clearNotChinese(s).replace(" ", "")+"\"";
    	//System.out.println(s+"sssssssssssssssssss");
    	list.add(s);
    	}
    	//System.out.println(list);
    	br.close();
    	return list;
    	}
}
