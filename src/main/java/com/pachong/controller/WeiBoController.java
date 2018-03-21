package com.pachong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pachong.service.WeiBoService;

/**
 * 反馈信息
 * 2018-1-13
 * 钮豪
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/weibo")
public class WeiBoController {
	@Resource
	private WeiBoService weiBoService;
@ResponseBody
	@RequestMapping(value = "/update")
	public int batchDelete(String[]  feedback_id) throws Exception {
		int update = weiBoService.update();
		return update;
	}
	
}
