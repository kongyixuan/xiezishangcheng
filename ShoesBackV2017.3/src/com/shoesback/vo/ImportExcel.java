package com.shoesback.vo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.shoesback.po.ImportShoes;

public class ImportExcel {
    //设置cell编码解决中文高位字节截断
	static short XLS_ENCODING=HSSFCell.ENCODING_UTF_16;
	//设置浮点数格式	
	static String NUMBER_FORMAT="#,##0.00";
	//指定日期格式
	static String DATE_FORMAT="m/d/yy";
	//创建输入流
    FileInputStream fis	=null;
    //创建文件对象
    File file=null;
	//创建excel文件对象
	HSSFWorkbook wb=null;	
	//创建excel内sheet对象
	HSSFSheet sheet=null;
	//创建行对象
	HSSFRow row=null;	
	//创建sheet、行数量
	int sheetNum=0,rowNum=0;
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
	public FileInputStream getFis() {
		return fis;
	}
	public void setFis(FileInputStream fis) {
		this.fis = fis;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public HSSFWorkbook getWb() {
		return wb;
	}
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
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
	public int getSheetNum() {
		return sheetNum;
	}
	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}	
	//空构造方法
	public ImportExcel(){}
	//代文件参数构造方法
	public ImportExcel(File file){
		this.file=file;
	}
	//读取excel 获取wordbook对象
	public void open() throws IOException{
		fis=new FileInputStream(file);
		wb=new HSSFWorkbook(new POIFSFileSystem(fis));
		fis.close();
	}
	//返回sheet表数目
	public int getSheetCount(){
		int sheetCount=-1;
		//获取一个excel表中的sheet数量
		sheetCount=wb.getNumberOfSheets();
		return sheetCount;
	}
	//sheetNumd下的记录行数
	public int getRowCount(){
		if(wb==null){
			System.out.println("Excel文件不存在!");
		}
		HSSFSheet sheet=wb.getSheetAt(this.sheetNum);
		int rowCount=-1;
		rowCount=sheet.getLastRowNum();
		return rowCount;
	}
	//读取指定sheetNum的rowCount
	public int getRowCount(int sheetNum){
		HSSFSheet sheet1=wb.getSheetAt(sheetNum);
		int rowCount=-1;
		rowCount=sheet1.getLastRowNum();
		return rowCount;
	}
	//得到指定行的内容
	public String[] readExcelLine(int lineNum){
		return readExcelLine(this.sheetNum,lineNum);
	}	
	//指定工作表和行数的内容
	public String[] readExcelLine(int sheetNum,int lineNum){
		if(sheetNum<0||lineNum<0){
			return null;
		}
		String[] strExcelLine=null;
		try {
			sheet=wb.getSheetAt(sheetNum);
			row=sheet.getRow(lineNum);
			int cellCount=row.getLastCellNum();
			strExcelLine=new String[cellCount+1];
			for (int i = 0; i <= cellCount; i++) {
				strExcelLine[i]=readStringExcelCell(lineNum,i);  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strExcelLine;
	}
	//读取指定行内容
	@SuppressWarnings("unused")
	private String readStringExcelCell(int cellNum){
		return readStringExcelCell(this.rowNum, cellNum);
	}
	//指定行和列编号的内容
	private String readStringExcelCell(int rowNum,int cellNum){
		return readStringExcelCell(this.sheetNum, rowNum, cellNum);
	}
	
	//指定工作表 行 列下的具体内容
	@SuppressWarnings("deprecation")
	private String readStringExcelCell(int sheetNum,int rowNum,int cellNum){
		if(sheetNum<0||rowNum<0){
			return "";
		}
		String strExcelCell="";
		try {
			sheet=wb.getSheetAt(sheetNum);
			row=sheet.getRow(rowNum);
			if(row.getCell((short)cellNum)!=null){
				switch (row.getCell((short)cellNum).getCellType()) {
				case HSSFCell.CELL_TYPE_FORMULA:
					strExcelCell="FORMULA";
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					strExcelCell=String.valueOf(row.getCell((short)cellNum).getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					strExcelCell=row.getCell((short)cellNum).getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					strExcelCell="";
					break;
				default:
					strExcelCell="";
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strExcelCell;
	}
	//转换List集合
	public List<ImportShoes> ImportShoes() {
		//创建导入鞋子集合
		List<ImportShoes> lisShoes=new ArrayList<ImportShoes>();
		//创建ImportSHoes对象
		ImportShoes importshoes=null;		
		try {
			//实例化文件输入流对象
			fis=new FileInputStream(file);
			wb=new HSSFWorkbook(new POIFSFileSystem(fis));
			//关闭输入流
			fis.close();
			//默认获取excel第一个sheet
			this.setSheetNum(0);
			int count=this.getRowCount();
			//循环遍历单元格数值
			for (int i = 0; i <=count; i++) {
				//获取i行单元格数值
				String[] rows=this.readExcelLine(i);
				System.out.println("Rows："+rows.length);
				//因为第一行是标题，因此从第二行开始获取封装对象
				if(i>0){
					importshoes=new ImportShoes();
					//遍历每一行单元格信息
					for (int j = 0; j < rows.length; j++) {
						//精确判断单元格不为空
						if(rows[j].trim().length()!=0&&!rows[j].trim().equals(null)&&!rows[j].trim().equals("")){
							//switch判断具体单元格信息
							switch (j) {
							case 0:
								System.out.println("鞋类型:"+rows[j]);
								importshoes.setTypes(rows[j].trim().toString());
								break;
							case 1:
								System.out.println("鞋品牌:"+rows[j]);
								importshoes.setBrands(rows[j].trim().toString());
								break;
							case 2:
								System.out.println("鞋标号:"+rows[j]);
								importshoes.setSnum(rows[j].trim().toString());
								break;
							case 3:
								System.out.println("鞋尺码:"+rows[j]);
								importshoes.setSizes(Float.parseFloat(rows[j].trim().toString()));
								break;
							case 4:
								System.out.println("相应尺码数量:"+rows[j]);
								String num=rows[j].trim().toString();
								int ind=num.indexOf(".");
								if(ind!=-1){
									//40.0
									importshoes.setSizenum(Integer.parseInt(num.substring(0,num.indexOf("."))));	
								}else{
									importshoes.setSizenum(Integer.parseInt(num));
								}								
								break;
							case 5:
								System.out.println("鞋名称:"+rows[j]);
								importshoes.setSname(rows[j].trim().toString());								
								break;
							case 6:
								System.out.println("鞋子价格:"+rows[j]);
								importshoes.setSprices(Float.parseFloat(rows[j].trim().toString()));								
								break;
							case 7:
								System.out.println("鞋库存量:"+rows[j]);
								String numk=rows[j].trim().toString();
								int mk=numk.indexOf(".");
								if(mk!=-1){
									importshoes.setSdiscount(Integer.parseInt(numk.substring(0,numk.indexOf("."))));	
								}else{
									importshoes.setSdiscount(Integer.parseInt(numk));
								}									
								break;//
							case 8:
								System.out.println("生产厂商:"+rows[j]);
								importshoes.setSproducer(rows[j].trim().toString());								
								break;
							case 9:
								System.out.println("性别属性:"+rows[j]);
								importshoes.setSgender(rows[j].trim().toString());								
								break;
							case 10:
								System.out.println("鞋子颜色:"+rows[j]);
								importshoes.setScolor(rows[j].trim().toString());								
								break;//
							case 11:
								System.out.println("内容简介:"+rows[j]);
								importshoes.setSinfo(rows[j].trim().toString());								
								break;
							case 12:
								System.out.println("鞋销量:"+rows[j]);
								String sold=rows[j].trim().toString();
								int so=sold.indexOf(".");
								if(so!=-1){
									importshoes.setStimessold(Integer.parseInt(sold.substring(0,sold.indexOf("."))));	
								}else{
									importshoes.setStimessold(Integer.parseInt(sold));
								}									
								break;
							case 13:
								System.out.println("详细详细:"+rows[j]);
								importshoes.setSdetail(rows[j].trim().toString());								
								break;
							case 14:
								System.out.println("单件鞋总积分:"+rows[j]);
								importshoes.setSintegral(Float.parseFloat(rows[j].trim().toString()));								
								break;
							case 15:
								System.out.println("鞋状态:"+rows[j]);
								importshoes.setSdetail(rows[j].trim().toString());								
								break;
							case 16:
								System.out.println("备注:"+rows[j]);
								importshoes.setSremarks(rows[j].trim().toString());								
								break;
							}
						}						
					}		
					lisShoes.add(importshoes);
				}else{
					System.out.println("第一行标题");
				}			
			}
			return lisShoes;			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
}







