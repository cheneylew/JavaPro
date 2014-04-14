package com.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainGUI extends JFrame implements ActionListener {

	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2;
	JTextField jtf1;
	JPasswordField jpd1;
	JButton jb1,jb2;
	
	public static void main(String[] args) {
		new MainGUI();
	}
	public MainGUI() {
		this.setSize(300, 200);
		this.setTitle("游戏登陆器");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(new GridLayout(3,1));
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		jl1 = new JLabel("管理员");
		jl2 = new JLabel("密码");
		
		jtf1 = new JTextField(10);
		jpd1 = new JPasswordField(10);
		
		jb1 = new JButton("确认");
		jb1.addActionListener(this);
		jb1.setActionCommand("确认");
		jb2 = new JButton("取消");
		
		jp1.add(jl1);
		jp1.add(jtf1);
		
		jp2.add(jl2);
		jp2.add(jpd1);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setSize(250, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("确认"))
		{
			JDialog jd = new JDialog();
			jd.setSize(200, 200);
			jd.show();
		};
	}

}
