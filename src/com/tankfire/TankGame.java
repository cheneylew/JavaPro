package com.tankfire;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TankGame extends JFrame {
	MPanel myPanel = null;
	public TankGame() {
		// TODO Auto-generated constructor stub
		myPanel = new MPanel();
		myPanel.setBackground(Color.black);
		this.add(myPanel);
		Thread tr = new Thread(myPanel);
		tr.start();
		
		//JFrame�¼�Դ
		this.addKeyListener(myPanel);
		this.addKeyListener(myPanel);
		
		this.setSize(400, 400);
		this.setLocation(600, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankGame tankGame = new TankGame();
	}

}

class MPanel extends JPanel implements KeyListener,Runnable
{
	Hero hero = null;
	Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
	int enemySize = 3;
	public MPanel() {
		super();
		hero = new Hero(180, 340);
		
		for (int i = 0; i < enemySize; i++) {
			EnemyTank oneTank = new EnemyTank(10+i*150, 10);
			enemyTanks.add(oneTank);
		}
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//�����ҵ�Tank
		this.drawTank(hero.getX(), hero.getY(), g, hero.getDirection(),1 );
		//�����ӵ�
		if(hero.shot!=null)
		{
			g.setColor(Color.yellow);
			g.fillOval(hero.shot.getX(), hero.shot.getY(), 2, 2);
		}
		//System.out.println("ˢ��ing");
		
		//��������Tank
		for (int i = 0; i < enemyTanks.size(); i++) {
			EnemyTank tank = enemyTanks.get(i);
			this.drawTank(tank.getX(), tank.getY(), g, Tank.DirectionBottom, 0);
		}
		
		
	}
	
	public void drawTank(int x, int y,Graphics g,int direct,int type)
	{
		switch (type) {
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.GREEN);
			break;
		case 2:
			g.setColor(Color.RED);
			break;
		}
		switch (direct) {
		case Tank.DirectionTop:
			//������߾���
			g.fill3DRect(x, y, 5, 30,false);
			//�����ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//�����м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//�����м�Բ��
			g.fillOval(x+4, y+10, 10, 10);
			//����ǹ
			g.drawLine(x+9, y+15, x+9, y+0);
			break;
		case Tank.DirectionRight:
			//������߾���
			g.fill3DRect(x, y, 30, 5,false);
			//�����ұ߾���
			g.fill3DRect(x, y+15, 30, 5,false);
			//�����м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//�����м�Բ��
			g.fillOval(x+10, y+4, 10, 10);
			//����ǹ
			g.drawLine(x+15, y+9, x+30, y+9);
			break;
		case Tank.DirectionBottom:
			//������߾���
			g.fill3DRect(x, y, 5, 30,false);
			//�����ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//�����м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//�����м�Բ��
			g.fillOval(x+4, y+10, 10, 10);
			//����ǹ
			g.drawLine(x+9, y+15, x+9, y+30);
			break;
		case Tank.DirectionLeft:
			//������߾���
			g.fill3DRect(x, y, 30, 5,false);
			//�����ұ߾���
			g.fill3DRect(x, y+15, 30, 5,false);
			//�����м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//�����м�Բ��
			g.fillOval(x+10, y+4, 10, 10);
			//����ǹ
			g.drawLine(x+15, y+9, x+0, y+9);
			break;
		}
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
			hero.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			hero.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			hero.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			hero.moveRight();
			break;
		default:
			break;
		}
		this.repaint();
		
		if(e.getKeyCode() == KeyEvent.VK_J )
		{
			hero.shotEnemy();
			this.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();
		}
		
	}
}