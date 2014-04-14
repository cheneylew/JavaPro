package com.tankfirev2;

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
		
		//JFrame事件源
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
	Image image1,image2,image3;
	Vector<Bomb> bombs = new Vector<Bomb>();
	public MPanel() {
		super();
		hero = new Hero(180, 340);
		hero.setTankName("Hero坦克");
		
		for (int i = 0; i < enemySize; i++) {
			EnemyTank oneTank = new EnemyTank(10+i*170, 10);
			oneTank.setTankName("敌人坦克"+i);
			enemyTanks.add(oneTank);
			Thread tr = new Thread(oneTank);
			tr.start();
		}
		//准备子弹图片
		this.image1 = Toolkit.getDefaultToolkit().getImage("bomb_1.gif");
		this.image2 = Toolkit.getDefaultToolkit().getImage("bomb_2.gif");
		this.image3 = Toolkit.getDefaultToolkit().getImage("bomb_3.gif");
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//判断敌人的坦克和我的坦克是否想碰
		
		//画出我的Tank
		this.drawTank(hero.getX(), hero.getY(), g, hero.getDirection(),1 );
		//画出子弹
		if(hero.shots.size()>0)
		{
			g.setColor(Color.yellow);
			for (int i = 0; i < hero.shots.size(); i++) {
				Shot shot = hero.shots.get(i);
				if(shot.isLivable == false)
				{
					System.out.println("移除："+hero.shots.remove(shot));
				}
				if(shot!=null && shot.isLivable==true)
				{
					g.fillOval(shot.getX(), shot.getY(), 2, 2);
				}
			}
		}
		//System.out.println("刷新ing");
		//画出炮弹
		for (int i = 0; i < this.bombs.size(); i++) {
			System.out.println(this.bombs.size());
			Bomb bomb = this.bombs.get(i);
			if (bomb.isLive) {
				if (bomb.life>6) {
					System.out.println(bomb.isLive);
					g.drawImage(this.image1, bomb.x, bomb.y, 30, 30, this);
				}else if (bomb.life>3) {
					g.drawImage(this.image2, bomb.x, bomb.y, 30, 30, this);
				}else if (bomb.life>=1) {
					g.drawImage(this.image3, bomb.x, bomb.y, 30, 30, this);
				}else{
					bomb.isLive = false;
				}
				bomb.lifeDown();
			}else this.bombs.remove(bomb);
			
		}
		//在画出敌人坦克之前，判断有没有和其它坦克相碰撞，若是碰撞，则调头
		this.enemyTankHits();
		//画出敌人Tank
		for (int i = 0; i < enemyTanks.size(); i++) {
			EnemyTank tank = enemyTanks.get(i);
			if (tank.isLivable) {
				this.drawTank(tank.getX(), tank.getY(), g, tank.getDirection(), 0);
			}else{
				//this.enemyTanks.remove(tank);
			}
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
			//画出左边矩形
			g.fill3DRect(x, y, 5, 30,false);
			//画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//画出中间圆形
			g.fillOval(x+4, y+10, 10, 10);
			//画出枪
			g.drawLine(x+9, y+15, x+9, y+0);
			break;
		case Tank.DirectionRight:
			//画出左边矩形
			g.fill3DRect(x, y, 30, 5,false);
			//画出右边矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//画出中间圆形
			g.fillOval(x+10, y+4, 10, 10);
			//画出枪
			g.drawLine(x+15, y+9, x+30, y+9);
			break;
		case Tank.DirectionBottom:
			//画出左边矩形
			g.fill3DRect(x, y, 5, 30,false);
			//画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//画出中间圆形
			g.fillOval(x+4, y+10, 10, 10);
			//画出枪
			g.drawLine(x+9, y+15, x+9, y+30);
			break;
		case Tank.DirectionLeft:
			//画出左边矩形
			g.fill3DRect(x, y, 30, 5,false);
			//画出右边矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//画出中间圆形
			g.fillOval(x+10, y+4, 10, 10);
			//画出枪
			g.drawLine(x+15, y+9, x+0, y+9);
			break;
		}
	}
	//判断一颗子弹和坦克是否相撞
	public boolean isHitTank(Shot shot, EnemyTank tank)
	{
		//判断
		switch (tank.direction) {
		case Tank.DirectionTop:
		case Tank.DirectionBottom:
			if(shot.getX()>=tank.getX() && shot.getX()<=(tank.getX()+18) && shot.getY()>=tank.getY() && shot.getY()<=(tank.getY()+28))
			{
				shot.setLivable(false);
				tank.setLivable(false);
				Bomb bomb = new Bomb(tank.x, tank.y);
				this.bombs.add(bomb);
				return true;
			}
			break;
		case Tank.DirectionLeft:
		case Tank.DirectionRight:
			if(shot.getX()>=tank.getX() && shot.getX()<=(tank.getX()+28) && shot.getY()>=tank.getY() && shot.getY()<=(tank.getY()+18))
			{
				shot.setLivable(false);
				tank.setLivable(false);
				Bomb bomb = new Bomb(tank.x, tank.y);
				this.bombs.add(bomb);
				return true;
			}
			break;

		default:
			break;
		}
		return false;
	}
	public void enemyTankHits()
	{
		//判断
		Vector<Tank> tanks= new Vector<Tank>();
		tanks.addAll(this.enemyTanks);
		tanks.add(this.hero);
		for (int i = 0; i < enemyTanks.size(); i++) {
			EnemyTank enemyTank = enemyTanks.get(i);
			enemyTank.checkHitTanks(tanks);
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
		if(e.getKeyCode() == KeyEvent.VK_J )
		{
			
			hero.shotEnemy();
		}
		this.repaint();
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
			for (int i = 0; i < hero.shots.size(); i++) {
				Shot shot = hero.shots.get(i);
				if(shot.isLivable){
					for (int j = 0; j < enemyTanks.size(); j++) {
						EnemyTank enemyTank = enemyTanks.get(j);
						if(enemyTank.isLivable)
						{
							this.isHitTank(shot, enemyTank);
						}
					}
				}
			}
			this.repaint();
		}
		
	}
}