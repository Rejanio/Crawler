package model;

public class Seed {
	
	private String url; 
	private boolean visited;
	
	public Seed(String url, boolean visited) {
		this.url = url;
		this.visited = visited;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
