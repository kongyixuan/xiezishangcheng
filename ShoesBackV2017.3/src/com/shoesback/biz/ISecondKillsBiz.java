package com.shoesback.biz;

import com.shoesback.po.SecondKills;
import com.shoesback.vo.PageBean;

public interface ISecondKillsBiz {
     //分页获取秒杀商品
	public PageBean FindByPage(int currentpage,int pageSize);
	//创建新增秒杀商品
	public void CreateSecondKill(SecondKills kill);
	//获取单个秒杀商品对象
	public SecondKills FindBySkid(int skid);
	//更新秒杀商品
	public void UpdateSecondKill(SecondKills kill);
}
