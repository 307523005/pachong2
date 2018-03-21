package com.pachong.dao;

import java.util.List;

import com.pachong.entity.ProxyIp;

public interface ProxyIpDao {
	/**
	 *查询所有 
	 * @return
	 */
	public List<ProxyIp>  getList();
	/**
	 * 根据id得到ip
	 * @return
	 */
	public List<ProxyIp>  getIpById(String id);
	/**
	 * 修改
	 * @param 
	 * @return
	 */
	public int update(ProxyIp proxyIp);
}
