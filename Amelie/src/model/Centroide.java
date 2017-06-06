/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Centroide {
private String conteudo;
private int peso;
private int repeticao;
private String endereco;

    public Centroide(String conteudo, int peso, int repeticao,String endereco) {
        this.conteudo = conteudo;
        this.peso = peso;
        this.repeticao = repeticao;
        this.endereco=endereco;
        
    }
    
    
    
    public String getEndereco() {
		return endereco;
	}



	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}



	public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = this.peso+peso;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = this.repeticao +repeticao;
    }
}