package com.jsoup.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Lottery {
	
	public static void main(String[] args) {
		try {
			Document doc=Jsoup.connect("https://m.dhlottery.co.kr/common.do?method=main").get();
			Element prizeresult=doc.select("div.prizeresult").first();
			Elements lotto=prizeresult.select("div.num span");
			for(int i=0;i<lotto.size();i++) {
				String su=lotto.get(i).text();
				System.out.print(su+"\t");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}