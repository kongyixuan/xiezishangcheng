package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Percontent;
import com.shoesback.po.Permission;

public interface IPercontentBiz {
    //获取管理员全部items
	public List<Percontent> FindByManager();
	//保存percontent
	public void SavePercontent(Percontent percontent);
	//获取单个权限信息对应的percontent集合
	public List<Percontent> FindByperid(int perid);
	//对删除原有和添加新增的权限items
	public void UpdatePercontent(Permission permission,List<String> nowper,List<String> overper);
}
