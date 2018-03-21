package com.pachong.thread;

import java.util.List;
import com.pachong.dao.WeiBoDao;
import com.pachong.entity.WeiBo;
import com.pachong.main.MyCrawler;
import com.pachong.main.MyCrawler;
import com.pachong.servlet.AllBean;
import com.pachong.util.ProxyIpUtil;
import com.pachong.util.RegexRule;
import com.pachong.weibo.WweiBoTwo;

public class TokenThread3 implements Runnable {
	/*
	 * @Resource private WxuserService wxuserService;
	 */
	WeiBoDao weiBoDao = (WeiBoDao) AllBean.getBean("weiBoDao");
	boolean login = false;
	boolean test=true;
	int a = 0;
	int c = 0;
	@Override
	public void run() {
		while (test) {
			
			List<WeiBo> list = weiBoDao.getList();
			a=0;
			c = 0;
			try {
				try {
					System.out.println("--线程2--延迟10---");
					Thread.sleep(1000 * 60*2);
					for (int j = 0; j < list.size(); j++) {
						if (c > 9) {
							String randomNum = RegexRule.getRandomNum(1);
							Thread.sleep(Integer.valueOf(randomNum) * 1000);
							c = 0;
							ProxyIpUtil.setProxyIp();
						}
						c++;
						WeiBo weiBos = new WeiBo();
						String uid = list.get(j).getUser_uid();
						System.out.println("---uid---" + uid);
						if (uid != null && !uid.equals("")) {
							List<WeiBo> titleByUid = weiBoDao
									.getTitleByUid(uid);
							weiBos.setUser_uid(uid);
							String wb_title1 = titleByUid.get(0).getWb_title1();
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
							// MyCrawler.getAjaxCotnent(wb_title1);HttpUtils
							ajaxCotnent1 = MyCrawler.getAjaxCotnent(wb_title1);
							weiBos.setWb_content1(ajaxCotnent1);
							String wb_title2 = titleByUid.get(0).getWb_title2();
							ajaxCotnent2 = MyCrawler.getAjaxCotnent(wb_title2);
							weiBos.setWb_content2(ajaxCotnent2);
							String wb_title3 = titleByUid.get(0).getWb_title3();
							ajaxCotnent3 = MyCrawler.getAjaxCotnent(wb_title3);
							weiBos.setWb_content3(ajaxCotnent3);
							String wb_title4 = titleByUid.get(0).getWb_title4();
							ajaxCotnent4 = MyCrawler.getAjaxCotnent(wb_title4);
							weiBos.setWb_content4(ajaxCotnent4);
							String wb_title5 = titleByUid.get(0).getWb_title5();
							ajaxCotnent5 = MyCrawler.getAjaxCotnent(wb_title5);
							weiBos.setWb_content5(ajaxCotnent5);
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
							String wb_title10 = titleByUid.get(0)
									.getWb_title10();
							ajaxCotnent10 = MyCrawler
									.getAjaxCotnent(wb_title10);
							weiBos.setWb_content10(ajaxCotnent10);
					/*		if (ajaxCotnent1.equals("")
									|| ajaxCotnent2.equals("")
									|| ajaxCotnent3.equals("")
									|| ajaxCotnent4.equals("")
									|| ajaxCotnent5.equals("")
									|| ajaxCotnent6.equals("")
									|| ajaxCotnent7.equals("")
									|| ajaxCotnent8.equals("")
									|| ajaxCotnent9.equals("")
									|| ajaxCotnent10.equals("")) {
								
								System.out
								.println("--*-*-111111000001*-*-getAjaxCotnent  ----*-*-*-*-");
								System.out
								.println("--*-*-111111000001*-*-getAjaxCotnent  ----*-*-*-*-");
								System.out
								.println("--*-*-111111000001*-*-getAjaxCotnent  ----*-*-*-*-");
								System.out
								.println("--*-*-111111000001*-*-getAjaxCotnent  ----*-*-*-*-");
								System.out
								.println("--*-*-111111000001*-*-getAjaxCotnent  ----*-*-*-*-");
								WweiBoTwo.getAjaxCotnent(uid);
							} else {*/
								weiBoDao.update(weiBos);
							//}
						}
						a++;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// 获取失败时，10秒之后尝试重新获取
					Thread.sleep(10 * 1000);
					e.printStackTrace();
				}
				if (a == list.size()) {
					// 之后重新进行获取
					System.out.println("-3333- 获取成功时，3分钟之后重新进行获取");
                    test = false;
	               // Thread.sleep(1000*60*3);
				} else {
					// 获取失败时，1秒之后尝试重新获取
					System.out.println("-3333- 获取失败时，5秒之后尝试重新获取");
					Thread.sleep(5 * 1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
