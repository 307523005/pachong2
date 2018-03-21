package com.pachong.ProxyIp;

import com.pachong.dao.WeiBoDao;
import com.pachong.entity.WeiBo;
import com.pachong.link.LinkFilter;
import com.pachong.link.Links;
import com.pachong.page.Page;
import com.pachong.page.RequestAndResponseTool;
/**  
 * 爬虫,爬取代理ip
 * User: 钮豪 
 * Date: 2018-3-13
 */   
public class ProxyIp {

    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     * @return
     */
    private void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++){
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }


    /**
     * 抓取过程
     *
     * @param seeds
     * @return
     */
	public String crawling(String[] seeds, final String seed) {
		Page page = null;
		String aa = null;
		try {
		// 初始化 URL 队列
		initCrawlerWithSeeds(seeds);
        //定义过滤器，提取以 http://www.baidu.com 开头的链接
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if (url.startsWith(seed))
                    return true;
                else
                    return false;
            }
        };
        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
		while (!Links.unVisitedUrlQueueIsEmpty()
				&& Links.getVisitedUrlNum() <= 1000) {
			// 先从待访问的序列中取出第一个；
			String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
			if (visitUrl == null) {
				continue;
			}
			// 根据URL得到page;
			page= RequestAndResponseTool
					.sendRequstAndGetResponse(visitUrl);
			aa=page.getHtml();
			return aa;
		}
	
		} catch (Exception e) {
			e.printStackTrace();
			return aa="";
		}
		return aa;
    }
    //main 方法入口
    public static void main(String[] args) {
    	ProxyIp crawler = new ProxyIp();
        //代理ip
        String url = "http://www.66ip.cn/areaindex_1/1.html";
        String crawling = crawler.crawling(new String[]{url},url);
    }
    public static String getAjaxCotnent1( )  {
    	String crawling = null;
			try {
				ProxyIp crawler = new ProxyIp();
				  String url = "https://www.kuaidaili.com/free/inha";
				crawling = crawler.crawling(new String[] { url }, url);
			} catch (Exception e) {
				e.printStackTrace();
				return crawling = "";
			}
			return crawling;
    }
    public static String getAjaxCotnent2( )  {
    	String crawling = null;
    	try {
    		ProxyIp crawler = new ProxyIp();
    		String url = "https://www.kuaidaili.com/free/intr";
    		crawling = crawler.crawling(new String[] { url }, url);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return crawling = "";
    	}
    	return crawling;
    }
}
