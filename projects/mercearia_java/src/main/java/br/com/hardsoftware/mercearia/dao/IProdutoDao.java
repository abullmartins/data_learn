package br.com.hardsoftware.mercearia.dao;

import java.util.List;

import br.com.hardsoftware.mercearia.model.Produto;

public interface IProdutoDao {
	
	public Produto insert (Produto p);
	public Produto alter (Produto p);
	//public boolean delete (Produto p);
	public List<Produto> findAll();
	public List<Produto> findByName (String nome);
	public Produto findByCod (int cod);
	public Produto findByCodBarras (String codBarras);
	
}
