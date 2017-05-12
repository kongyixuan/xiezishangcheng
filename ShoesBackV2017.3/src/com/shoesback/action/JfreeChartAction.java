package com.shoesback.action;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IOrdersBiz;
import com.shoesback.biz.IShoesBiz;
import com.shoesback.po.OrderShoes;
import com.shoesback.po.Orders;
import com.shoesback.po.Shoes;

@SuppressWarnings("serial")
public class JfreeChartAction extends ActionSupport implements Preparable{
	IShoesBiz shoesBiz;
	IOrdersBiz ordersBiz;	
	String type;
	ActionContext ac;
	public IOrdersBiz getOrdersBiz() {
		return ordersBiz;
	}
	public void setOrdersBiz(IOrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public IShoesBiz getShoesBiz() {
		return shoesBiz;
	}
	public void setShoesBiz(IShoesBiz shoesBiz) {
		this.shoesBiz = shoesBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
	}
	/**
	 *生成jfreechart图片
	 */
	public String execute() {
		// TODO Auto-generated method stub
		if("yeji".equals(type)){
			System.out.println("Typd:"+type);
			//获取售出次数不等0的鞋子集合，鞋子售出情况
			List<Shoes> lisShoes=shoesBiz.FindByOrders();
			//获取业绩销量3D饼图
			getChart3D(lisShoes);	
		}else if("time".equals(type)){
			List<Orders> orderList=ordersBiz.FindAll();
			this.Time(orderList);
		}else{
			List<Shoes> lisShoes=shoesBiz.FindByOrders();
			this.getChartPic(lisShoes);
		}		
		return SUCCESS;
	}
	//营销报表
	public void getChartPic(List<Shoes> lisShoes){
		//创建jfreechart对象
		JFreeChart chart=ChartFactory.createPieChart("商品类型统计图",this.getDataSetPic(lisShoes), true,false,false);
		//重新设置图标标题，并改变字体
		chart.setTitle(new TextTitle("商品类型统计图",new Font("黑体",Font.ITALIC,22)));
		//取得统计报表的第一个图例
		LegendTitle legend=chart.getLegend(0);
		//修改图例的字体
		legend.setItemFont(new Font("宋体",Font.BOLD,14));
		//获取饼图的plot对象
		PiePlot plot=(PiePlot) chart.getPlot();
		//设置饼图各部分的标签字体
		plot.setLabelFont(new Font("隶书",Font.BOLD,18));
		//设置背景透明度  （0-0.1之间)
		plot.setBackgroundAlpha(0.9f);
		//创建文件输出流形成图片
		FileOutputStream fos;
		try {
			//形成文件输出流路径D:\Tomcat 7.0\wtpwebapps\ShoesFrontV2017.03
			//D:\Tomcat 7.0\webapps\back
			fos=new FileOutputStream("D://Tomcat7.0//webapps//back//images//chart//7c.jpg");
			ac.put("image","7c.jpg");
			//文件输出
			ChartUtilities.writeChartAsJPEG(fos, 1, chart, 800,600,null);
			//输出流关闭
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//时间流量表
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void Time(List<Orders> orderList){
		TimeSeries timeSeries1 = new TimeSeries("2015", Day.class);		
		//时间曲线数据集合
		TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		//构造数据集合
		int num=0;
		boolean fa=true;
		List<String> lisStr=new ArrayList<String>();
		for (Orders orders : orderList) {
			fa=true;
			for (String timestamp : lisStr) {
			    if(orders.getOrdertime().toString().substring(0, 10).equals(timestamp)){
			    	fa=false ;
					break;			    	    	
			    }	
			}
			if(fa){
				lisStr.add(orders.getOrdertime().toString().substring(0, 10));	
			}	
		}	
		for (String s:lisStr) {
			System.out.println("str:"+s);			
		}
		for (String s:lisStr) {
			num=0;
			for (Orders orders : orderList) {				
				if(s.equals(orders.getOrdertime().toString().substring(0, 10))){					
					Iterator<OrderShoes> os=orders.getOrderShoeses().iterator();
					while(os.hasNext()){						
						OrderShoes ordershoe=os.next();					
						num+=ordershoe.getOsnum();
					}
				}
			}
			timeSeries1.add(new Day(Integer.parseInt(s.substring(8,10)),Integer.parseInt(s.substring(5,7)),Integer.parseInt(s.substring(0,4))),num);
		}
		lineDataset.addSeries(timeSeries1);
		JFreeChart chart = ChartFactory.createTimeSeriesChart("订单时间及数量图", "时间", "订单商品数量", lineDataset, true, true, true);		
		chart.setBackgroundPaint(Color.white); 
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.BOLD, 20));  
		LegendTitle legend = chart.getLegend(); 
		if (legend != null) {
			legend.setItemFont(new Font("宋体", Font.BOLD, 20));
		}  
		XYPlot xyplot = (XYPlot)chart.getPlot();
		//获得 plot : XYPlot!!                   
		ValueAxis domainAxis=xyplot.getDomainAxis();  
		domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,20));
		//设置x轴坐标上的字体                 
		domainAxis.setLabelFont(new Font("宋体",Font.BOLD,20));
		//设置x轴坐标上的标题的字体                  
		ValueAxis rangeAxis=xyplot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("宋体",Font.BOLD,20));
        //设置y轴坐标上的字体 
        rangeAxis.setLabelFont(new Font("宋体",Font.BOLD,20));
        //设置y轴坐标上的标题的字体
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.setDomainCrosshairVisible(true);   
        xyplot.setRangeCrosshairVisible(true);
		FileOutputStream fos;
		try {
			//形成文件输出流路径
			fos=new FileOutputStream("D://Tomcat7.0//webapps//back//images//chart//temp.jpg");
			ac.put("image","temp.jpg");
			//文件输出                               
			ChartUtilities.writeChartAsJPEG(fos, 1, chart, 500, 400,null);
			//输出流关闭
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	//业绩报表 
	public void getChart3D(List<Shoes> lisShoes){
		//创建jfreechart对象
		JFreeChart chart;
		//创建jfreechart3d饼图
		chart=ChartFactory.createPieChart3D("商品业绩报表",this.getDataSet3D(lisShoes), true,false,false);
		//重新设置图标标题，并改变字体
		chart.setTitle(new TextTitle("商品业绩报表",new Font("黑体",Font.ITALIC,22)));
		//取得统计报表的第一个图例
		LegendTitle legend=chart.getLegend(0);
		//修改图例的字体
		legend.setItemFont(new Font("宋体",Font.BOLD,14));
		//获取饼图的plot对象
		PiePlot plot=(PiePlot) chart.getPlot();
		//设置饼图各部分的标签字体
		plot.setLabelFont(new Font("隶书",Font.BOLD,18));
		//设置背景透明度  （0-0.1之间)
		plot.setBackgroundAlpha(0.9f);
		//设置前景透明度 (0-0.1之间)
		plot.setForegroundAlpha(0.50f);
		//====以上jfreechart部分over=====
		//创建文件输出流形成图片
		FileOutputStream fos;
		try {
			//形成文件输出流路径
			fos=new FileOutputStream("D://Tomcat7.0//webapps//back//images//chart//6c.jpg");
			ac.put("image","6c.jpg");
			//文件输出
			ChartUtilities.writeChartAsJPEG(fos, 1, chart, 800,600,null);
			//输出流关闭
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取生成统计图Dataset
	private DefaultPieDataset getDataSet3D(List<Shoes> lisShoes){
		DefaultPieDataset dataset=new DefaultPieDataset();
		//循环遍历统计鞋子信息集合
		for (Shoes shoes : lisShoes) {
			//3d图给两个数据：鞋子名称 ，售出次数
			dataset.setValue(shoes.getSname(),shoes.getStimessold());
		}
		return dataset;
	}
	private DefaultPieDataset getDataSetPic(List<Shoes> lisShoes){
		DefaultPieDataset dataset=new DefaultPieDataset();
		//循环遍历统计鞋子信息集合
		for (Shoes shoes : lisShoes) {
			dataset.setValue(shoes.getTypes().getTname(),shoes.getStimessold());
		}
		return dataset;
	}
}