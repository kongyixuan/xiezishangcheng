package com.shoesback.interceptor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.shoesback.biz.IShoesBiz;
import com.shoesback.po.Shoes;
import com.shoesback.vo.ExceptExcel;

@SuppressWarnings("serial")
public class ExcelInterceptor extends MethodFilterInterceptor {
	IShoesBiz shoesBiz;
	String fuzzy;
	public String getFuzzy() {
		return fuzzy;
	}
	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}
	public IShoesBiz getShoesBiz() {
		return shoesBiz;
	}
	public void setShoesBiz(IShoesBiz shoesBiz) {
		this.shoesBiz = shoesBiz;
	}
	/*
	 *Excel导出拦截器，在用户下载导出的鞋子信息之前，要excel导出所需鞋子集合 
	 */
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		//获取前台页面条件提交表单参数map集合
		Map<String, Object> map=arg0.getInvocationContext().getParameters();
		//测试打印参数数值
		Iterator<Object>  irr=map.values().iterator();
		while (irr.hasNext()) {
			System.out.println("valuesMap:"+irr.next());			
		}
		//获取模糊查询条件值
		String[] content=(String[]) map.get("fuzzy");
		System.out.println("模糊条件："+content);
		//创建exceptexcel对象
		ExceptExcel ex=new ExceptExcel();
		List<Shoes> s=shoesBiz.FuzzySearchShoe(content[0]);
		String result=null;
		//将查询获取的集合转成excel
		if(ex.ExceptShoes(s)){
			System.out.println("sizeS:"+s.size());
			result=arg0.invoke();//跳转action方法
			System.out.println("resutl:"+result);
			return result;
		}else{
			return null;	
		}		
	}
}
