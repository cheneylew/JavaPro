package com.event.mouseEvent;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MouseEvent extends JFrame {

	public MouseEvent() {
		// TODO Auto-generated constructor stub
		MyPanel myPanel = new MyPanel();
		this.add(myPanel);
		this.addMouseMotionListener(myPanel);
		this.addMouseListener(myPanel);
		
		this.setSize(400, 400);
		this.setLocation(600, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		System.out.println(this.getBounds().width);
		System.out.println(this.getBounds().height);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MouseEvent();
	}

}

class MyPanel extends JPanel implements MouseListener,MouseMotionListener
{
	int type = 0;
	int x;
	int y;
	int width;
	int height;
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		switch (type) {
		case 0:
			g.drawRect(x, y, width, height);
			break;

		default:
			break;
		}
	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		this.width = e.getX() - this.x;
		this.height = e.getY() - this.y;
		this.repaint();
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		this.type = 0;
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
