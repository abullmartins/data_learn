package br.com.hardsoftware.mercearia.model;

public class UnidadeProduto {
	
	private int codUnidade;
	private String descricaoUnidade;
	
        public String toString(){
            return descricaoUnidade;
        }
	public int getCodUnidade() {
		return codUnidade;
	}
	public void setCodUnidade(int codUnidade) {
		this.codUnidade = codUnidade;
	}
	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}
	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

}
