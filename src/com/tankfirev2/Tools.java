package com.tankfirev2;

public class Tools {

	public final static int GetNum(int[] i){
		  int n=0;
		  int a[]=i;
		  java.util.Random r=new java.util.Random();
		  n=r.nextInt(a.length);
		  n=a[n];
		  return n;
	}
}
