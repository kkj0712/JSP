package com.jsoup.test;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test01 {

	public static void main(String[] args) {
		try {
			Document doc=Jsoup.connect("https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&sq=&o=&q=%EC%98%81%ED%99%94").get();
//			System.out.println(doc);
			
			Elements movie_list=doc.select("ol.movie_list li");
//			System.out.println(movie_list);

			System.out.println("====力格====");
			Elements titleContainer=movie_list.select("div.info_tit a");
//			System.out.println(titleContainer);
//			for(int i=0;i<titleContainer.size();i++) {
//				System.out.println(titleContainer.get(i).text());
//			}
//			
//			System.out.println("====乞痢====");
			Elements scoreContainer=movie_list.select("em.rate");
//			for(int i=0;i<scoreContainer.size();i++) {
//				System.out.println(scoreContainer.get(i).text());
//			}
			
			ArrayList<Movie> arr=new ArrayList<Movie>(); 
			for(int i=0;i<scoreContainer.size();i++) {
				Movie movie=new Movie();
				String title=titleContainer.get(i).text();
				String rate=scoreContainer.get(i).text();
				movie.setTitle(title);
				movie.setRate(rate);
				arr.add(movie);
			}
			for(Movie movie:arr) {
				System.out.println("力格:"+movie.getTitle());
				System.out.println("乞痢: "+movie.getRate());
				System.out.println();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
