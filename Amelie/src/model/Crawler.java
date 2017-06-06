package model;

import java.util.LinkedList;
import java.util.List;

public class Crawler {
	
	public List<String> words = new LinkedList<String>();
	public List<String> stopList = new LinkedList<String>();
	private List<String> to_Visit = new LinkedList<String>();
	
	public Crawler(List<String> keywords, List<String> stoplists, List<String> seeds){
		
		words.addAll(keywords);
		stopList.addAll(stoplists);
		to_Visit.addAll(seeds);
	}
	
	

}
