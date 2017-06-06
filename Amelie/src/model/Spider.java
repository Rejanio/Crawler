package model;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider 
{
		
	  //N�mero de P�ginas para visitar
	  private static final int numtoSearch = 10;
	  //� um set pq n�o haver� repeti��es :) 
	  private Set<String> visited = new HashSet<String>();
	  //Apenas para armazenar, nenhuma magia especial requerida aqui
	  private List<String> toVisit = new LinkedList<String>();
	  
	  //Verifica a exist�ncia da url na lista 
	  @SuppressWarnings("unused")
	private String next(String url)
	  {
		  String nextUrl = url;
		  
		  do{
	        nextUrl= this.toVisit.remove(0);
	      }while(this.visited.contains(nextUrl));
	       this.visited.add(nextUrl);
	       return nextUrl;
	  }

	public Set<String> getVisited() 
	{
		return visited;
	}

	public void setVisited(Set<String> visited) 
	{
		this.visited = visited;
	}

	public List<String> getToVisit() 
	{
		return toVisit;
	}

	public void setToVisit(List<String> toVisit) 
	{
		this.toVisit = toVisit;
	}

	public static int getNumtosearch() 
	{
		return numtoSearch;
	}
	  
	  

}
