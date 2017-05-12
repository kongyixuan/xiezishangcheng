package com.shoesback.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.shoesback.biz.ISpsSizesBiz;
import com.shoesback.dao.ISpsSizesDao;
import com.shoesback.po.Sizes;
import com.shoesback.po.SpcifyShoes;
import com.shoesback.po.SpsSizes;

public class SpsSizesBizImpl implements ISpsSizesBiz {
    ISpsSizesDao spsSizesDao;
	public ISpsSizesDao getSpsSizesDao() {
		return spsSizesDao;
	}
	public void setSpsSizesDao(ISpsSizesDao spsSizesDao) {
		this.spsSizesDao = spsSizesDao;
	}
	@Override
	public List<SpsSizes> FindBySpsShoes(int spsid) {
		// TODO Auto-generated method stub
		String hql="from SpsSizes where spcifyShoes.spsid=? ";
		return spsSizesDao.findByObject(hql, new Object[]{spsid});
	}
	@Override
	public boolean ModifySpsSizes(SpcifyShoes spcify, String[] chk,
			List<Integer> sizeid, List<Sizes> lstSpsSize) {
		//获取更新定制鞋尺寸信息不同的内容，获取新增的尺寸信息进行添加操作，反之删除操作
		//创建新增尺寸信息集合
		List<Sizes> lsit=new ArrayList<Sizes>();
		//创建删除原有的定制鞋尺寸信息
		List<Sizes> lover=new ArrayList<Sizes>();
		//创建整形集合，添加新增定制鞋信息
		List<Integer> sizenow=new ArrayList<Integer>();
		List<Integer> sizeover=new ArrayList<Integer>();
		List<Integer> sizenow1=new ArrayList<Integer>();
		//循环遍历出新增和删除原有定制鞋尺寸信息
		for (int i = 0; i < chk.length; i++) {
			//判断是否是新增尺寸信息
			if(!sizeid.contains(Integer.parseInt(chk[i]))){
				//证明是新选中的一个定制鞋尺寸信息
				sizeover.add(Integer.parseInt(chk[i]));
			}
			if(sizeid.contains(Integer.parseInt(chk[i]))){
				sizenow.add(Integer.parseInt(chk[i]));
			}
		}
		for (Integer integer : sizeid) {
			if(!sizenow.contains(integer)){
				sizenow1.add(integer);
			}
		}
		for (Integer integer : sizenow1) {
			//删除原有尺寸信息
			for (Sizes sizes : lstSpsSize) {
				if(sizes.getSizeid().equals(integer)){
					lover.add(sizes);
					String hql="delete from SpsSizes where spcifyShoes=? and sizes=?";
					spsSizesDao.bulkUpdate(hql, new Object[]{spcify,sizes});
				}				
			}
		}
		SpsSizes sps=null;
		for (Integer integer : sizeover) {
			//添加新尺寸信息
			for (Sizes sizes: lstSpsSize) {
				if(sizes.getSizeid().equals(integer)){
					lsit.add(sizes);
					sps=new SpsSizes();
					sps.setSizes(sizes);
					sps.setSpcifyShoes(spcify);
					sps.setSpsstate(1);
					spsSizesDao.create(sps);
				}
			}
		}
		return true;
	}
	@Override
	public void SaveSpsSizesList(List<SpsSizes> lstSpsSizes) {
		// TODO Auto-generated method stub
		for (SpsSizes spsSizes : lstSpsSizes) {
			spsSizesDao.create(spsSizes);
		}
	}

}
