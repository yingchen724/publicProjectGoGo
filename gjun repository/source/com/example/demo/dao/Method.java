package com.example.demo.dao;

public class Method {
	public static String formatDate(int time) {
		return time<10? "0"+time: ""+time;
	}
	
	public static String formatThree(int num) {
		return num>99? ""+num: (num>9? "0"+num:"00"+num);
	}
}
