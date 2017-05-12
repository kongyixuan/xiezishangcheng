
package com.shoesback.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IBrandsBiz;
import com.shoesback.biz.IShoesBiz;
import com.shoesback.biz.IShoesizesBiz;
import com.shoesback.biz.ISizesBiz;
import com.shoesback.biz.ITypesBiz;
import com.shoesback.po.Brands;
import com.shoesback.po.ImportShoes;
import com.shoesback.po.Shoes;
import com.shoesback.po.Shoesizes;
import com.shoesback.po.Sizes;
import com.shoesback.po.Types;
import com.shoesback.vo.ImportExcel;
/*
 * Excel导入方法，
 * 首先，上传excel文件到服务器路径
 * 然后，excelPoi把Excel文件信息分解为List结合
 * 最后，批量插入数据库
 */
@SuppressWarnings("serial")
public class ImportShoesAction extends ActionSupport implements Preparable{
	IShoesBiz shoesBiz;
    ISizesBiz sizesBiz;
    IBrandsBiz brandsBiz;
    ITypesBiz typesBiz;
    IShoesizesBiz shoesizesBiz;   
    ActionContext ac;
    private File uploadImage;//上传图片变量
    private String uploadImageContentType;//得到上传文件类型   
	private String uploadImageFileName;//得到上传文件名称
	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

	public IShoesBiz getShoesBiz() {
		return shoesBiz;
	}

	public void setShoesBiz(IShoesBiz shoesBiz) {
		this.shoesBiz = shoesBiz;
	}

	public ISizesBiz getSizesBiz() {
		return sizesBiz;
	}

	public void setSizesBiz(ISizesBiz sizesBiz) {
		this.sizesBiz = sizesBiz;
	}

	public IBrandsBiz getBrandsBiz() {
		return brandsBiz;
	}

	public void setBrandsBiz(IBrandsBiz brandsBiz) {
		this.brandsBiz = brandsBiz;
	}

	public ITypesBiz getTypesBiz() {
		return typesBiz;
	}

	public void setTypesBiz(ITypesBiz typesBiz) {
		this.typesBiz = typesBiz;
	}

	public IShoesizesBiz getShoesizesBiz() {
		return shoesizesBiz;
	}

	public void setShoesizesBiz(IShoesizesBiz shoesizesBiz) {
		this.shoesizesBiz = shoesizesBiz;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		//上传excel文件
		String realpath=ServletActionContext.getServletContext().getRealPath("/upload/excel");
		//创建file对象
		File file=new File(realpath);
		if(!file.exists()){
			file.mkdirs();
		}	
		try {
			FileUtils.copyFile(uploadImage, new File(file,uploadImageFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//利用Poi转换Excel为list,创建File对象
        File fi=new File(realpath+"/"+uploadImageFileName);
        //创建ImportExcel对象，并实例化带参数构造函数
        ImportExcel read=new ImportExcel(fi);
        //获取品牌集合
        List<Brands> lstBrands=new ArrayList<Brands>();
        if(ac.getSession().get("lstBrands")!=null){
        	lstBrands=(List<Brands>) ac.getSession().get("lstBrands");
        }else{
        	lstBrands=brandsBiz.FindAll();
        }
        //获取类型集合
        List<Types> lstTypes=new ArrayList<Types>();
        if(ac.getSession().get("lstTypes")!=null){
        	lstTypes=(List<Types>) ac.getSession().get("lstTypes");
        }else{
        	lstTypes=typesBiz.FindAll();
        }
        //获取尺寸集合
        List<Sizes> lstSizes=new ArrayList<Sizes>();
        if(ac.getSession().get("lstSizes")!=null){
        	lstSizes=(List<Sizes>) ac.getSession().get("lstSizes");
        }else{
        	lstSizes=sizesBiz.FindAll();
        }
        try {
			//封装集合，创建鞋\尺寸对象,及集合
			Shoes shoes=null;
			Shoesizes shoesize=null;
			List<Shoes> lstShoes=new ArrayList<Shoes>();
			List<Shoesizes> lstShoesizes=new ArrayList<Shoesizes>();
			//鞋唯一标准字符串
			String shoenum="";        
			//获取excel导入集合
			List<ImportShoes> lisimp=read.ImportShoes();
			if(lisimp!=null){
				//循环遍历导入的鞋集合
			    for (ImportShoes importShoes : lisimp) {
			        //判断字段不为空
			    	if(importShoes.getBrands()!=null){
			    	    //判断是否是新鞋
			    		if(shoenum!=importShoes.getSname()){
			    			//证明是新鞋
			    			shoenum=importShoes.getSname();
			    			shoes=new Shoes();
			    			//给鞋对象赋值类型对象
			    			for (Types types : lstTypes) {
								if(importShoes.getTypes().equals(types.getTname())){
									shoes.setTypes(types);
								}
							}
			    			//给鞋对象赋值品牌对象
			    			for (Brands brands : lstBrands) {
								if(importShoes.getBrands().equals(brands.getBname())){
									shoes.setBrands(brands);
								}
							}
			    			//依次赋值其他属性
			    			shoes.setSnum(importShoes.getSnum());
			    			shoes.setSname(importShoes.getSname());
			    			shoes.setSprices(importShoes.getSprices());
			    			shoes.setSdiscount(importShoes.getSdiscount());
			    			shoes.setSpubtime(new Timestamp(new Date().getTime()));
			    			shoes.setSproducer(importShoes.getSproducer());
			    			shoes.setSgender(importShoes.getSgender());
			    			shoes.setScolor(importShoes.getScolor());
			    			shoes.setSinfo(importShoes.getSinfo());
			    			shoes.setStimessold(importShoes.getStimessold());
			    			shoes.setSdelete(0);
			    			shoes.setSdetail(importShoes.getSdetail());
			    			shoes.setSintegral(importShoes.getSintegral());
			    			shoes.setSremarks(importShoes.getSremarks());
			    			lstShoes.add(shoes);
			    		}
			    		shoesize=new Shoesizes();
			    		shoesize.setShoes(shoes);
			    		//赋值尺寸信息
			    		for (Sizes sizes : lstSizes) {
							if(sizes.getSizenum().equals(importShoes.getSizes())){
								shoesize.setSizes(sizes);
							}
						}
			    		shoesize.setScount(importShoes.getSizenum());
			    		shoesize.setSstate(1);
			    		lstShoesizes.add(shoesize);
			    	}            		
				}            
			}
			//批量添加到数据库
			if(shoesBiz.ImportShoes(lstShoes, lstShoesizes)){
				
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
	}
}