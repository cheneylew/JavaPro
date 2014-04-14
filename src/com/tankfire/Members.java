package com.tankfire;

class Tank
{
	public static final int DirectionTop = 0;
	public static final int DirectionRight = 1;
	public static final int DirectionBottom = 2;
	public static final int DirectionLeft = 3;
	int x = 0;
	int y = 0;
	int speed = 3;
	int direction = DirectionTop;
	public Tank(int x,int y) {
		this.x = x;
		this.y = y;
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
	Shot shot = null;
	public Shot getShot() {
		return shot;
	}
	public void setShot(Shot shot) {
		this.shot = shot;
	}
	public Hero(int x, int y) {
		super(x, y);
		
	}
	public void shotEnemy(){
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
		Thread tr = new Thread(this.shot);
		tr.start();
		
	}
}

class EnemyTank extends Tank
{

	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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