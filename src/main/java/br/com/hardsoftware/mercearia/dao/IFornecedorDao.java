package br.com.hardsoftware.mercearia.dao;

import java.util.List;

import br.com.hardsoftware.mercearia.model.Cidade;
import br.com.hardsoftware.mercearia.model.Fornecedor;

public interface IFornecedorDao {
	
	public Fornecedor insert (Fornecedor fo);
	public Fornecedor alter (Fornecedor fo);
	public boolean delete (Fornecedor c);
	public List<Fornecedor> findAll();
	public List<Fornecedor> findByName(String nome);
	public Fornecedor findByCod(int cod);
	public Fornecedor findByCpf(String cpf);
	public List<Fornecedor> findByCity(Cidade cidade);
	public List<Fornecedor> findInative();

}
