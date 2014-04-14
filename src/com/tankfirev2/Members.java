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
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.speed = 3;
		this.moveDirectionState = (int)(Math.random()*4);
	}
	
	public int getMoveDirectionState() {
		return moveDirectionState;
	}

	public void setMoveDirectionState(int moveDirectionState) {
		this.moveDirectionState = moveDirectionState;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			switch (this.moveDirectionState) {
			case 0:
				this.moveUp();
				break;
			case 1:
				this.moveRight();
				break;
			case 2:
				this.moveDown();
				
				break;
			case 3:
				this.moveLeft();
				break;
					
			default:
				break;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.x<=0){
				int a[]={0,1,2};
				this.moveDirectionState = Tools.GetNum(a);
			}
			if(this.x>=370){
				int a[]={0,2,3};
				this.moveDirectionState = Tools.GetNum(a);
			}
			if(this.y<=0){
				int a[]={1,2,3};
				this.moveDirectionState = Tools.GetNum(a);
			}
			if(this.y>=340){
				int a[]={0,1,3};
				this.moveDirectionState = Tools.GetNum(a);
			}
		}
	}
	public void checkHitTanks(Vector<Tank> tanks){
		
		for (int i = 0; i < tanks.size(); i++) {
			Tank tank = tanks.get(i);
			if(tank!=this){
				this.checkHitOneTank(tank);
			}
		}
	}
	private void checkHitOneTank(Tank tank){
		//System.out.println(tank.getTankName());
		switch (this.moveDirectionState) {
			case Tank.DirectionTop:
				if(tank.direction == Tank.DirectionTop || tank.direction == Tank.DirectionBottom){
					if (this.x>tank.getX()-20 && this.x<tank.getX()+20 && (this.y-tank.getY())>30) {
						this.moveDirectionState = Tank.DirectionBottom;
					}
				}else{
					if (this.x>tank.getX()-30 && this.x<tank.getX()+30 && (this.y-tank.getY())>30) {
						this.moveDirectionState = Tank.DirectionBottom;
					}
				}
				break;
			case Tank.DirectionBottom:
				if(tank.direction == Tank.DirectionTop || tank.direction == Tank.DirectionBottom){
					if (this.x>tank.getX()-20 && this.x<tank.getX()+20 && Math.abs(this.y-tank.getY())<30) {
						this.moveDirectionState = Tank.DirectionTop;
					}
				}else{
					if (this.x>tank.getX()-30 && this.x<tank.getX()+30 && Math.abs(this.y-tank.getY())<30) {
						this.moveDirectionState = Tank.DirectionTop;
						System.out.println(this.moveDirectionState);
					}
				}
				
				break;
			case Tank.DirectionRight:
				if(tank.direction == Tank.DirectionRight || tank.direction == Tank.DirectionLeft){
					if (this.y>tank.getY()-20 && this.x<tank.getY()+20 && Math.abs(this.x-tank.getX())<30) {
						this.moveDirectionState = Tank.DirectionLeft;
					}
				}else{
					if (this.y>tank.getY()-30 && this.x<tank.getY()+30 && Math.abs(this.x-tank.getX())<30) {
						this.moveDirectionState = Tank.DirectionLeft;
					}
				}
				break;
			case Tank.DirectionLeft:
				if(tank.direction == Tank.DirectionRight || tank.direction == Tank.DirectionLeft){
					if (this.y>tank.getY()-20 && this.x<tank.getY()+20 && Math.abs(this.x-tank.getX())<30) {
						this.moveDirectionState = Tank.DirectionRight;
					}
				}else{
					if (this.y>tank.getY()-30 && this.x<tank.getY()+30 && Math.abs(this.x-tank.getX())<30) {
						this.moveDirectionState = Tank.DirectionRight;
					}
				}
				break;
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
				System.out.println("×Óµ¯Î»ÖÃ£ºx:"+this.getX()+" Y:"+this.getY());
				break;
			}
		}
		
	}
}