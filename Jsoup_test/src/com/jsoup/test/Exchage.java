package com.jsoup.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Exchage {

	public static void main(String[] args) {
		Document doc;
		try {
			doc=Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%ED%99%98%EC%9C%A8").get();
			Elements table=doc.select("table.rate_table_info");
			Elements tr=table.select("tbody tr");
			for(int i=0;i<tr.size();i++) {
				String country=tr.get(i).select("th a").text();
				String ratio=tr.get(i).select("td").first().text();
				System.out.println(country+"=>"+ratio);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}