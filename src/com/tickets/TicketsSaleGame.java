package com.tickets;

import java.util.*;

public class TicketsSaleGame {
	Headquarter headquarter;
	TicketsShop shop1,shop2,shop3;
	public TicketsSaleGame() {
		// TODO Auto-generated constructor stub
		headquarter = new Headquarter(5000);
		shop1 = new TicketsShop("售票点1", headquarter,700,800);
		shop2 = new TicketsShop("售票点2", headquarter,750,780);
		shop3 = new TicketsShop("售票点3", headquarter,770,790);
	}
	public void beginSale()
	{
		this.shop1.start();
		this.shop2.start();
		this.shop3.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketsSaleGame game=new TicketsSaleGame();
		game.beginSale();
	}

}
class Ticket
{
	private int ticketId;
	public Ticket(int id) {
		this.ticketId = id;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
}

class Headquarter
{
	int ticketsNum;
	Vector<Ticket> tVector = null;
	public Headquarter(int num)
	{
		this.ticketsNum = num;
		tVector = new Vector<Ticket>();
		for (int i = 0; i < this.ticketsNum; i++) {
			this.tVector.add(new Ticket(i));
		}
	}
	public Ticket giveATicket(int id)
	{
		Ticket ticket = null;
		for (int i = 0; i < tVector.size(); i++) {
			if(tVector.get(i).getTicketId() == id){
				ticket = tVector.get(i);
				this.tVector.remove(i);
			}
		}
		System.out.println("余票"+tVector.size()+"张");
		return ticket;
	}
	public int searchTicket(int id)
	{
		int flag = 0;
		for (int i = 0; i < tVector.size(); i++) {
			if(tVector.get(i).getTicketId() == id){
				flag++;
			}
		}
		return flag;
	}
}
class TicketsShop extends Thread
{
	String shopName = null;
	Headquarter headquarter = null;
	int min;
	int max;
	public TicketsShop(String shopName,Headquarter headquarter,int min,int max) {
		super();
		this.headquarter = headquarter;
		this.shopName = shopName;
		this.min = min;
		this.max = max;
	}
	public int searchTicket(int id)
	{
		return this.headquarter.searchTicket(id);
	}
	public Ticket saleATicket(int id)
	{
		if(this.searchTicket(id)>0){
			Ticket ticket = this.headquarter.giveATicket(id);
			System.out.println(this.shopName+":"+id+"出票完成！");
			return ticket;
		}else{
			System.out.println(this.shopName+":"+id+"票已经买出了");
			return null;
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = this.min; i < this.max; i++) {
			try {
				Thread.sleep(200);
				this.saleATicket(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}