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
    //����cell���������ĸ�λ�ֽڽض�
	static short XLS_ENCODING=HSSFCell.ENCODING_UTF_16;
	//���ø�������ʽ	
	static String NUMBER_FORMAT="#,##0.00";
	//ָ�����ڸ�ʽ
	static String DATE_FORMAT="m/d/yy";
	//����������
    FileInputStream fis	=null;
    //�����ļ�����
    File file=null;
	//����excel�ļ�����
	HSSFWorkbook wb=null;	
	//����excel��sheet����
	HSSFSheet sheet=null;
	//�����ж���
	HSSFRow row=null;	
	//����sheet��������
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
	//�չ��췽��
	public ImportExcel(){}
	//���ļ��������췽��
	public ImportExcel(File file){
		this.file=file;
	}
	//��ȡexcel ��ȡwordbook����
	public void open() throws IOException{
		fis=new FileInputStream(file);
		wb=new HSSFWorkbook(new POIFSFileSystem(fis));
		fis.close();
	}
	//����sheet����Ŀ
	public int getSheetCount(){
		int sheetCount=-1;
		//��ȡһ��excel���е�sheet����
		sheetCount=wb.getNumberOfSheets();
		return sheetCount;
	}
	//sheetNumd�µļ�¼����
	public int getRowCount(){
		if(wb==null){
			System.out.println("Excel�ļ�������!");
		}
		HSSFSheet sheet=wb.getSheetAt(this.sheetNum);
		int rowCount=-1;
		rowCount=sheet.getLastRowNum();
		return rowCount;
	}
	//��ȡָ��sheetNum��rowCount
	public int getRowCount(int sheetNum){
		HSSFSheet sheet1=wb.getSheetAt(sheetNum);
		int rowCount=-1;
		rowCount=sheet1.getLastRowNum();
		return rowCount;
	}
	//�õ�ָ���е�����
	public String[] readExcelLine(int lineNum){
		return readExcelLine(this.sheetNum,lineNum);
	}	
	//ָ�������������������
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
	//��ȡָ��������
	@SuppressWarnings("unused")
	private String readStringExcelCell(int cellNum){
		return readStringExcelCell(this.rowNum, cellNum);
	}
	//ָ���к��б�ŵ�����
	private String readStringExcelCell(int rowNum,int cellNum){
		return readStringExcelCell(this.sheetNum, rowNum, cellNum);
	}
	
	//ָ�������� �� ���µľ�������
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
	//ת��List����
	public List<ImportShoes> ImportShoes() {
		//��������Ь�Ӽ���
		List<ImportShoes> lisShoes=new ArrayList<ImportShoes>();
		//����ImportSHoes����
		ImportShoes importshoes=null;		
		try {
			//ʵ�����ļ�����������
			fis=new FileInputStream(file);
			wb=new HSSFWorkbook(new POIFSFileSystem(fis));
			//�ر�������
			fis.close();
			//Ĭ�ϻ�ȡexcel��һ��sheet
			this.setSheetNum(0);
			int count=this.getRowCount();
			//ѭ��������Ԫ����ֵ
			for (int i = 0; i <=count; i++) {
				//��ȡi�е�Ԫ����ֵ
				String[] rows=this.readExcelLine(i);
				System.out.println("Rows��"+rows.length);
				//��Ϊ��һ���Ǳ��⣬��˴ӵڶ��п�ʼ��ȡ��װ����
				if(i>0){
					importshoes=new ImportShoes();
					//����ÿһ�е�Ԫ����Ϣ
					for (int j = 0; j < rows.length; j++) {
						//��ȷ�жϵ�Ԫ��Ϊ��
						if(rows[j].trim().length()!=0&&!rows[j].trim().equals(null)&&!rows[j].trim().equals("")){
							//switch�жϾ��嵥Ԫ����Ϣ
							switch (j) {
							case 0:
								System.out.println("Ь����:"+rows[j]);
								importshoes.setTypes(rows[j].trim().toString());
								break;
							case 1:
								System.out.println("ЬƷ��:"+rows[j]);
								importshoes.setBrands(rows[j].trim().toString());
								break;
							case 2:
								System.out.println("Ь���:"+rows[j]);
								importshoes.setSnum(rows[j].trim().toString());
								break;
							case 3:
								System.out.println("Ь����:"+rows[j]);
								importshoes.setSizes(Float.parseFloat(rows[j].trim().toString()));
								break;
							case 4:
								System.out.println("��Ӧ��������:"+rows[j]);
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
								System.out.println("Ь����:"+rows[j]);
								importshoes.setSname(rows[j].trim().toString());								
								break;
							case 6:
								System.out.println("Ь�Ӽ۸�:"+rows[j]);
								importshoes.setSprices(Float.parseFloat(rows[j].trim().toString()));								
								break;
							case 7:
								System.out.println("Ь�����:"+rows[j]);
								String numk=rows[j].trim().toString();
								int mk=numk.indexOf(".");
								if(mk!=-1){
									importshoes.setSdiscount(Integer.parseInt(numk.substring(0,numk.indexOf("."))));	
								}else{
									importshoes.setSdiscount(Integer.parseInt(numk));
								}									
								break;//
							case 8:
								System.out.println("��������:"+rows[j]);
								importshoes.setSproducer(rows[j].trim().toString());								
								break;
							case 9:
								System.out.println("�Ա�����:"+rows[j]);
								importshoes.setSgender(rows[j].trim().toString());								
								break;
							case 10:
								System.out.println("Ь����ɫ:"+rows[j]);
								importshoes.setScolor(rows[j].trim().toString());								
								break;//
							case 11:
								System.out.println("���ݼ��:"+rows[j]);
								importshoes.setSinfo(rows[j].trim().toString());								
								break;
							case 12:
								System.out.println("Ь����:"+rows[j]);
								String sold=rows[j].trim().toString();
								int so=sold.indexOf(".");
								if(so!=-1){
									importshoes.setStimessold(Integer.parseInt(sold.substring(0,sold.indexOf("."))));	
								}else{
									importshoes.setStimessold(Integer.parseInt(sold));
								}									
								break;
							case 13:
								System.out.println("��ϸ��ϸ:"+rows[j]);
								importshoes.setSdetail(rows[j].trim().toString());								
								break;
							case 14:
								System.out.println("����Ь�ܻ���:"+rows[j]);
								importshoes.setSintegral(Float.parseFloat(rows[j].trim().toString()));								
								break;
							case 15:
								System.out.println("Ь״̬:"+rows[j]);
								importshoes.setSdetail(rows[j].trim().toString());								
								break;
							case 16:
								System.out.println("��ע:"+rows[j]);
								importshoes.setSremarks(rows[j].trim().toString());								
								break;
							}
						}						
					}		
					lisShoes.add(importshoes);
				}else{
					System.out.println("��һ�б���");
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







