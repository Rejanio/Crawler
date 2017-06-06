package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;

import model.Seed;

import org.w3c.dom.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@SuppressWarnings("unused")
public class XMLComposer {
	
	
	public void composing(List<Seed> list) throws ParserConfigurationException, TransformerException{
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		Element seed = document.createElement("Seed");
		document.appendChild(seed);
		
		
		
		for(int i = 0; i<list.size();i++){
			Element pagina = document.createElement("Pagina");
			seed.appendChild(pagina);			
			Attr attr = document.createAttribute("id");
			String temp = String.valueOf(i);
			attr.setValue(temp);
			pagina.setAttributeNode(attr);
			
			Element url = document.createElement("url");
			url.appendChild(document.createTextNode(list.get(i).getUrl()));
			pagina.appendChild(url);
			
			Element bol = document.createElement("visitada");
			String x = String.valueOf(list.get(i).isVisited());
			bol.appendChild(document.createTextNode(x));
			pagina.appendChild(bol);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new File("xml/seeds.xml"));
		
		transformer.transform(domSource, streamResult);

	}

}
