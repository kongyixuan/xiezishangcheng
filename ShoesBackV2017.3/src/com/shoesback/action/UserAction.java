package com.shoesback.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IUserBiz;
import com.shoesback.po.Users;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ModelDriven<Users>,Preparable{
    IUserBiz userBiz;
    Users use;
    ActionContext ac;    
    //     页大小，  当前第几页        模糊搜索参数
    String pagesize,currentPage,fuzzy;   
	//批量复选框用户id
    int[] chk_uid;
    public String getFuzzy() {
		return fuzzy;
	}
	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}
	public int[] getChk_uid() {
		return chk_uid;
	}
	public void setChk_uid(int[] chk_uid) {
		this.chk_uid = chk_uid;
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
	public Users getUse() {
		return use;
	}
	public void setUse(Users use) {
		this.use = use;
	}
	public IUserBiz getUserBiz() {
		return userBiz;
	}
	public void setUserBiz(IUserBiz userBiz) {
		this.userBiz = userBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		use=new Users();
		ac=ActionContext.getContext();		
	}
	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return use;
	}
	/**
	 * 分页显示前台用户信息:
	 * 1.考虑第一次进入此页面默认选择全部信息第一页
	 * 2.分页接收currentPage参数信息，如果有模糊搜索条件参数，则按照模糊搜索条件查询
	 * 3.模糊搜索条件查询。
	 */
	public String execute() {
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=new PageBean();
		//判断是否有模糊搜索参数存在
		if(fuzzy!=null&&fuzzy.trim()!=""){
			System.out.println("Fuzzy:"+fuzzy);
			//分页获取模糊搜索查询结果
			pb=userBiz.FuzzySearchUser(fuzzy, currentpage, pages);
			//为了显示页面模糊参数，封装request，考虑到分页查询如果有模糊搜索条件则分页显示模糊搜索后的信息内容
			ac.put("fuzzy", fuzzy);
		}else{
            //没有搜索参数
			pb=userBiz.FindByPageUser(currentpage, pages);	
		}		
		ac.getSession().put("ShowUser",pb);
		return SUCCESS;
	}
	//获取单个更新用户对象
	@SuppressWarnings("unchecked")
	public String UserUpdate(){
		/*
		 * 首先判断分页显示回话中遍历读取对应用户信息，
		 * 其次在读取数据库获取单个对象信息
		 */
		System.out.println("Uid:"+use.getUid());
		//创建获取显示用户列表集合
		List<Users> lisUser=new ArrayList<Users>();
		//判断回话是否存在，并遍历集合
		if(ac.getSession().get("ShowUser")!=null){
			lisUser=(List<Users>)((PageBean)ac.getSession().get("ShowUser")).getData();
			//遍历结果集合并找出相对应用户对象信息
			for (Users users : lisUser) {
				if(users.getUid().equals(this.use.getUid())){
					this.use=users;
					break;
				}
			}
		}else{
			this.use=userBiz.FindByUid(this.use.getUid());
		}
		//封装用户对象回话信息
		ac.put("user", this.use);
 		return "UpdateUser";
	}
	//更新用户信息
	public String ModifyUser(){
		System.out.println("modifyUid:"+use.getUid());
		//补充字段
		use.setUdelete(0);
		userBiz.UpdateUser(use);
		return execute();
	}
	//禁用用户
	public String DeleteUser(){
		System.out.println("Deleteuser:"+use.getUid());
		//禁用前台用户udelete为1
		userBiz.DeleteUser(1, use.getUid());
		return execute();
	}
	//反禁用用户
	public String UnDeleteUser(){
		System.out.println("unDeleteuser:"+use.getUid());
		//反禁用前台用户udelete为0
		userBiz.DeleteUser(0, use.getUid());
		return execute();
	}
	//批量禁/反禁用用户
	@SuppressWarnings("unchecked")
	public String BatchDeleteUser(){
		/*
		 * 不采用单个逐条记录修改用户表信息，
		 * 获取前台用户会话信息中遍历查找，有利于减少数据库交互次数，
		 * 根据不同用户状态来封装拼接数据库hql语句
		 * 如果会话不存在则获取全部数据库信息遍历查找 
		 */
		//测试选中用户id
        for (int i = 0; i < this.chk_uid.length; i++) {
			System.out.println("che_id:"+chk_uid[i]);
		}
        //创建禁用和反禁用用户操作hql语句
        String hqldel="(",hqlundel="(";
        //创建遍历用户集合
        List<Users> lisUser=new ArrayList<Users>();
        //判断会话中是否存在
        if(ac.getSession().get("ShowUser")!=null){
        	lisUser=(List<Users>)((PageBean)ac.getSession().get("ShowUser")).getData();
        }else{
        	lisUser=userBiz.FindAll();
        }
        //遍历集合获取相应要批量更改用户对象集合
        for (Users users : lisUser) {
			for (int i = 0; i < this.chk_uid.length; i++) {
				if(chk_uid[i]==users.getUid()){
					//判断用户状态是否已经禁用
					if(users.getUdelete().equals(0)){//正常用户，禁用操作
						//拼接hql语句
						hqldel+=chk_uid[i]+",";
					}else{                          //禁用用户，反禁用操作
						hqlundel+=chk_uid[i]+",";
					}
					break;
				}
			}
		}
        //判断禁用操作的用户是否存在
        if(!hqldel.equals("(")){
        	//拼接批量更新用户操作hql语句，删除末尾",",添加"）"
        	hqldel="udelete=1 where uid in "+hqldel.substring(0, hqldel.length()-1)+")";
        	System.out.println("hqldel:"+hqldel);
        	//批量禁用用户操作
        	userBiz.BatchDeleteUser(hqldel);
        }
        //判断反禁用操作的用户是否存在
        if(!hqlundel.equals("(")){
        	hqlundel="udelete=0 where uid in "+hqlundel.substring(0, hqlundel.length()-1)+")";
        	System.out.println("hqlundel:"+hqlundel);
        	//批量反禁用用户操作
        	userBiz.BatchDeleteUser(hqlundel);
        }
		return execute();
	}
}