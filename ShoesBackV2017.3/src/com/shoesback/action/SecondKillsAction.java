package com.shoesback.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.ISecondKillsBiz;
import com.shoesback.biz.IShoesBiz;
import com.shoesback.po.SecondKills;
import com.shoesback.po.Shoes;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class SecondKillsAction extends ActionSupport implements ModelDriven<SecondKills>,Preparable{
    ISecondKillsBiz secondKillsBiz;
    IShoesBiz shoesBiz;
    SecondKills kill;
    ActionContext ac;
    String pagesize,currentPage;
    //秒杀商品id
    int shoesid;
    //秒杀开始时间设置
    String skstarttimeStr;
    //秒杀结束时间设置
    String skduratoinStr;
    //秒杀尺寸
    String sksizes;    
    int modifySkisvalid;
    float multiple;
	public float getMultiple() {
		return multiple;
	}
	public void setMultiple(float multiple) {
		this.multiple = multiple;
	}
	public int getModifySkisvalid() {
		return modifySkisvalid;
	}
	public void setModifySkisvalid(int modifySkisvalid) {
		this.modifySkisvalid = modifySkisvalid;
	}
	public String getSksizes() {
		return sksizes;
	}
	public void setSksizes(String sksizes) {
		this.sksizes = sksizes;
	}
	public String getSkstarttimeStr() {
		return skstarttimeStr;
	}
	public void setSkstarttimeStr(String skstarttimeStr) {
		this.skstarttimeStr = skstarttimeStr;
	}
	public String getSkduratoinStr() {
		return skduratoinStr;
	}
	public void setSkduratoinStr(String skduratoinStr) {
		this.skduratoinStr = skduratoinStr;
	}	
    public int getShoesid() {
		return shoesid;
	}
	public void setShoesid(int shoesid) {
		this.shoesid = shoesid;
	}
	public SecondKills getKill() {
		return kill;
	}
	public void setKill(SecondKills kill) {
		this.kill = kill;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public IShoesBiz getShoesBiz() {
		return shoesBiz;
	}
	public void setShoesBiz(IShoesBiz shoesBiz) {
		this.shoesBiz = shoesBiz;
	}	
	public ISecondKillsBiz getSecondKillsBiz() {
		return secondKillsBiz;
	}
	public void setSecondKillsBiz(ISecondKillsBiz secondKillsBiz) {
		this.secondKillsBiz = secondKillsBiz;
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		kill=new SecondKills();
		ac=ActionContext.getContext();
	}
	@Override
	public SecondKills getModel() {
		// TODO Auto-generated method stub
		return kill;
	}
	/**
	 *分页获取秒杀商品操作
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		// TODO Auto-generated method stub
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=secondKillsBiz.FindByPage(currentpage, pages);		
		ac.getSession().put("ShowSecondKills",pb);
		//封装秒杀商品图片
		Iterator<SecondKills> seckillList=pb.getData().iterator();
		int size=pb.getTotalRows();
		String[] shoesimg=new String[size];
		String[] tempShoesImg=null;
		int i=0;
		while (seckillList.hasNext()) {
			String temp=seckillList.next().getShoes().getSimage();
			if(temp.contains(";")){
				tempShoesImg=temp.split(";");
			}
			if(tempShoesImg!=null){
				shoesimg[i++]=tempShoesImg[0];
			}else{
				shoesimg[i++]=null;
			}
			tempShoesImg=null;
		}
		ac.put("shoesimg",shoesimg);
		return SUCCESS;
	}
	//获取图片商品信息
	@SuppressWarnings("unchecked")
	public String FindShoesByPage(){
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=shoesBiz.FindNoSecondKillsByPage(currentpage, pages);		
		ac.getSession().put("ShowShoes",pb);
		//封装普通商品图片
		Iterator<Shoes> seckillList=pb.getData().iterator();
		int size=pb.getTotalRows();
		String[] shoesimg=new String[size];
		String[] tempShoesImg=null;
		int i=0;
		while (seckillList.hasNext()) {
			String temp=seckillList.next().getSimage();
			if(temp.contains(";")){
				tempShoesImg=temp.split(";");
			}
			if(tempShoesImg!=null){
				shoesimg[i++]=tempShoesImg[0];
			}else{
				shoesimg[i++]=null;
			}
			tempShoesImg=null;
		}
		ac.put("shoesimg",shoesimg);
		return NONE;
	}
	//修改，获取单个对象
	@SuppressWarnings("deprecation")
	public String ModifySecondKills(){
		System.out.println("skid+"+kill.getSkid());
		kill=secondKillsBiz.FindBySkid(kill.getSkid());
		ac.getSession().put("preModSeckill",kill);
		//设置时间
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String startDate=df.format(kill.getSkstarttime());
		int tempStartHour=kill.getSkstarttime().getHours();
		String startHour;
		if(tempStartHour<10){
			startHour="0"+tempStartHour;
		}else{
			startHour=""+tempStartHour;
		}
		int tempStartMin=kill.getSkstarttime().getMinutes();
		String startMin;
		if(tempStartMin<10){
			startMin="0"+tempStartMin;
		}else{
			startMin=" "+tempStartMin;
		}
		String endDate=df.format(kill.getSkduration());
		int tempEndHour=kill.getSkduration().getHours();
		String endHour;
		if(tempEndHour<10){
			endHour="0"+tempEndHour;
		}else{
			endHour=""+tempEndHour;
		}
		int tempEndMin=kill.getSkduration().getMinutes();
		String endMin;
		if(tempEndMin<10){
			endMin="0"+tempEndMin;
		}else{
			endMin=""+tempEndMin;
		}
		ac.put("startDate",startDate);
		ac.put("startHour",startHour);
		ac.put("startMin",startMin);
		ac.put("endDate",endDate);
		ac.put("endHour",endHour);
		ac.put("endMin",endMin);
		String preModifyShoesimg=null;
		String preModifyShoesimgLst[]=null;
		String temp=kill.getShoes().getSimage();
		if(temp.contains(";")){
			preModifyShoesimgLst=temp.split(";");
		}
		if(preModifyShoesimgLst!=null){
			preModifyShoesimg=preModifyShoesimgLst[0];
		}
		ac.put("preModifyShoesimg",preModifyShoesimg);
		return INPUT;
	}
	//普通商品列表中添加秒杀商品信息
	public String AddSecondKills(){
		System.out.println("shoesid:"+this.shoesid);
		Shoes shoesk=shoesBiz.Findbysid(shoesid);
		//解析商品图片信息
		String preAddshoesimg[]=null;
		String temp=shoesk.getSimage();
		if(temp.contains(";")){
			preAddshoesimg=temp.split(";");
		}
		ac.put("preAddshoesimg", preAddshoesimg);
		ac.put("preAddShoes",shoesk);				
		return "AddSecondKills";		
	}
	//普通商品添加到秒杀商品信息
	public String SaveSecondKills(){
		System.out.println("sksi+"+sksizes);
		//转换开始，结束时间
		Timestamp starttime=Timestamp.valueOf(skstarttimeStr);
		Timestamp duration=Timestamp.valueOf(skduratoinStr);
		if(starttime.after(duration)){
			//证明秒杀开始时间晚于结束时间
			return ERROR;
		}else if(starttime.equals(duration)){
			//证明秒杀开始时间晚于结束时间
			return ERROR;
		}
		kill.setShoes(shoesBiz.Findbysid(shoesid));
		//36.0   36
		kill.setSksize(Integer.parseInt(sksizes.substring(0, sksizes.length()-2)));
		kill.setSkduration(duration);
		kill.setSkstarttime(starttime);
		kill.setSkisvalid(1);
		//添加新的秒杀商品
		secondKillsBiz.CreateSecondKill(kill);
		//更新普通商品状态 为秒杀商品，7
		shoesBiz.UpdateSecondKills(this.getShoesid());
		return execute();
	}
	//更新秒杀商品信息
	public String UpdateSecondKills(){
		System.out.println("skid+"+kill.getSkid());
		SecondKills skill=new SecondKills();
		if(ac.getSession().get("preModSeckill")!=null){
			skill=(SecondKills) ac.getSession().get("preModSeckill");
		}else{
			skill=secondKillsBiz.FindBySkid(kill.getSkid());
		}
		kill.setShoes(skill.getShoes());
		kill.setSkremarks(skill.getSkremarks());
		//转换开始，结束时间
		Timestamp starttime=Timestamp.valueOf(skstarttimeStr);
		Timestamp duration=Timestamp.valueOf(skduratoinStr);
		kill.setSkduration(duration);
		kill.setSkstarttime(starttime);
		kill.setSksize(Integer.parseInt(sksizes.substring(0, sksizes.length()-2)));
		kill.setSkisvalid(modifySkisvalid);
		secondKillsBiz.UpdateSecondKill(kill);
		return execute();
	}
	//设置积分倍数
	public String SetSecondKills(){
		List<Shoes> lstShoes=shoesBiz.FindAllShoes();
		for (Shoes shoes : lstShoes) {
			shoes.setSintegral(shoes.getSintegral()*this.multiple);
			shoesBiz.UpdateShoes(shoes);
		}
		return FindShoesByPage();
	}
}