package com.pachong.dao;

import java.util.List;

import com.pachong.entity.WeiBo;


public interface WeiBoDao {
	/**
	 * 
	 * @return
	 */
	public List<WeiBo>  getList();
	/**
	 * 
	 * @return
	 */
	public List<WeiBo>  getTitleByUid(String uid);
	/**
	 * 
	 * @param weiBo
	 * @return
	 */
	public int update(WeiBo weiBo);
}
