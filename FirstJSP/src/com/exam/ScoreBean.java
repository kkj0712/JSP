package com.exam;

public class ScoreBean {
	private String name;
	private int kor;
	private int eng;
	private int math;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	public int getTot() {
		return (kor+eng+math);
	}
	public int getAvg() {
		return (kor+eng+math)/3;
	}
	public String getGrade() {
		String grade="";
		switch((kor+eng+math)/3/10) {
			case 10:
			case 9: grade="A학점"; break;
			case 8: grade="B학점"; break;
			case 7: grade="C학점"; break;
			default: grade="F학점"; break;
		}
		return grade;
	}
}