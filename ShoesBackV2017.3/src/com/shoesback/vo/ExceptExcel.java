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
    //����cell���������ĸ�λ�ֽڽض�
	static short XLS_ENCODING=HSSFCell.ENCODING_UTF_16;
	//���ø�������ʽ	
	static String NUMBER_FORMAT="#,##0.00";
	//ָ�����ڸ�ʽ
	static String DATE_FORMAT="m/d/yy";
	//���������
	OutputStream out=null;
	//����excel�ļ�����
	HSSFWorkbook workbook=null;	
	//����excel��sheet����
	HSSFSheet sheet=null;
	//�����ж���
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
	//���캯��
	public ExceptExcel(){
		
	}
	//��ʼ��excel
	public ExceptExcel(OutputStream out) {
        this.out=out;
        this.workbook=new HSSFWorkbook();
        this.sheet=workbook.createSheet();
	}
	//����excel
	public void export() throws IOException{
		workbook.write(out);
		out.flush();
		out.close();
	}
	//���һ��
	public void createRow(int index){
		this.row=this.sheet.createRow(index);		
	}
	//��ȡ��Ԫ��value
	
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
	//���õ�Ԫ��index���value����Ԫ�����ֵ,���ط���
	public void setCell(int index,int value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}
	public void setCell(int index,double value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		//������cell��ʽ
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		HSSFDataFormat format=workbook.createDataFormat();
		//����cell��ʽΪ���Ƹ�������ʽ
	    cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT));
		//���ø�cell��������ʾ��ʽ
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
	//����Ь�Ӽ�����Ϣ
	public boolean ExceptShoes(List<Shoes> lstShoes){
		System.out.println("��ʼ����excel....");
		//��������ϵͳ�ϴ�����·��,��ϵͳĬ��tomcat����·��
		File f=new File("D:\\Tomcat 7.0\\webapps\\back\\upload\\excel\\exceptshoes.xls");
		if(!f.exists()){
			f.delete();
		}
		//����excel��������
		ExceptExcel e=null;
		//��������exce��Ϣ��������		
		try {
			//ʵ����ExceptExcel��file·�����ݹ��캯����		    
			e=new ExceptExcel(new FileOutputStream(f));
	    	//�ֹ�����excel��һ�У�����
			e.createRow(0);
			//���뵥Ԫ��
			e.setCell(0, "Id");
			e.setCell(1, "Ь������");
			e.setCell(2, "Ь��Ʒ��");
			e.setCell(3, "Ь�ӱ��");
			e.setCell(4, "Ь������");
			e.setCell(5, "Ь�Ӽ۸�");
			e.setCell(6, "Ь�����");
			e.setCell(7, "����ʱ��");
			e.setCell(8, "��������");
			e.setCell(9, "�Ա�����");
			e.setCell(10, "Ь����ɫ");
			e.setCell(11, "���ݼ��");
			e.setCell(12, "Ь������");
			e.setCell(13, "��ϸ��Ϣ");
			e.setCell(14, "����Ь�ܻ���");
			e.setCell(15, "Ь��״̬");
			e.setCell(16, "��ע");
			//��װ���ϣ�ѭ����̬��������Ь�Ӿ���������Ϣ
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
				e.setCell(15,shoe.getSdelete()==0?"����":"����");
				e.setCell(16,shoe.getSremarks());
			}
			e.export();
			System.out.println("Excel�����ɹ���");
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
