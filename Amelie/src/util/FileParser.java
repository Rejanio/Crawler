package util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unused")
public class FileParser 
{
	
	private List<String> words = new LinkedList<String>();
	
	public List<String> parsing(String loc) throws IOException
	{	
		
		FileReader fr = new FileReader(loc);
		BufferedReader br = new BufferedReader(fr);
		
		String ss;
				
		while((ss = br.readLine()) != null){
			
			if(ss.isEmpty() || 
					ss.trim().equals("") || 
					ss.trim().equals("\n")|| 
					ss.trim().equals("*** Terminacoes Ignoraveis") ||
					ss.trim().equals("*** Palavras Complementares"));
			else{
				this.words.add(ss);
			}
		}
		
		br.close();
		return words;
		
		
	}

}
