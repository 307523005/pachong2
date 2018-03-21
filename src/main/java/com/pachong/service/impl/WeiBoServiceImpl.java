package com.pachong.service.impl;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pachong.dao.WeiBoDao;
import com.pachong.entity.WeiBo;
import com.pachong.service.WeiBoService;
import com.pachong.util.ProxyIpUtil;
import com.pachong.util.RegexRule;
import com.pachong.weibo.SinaWeibo;
@Service
@Transactional
public class WeiBoServiceImpl implements WeiBoService {
	@Autowired
	private WeiBoDao weiBoDao;

	@Override
	public List<WeiBo> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeiBo> getTitleByUid(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update() {
		// TODO Auto-generated method stub
		HttpClient client = null;
		int d = 0;
		int a = 0;
		int c = 1;
		List<WeiBo> list = weiBoDao.getList();
		// int b = list.size() / 2;
		try {
			client = new DefaultHttpClient();
			SinaWeibo weibo = new SinaWeibo("15896863367", "15896863367nh");
			/*
			 * SinaWeibo weibo = new SinaWeibo("13526516113", "nao210xu");
			 */
			weibo.login(client);
			ProxyIpUtil.setProxyIp();
			boolean login = weibo.login(client);
			while (!login) {
				ProxyIpUtil.setProxyIp();
				login = weibo.login(client);
				if (login) {
					break;
				}
			}

			/*
			 * list.add("1744601577"); list.add("5597175755");
			 * list.add("6418914145"); list.add("1757500267");
			 * list.add("3196944050"); list.add("5347286978");
			 * list.add("3008527310"); list.add("3744050423");
			 * list.add("5996184614"); list.add("6209134142");
			 */
			int e = 0;
			for (int i = 0; i < 20; i++) {
				if (e > 3) {

					// 随机数
					String randomNum = RegexRule.getRandomNum(1);
					System.out.println("--1个休息" + randomNum + "秒---");
					Thread.sleep(Integer.valueOf(randomNum) * 1000);
					e = 0;
					ProxyIpUtil.setProxyIp();
				}
				/*if (c > 20) {
					// 随机数
					String randomNum = RegexRule.getRandomNum(1);
					System.out.println("---换账号---1个休息" + randomNum + "秒---");
					Thread.sleep(Integer.valueOf(randomNum) * 1000);
					if (d == 0) {
						ProxyIpUtil.setProxyIp();
						client = new DefaultHttpClient();
						weibo = new SinaWeibo("13526516113", "nao210xu");
						weibo.login(client);
						d = 1;
					} else {
						ProxyIpUtil.setProxyIp();
						weibo = weibo = new SinaWeibo("15896863367",
								"15896863367nh");
						weibo.login(client);
						d = 0;
					}
					c = 0;

				}*/
				c++;
				WeiBo weiBos = new WeiBo();
				String uid = list.get(i).getUser_uid();
				weiBos.setUser_uid(String.valueOf(uid));
				List weiBoTitleId = weibo.getWeiBoTitleId(uid, client);
				// 是否有文章id
				if (weiBoTitleId.size() > 0) {
					// 取前十文章
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
						} else if (j == 5) {
							weiBos.setWb_title6(weiBoTitleId.get(j).toString());
						} else if (j == 6) {
							weiBos.setWb_title7(weiBoTitleId.get(j).toString());
						} else if (j == 7) {
							weiBos.setWb_title8(weiBoTitleId.get(j).toString());
						} else if (j == 8) {
							weiBos.setWb_title9(weiBoTitleId.get(j).toString());
						} else if (j == 9) {
							weiBos.setWb_title10(weiBoTitleId.get(j).toString());
						}
					}
				}
				weiBoDao.update(weiBos);
				a++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 获取失败时，10秒之后尝试重新获取
			e.printStackTrace();
		}
		if (a == list.size()) {
			// 7000秒之后重新进行获取
			System.out.println("----- 获取成功时，return 1---");
			return 1;
		} else {
			// 获取失败时，1秒之后尝试重新获取
			System.out.println("--- 获取失败时，return 0---");
			return 0;
		}

	}

}
