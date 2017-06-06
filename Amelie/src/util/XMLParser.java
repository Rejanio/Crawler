package util;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class XMLParser {
	
	private File file;
	private String location;
	private List<String> toVisit;
	
	public XMLParser() throws IOException{
		
		this.file = new File("xml/seeds.xml");
		this.location = FileUtils.readFileToString(file);
		this.toVisit = new LinkedList<String>();
	}
	
	public List<String> parsing(){
		Document doc = Jsoup.parse(location, "", Parser.xmlParser());
		for(Element a : doc.select("url")){
			a.getElementsByTag("url");
			String x = a.text(); 
			String temp = x.split("<url>")[0];
			toVisit.add(temp);
							
		}
		return toVisit;
	}
	
	public void unparsing() throws ParserConfigurationException{
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		@SuppressWarnings("unused")
		Document document = (Document) documentBuilder.newDocument(); 
		
	}
	
	

}
