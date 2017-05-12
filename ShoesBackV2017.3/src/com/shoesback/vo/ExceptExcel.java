package com.shoesback.vo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.shoesback.po.Shoes;
@SuppressWarnings("deprecation")
public class ExceptExcel {
    //设置cell编码解决中文高位字节截断
	static short XLS_ENCODING=HSSFCell.ENCODING_UTF_16;
	//设置浮点数格式	
	static String NUMBER_FORMAT="#,##0.00";
	//指定日期格式
	static String DATE_FORMAT="m/d/yy";
	//创建输出流
	OutputStream out=null;
	//创建excel文件对象
	HSSFWorkbook workbook=null;	
	//创建excel内sheet对象
	HSSFSheet sheet=null;
	//创建行对象
	HSSFRow row=null;
	public static short getXLS_ENCODING() {
		return XLS_ENCODING;
	}
	public static void setXLS_ENCODING(short xLS_ENCODING) {
		XLS_ENCODING = xLS_ENCODING;
	}
	public static String getNUMBER_FORMAT() {
		return NUMBER_FORMAT;
	}
	public static void setNUMBER_FORMAT(String nUMBER_FORMAT) {
		NUMBER_FORMAT = nUMBER_FORMAT;
	}
	public static String getDATE_FORMAT() {
		return DATE_FORMAT;
	}
	public static void setDATE_FORMAT(String dATE_FORMAT) {
		DATE_FORMAT = dATE_FORMAT;
	}
	public OutputStream getOut() {
		return out;
	}
	public void setOut(OutputStream out) {
		this.out = out;
	}
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}
	public HSSFSheet getSheet() {
		return sheet;
	}
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}
	public HSSFRow getRow() {
		return row;
	}
	public void setRow(HSSFRow row) {
		this.row = row;
	}	
	//构造函数
	public ExceptExcel(){
		
	}
	//初始化excel
	public ExceptExcel(OutputStream out) {
        this.out=out;
        this.workbook=new HSSFWorkbook();
        this.sheet=workbook.createSheet();
	}
	//导出excel
	public void export() throws IOException{
		workbook.write(out);
		out.flush();
		out.close();
	}
	//添加一行
	public void createRow(int index){
		this.row=this.sheet.createRow(index);		
	}
	//获取单元格value
	
	public String getCell(int index){
		HSSFCell cell=this.row.getCell((short)index);
		String strExcelCell="";
		if(cell!=null){
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				strExcelCell="FORMULA";
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				strExcelCell=String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				strExcelCell=cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				strExcelCell="";
				break;
			default:
				strExcelCell="";
				break;
			}
		}
		return strExcelCell;
	}
	//设置单元格index序号value；单元格填充值,重载方法
	public void setCell(int index,int value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}
	public void setCell(int index,double value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		//建立新cell样式
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		HSSFDataFormat format=workbook.createDataFormat();
		//设置cell样式为定制浮点数格式
	    cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT));
		//设置该cell浮点数显示格式
	    cell.setCellStyle(cellStyle);
	}
	public void setCell(int index,String value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setEncoding(XLS_ENCODING);
		cell.setCellValue(value);
	}
	public void setCell(int index,Calendar value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setEncoding(XLS_ENCODING);
		cell.setCellValue(value.getTime());
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT));
		cell.setCellStyle(cellStyle);
	}
	//导出鞋子集合信息
	public boolean ExceptShoes(List<Shoes> lstShoes){
		System.out.println("开始导出excel....");
		//创建发布系统上传下载路径,本系统默认tomcat发布路径
		File f=new File("D:\\Tomcat 7.0\\webapps\\back\\upload\\excel\\exceptshoes.xls");
		if(!f.exists()){
			f.delete();
		}
		//创建excel导出对象
		ExceptExcel e=null;
		//遍历导出exce信息到集合中		
		try {
			//实例化ExceptExcel，file路径传递构造函数中		    
			e=new ExceptExcel(new FileOutputStream(f));
	    	//手工创建excel第一行，标题
			e.createRow(0);
			//插入单元格
			e.setCell(0, "Id");
			e.setCell(1, "鞋子类型");
			e.setCell(2, "鞋子品牌");
			e.setCell(3, "鞋子标号");
			e.setCell(4, "鞋子名称");
			e.setCell(5, "鞋子价格");
			e.setCell(6, "鞋库存量");
			e.setCell(7, "上市时间");
			e.setCell(8, "生产厂商");
			e.setCell(9, "性别属性");
			e.setCell(10, "鞋子颜色");
			e.setCell(11, "内容简介");
			e.setCell(12, "鞋子销量");
			e.setCell(13, "详细信息");
			e.setCell(14, "单件鞋总积分");
			e.setCell(15, "鞋子状态");
			e.setCell(16, "备注");
			//封装集合，循环动态遍历集合鞋子具体数据信息
			Shoes shoe=null;
			for (int i = 1; i <= lstShoes.size(); i++) {
				shoe=new Shoes();
				shoe=lstShoes.get(i-1);
				e.createRow(i);
				e.setCell(0,shoe.getSid());
				e.setCell(1,shoe.getTypes().getTname());
				e.setCell(2,shoe.getBrands().getBname());
				e.setCell(3,shoe.getSnum());
				e.setCell(4,shoe.getSname());
				e.setCell(5,shoe.getSprices());
				e.setCell(6,shoe.getSdiscount());
				e.setCell(7,shoe.getSpubtime().toString());
				e.setCell(8,shoe.getSproducer());
				e.setCell(9,shoe.getSgender());
				e.setCell(10,shoe.getScolor());
				e.setCell(11,shoe.getSinfo());
				e.setCell(12,shoe.getStimessold());
				e.setCell(13,shoe.getSdetail());
				e.setCell(14,shoe.getSintegral());
				e.setCell(15,shoe.getSdelete()==0?"正常":"禁用");
				e.setCell(16,shoe.getSremarks());
			}
			e.export();
			System.out.println("Excel导出成功！");
			return true;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return false;
		}	
	}
}
