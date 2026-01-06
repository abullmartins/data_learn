package br.com.hardsoftware.mercearia.dao;

import java.util.List;

import br.com.hardsoftware.mercearia.model.Cidade;
import br.com.hardsoftware.mercearia.model.Cliente;

public interface IClienteDao {
	
	public Cliente insert (Cliente c);
	public Cliente alter (Cliente c);
	public boolean delete (Cliente c);
	public List<Cliente> findAll();
	public List<Cliente> findByName(String nome);
	public Cliente findByCod(int cod);
	public Cliente findByCpf(String cpf);
	public List<Cliente> findByCity(Cidade cidade);
	public List<Cliente> findInative();

}
