package com.thread;

public class Mainer {

	public Mainer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat cat = new Cat();
		cat.start();
	}

}

class Cat extends Thread
{

	int times = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Hello world"+times);
			times++;
			if (times>= 10) {
				break;
			}
		}
	}
	
}
