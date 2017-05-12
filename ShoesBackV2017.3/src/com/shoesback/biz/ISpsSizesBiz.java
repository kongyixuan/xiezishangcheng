package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Sizes;
import com.shoesback.po.SpcifyShoes;
import com.shoesback.po.SpsSizes;

public interface ISpsSizesBiz {
    //获取相应定制鞋尺寸信息,                  定制鞋id
	public List<SpsSizes> FindBySpsShoes(int spsid);
	//更新定制鞋尺寸信息                        定制鞋对象                     更新后定制鞋尺寸信息，更新前尺寸信息，全部鞋尺寸信息
	public boolean ModifySpsSizes(SpcifyShoes spcify,String[] chk,List<Integer> sizeid,List<Sizes> lstSpsSize);
	//批量保存定制鞋尺寸信息
	public void SaveSpsSizesList(List<SpsSizes> lstSpsSizes );
}
