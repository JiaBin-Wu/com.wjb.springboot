package com.wjb.springboot.controller;

public class LambdaTest {
	public static void main(String[] args) {
		Thread t=new Thread(()->System.out.println(1));
		t.start();
	}
}
