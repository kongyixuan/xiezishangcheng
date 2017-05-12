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
		//创建图片表头
		response.setHeader("Pragma", "No-Cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expries",0);
		//产生随机数
		Random random=new Random();
		//设置随机类
		int length=5;
		//验证码字符串
		String checkCode="";
		char code;
		int number;
		//产生验证码
		for (int i = 0; i < length; i++) {
			number=random.nextInt(26);
			if(number%2==0){
				code=(char)('0'+(char)(number%10));
			}else{
				code=(char)('A'+(char)(number%26));
			}
			checkCode+=code+"";
		}
		//在内存中创建图像,定义宽高
		int width=(int)Math.ceil(length*12.5),height=22;
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获取图片上下文
		Graphics g=image.getGraphics();
		//设置背景颜色
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		//画随机数边框
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width-1, height-1);
		//随机产生10条干扰线，使得图像中的认证码不易被其他程序探测
		for (int i = 0; i < 10; i++) {
			int x1=random.nextInt(width);
			int y1=random.nextInt(height);
			int x2=random.nextInt(width);
			int y2=random.nextInt(height);
			g.setColor(Color.RED);
			g.drawLine(x1, y1, x2, y2);
		}
		//设置字体，画验证码
		g.setColor(Color.green);
		g.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,16));
		g.drawString(checkCode, 5, 18);
		//验证码封装回话
		HttpSession session=request.getSession();
		session.setAttribute("rand",checkCode);
		//图片生效
		g.dispose();
		//输出并显示到页面
		ImageIO.write(image, "JPEG",response.getOutputStream());
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
