package com.jsoup.test;

public class Movie {
	private String title;
	private String rate;
	
	public String getTitle() {
		return title == null ? "" : title.trim();
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRate() {
		return rate == null ? "" : rate.trim();
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
}