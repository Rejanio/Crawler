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

import model.Centroide;
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
public class Creator {
	public static final String xmlFilePath = "xml/seed_entrada.xml";
	public static final String xmlFilePath2 = "xml/centroide.xml";
	public Document document;
	
	public void xmlWriter(List<Seed> list) throws ParserConfigurationException, TransformerException{
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
		StreamResult streamResult = new StreamResult(new File(xmlFilePath));
		
		transformer.transform(domSource, streamResult);

	}
	
	public void xmlCentroide( List<Centroide> list) throws ParserConfigurationException, TransformerException{
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		this.document = documentBuilder.newDocument();
		
		Element centroide = document.createElement("Centroide");
		this.document.appendChild(centroide);
		String enderecoPagina=null;
		Element pagina=null;
			for(int i = 0; i<list.size();i++){
				
				if(enderecoPagina==null){
					enderecoPagina=list.get(i).getEndereco();
					
					pagina = document.createElement("Pagina");
					centroide.appendChild(pagina);
					Attr attr = document.createAttribute("url");
					attr.setValue(enderecoPagina);
					pagina.setAttributeNode(attr);
					
					//criando a palavra
					Element palavra = document.createElement("Palavra");
					String temp = list.get(i).getConteudo();
					Attr x= document.createAttribute("selecionada");
					x.setValue(temp);
					palavra.setAttributeNode(x);
					pagina.appendChild(palavra);
					
					Element peso = document.createElement("peso");
					String temp1 = String.valueOf(list.get(i).getPeso());
					peso.appendChild(document.createTextNode(temp1));
					palavra.appendChild(peso);
					
					Element rep = document.createElement("repetição");
					String temp2 = String.valueOf(list.get(i).getRepeticao());
					rep.appendChild(document.createTextNode(temp2));
					palavra.appendChild(rep);
					
					
				}
				else{
					if(enderecoPagina.equals(list.get(i).getEndereco())){
						Element palavra = document.createElement("Palavra");
						String temp = list.get(i).getConteudo();
						Attr x= document.createAttribute("selecionada");
						x.setValue(temp);
						palavra.setAttributeNode(x);
						pagina.appendChild(palavra);
						
						Element peso = document.createElement("peso");
						String temp1 = String.valueOf(list.get(i).getPeso());
						peso.appendChild(document.createTextNode(temp1));
						palavra.appendChild(peso);
						
						Element rep = document.createElement("repetição");
						String temp2 = String.valueOf(list.get(i).getRepeticao());
						rep.appendChild(document.createTextNode(temp2));
						palavra.appendChild(rep);
						
						
					}
					else{
						if(!enderecoPagina.equals(list.get(i).getEndereco())){
							enderecoPagina=list.get(i).getEndereco();
							
							pagina = document.createElement("Pagina");
							centroide.appendChild(pagina);
							pagina.appendChild(document.createTextNode(enderecoPagina));
							Element palavra = document.createElement("Palavra");
							String temp = list.get(i).getConteudo();
							Attr x= document.createAttribute("selecionada");
							x.setValue(temp);
							palavra.setAttributeNode(x);
							pagina.appendChild(palavra);
							
							Element peso = document.createElement("peso");
							String temp1 = String.valueOf(list.get(i).getPeso());
							peso.appendChild(document.createTextNode(temp1));
							palavra.appendChild(peso);
							
							Element rep = document.createElement("repetição");
							String temp2 = String.valueOf(list.get(i).getRepeticao());
							rep.appendChild(document.createTextNode(temp2));
							palavra.appendChild(rep);
							
						}
						
						
						
						
					}
					
				}
				//Element pagina = document.createElement("Pagina");
				//centroide.appendChild(pagina);
				//pagina.appendChild(document.createTextNode(list.get(i)));
			
				//for(int j = 0; j < list.size();j++){
		
					//Element palavra = document.createElement("Palavra");
					//palavra.appendChild(document.createTextNode(list.get(j).getConteudo()));
					//pagina.appendChild(palavra);
				//}	
			}
		
		
	}
	public void criarCentroide() throws TransformerException{
		
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(this.document);
		StreamResult streamResult = new StreamResult(new File(xmlFilePath2));
		
		transformer.transform(domSource, streamResult);
	}
}
