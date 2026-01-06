package br.com.hardsoftware.mercearia.model;

import java.util.ArrayList;
import java.util.Date;

public class Venda {
	
	private int codVenda;
	private Date dataVenda;
	private ArrayList<Produto> prodList; //Inserir dados da entidade Produto
	private Cliente cliente; //Dados de cliente
	private Funcionario vendedor; //Dados de funcion�rio
        private double valorTotal;
	
        public String toString(){
            return String.valueOf(codVenda);
        }
        
	public int getCodVenda() {
		return codVenda;
	}
	public void setCodVenda(int codVenda) {
		this.codVenda = codVenda;
	}
	
	public ArrayList<Produto> getProdList() {
		return prodList;
	}
	public void setProdList(ArrayList<Produto> prodList) {
		this.prodList = prodList;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Funcionario vendedor) {
		this.vendedor = vendedor;
	}

        public Date getDataVenda() {
            return dataVenda;
        }
        
        public void setDataVenda(Date dataVenda) {
            this.dataVenda = dataVenda;
        }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

}
