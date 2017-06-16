package model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import util.FileParser;
import util.XMLParser;
import model.CrawlerDrone;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
	private int qtdCrawler = 0;
	private List<Centroide> termoParaCalculo;
	private int tamanhoListaUrls=0;
	Hashtable companies = new Hashtable();
	private List<String> nomesEncotrados = new LinkedList<String>();
	public CrawlerController(int qtdCrawler) throws IOException
	{
		
		this.xparser = new XMLParser();
		fparser = new FileParser();
		this.spider = new Spider();
		this.qtdCrawler= qtdCrawler;
		this.termoParaCalculo = new LinkedList <Centroide>(); 
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
		//pegando a quantidade de links que serão lindos
		this.tamanhoListaUrls = urls.size();
		
	}
	
	//método que vai jogar os métodos para os Drones
	//iniciando as threads  a partir das listas de CrawlerDrone com seus endereços 
	/**
	public void iniciandoAsThreads() throws IOException 
	{
	
			System.out.println("tamanho da urls" + this.tamanhoListaUrls );
			
			for(int x = 0; x < this.tamanhoListaUrls ; x++){
				
				System.out.println("leitura do endereço url " +x);
				for(int i = 0 ; i < this.qtdCrawler; i++){
					System.out.println("Criacao do crawler : "+ i);
					
					CrawlerDrone drone = new CrawlerDrone(urls.get(x));
					Thread thread = new Thread(drone);
					thread.start();
					//drone.verificadorDeBuscar(urls.get(x));
					System.out.println("endereço na url "+ urls.get(x));
					//this.verificandoSeTemOstermos(drone);
					
				}
				
			}
			
	}
	 * @throws IOException 
	**/
	public void iniciando() throws IOException{
		System.out.println("tamanho da urls" + this.tamanhoListaUrls );
		
		for(int x = 0; x < this.tamanhoListaUrls ; x++){
			
				System.out.println("leitura do endereço url "+ x);
				CrawlerDrone drone = new CrawlerDrone(urls.get(x));
				drone.verificadorDeBuscar(urls.get(x));
				System.out.println("endereço na url "+ urls.get(x));
				System.out.println("valor de x: "+x);
				this.verificandoSeTemOstermos(drone);
				
	
		}
	}
		public void listarTermoParaCalculo(){
			System.out.println("entrou aqui");
			System.out.println("termos para calcular " +this.termoParaCalculo.size());
			
			for(int i = 0 ; i < this.termoParaCalculo.size() ; i++){
				Centroide temp = this.termoParaCalculo.get(i);
				System.out.println("Palavra : " + temp.getConteudo() + " repetiu : " + temp.getRepeticao() + " url " + temp.getEndereco());
			}
		}
		
		// se for verdadeiro passar os centroide junto com endereço para a lista de termoParaCalculo que deve ter aqui 
		// para uma posterior construção da lista inversa 
		// adição do termo na hashtable 
		public void verificandoSeTemOstermos(CrawlerDrone crawler){
			System.out.println("AQUI"); 
		for (int i = 0 ; i < crawler.getCentroide().size();i++){	 
			if(this.companies == null){
				Centroide temp = crawler.getCentroide().get(i);
				//this.termoParaCalculo.add(temp);
				//System.out.println("AQUI");
				List <Centroide> tempInversa =  new LinkedList<Centroide>();
				tempInversa.add(temp);
				companies.put(temp.getConteudo(), tempInversa);
				this.nomesEncotrados.add(temp.getConteudo());
			}
			//caso já exista um elemento na hashtable
			else{
				Centroide temp = crawler.getCentroide().get(i);
				// verificando se não existe nenhuma lista contendo os nós com aquela palavra
				if( this.companies.containsKey(temp.getConteudo())==false){
					List <Centroide> tempInversa2 =  new LinkedList<Centroide>();
					tempInversa2.add(temp);
					companies.put(temp.getConteudo(), tempInversa2);
					this.nomesEncotrados.add(temp.getConteudo());
				}
				//quando já existe uma lista com os nós 
				else{
					List <Centroide> tempInversa3 =  (LinkedList)this.companies.get(temp.getConteudo());
					tempInversa3.add(temp);
				}
				
				
			}
			System.out.println("tamanho da lista de nomes adicionados " + this.nomesEncotrados.size());
		}
		
	}
	
		
	//exibir a lista invertida e seu conteúdo	
		//deu um  bug feio na hora de mostrar os nomes na quantidade certa
	public void listaInvertida(){
		
		for(int i = 0; i<this.nomesEncotrados.size(); i++){
			List <Centroide> temp = (LinkedList)this.companies.get(this.nomesEncotrados.get(i));
			
			//System.out.println("tamanho de temp centroide " + temp.get(j).getRepeticao());
			for(int j = 0; j <temp.size();j++){
				System.out.println("Resultado " + temp.get(j).getConteudo() + " repeticoes "+temp.get(j).getRepeticao()+" endereco "+temp.get(j).getEndereco());
			}	
			
	//	List <Centroide> temp = (LinkedList)this.companies.get(this.nomesEncotrados.get(0));
		//System.out.println("o tamanho da lista de Carro "+temp.size());
		
			
		}
	
		System.out.println("tamanho da lista da hash " + this.companies.size());
		
	
	}
		
		
		/**
		/// já tá passando instanciando i drone e passando o endereço
		// a variavel que vai determinar o ' do for é a this.tamanho pq ela que informa quantos crawler estão roda
	public void adicionandoOsEnderecosParaOsDrones(){
		
		for(int i=0; i<this.tamanho; i++ ){
			CrawlerDrone temp = new CrawlerDrone(urls.get(i));
			
			drones.add(temp);
			System.out.println("Passando os endereço: " +temp.getEndereco());
		}
	}
	**/
	public void reader(){
		for(int i = 0; i < urls.size(); i++){
			System.out.println(urls.get(i));
		}
	}
}	
