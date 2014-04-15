package com.tankfirev2;

import java.util.*;

class Tank
{
	public static final int DirectionTop = 0;
	public static final int DirectionRight = 1;
	public static final int DirectionBottom = 2;
	public static final int DirectionLeft = 3;
	int x = 0;
	int y = 0;
	int speed = 3;
	boolean isLivable;
	String tankName;
	int direction = DirectionTop;
	public Tank(int x,int y) {
		this.x = x;
		this.y = y;
		this.isLivable = true;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isLivable() {
		return isLivable;
	}
	public void setLivable(boolean isLivable) {
		this.isLivable = isLivable;
	}
	public String getTankName() {
		return tankName;
	}
	public void setTankName(String tankName) {
		this.tankName = tankName;
	}
	public void moveUp(){
		this.direction = Tank.DirectionTop;
		this.y-=this.speed;
	}
	public void moveDown(){
		this.direction = Tank.DirectionBottom;
		this.y+=this.speed;
	}
	public void moveLeft(){
		this.direction = Tank.DirectionLeft;
		this.x-=this.speed;
	}
	public void moveRight(){
		this.direction = Tank.DirectionRight;
		this.x+=this.speed;
	}
	
}

class Hero extends Tank
{
	int maxShot = 5;
	Vector<Shot> shots = null;
	public Hero(int x, int y) {
		super(x, y);
		this.shots = new Vector<Shot>();
		
	}
	public void shotEnemy(){
		if(this.shots.size()>this.maxShot)
		{
			System.out.println(this.shots.size());
			return;
		}
		Shot shot = null;
		switch (this.direction) {
		case Tank.DirectionTop:
			shot = new Shot(this.x+9, this.y, Tank.DirectionTop);
			break;
		case Tank.DirectionRight:
			shot = new Shot(this.x+29, this.y+8, Tank.DirectionRight);
			break;
		case Tank.DirectionBottom:
			shot = new Shot(this.x+8, this.y+29, Tank.DirectionBottom);
			break;
		case Tank.DirectionLeft:
			shot = new Shot(this.x, this.y+8, Tank.DirectionLeft);
			break;
		default:
			break;
		}
		Thread tr = new Thread(shot);
		tr.start();
		this.shots.add(shot);
	}
}

class EnemyTank extends Tank implements Runnable
{
	private int moveDirectionState;
	Vector<Shot> shots = null;
	int maxShot = 3;
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.speed = 3;
		this.moveDirectionState = (int)(Math.random()*4);
		this.shots = new Vector<Shot>();
	}
	
	public int getMoveDirectionState() {
		return moveDirectionState;
	}

	public void setMoveDirectionState(int moveDirectionState) {
		this.moveDirectionState = moveDirectionState;
	}
	public void shotEnemy(){
		if(this.shots.size()>this.maxShot)
		{
			//System.out.println(this.shots.size());
			this.shots.removeAllElements();
			return;
		}
		Shot shot = null;
		switch (this.direction) {
		case Tank.DirectionTop:
			shot = new Shot(this.x+9, this.y, Tank.DirectionTop);
			break;
		case Tank.DirectionRight:
			shot = new Shot(this.x+29, this.y+8, Tank.DirectionRight);
			break;
		case Tank.DirectionBottom:
			shot = new Shot(this.x+8, this.y+29, Tank.DirectionBottom);
			break;
		case Tank.DirectionLeft:
			shot = new Shot(this.x, this.y+8, Tank.DirectionLeft);
			break;
		default:
			break;
		}
		Thread tr = new Thread(shot);
		tr.start();
		this.shots.add(shot);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			m:
			switch (this.moveDirectionState) {
			case 0:
				for (int i = 0; i < 30; i++) {
					if(this.y>0){
						this.moveUp();
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				break;
			case 1:
				for (int i = 0; i < 30; i++) {
					if(this.x<370){
						this.moveRight();
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for (int i = 0; i < 30; i++) {
					if(this.y<370){
						this.moveDown();
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			case 3:
				for (int i = 0; i < 30; i++) {
					if(this.x>0){
						this.moveLeft();
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
			this.shotEnemy();
			this.moveDirectionState = (int)(Math.random()*4);
			if(this.isLivable == false){
				System.out.println("清除敌人坦克的僵尸进程！");
				break;
			}
		}
	}
}

class Shot implements Runnable
{
	int x;
	int y;
	int direct;
	int speed = 2;
	boolean isLivable = false;
	public Shot(int x,int y,int direct)
	{
		this.x = x;
		this.y = y;
		this.direct = direct;
		this.isLivable = true;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public boolean isLivable() {
		return isLivable;
	}
	public void setLivable(boolean isLivable) {
		this.isLivable = isLivable;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch (this.direct) {
			case Tank.DirectionTop:
				this.y -= this.speed;
				break;
			case Tank.DirectionRight:
				this.x += this.speed;			
				break;
			case Tank.DirectionBottom:
				this.y += this.speed;
				break;
			case Tank.DirectionLeft:
				this.x -= this.speed;
				break;
			default:
				break;
			}
			if (this.x<=-2 || this.x>=398||this.y<=-2 || this.y>=398) {
				this.isLivable = false;
				System.out.println("子弹位置：x:"+this.getX()+" Y:"+this.getY());
				break;
			}
		}
		
	}
}

class Bomb
{
	int x,y;
	int life =9;
	boolean isLive = true;
	public Bomb(int x,int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public void lifeDown()
	{
		if(life>0){
			this.life--;
		}else{
			this.isLive=false;
		}
	}
	
}