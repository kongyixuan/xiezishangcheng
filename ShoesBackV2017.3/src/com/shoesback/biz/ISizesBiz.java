package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Sizes;

public interface ISizesBiz {
   // 获取全部尺寸信息
	public List<Sizes> FindAll();
}
