package view;

import java.io.IOException;
import java.util.Scanner;

import model.CrawlerController;

public class View {
	
	public View()
	{
		
	}

	public static void main(String[] args) throws IOException 
	{
		//System.out.println("ola");
		String loc_pt = "FileDirectory/stoplist.txt";
		String loc_eng = "FileDirectory/stoplistEn.txt";
		
		// lendo a quantidade de crawler rodando ao mesmo tempo
		System.out.println("Digite a quantidade de crawlers simultaneos: " );
		//Scanner ler = new Scanner(System.in);  
		CrawlerController cc = new CrawlerController(1);
		
		cc.start(loc_pt,loc_eng);
		//cc.reader();
		//cc.adicionandoOsEnderecosParaOsDrones();
		cc.iniciando();
		cc.listaInvertida();
		//cc.listarTermoParaCalculo();
		//cc.reader();
		//System.out.println("Estabelecendo conexão...");
	}

}
