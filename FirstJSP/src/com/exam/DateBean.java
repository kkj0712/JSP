package com.exam;

import java.util.Calendar;

public class DateBean {
	Calendar ca=Calendar.getInstance();
	String[]arr= {"일","월","화","수","목","금","토"};
	
	public String getToday() {
		String str=ca.get(Calendar.YEAR)+"년";
		str+=ca.get(Calendar.MONDAY)+1+"월";
		str+=ca.get(Calendar.DATE)+"일";
		str+=arr[ca.get(Calendar.DAY_OF_WEEK)-1]+"요일";
		return str;
	}
	public String getDay() {
		StringBuilder sb=new StringBuilder(); //문자열이 계속 바뀌는 상황에선 StringBuilder가 유용(동적인 문자열)
		sb.append(ca.get(Calendar.YEAR)+"년");
		sb.append(ca.get(Calendar.MONDAY)+1+"월");
		sb.append(ca.get(Calendar.DATE)+"일");
		sb.append(arr[ca.get(Calendar.DAY_OF_WEEK)-1]+"요일");
		return sb.toString();  //StringBuilder에서는 toString()을 이용하여 문자로 변환
	}
}
