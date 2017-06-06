package model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import util.FileParser;
import util.XMLParser;

public class CrawlerController 
{
	
	private XMLParser xparser;
	private FileParser fparser;
	
	@SuppressWarnings("unused")
	private Spider spider;
	private List<String> stoplist = new LinkedList<String>();
	private List<String> urls = new LinkedList<String>();
	@SuppressWarnings("unused")
	private List<CrawlerDrone> drones = new LinkedList<CrawlerDrone>();
	private int tamanho = 10;
	
	public CrawlerController() throws IOException
	{
		
		this.xparser = new XMLParser();
		fparser = new FileParser();
		this.spider = new Spider();
	}
	
	/**Tudo que for necessário para montagem do crawler e funcionamento do mesmo
	  deve estar aqui
	 * @throws IOException 
	 */
	
	public void start(String loc1,String loc2) throws IOException
	{
		urls.addAll(xparser.parsing());
		this.stoplist.addAll(fparser.parsing(loc1));
		this.stoplist.addAll(fparser.parsing(loc2));
		
	}
	
	
	//iniciando as threads  a partir das listas de CrawlerDrone com seus endereços 
		public void iniciandoAsThreads() throws IOException //método que vai jogar os métodos para os Drones
	{
		// iniciando o
			for (int i =0 ; i < urls.size() ; i++){
				
				CrawlerDrone temp =(CrawlerDrone)drones.get(i);
				temp.inicio();
				Thread thread = new Thread(temp);
				thread.start();
				
			}
			
	}
		
		
	/// já tá passando instanciando i drone e passando o endereço
	public void adicionandoOsEnderecosParaOsDrones(){
		
		for(int i=0; i<urls.size(); i++ ){
			CrawlerDrone temp = new CrawlerDrone(urls.get(i));
			
			drones.add(temp);
			System.out.println("Passando os endereço: " +temp.getEndereco());
		}
	}
	
	public void reader(){
		for(int i = 0; i < urls.size(); i++){
			System.out.println(urls.get(i));
		}
	}
}	
