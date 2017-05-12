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
	 *����jfreechartͼƬ
	 */
	public String execute() {
		// TODO Auto-generated method stub
		if("yeji".equals(type)){
			System.out.println("Typd:"+type);
			//��ȡ�۳���������0��Ь�Ӽ��ϣ�Ь���۳����
			List<Shoes> lisShoes=shoesBiz.FindByOrders();
			//��ȡҵ������3D��ͼ
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
	//Ӫ������
	public void getChartPic(List<Shoes> lisShoes){
		//����jfreechart����
		JFreeChart chart=ChartFactory.createPieChart("��Ʒ����ͳ��ͼ",this.getDataSetPic(lisShoes), true,false,false);
		//��������ͼ����⣬���ı�����
		chart.setTitle(new TextTitle("��Ʒ����ͳ��ͼ",new Font("����",Font.ITALIC,22)));
		//ȡ��ͳ�Ʊ���ĵ�һ��ͼ��
		LegendTitle legend=chart.getLegend(0);
		//�޸�ͼ��������
		legend.setItemFont(new Font("����",Font.BOLD,14));
		//��ȡ��ͼ��plot����
		PiePlot plot=(PiePlot) chart.getPlot();
		//���ñ�ͼ�����ֵı�ǩ����
		plot.setLabelFont(new Font("����",Font.BOLD,18));
		//���ñ���͸����  ��0-0.1֮��)
		plot.setBackgroundAlpha(0.9f);
		//�����ļ�������γ�ͼƬ
		FileOutputStream fos;
		try {
			//�γ��ļ������·��D:\Tomcat 7.0\wtpwebapps\ShoesFrontV2017.03
			//D:\Tomcat 7.0\webapps\back
			fos=new FileOutputStream("D://Tomcat7.0//webapps//back//images//chart//7c.jpg");
			ac.put("image","7c.jpg");
			//�ļ����
			ChartUtilities.writeChartAsJPEG(fos, 1, chart, 800,600,null);
			//������ر�
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//ʱ��������
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void Time(List<Orders> orderList){
		TimeSeries timeSeries1 = new TimeSeries("2015", Day.class);		
		//ʱ���������ݼ���
		TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		//�������ݼ���
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
		JFreeChart chart = ChartFactory.createTimeSeriesChart("����ʱ�估����ͼ", "ʱ��", "������Ʒ����", lineDataset, true, true, true);		
		chart.setBackgroundPaint(Color.white); 
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.BOLD, 20));  
		LegendTitle legend = chart.getLegend(); 
		if (legend != null) {
			legend.setItemFont(new Font("����", Font.BOLD, 20));
		}  
		XYPlot xyplot = (XYPlot)chart.getPlot();
		//��� plot : XYPlot!!                   
		ValueAxis domainAxis=xyplot.getDomainAxis();  
		domainAxis.setTickLabelFont(new Font("����",Font.BOLD,20));
		//����x�������ϵ�����                 
		domainAxis.setLabelFont(new Font("����",Font.BOLD,20));
		//����x�������ϵı��������                  
		ValueAxis rangeAxis=xyplot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("����",Font.BOLD,20));
        //����y�������ϵ����� 
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,20));
        //����y�������ϵı��������
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.setDomainCrosshairVisible(true);   
        xyplot.setRangeCrosshairVisible(true);
		FileOutputStream fos;
		try {
			//�γ��ļ������·��
			fos=new FileOutputStream("D://Tomcat7.0//webapps//back//images//chart//temp.jpg");
			ac.put("image","temp.jpg");
			//�ļ����                               
			ChartUtilities.writeChartAsJPEG(fos, 1, chart, 500, 400,null);
			//������ر�
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	//ҵ������ 
	public void getChart3D(List<Shoes> lisShoes){
		//����jfreechart����
		JFreeChart chart;
		//����jfreechart3d��ͼ
		chart=ChartFactory.createPieChart3D("��Ʒҵ������",this.getDataSet3D(lisShoes), true,false,false);
		//��������ͼ����⣬���ı�����
		chart.setTitle(new TextTitle("��Ʒҵ������",new Font("����",Font.ITALIC,22)));
		//ȡ��ͳ�Ʊ���ĵ�һ��ͼ��
		LegendTitle legend=chart.getLegend(0);
		//�޸�ͼ��������
		legend.setItemFont(new Font("����",Font.BOLD,14));
		//��ȡ��ͼ��plot����
		PiePlot plot=(PiePlot) chart.getPlot();
		//���ñ�ͼ�����ֵı�ǩ����
		plot.setLabelFont(new Font("����",Font.BOLD,18));
		//���ñ���͸����  ��0-0.1֮��)
		plot.setBackgroundAlpha(0.9f);
		//����ǰ��͸���� (0-0.1֮��)
		plot.setForegroundAlpha(0.50f);
		//====����jfreechart����over=====
		//�����ļ�������γ�ͼƬ
		FileOutputStream fos;
		try {
			//�γ��ļ������·��
			fos=new FileOutputStream("D://Tomcat7.0//webapps//back//images//chart//6c.jpg");
			ac.put("image","6c.jpg");
			//�ļ����
			ChartUtilities.writeChartAsJPEG(fos, 1, chart, 800,600,null);
			//������ر�
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��ȡ����ͳ��ͼDataset
	private DefaultPieDataset getDataSet3D(List<Shoes> lisShoes){
		DefaultPieDataset dataset=new DefaultPieDataset();
		//ѭ������ͳ��Ь����Ϣ����
		for (Shoes shoes : lisShoes) {
			//3dͼ���������ݣ�Ь������ ���۳�����
			dataset.setValue(shoes.getSname(),shoes.getStimessold());
		}
		return dataset;
	}
	private DefaultPieDataset getDataSetPic(List<Shoes> lisShoes){
		DefaultPieDataset dataset=new DefaultPieDataset();
		//ѭ������ͳ��Ь����Ϣ����
		for (Shoes shoes : lisShoes) {
			dataset.setValue(shoes.getTypes().getTname(),shoes.getStimessold());
		}
		return dataset;
	}
}