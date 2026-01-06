/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.conexao.Conexao;
import br.com.hardsoftware.mercearia.model.Cidade;
import br.com.hardsoftware.mercearia.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Erbert
 */
public class FornecedorDao implements IFornecedorDao {

    Connection con = Conexao.getConexao();
    PreparedStatement stm = null;
    ResultSet rs = null;

    @Override
    public Fornecedor insert(Fornecedor fo) {
        try {
            String sql = "Insert into tbfornecedor("
                    + "tbCidade_cdCidade, entregaFornecedor, situacaoFornecedor, observFornecedor,"
                    + "nmFornecedor, cpfFornecedor, emailFornecedor, enderecoFornecedor, telefoneFornecedor, celularFornecedor, cepFornecedor)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            stm = con.prepareStatement(sql);

            stm.setInt(1, fo.getCidadeFornecedor().getCdCidade());
            stm.setBoolean(2, fo.isEntregaFornecedor());
            stm.setString(3, fo.getSituacaoFornecedor());
            stm.setString(4, fo.getObservFornecedor());
            stm.setString(5, fo.getNmFornecedor());
            stm.setString(6, fo.getCnpjFornecedor());
            stm.setString(7, fo.getEmailFornecedor());
            stm.setString(8, fo.getEnderecoFornecedor());
            stm.setString(9, fo.getTelefoneFornecedor());
            stm.setString(10, fo.getCelularFornecedor());
            stm.setString(11, fo.getCepFornecedor());

            stm.execute();
            
            stm.close();

            return fo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Fornecedor alter(Fornecedor fo) {
        try {

            String sql = "update tbfornecedor set " + "tbCidade_cdCidade = ?, " + "entregaFornecedor = ?, " + "situacaoFornecedor = ?, "
                    + "observFornecedor = ?, " + "nmFornecedor = ?, " + "cpfFornecedor = ?, " + "emailFornecedor = ?, " + "enderecoFornecedor = ?, "
                    + "telefoneFornecedor = ?, " + "celularFornecedor = ?," + " cepFornecedor = ? where cdFornecedor = ?"  ;

            stm = con.prepareStatement(sql);

            stm.setInt(1, fo.getCidadeFornecedor().getCdCidade());
            stm.setBoolean(2, fo.isEntregaFornecedor());
            stm.setString(3, fo.getSituacaoFornecedor());
            stm.setString(4, fo.getObservFornecedor());
            stm.setString(5, fo.getNmFornecedor());
            stm.setString(6, fo.getCnpjFornecedor());
            stm.setString(7, fo.getEmailFornecedor());
            stm.setString(8, fo.getEnderecoFornecedor());
            stm.setString(9, fo.getTelefoneFornecedor());
            stm.setString(10, fo.getCelularFornecedor());
            stm.setString(11, fo.getCepFornecedor());
            stm.setInt(12, fo.getCodFornecedor());

            stm.execute();
            stm.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public boolean delete(Fornecedor c) {
        try {

            String sql = "update tbfornecedor set situacaoFornecedor = \"Inativo\" where cdFornecedor = ?";

            stm = con.prepareStatement(sql);
            stm.setInt(1, c.getCodFornecedor());
            stm.execute();
            stm.close();

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List<Fornecedor> findAll() {
        try {

            ArrayList<Fornecedor> array = new ArrayList<Fornecedor>();
            stm = con.prepareStatement("SELECT * FROM tbfornecedor where situacaoFornecedor = \"Ativo\"");
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                CidadeDao cidadeDao = new CidadeDao();
                fornecedor.setCodFornecedor(rs.getInt("cdFornecedor"));
                fornecedor.setEntregaFornecedor(rs.getBoolean("entregaFornecedor"));
                fornecedor.setCidadeFornecedor(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));
                fornecedor.setSituacaoFornecedor(rs.getString("situacaoFornecedor"));
                fornecedor.setObservFornecedor(rs.getString("observFornecedor"));
                fornecedor.setNmFornecedor(rs.getString("nmFornecedor"));
                fornecedor.setCnpjFornecedor(rs.getString("cpfFornecedor"));
                fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                fornecedor.setCelularFornecedor(rs.getString("celularFornecedor"));
                fornecedor.setCepFornecedor(rs.getString("cepFornecedor"));
                array.add(fornecedor);
            }
            rs.close();
            stm.close();

            return array;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public List<Fornecedor> findByName(String nome) {
        try {

            ArrayList<Fornecedor> array = new ArrayList<Fornecedor>();
            stm = con.prepareStatement("SELECT * FROM tbfornecedor where situacaoFornecedor = \"Ativo\" and nmFornecedor like ?");
            nome = "%" + nome + "%";
            stm.setString(1, nome);
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                CidadeDao cidadeDao = new CidadeDao();
                fornecedor.setCodFornecedor(rs.getInt("cdFornecedor"));
                fornecedor.setEntregaFornecedor(rs.getBoolean("entregaFornecedor"));
                fornecedor.setCidadeFornecedor(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));
                fornecedor.setSituacaoFornecedor(rs.getString("situacaoFornecedor"));
                fornecedor.setObservFornecedor(rs.getString("observFornecedor"));
                fornecedor.setNmFornecedor(rs.getString("nmFornecedor"));
                fornecedor.setCnpjFornecedor(rs.getString("cpfFornecedor"));
                fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                fornecedor.setCelularFornecedor(rs.getString("celularFornecedor"));
                fornecedor.setCepFornecedor(rs.getString("cepFornecedor"));

                array.add(fornecedor);
            }
            rs.close();
            stm.close();

            return array;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public List<Fornecedor> findInativeByName(String nome) {
        try {

            ArrayList<Fornecedor> array = new ArrayList<Fornecedor>();
            stm = con.prepareStatement("SELECT * FROM tbfornecedor where situacaoFornecedor = \"Inativo\" and nmFornecedor like ?");
            nome = "%" + nome + "%";
            stm.setString(1, nome);
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                CidadeDao cidadeDao = new CidadeDao();
                fornecedor.setCodFornecedor(rs.getInt("cdFornecedor"));
                fornecedor.setEntregaFornecedor(rs.getBoolean("entregaFornecedor"));
                fornecedor.setCidadeFornecedor(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));
                fornecedor.setSituacaoFornecedor(rs.getString("situacaoFornecedor"));
                fornecedor.setObservFornecedor(rs.getString("observFornecedor"));
                fornecedor.setNmFornecedor(rs.getString("nmFornecedor"));
                fornecedor.setCnpjFornecedor(rs.getString("cpfFornecedor"));
                fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                fornecedor.setCelularFornecedor(rs.getString("celularFornecedor"));
                fornecedor.setCepFornecedor(rs.getString("cepFornecedor"));

                array.add(fornecedor);
            }
            rs.close();
            stm.close();

            return array;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Fornecedor findByCod(int cod) {
        try {

            stm = con.prepareStatement("SELECT * FROM tbfornecedor where situacaoFornecedor = \"Ativo\" and cdFornecedor like ?");
            stm.setInt(1, cod);
            stm.execute();
            rs = stm.executeQuery();
            Fornecedor fornecedor = new Fornecedor();

            while (rs.next()) {
                fornecedor = new Fornecedor();
                CidadeDao cidadeDao = new CidadeDao();
                fornecedor.setCodFornecedor(rs.getInt("cdFornecedor"));
                fornecedor.setEntregaFornecedor(rs.getBoolean("entregaFornecedor"));
                fornecedor.setCidadeFornecedor(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));
                fornecedor.setSituacaoFornecedor(rs.getString("situacaoFornecedor"));
                fornecedor.setObservFornecedor(rs.getString("observFornecedor"));
                fornecedor.setNmFornecedor(rs.getString("nmFornecedor"));
                fornecedor.setCnpjFornecedor(rs.getString("cpfFornecedor"));
                fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                fornecedor.setCelularFornecedor(rs.getString("celularFornecedor"));
                fornecedor.setCepFornecedor(rs.getString("cepFornecedor"));
            }
            rs.close();
            stm.close();

            return fornecedor;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
        public Fornecedor findByCnpj(String cnpj) {
        try {

            stm = con.prepareStatement("SELECT * FROM tbfornecedor where situacaoFornecedor = \"Ativo\" and cpfFornecedor like ?");
            stm.setString(1, cnpj);
            stm.execute();
            rs = stm.executeQuery();
            Fornecedor fornecedor = new Fornecedor();

            while (rs.next()) {
                fornecedor = new Fornecedor();
                CidadeDao cidadeDao = new CidadeDao();
                fornecedor.setCodFornecedor(rs.getInt("cdFornecedor"));
                fornecedor.setEntregaFornecedor(rs.getBoolean("entregaFornecedor"));
                fornecedor.setCidadeFornecedor(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));
                fornecedor.setSituacaoFornecedor(rs.getString("situacaoFornecedor"));
                fornecedor.setObservFornecedor(rs.getString("observFornecedor"));
                fornecedor.setNmFornecedor(rs.getString("nmFornecedor"));
                fornecedor.setCnpjFornecedor(rs.getString("cpfFornecedor"));
                fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                fornecedor.setCelularFornecedor(rs.getString("celularFornecedor"));
                fornecedor.setCepFornecedor(rs.getString("cepFornecedor"));
            }
            rs.close();
            stm.close();

            return fornecedor;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    
    public Fornecedor findInativeByCod(int cod) {
        try {

            stm = con.prepareStatement("SELECT * FROM tbfornecedor where situacaoFornecedor = \"Inativo\" and cdFornecedor like ?");
            stm.setInt(1, cod);
            stm.execute();
            rs = stm.executeQuery();
            Fornecedor fornecedor = new Fornecedor();

            while (rs.next()) {
                fornecedor = new Fornecedor();
                CidadeDao cidadeDao = new CidadeDao();
                fornecedor.setCodFornecedor(rs.getInt("cdFornecedor"));
                fornecedor.setEntregaFornecedor(rs.getBoolean("entregaFornecedor"));
                fornecedor.setCidadeFornecedor(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));
                fornecedor.setSituacaoFornecedor(rs.getString("situacaoFornecedor"));
                fornecedor.setObservFornecedor(rs.getString("observFornecedor"));
                fornecedor.setNmFornecedor(rs.getString("nmFornecedor"));
                fornecedor.setCnpjFornecedor(rs.getString("cpfFornecedor"));
                fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                fornecedor.setCelularFornecedor(rs.getString("celularFornecedor"));
                fornecedor.setCepFornecedor(rs.getString("cepFornecedor"));
            }
            rs.close();
            stm.close();

            return fornecedor;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public Fornecedor findByCpf (String cnpj) {
        try {

            stm = con.prepareStatement("SELECT * FROM tbfornecedor where situacaoFornecedor = \"Ativo\" and cnpjFornecedor like ?");
            stm.setString(1, cnpj);
            stm.execute();
            rs = stm.executeQuery();
            Fornecedor fornecedor = new Fornecedor();

            while (rs.next()) {
                fornecedor = new Fornecedor();
                CidadeDao cidadeDao = new CidadeDao();
                fornecedor.setCodFornecedor(rs.getInt("cdFornecedor"));
                fornecedor.setEntregaFornecedor(rs.getBoolean("entregaFornecedor"));
                fornecedor.setCidadeFornecedor(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));
                fornecedor.setSituacaoFornecedor(rs.getString("situacaoFornecedor"));
                fornecedor.setObservFornecedor(rs.getString("observFornecedor"));
                fornecedor.setNmFornecedor(rs.getString("nmFornecedor"));
                fornecedor.setCnpjFornecedor(rs.getString("cnpjFornecedor"));
                fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                fornecedor.setCelularFornecedor(rs.getString("celularFornecedor"));
                fornecedor.setCepFornecedor(rs.getString("cepFornecedor"));
            }
            rs.close();
            stm.close();

            return fornecedor;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public List<Fornecedor> findByCity(Cidade cidade) {
        try {

            ArrayList<Fornecedor> array = new ArrayList<Fornecedor>();
            stm = con.prepareStatement("SELECT * FROM tbfornecedor where situacaoFornecedor = \"Ativo\" and tbCidade_cdCidade like ?");
            stm.setInt(1, cidade.getCdCidade());
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                CidadeDao cidadeDao = new CidadeDao();
                fornecedor.setCodFornecedor(rs.getInt("cdFornecedor"));
                fornecedor.setEntregaFornecedor(rs.getBoolean("entregaFornecedor"));
                fornecedor.setCidadeFornecedor(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));
                fornecedor.setSituacaoFornecedor(rs.getString("situacaoFornecedor"));
                fornecedor.setObservFornecedor(rs.getString("observFornecedor"));
                fornecedor.setNmFornecedor(rs.getString("nmFornecedor"));
                fornecedor.setCnpjFornecedor(rs.getString("cpfFornecedor"));
                fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                fornecedor.setCelularFornecedor(rs.getString("celularFornecedor"));
                fornecedor.setCepFornecedor(rs.getString("cepFornecedor"));

                array.add(fornecedor);
            }
            rs.close();
            stm.close();

            return array;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public List<Fornecedor> findInative() {
        try {

            ArrayList<Fornecedor> array = new ArrayList<Fornecedor>();
            stm = con.prepareStatement("SELECT * FROM tbfornecedor where situacaoFornecedor = \"Inativo\"");
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                CidadeDao cidadeDao = new CidadeDao();
                fornecedor.setCodFornecedor(rs.getInt("cdFornecedor"));
                fornecedor.setEntregaFornecedor(rs.getBoolean("entregaFornecedor"));
                fornecedor.setCidadeFornecedor(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));
                fornecedor.setSituacaoFornecedor(rs.getString("situacaoFornecedor"));
                fornecedor.setObservFornecedor(rs.getString("observFornecedor"));
                fornecedor.setNmFornecedor(rs.getString("nmFornecedor"));
                fornecedor.setCnpjFornecedor(rs.getString("cpfFornecedor"));
                fornecedor.setEmailFornecedor(rs.getString("emailFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("enderecoFornecedor"));
                fornecedor.setTelefoneFornecedor(rs.getString("telefoneFornecedor"));
                fornecedor.setCelularFornecedor(rs.getString("celularFornecedor"));
                fornecedor.setCepFornecedor(rs.getString("cepFornecedor"));

                array.add(fornecedor);
            }
            rs.close();
            stm.close();

            return array;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
