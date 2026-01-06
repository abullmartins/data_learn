package br.com.hardsoftware.mercearia.model;

public class Cidade {
	
	private int cdCidade;
	private String nmCidade;
	private Estado estado;
	
	public String toString(){
		return nmCidade;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public int getCdCidade() {
		return cdCidade;
	}
	public void setCdCidade(int cdCidade) {
		this.cdCidade = cdCidade;
	}
	public String getNmCidade() {
		return nmCidade;
	}
	public void setNmCidade(String nmCidade) {
		this.nmCidade = nmCidade;
	}
	
	
}
