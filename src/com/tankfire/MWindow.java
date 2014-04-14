package com.tankfire;

import java.awt.*;
import javax.swing.*;

public class MWindow extends JFrame {

	public MWindow() {
		// TODO Auto-generated constructor stub
		MyPanel myPanel = new MyPanel();
		this.add(myPanel);
		
		this.setSize(400, 400);
		this.setLocation(600, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MWindow mw = new MWindow();
	}

}

class MyPanel extends JPanel
{
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
//		g.setColor(Color.green);
//		g.drawOval(10, 10, 50, 50);
//		g.draw3DRect(200, 200, 100, 100, false);	
//		画一个图片
//		Image im = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/fj.png"));
//		g.drawImage(im, 10, 10, 300, 300, this);
//		g.setFont(new Font("楷体", Font.BOLD, 18));
//		g.drawString("祖国万岁", 100, 100);
		g.fillArc(0, 0, 100, 100, 0, 90);
	}
}
