package com.f3.example;

public class ThreadTest {
	
	private volatile static int i;
	public static void main(String[] args) {
		threadA();
		threadB();
	}
	
	public static void threadA(){
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				
				while(true){
					if (i>999990 && i<999999) {
						System.out.println("threadA's i="+i);
					}
				}
				
			}
		});
		thread.start();
	}
	
	public static void threadB(){
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int index=0;index<1000000;index++){
					if (index>999990) {
						i=index;
						System.out.println("theadB's i="+i);
					}
				}
				
			}
		});
		thread.start();
	}
}
