package com.jsoup.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Lottery2 {

	public static void main(String[] args) {
		Document doc;
		try {
			doc=Jsoup.connect("https://m.dhlottery.co.kr/common.do?method=main").get();
			Elements lottoNo=doc.select("#lottoDrwNo"); //회차
			Elements lottoDrwNo=doc.select("div.prizeresult"); //로또번호
		
			for(int i=0;i<lottoNo.size();i++) {
				System.out.println(lottoNo.get(i).text()+" 당첨번호"); //회차
				//회차에 해당하는 로또 번호
				System.out.println(lottoDrwNo.get(i).select("span").text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}