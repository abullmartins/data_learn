package br.com.hardsoftware.mercearia.model;

public class Telefone {
	
	private int cdTelefone;
	private int cdPessoa;
	public int getCdPessoa() {
		return cdPessoa;
	}
	public void setCdPessoa(int cdPessoa) {
		this.cdPessoa = cdPessoa;
	}
	private String numTelefone;
	
	
	public int getCdTelefone() {
		return cdTelefone;
	}
	public void setCdTelefone(int cdTelefone) {
		this.cdTelefone = cdTelefone;
	}
	public String getNumTelefone() {
		return numTelefone;
	}
	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

}
