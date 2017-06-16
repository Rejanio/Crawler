package model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import util.Creator;

/**Classe responsável por fazer a busca das tags envolvidas no projeto
 * foi requerido que buscassemos por Carros Esportivos, porém essa classe
 * funciona de uma maneira que permite a busca de qualquer coisa
 * desde que o documento key_words seja mudado de maneira correta**/

public class CrawlerDrone implements Runnable {
	private int num_Pages = 4;
	private List<Seed> to_Visit = new LinkedList<Seed>();
	private List<String> Visited = new LinkedList<String>();
	public List<String> tags = new LinkedList<String>();
	public List<String> stopList = new LinkedList<String>();
    private List<Centroide> centroide;
    
    private  String endereco;
    private String termo1 = "Carro";
    private String termo2 = "esportivos";
    private String termo3 = "Carros";
    
	private boolean termoAchado = false;
	
    public CrawlerDrone ( String endereco ) {
		tags.add("title");
		this.centroide = new LinkedList <Centroide>(); 
		this.endereco=endereco;
	}
	
    public String getEndereco() {
		return endereco;
	}

    

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}	
	
	//verifica se os termos procurados ('carro' e 'esportivo') estão no título do da página , se tiver coloca  o valor na 
	// variavel termoAchado 
	public void verificadorDeBuscar(String string) throws IOException{
		
		if(centroide == null){
			this.termoAchado = false;
		}
		else{
			this.termoAchado = true;
		}
		
		
		String temp = this.getTitle(this.getEndereco());
		this.classificar(temp, tags.get(0), this.getEndereco());
		

		
		
	}
	
	public boolean getTermoAchado(){
		return this.termoAchado;
	}
	
	//Vai receber uma url e retornar o titulo dela 
	public String getTitle(String url) throws IOException {
			Document doc = Jsoup.connect(url).get();
			return doc.title();
	}
	
	//Vai receber uma url e um termo e vai processar e retornar esse termo
	public String getTermo(String url,String termo) throws IOException{
		
		String temp = new String ();
		Document doc = Jsoup.connect(url).get();
		//Cria um "array" com todas as tags selecionadas
		Elements tags = doc.select(termo);
		
		for(Element x: tags){ 
              	temp = x.text();
			
		}
               
		return temp;
	}
	
    //
	 public void classificar(String texto , String tagAtual,String endereco){
	        
	        @SuppressWarnings("resource")
			Scanner scan = new Scanner(texto);
	        //System.out.println(" \n A tag atual é : " +tagAtual);
		
	        int peso = this.pesoTag(tagAtual);
	        String palavra=null;
	        while(scan.hasNext()){
	  	  
	          palavra = scan.next();
	          
	  	  //System.out.println("variavel palavra é "+palavra);
	  	  
	          if(palavra.equals(" ")){
	              scan.next();
	              //System.out.println("codicao vazia do if");
	          }
	          else{
	              // criando a lista de centroide caso não exista ainda 
	              //System.out.println("dentro do else grande");
	              
	              if(centroide.isEmpty() && (palavra.equals(termo1) || palavra.equals(termo2) || palavra.equals(termo3))){
	                  //System.out.println("Verificando se a lista da centroide ta´ vazia");
	                  //System.out.println("valor de centroide é "+ centroide.size());
	                  
	                  Centroide temp = new Centroide(palavra,peso,1,endereco);
	                  //System.out.println("A palavra classificada atualmente é " +temp.getConteudo());
	                  centroide.add(temp);
	              }
	              //caso já exista a lista verifica se a palavra já existe na lista
	              // caso não exista cria atribuindo os pesos e repetição
	              //caso exista só altera os valores de repetição e peso
	              else{
	                  if(this.buscarPalavra(palavra) == -1 && (palavra.equals(termo1) || palavra.equals(termo2) || palavra.equals(termo3))){
	                      System.out.println("Verificando se a palavra existe e adicionando a palavra : " +palavra);
	                      Centroide temp = new Centroide(palavra,peso,1,endereco);
	                      centroide.add(temp);
	                      
	                  }
	                  else if (this.buscarPalavra(palavra) !=-1  && (palavra.equals(termo1) || palavra.equals(termo2) || palavra.equals(termo3))){
	                      //System.out.println(this.buscarPalavra(palavra));
	                	  Centroide temp2 = (Centroide) centroide.get(this.buscarPalavra(palavra));
	                      temp2.setPeso(peso);
	                      temp2.setRepeticao(1);
	                  }
	              }
	          }
	          
	        }
	        
	        //teste para ver o q tem na lista e seus pesos
	        for (int i = 0; i < centroide.size(); i++){
	            Centroide temp = (Centroide) centroide.get(i);
	            
	         //   System.out.println("a palavra eh : " + temp.getConteudo() + " e seu peso é : " + temp.getPeso() + " e repetições: " + temp.getRepeticao() );
	            
	        }
	        }
	        //Busca uma palavra na lista de centroide, caso não ache retorna -1 
	        // caso ache a palavra retorna a posição na lista
	        public int buscarPalavra(String palavra){
	            int iterador=0;
	            int posicao = -1;
	            Centroide temp=null;
	            while(centroide.size()>iterador){
	                temp = (Centroide) centroide.get(iterador);
	                if(palavra.equals(temp.getConteudo())){
	                    posicao=iterador;
	                    return posicao;
	                }
	                else{
	                    iterador++;
	                }
	            }
	            return posicao;
	        }
	        /**
	         * Método que recebe uma tag e retorna o seu peso
	         * @param tagAtual
	         * @return 
	         */

	        public int pesoTag (String tagAtual){
	            if(tagAtual.equals("title")){
	                return 10;
	            }
	            else if(tagAtual.equals("h1"))
	                return 7;
	            
	            else if(tagAtual.equals("h2"))
	                return 6;
	            
	            else if(tagAtual.equals("h3")|| tagAtual.equals("a"))
	                return 5;
	            
	            else if(tagAtual.equals("h4")|| tagAtual.equals("h5")||tagAtual.equals("h6"))
	                return 4;
	            
	            else if(tagAtual.equals("big") || tagAtual.equals("b") || tagAtual.equals("em") || tagAtual.equals("i") || tagAtual.equals("u") || tagAtual.equals("strong") || tagAtual.equals("strike") || tagAtual.equals("center"))
	                return 3;
	            
	            else if (tagAtual.equals("small") || tagAtual.equals("sub") || tagAtual.equals("sup") || tagAtual.equals("font") || tagAtual.equals("address"))
	                return 2;
	            else
	                return 1;
	            
	            
	        }
		//Pega todos os hyperlinks de uma página. 
		public List<Seed> hyperlinks(Seed seed) throws IOException{
			
			Document doc = Jsoup.connect(seed.getUrl()).get();
			Elements link = doc.select("a[href]");
			int i = 0;
			
			if(seed.isVisited()==false){
				for(Element x: link){
					
					if(i<num_Pages/this.to_Visit.size()){
						Seed aux = new Seed(x.absUrl("href"),false);
						to_Visit.add(aux);
						i++;
					}
					else{
						break;
					}
				}		
			}
			
			return to_Visit;
		}
		

	    public List<Seed> getTo_Visit() {
			return to_Visit;
		}

		public void setTo_Visit(List<Seed> to_Visit) {
			this.to_Visit = to_Visit;
		}

		public List<String> getVisited() {
			return Visited;
		}

		public void setVisited(List<String> visited) {
			Visited = visited;
		}
		
		
		
		public List<Centroide> getCentroide() {
			return centroide;
		}

		public void setCentroide(List<Centroide> centroide) {
			this.centroide = centroide;
		}
		
		

		public List<String> getTags() {
			return tags;
		}

		public void setTags(List<String> tags) {
			this.tags = tags;
		}

    
	/*
	public CrawlerDrone(int numero) {
		this.numero = numero;
	}
	*/
	
	public void run() {
		//for(int i = 0 ; i<100 ;i++)
		//System.out.println("O numero eh da thread eh"  + this.getNumero() +" mas o numero agora eh: " +i);
		
		
	}
 	//e.hyperlinks(e.getTo_Visit().get(i));
	//
	/**
	public void inicio() throws IOException{
		for(int j = 0; j < this.getTags().size(); j++){
		       			   			       		
			//String a = e.getTo_Visit().get(i).getUrl();
    		String b = this.getTags().get(j);
    		this.classificar(this.getTermo(this.endereco,b),b, this.endereco);
    		//e.getTo_Visit().get(i).setVisited(true);    		
		
		}

	}
	**/
	
}

    	
   
	


	


