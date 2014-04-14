package com.event.ballgame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class BallGame extends JFrame {

	MyPanel jp = null;
	public BallGame() {
		// TODO Auto-generated constructor stub
		jp = new MyPanel();
		
		this.add(jp);
		this.addKeyListener(jp);
		
		this.setSize(440, 440);
		this.setLocation(600, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BallGame();
	}

}

class MyPanel extends JPanel implements KeyListener
{
	int x =10;
	int y =10;
	int width = 10;
	int height = 10;
	int speed = 5;

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.fillOval(this.x, this.y, this.width, this.height);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			this.y-=this.speed;
			break;
		case KeyEvent.VK_DOWN:
			this.y+=this.speed;
			break;
		case KeyEvent.VK_RIGHT:
			this.x+=this.speed;
			break;
		case KeyEvent.VK_LEFT:
			this.x-=this.speed;
			break;
		
		default:
			break;
		}
		if (this.x<=0) {
			this.x = 0;
		}
		if(this.x>=400-this.width) {
			this.x = 400-this.width;
		}
		if (this.y<=0) {
			this.y = 0;
		}
		if(this.y>400-this.height){
			this.y=400-this.height;
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}