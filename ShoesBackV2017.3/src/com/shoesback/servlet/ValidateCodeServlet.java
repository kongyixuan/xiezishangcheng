package com.shoesback.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ValidateCodeServlet extends HttpServlet {

	public ValidateCodeServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("gbk");
		//����ͼƬ��ͷ
		response.setHeader("Pragma", "No-Cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expries",0);
		//���������
		Random random=new Random();
		//���������
		int length=5;
		//��֤���ַ���
		String checkCode="";
		char code;
		int number;
		//������֤��
		for (int i = 0; i < length; i++) {
			number=random.nextInt(26);
			if(number%2==0){
				code=(char)('0'+(char)(number%10));
			}else{
				code=(char)('A'+(char)(number%26));
			}
			checkCode+=code+"";
		}
		//���ڴ��д���ͼ��,������
		int width=(int)Math.ceil(length*12.5),height=22;
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//��ȡͼƬ������
		Graphics g=image.getGraphics();
		//���ñ�����ɫ
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		//��������߿�
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width-1, height-1);
		//�������10�������ߣ�ʹ��ͼ���е���֤�벻�ױ���������̽��
		for (int i = 0; i < 10; i++) {
			int x1=random.nextInt(width);
			int y1=random.nextInt(height);
			int x2=random.nextInt(width);
			int y2=random.nextInt(height);
			g.setColor(Color.RED);
			g.drawLine(x1, y1, x2, y2);
		}
		//�������壬����֤��
		g.setColor(Color.green);
		g.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,16));
		g.drawString(checkCode, 5, 18);
		//��֤���װ�ػ�
		HttpSession session=request.getSession();
		session.setAttribute("rand",checkCode);
		//ͼƬ��Ч
		g.dispose();
		//�������ʾ��ҳ��
		ImageIO.write(image, "JPEG",response.getOutputStream());
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
