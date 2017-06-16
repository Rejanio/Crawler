package model;
/**
 * Classe que vai servir como nó que a lista da hashtable irá apontar. E assim fazer a lista inversa
 * @author hprf
 *
 */
public class CentroideInversa {

	private String nome , endereco;
	private int peso, repeticao;
	
	
	
	public CentroideInversa(String nome, String endereco, int peso, int repeticao) {
	
		this.nome = nome;
		this.endereco = endereco;
		this.peso = peso;
		this.repeticao = repeticao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getRepeticao() {
		return repeticao;
	}
	public void setRepeticao(int repeticao) {
		this.repeticao = repeticao;
	}
	
	
}
