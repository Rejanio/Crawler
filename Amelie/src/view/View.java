package view;

import java.io.IOException;

import model.CrawlerController;

public class View {
	
	public View()
	{
		
	}

	public static void main(String[] args) throws IOException 
	{
		System.out.println("ola");
		String loc_pt = "FileDirectory/stoplist.txt";
		String loc_eng = "FileDirectory/stoplistEn.txt";
		CrawlerController cc = new CrawlerController();
		
		cc.start(loc_pt,loc_eng);
		cc.reader();
		cc.adicionandoOsEnderecosParaOsDrones();
		cc.iniciandoAsThreads();
		
		//cc.reader();
		//System.out.println("Estabelecendo conexão...");
	}

}
