package br.com.hardsoftware.mercearia.dao;

import java.util.List;

import br.com.hardsoftware.mercearia.model.Cidade;
import br.com.hardsoftware.mercearia.model.Funcionario;

public interface IFuncionarioDao {
	
	public Funcionario insert (Funcionario f);
	public Funcionario alter (Funcionario f);
	public boolean delete (Funcionario f);
	public List<Funcionario> findAll();
	public List<Funcionario> findByName(String nome);
	public Funcionario findByCod(int cod);
	public Funcionario findByCpf(String cpf);
	public List<Funcionario> findByCity(Cidade cidade);
	public List<Funcionario> findInative();

}
