package com.pachong.weibo;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pachong.dao.WeiBoDao;
import com.pachong.entity.WeiBo;
import com.pachong.main.MyCrawler;
import com.pachong.servlet.AllBean;
import com.pachong.util.ProxyIpUtil;
/**
 * 
 * @author Administrator
 *
 */

public class WweiBoTwo {

public static void main(String[] args) {
/*	try {
		getAjaxCotnent("1707180677");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
}
/**
 * 
 * @param uid
 * @throws IOException
 */
	public static    void getAjaxCotnent(String uid) throws IOException {
		WeiBoDao weiBoDao = (WeiBoDao) AllBean.getBean("weiBoDao");
		System.out.println("----------------------getAjaxCotnent  ----*-*-*-*-");
		System.out.println("---------------------getAjaxCotnent  ----*-*-*-*-");
		System.out.println("--------------------------getAjaxCotnent  ----*-*-*-*-");
		System.out.println("---------------------getAjaxCotnent  ----*-*-*-*-");
		System.out.println("---------------------getAjaxCotnent  ----*-*-*-*-");
		 HttpClient 	client = new DefaultHttpClient();
			SinaWeibo weibo = new SinaWeibo("15896863367",
					"15896863367nh");
			ProxyIpUtil.setProxyIp();
			weibo.login(client);
			List weiBoTitleId = weibo.getWeiBoTitleId(uid,client);
			WeiBo weiBos = new WeiBo();
			weiBos.setUser_uid(uid);
			for (int j = 0; j < weiBoTitleId.size(); j++) {
				if (j == 0) {
					weiBos.setWb_title1(weiBoTitleId.get(j).toString());
				} else if (j == 1) {
					weiBos.setWb_title2(weiBoTitleId.get(j).toString());
				} else if (j == 2) {
					weiBos.setWb_title3(weiBoTitleId.get(j).toString());
				} else if (j == 3) {
					weiBos.setWb_title4(weiBoTitleId.get(j).toString());
				} else if (j == 4) {
					weiBos.setWb_title5(weiBoTitleId.get(j).toString());
				}else if (j == 5) {
					weiBos.setWb_title5(weiBoTitleId.get(j).toString());
				}else if (j == 6) {
					weiBos.setWb_title7(weiBoTitleId.get(j).toString());
				}else if (j == 7) {
					weiBos.setWb_title8(weiBoTitleId.get(j).toString());
				}else if (j == 8) {
					weiBos.setWb_title9(weiBoTitleId.get(j).toString());
				}else if (j == 9) {
					weiBos.setWb_title10(weiBoTitleId.get(j).toString());
				}
			}
				weiBoDao.update(weiBos);
				WeiBo weiBoss = new WeiBo();
				if (uid!=null&&!uid.equals("")) {
					String ajaxCotnent1 = null;
					String ajaxCotnent2 = null;
					String ajaxCotnent3 = null;
					String ajaxCotnent4 = null;
					String ajaxCotnent5 = null;
					String ajaxCotnent6 = null;
					String ajaxCotnent7 = null;
					String ajaxCotnent8 = null;
					String ajaxCotnent9 = null;
					String ajaxCotnent10 = null;
					List<WeiBo> titleByUid = weiBoDao.getTitleByUid(uid);
					weiBoss.setUser_uid(uid);
				String wb_title1 = titleByUid.get(0).getWb_title1();
					//String ajaxCotnent1 = MyCrawler.getAjaxCotnent(wb_title1);HttpUtils
					 ajaxCotnent1 = MyCrawler.getAjaxCotnent(wb_title1);
					weiBoss.setWb_content1(ajaxCotnent1);
					String wb_title2 = titleByUid.get(0).getWb_title2();
					 ajaxCotnent2 = MyCrawler.getAjaxCotnent(wb_title2);
					weiBoss.setWb_content2(ajaxCotnent2);
					String wb_title3 = titleByUid.get(0).getWb_title3();
					 ajaxCotnent3 = MyCrawler.getAjaxCotnent(wb_title3);
					weiBoss.setWb_content3(ajaxCotnent3);
					String wb_title4 = titleByUid.get(0).getWb_title4();
					 ajaxCotnent4 = MyCrawler.getAjaxCotnent(wb_title4);
					weiBoss.setWb_content4(ajaxCotnent4);
					String wb_title5 = titleByUid.get(0).getWb_title5();
					 ajaxCotnent5 = MyCrawler.getAjaxCotnent(wb_title5);
					weiBoss.setWb_content5(ajaxCotnent5);
					String wb_title6 = titleByUid.get(0).getWb_title6();
					ajaxCotnent6 = MyCrawler.getAjaxCotnent(wb_title6);
					weiBos.setWb_content6(ajaxCotnent6);

					String wb_title7 = titleByUid.get(0).getWb_title7();
					ajaxCotnent7 = MyCrawler.getAjaxCotnent(wb_title7);
					weiBos.setWb_content7(ajaxCotnent7);

					String wb_title8 = titleByUid.get(0).getWb_title8();
					ajaxCotnent8 = MyCrawler.getAjaxCotnent(wb_title8);
					weiBos.setWb_content8(ajaxCotnent8);

					String wb_title9 = titleByUid.get(0).getWb_title9();
					ajaxCotnent9 = MyCrawler.getAjaxCotnent(wb_title9);
					weiBos.setWb_content9(ajaxCotnent9);

					String wb_title10 = titleByUid.get(0).getWb_title10();
					ajaxCotnent10 = MyCrawler.getAjaxCotnent(wb_title10);
					weiBos.setWb_content10(ajaxCotnent10);
						weiBoDao.update(weiBoss);
					System.out.println("----------------------weiBoDao  ----*-*-*-*-");
					System.out.println("---------------------weiBoDao  ----*-*-*-*-");
					System.out.println("--------------------------weiBoDao  ----*-*-*-*-");
					System.out.println("---------------------weiBoDao  ----*-*-*-*-");
					System.out.println("---------------------weiBoDao  ----*-*-*-*-");
			}
	}
}
