package br.com.hardsoftware.mercearia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import br.com.hardsoftware.mercearia.conexao.Conexao;
import br.com.hardsoftware.mercearia.model.Cidade;
import br.com.hardsoftware.mercearia.model.Cliente;
import javax.swing.JOptionPane;

public class ClienteDao implements IClienteDao {

    Connection con = Conexao.getConexao();
    PreparedStatement stm = null;
    ResultSet rs = null;

    public Cliente insert(Cliente cliente) {

        try {
            String sql = "insert into tbcliente("
                    + "nmCliente, cpfCliente, emailcliente,"
                    + " rgcliente, situacaoCliente,enderecoCliente, "
                    + "dtNascCliente, sexoCliente, tbCidade_cdCidade, celularCliente, telefoneCliente, cepCliente) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?);";

            stm = con.prepareStatement(sql);

            stm.setString(1, cliente.getNmCliente()); // ('posi��o da interroga��o que quer substituir', valor )
            stm.setString(2, cliente.getCpfCliente());
            stm.setString(3, cliente.getEmailCliente());
            stm.setString(4, cliente.getRg());
            stm.setString(5, cliente.getSituacaoCliente());
            stm.setString(6, cliente.getEndereco());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date data = new java.sql.Date(format.parse(cliente.getDtNascCliente()).getTime()); // Coloca formato certo  na data

            stm.setDate(7, data);
            stm.setString(8, String.valueOf(cliente.getSexoCliente()));
            stm.setInt(9, cliente.getCidadeCliente().getCdCidade()); // Inteiro para o c�digo da cidade, ou seja setInt
            stm.setString(10, cliente.getCelularCliente());
            stm.setString(11, cliente.getTelefoneCliente() );
            stm.setString(12, cliente.getCepCliente());
            stm.execute(); // Executa a SQL

            sql = "select * from tbcliente where cpfCliente = " + cliente.getCpfCliente();
            stm = con.prepareStatement(sql);

            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            stm.close(); // Fecha o statement
            return cliente;

        } catch (Exception e) {

            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }

    public Cliente alter(Cliente cliente) {
        try {
            String sql = "update tbcliente set "
                    + "nmCliente = ?, "
                    + " cpfCliente = ?, "
                    + " emailcliente = ?, "
                    + " rgcliente = ?, "
                    + " situacaoCliente = ?, "
                    + "enderecoCliente = ?, "
                    + " dtNascCliente = ?, "
                    + " sexoCliente = ?, "
                    + " tbCidade_cdCidade = ?, "
                    + " celularCliente = ?, "
                    + " telefoneCliente = ?,"
                    + "cepCliente = ? "
                    + "where cdCliente = ? ";

            stm = con.prepareStatement(sql);

            stm.setString(1, cliente.getNmCliente()); // ('posi��o da interroga��o que quer substituir', valor )
            stm.setString(2, cliente.getCpfCliente());
            stm.setString(3, cliente.getEmailCliente());
            stm.setString(4, cliente.getRg());
            stm.setString(5, cliente.getSituacaoCliente());
            stm.setString(6, cliente.getEndereco());
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date data = new java.sql.Date(format.parse(cliente.getDtNascCliente()).getTime()); // Coloca formato certo  na data
            
            stm.setString(7, data.toString());
            stm.setString(8, String.valueOf(cliente.getSexoCliente()));
            stm.setInt(9, cliente.getCidadeCliente().getCdCidade()); // Inteiro para  o c�digo da cidade, ou seja setInt
            stm.setString(10, cliente.getCelularCliente());
            stm.setString(11, cliente.getTelefoneCliente());
            stm.setString(12, cliente.getCepCliente());
            stm.setInt(13, cliente.getCodCliente());
            
            stm.executeUpdate();
            
            stm.close();	//Fecha o statement

            return cliente;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }
        return null;
    }

    public boolean delete(Cliente cliente) {
        try {
            String sql = "update tbcliente set situacaoCliente = \"Inativo\" where cdcliente = ?";

            stm = con.prepareStatement(sql);
            stm.setInt(1, cliente.getCodCliente());
            stm.execute();  // Executa a SQL
            stm.close();	//Fecha o statement

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }
        return false;
    }

    public List<Cliente> findAll() {
        try {
            ArrayList<Cliente> array = new ArrayList<Cliente>();
            stm = con.prepareStatement("SELECT * FROM tbcliente where situacaoCliente = \"Ativo\" and cdCliente != 0");
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                CidadeDao cidadeDao = new CidadeDao();
                
                cliente.setNmCliente(rs.getString("nmCliente"));
                cliente.setCodCliente(rs.getInt("cdCliente"));
                cliente.setCpfCliente(rs.getString("cpfCliente"));
                cliente.setCidadeCliente(cidadeDao.findByCod(rs.getInt("tbcidade_cdCidade")));
                Date data = rs.getDate("DtNascCliente");
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                cliente.setDtNascCliente("" + f.format(data));//vai te retorna uma String
                cliente.setEmailCliente(rs.getString("emailCliente"));
                cliente.setEndereco(rs.getString("enderecoCliente"));
                cliente.setRg(rs.getString("rgCliente"));
                cliente.setSexoCliente(rs.getString("sexoCliente").charAt(0));
                cliente.setSituacaoCliente(rs.getString("situacaoCliente"));
                cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));                cliente.setCepCliente(rs.getString("cepCliente"));
                
                array.add(cliente);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    public List<Cliente> findByName(String nome) {
        try {
            ArrayList<Cliente> array = new ArrayList<Cliente>();

            stm = con.prepareStatement("SELECT * FROM tbcliente where  situacaoCliente = \"Ativo\" and nmcliente like ? and cdCliente != 0");
            nome = "%" + nome + "%"; //Prepara String para fazer consulta certa
            stm.setString(1, nome);
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                CidadeDao cidadeDao = new CidadeDao();

                cliente.setNmCliente(rs.getString("nmCliente"));
                cliente.setCodCliente(rs.getInt("cdCliente"));
                cliente.setCpfCliente(rs.getString("cpfCliente"));
                cliente.setCidadeCliente(cidadeDao.findByCod(rs.getInt("tbcidade_cdCidaDE")));
                Date data = rs.getDate("DtNascCliente");

                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                cliente.setDtNascCliente("" + f.format(data)); // vai te retornar uma String
                cliente.setEmailCliente(rs.getString("emailCliente"));
                cliente.setEndereco(rs.getString("enderecoCliente"));
                cliente.setRg(rs.getString("rgCliente"));
                cliente.setSexoCliente(rs.getString("sexoCliente").charAt(0));
                cliente.setSituacaoCliente(rs.getString("situacaoCliente"));
                cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));                cliente.setCepCliente(rs.getString("cepCliente"));                cliente.setCepCliente(rs.getString("cepCliente"));
                

                array.add(cliente);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }

    public List<Cliente> findInativeByName(String nome) {
        try {
            ArrayList<Cliente> array = new ArrayList<Cliente>();

            stm = con.prepareStatement("SELECT * FROM tbcliente where  situacaoCliente = \"Inativo\" and nmcliente like ?");
            nome = "%" + nome + "%"; //Prepara String para fazer consulta certa
            stm.setString(1, nome);
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                CidadeDao cidadeDao = new CidadeDao();

                cliente.setNmCliente(rs.getString("nmCliente"));
                cliente.setCodCliente(rs.getInt("cdCliente"));
                cliente.setCpfCliente(rs.getString("cpfCliente"));
                cliente.setCidadeCliente(cidadeDao.findByCod(rs.getInt("tbcidade_cdCidaDE")));
                Date data = rs.getDate("DtNascCliente");

                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                cliente.setDtNascCliente("" + f.format(data)); // vai te retornar uma String

                cliente.setEmailCliente(rs.getString("emailCliente"));
                cliente.setEndereco(rs.getString("enderecoCliente"));
                cliente.setRg(rs.getString("rgCliente"));
                cliente.setSexoCliente(rs.getString("sexoCliente").charAt(0));
                cliente.setSituacaoCliente(rs.getString("situacaoCliente"));
                cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));                 
                cliente.setCepCliente(rs.getString("cepCliente"));

                array.add(cliente);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }
    @Override
    public Cliente findByCod(int cod) {

        try {

            stm = con.prepareStatement("SELECT * FROM tbcliente where  situacaoCliente = \"Ativo\" and cdcliente = ? and cdCliente != 0");
            stm.setInt(1, cod);
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();
            Cliente cliente = new Cliente();
            while (rs.next()) {
                cliente = new Cliente();
                CidadeDao cidadeDao = new CidadeDao();
                cliente.setNmCliente(rs.getString("nmCliente"));
                cliente.setCodCliente(rs.getInt("cdCliente"));
                cliente.setCpfCliente(rs.getString("cpfCliente"));
                cliente.setCidadeCliente(cidadeDao.findByCod(rs.getInt("tbcidade_cdCidaDE")));
                Date data = rs.getDate("DtNascCliente");
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                // vai te retornar uma String
                cliente.setDtNascCliente("" + f.format(data));
                cliente.setEndereco(rs.getString("enderecoCliente"));
                cliente.setRg(rs.getString("rgCliente"));
                cliente.setEmailCliente(rs.getString("emailCliente"));
                cliente.setSexoCliente(rs.getString("sexoCliente").charAt(0));
                cliente.setSituacaoCliente(rs.getString("situacaoCliente"));
                cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));
                cliente.setCepCliente(rs.getString("cepCliente"));
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return cliente;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }
    
    public Cliente findInativeByCod(int cod) {

        try {

            stm = con.prepareStatement("SELECT * FROM tbcliente where  situacaoCliente = \"Inativo\" and cdcliente = ?");
            stm.setInt(1, cod);
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();
            Cliente cliente = new Cliente();
            while (rs.next()) {
                cliente = new Cliente();
                CidadeDao cidadeDao = new CidadeDao();
                cliente.setNmCliente(rs.getString("nmCliente"));
                cliente.setCodCliente(rs.getInt("cdCliente"));
                cliente.setCpfCliente(rs.getString("cpfCliente"));
                cliente.setCidadeCliente(cidadeDao.findByCod(rs.getInt("tbcidade_cdCidaDE")));
                Date data = rs.getDate("DtNascCliente");
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                // vai te retornar uma String
                cliente.setDtNascCliente("" + f.format(data));
                cliente.setEndereco(rs.getString("enderecoCliente"));
                cliente.setRg(rs.getString("rgCliente"));
                cliente.setSexoCliente(rs.getString("sexoCliente").charAt(0));
                cliente.setSituacaoCliente(rs.getString("situacaoCliente"));
                cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));  
                cliente.setCepCliente(rs.getString("cepCliente"));
                cliente.setEmailCliente(rs.getString("emailCliente"));
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return cliente;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Cliente findByCpf(String cpf) {
        try {

            stm = con.prepareStatement("SELECT * FROM tbcliente where  situacaoCliente = \"Ativo\" and cpfcliente = ? and cdCliente != 0");
            stm.setString(1, cpf);
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();
            Cliente cliente = new Cliente();
            while (rs.next()) {
                cliente = new Cliente();
                CidadeDao cidadeDao = new CidadeDao();
                cliente.setNmCliente(rs.getString("nmCliente"));
                cliente.setEmailCliente(rs.getString("emailCliente"));
                cliente.setCodCliente(rs.getInt("cdCliente"));
                cliente.setCpfCliente(rs.getString("cpfCliente"));
                cliente.setCidadeCliente(cidadeDao.findByCod(rs.getInt("tbcidade_cdCidaDE")));
                Date data = rs.getDate("DtNascCliente");
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                // vai te retornar uma String
                cliente.setDtNascCliente("" + f.format(data));
                cliente.setEndereco(rs.getString("enderecoCliente"));
                cliente.setRg(rs.getString("rgCliente"));
                cliente.setSexoCliente(rs.getString("sexoCliente").charAt(0));
                cliente.setSituacaoCliente(rs.getString("situacaoCliente"));
                cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));       
                cliente.setCepCliente(rs.getString("cepCliente"));
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return cliente;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Cliente> findByCity(Cidade cidade) {
        try {
            ArrayList<Cliente> array = new ArrayList<Cliente>();
            stm = con.prepareStatement("SELECT * FROM tbcliente where  situacaoCliente = \"Ativo\" and tbCidade_cdCidade = ? and cdCliente != 0");
            stm.setInt(1, cidade.getCdCidade());
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                CidadeDao cidadeDao = new CidadeDao();
                cliente.setNmCliente(rs.getString("nmCliente"));
                cliente.setCodCliente(rs.getInt("cdCliente"));
                cliente.setCpfCliente(rs.getString("cpfCliente"));
                cliente.setCidadeCliente(cidadeDao.findByCod(rs.getInt("tbcidade_cdCidaDE")));

                Date data = rs.getDate("DtNascCliente");
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                // vai te retornar uma String
                cliente.setDtNascCliente("" + f.format(data));

                cliente.setEmailCliente(rs.getString("emailCliente"));
                cliente.setEndereco(rs.getString("enderecoCliente"));
                cliente.setRg(rs.getString("rgCliente"));
                cliente.setSexoCliente(rs.getString("sexoCliente").charAt(0));
                cliente.setSituacaoCliente(rs.getString("situacaoCliente"));
                cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));   
                cliente.setCepCliente(rs.getString("cepCliente"));
                array.add(cliente);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Cliente> findInative() {
        try {
            ArrayList<Cliente> array = new ArrayList<Cliente>();
            stm = con.prepareStatement("SELECT * FROM tbcliente where  situacaoCliente = \"Inativo\"");
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                CidadeDao cidadeDao = new CidadeDao();
                cliente.setNmCliente(rs.getString("nmCliente"));
                cliente.setCodCliente(rs.getInt("cdCliente"));
                cliente.setCpfCliente(rs.getString("cpfCliente"));
                cliente.setCidadeCliente(cidadeDao.findByCod(rs.getInt("tbcidade_cdCidaDE")));

                Date data = rs.getDate("DtNascCliente");
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                // vai te retornar uma String
                cliente.setDtNascCliente("" + f.format(data));

                cliente.setEndereco(rs.getString("enderecoCliente"));
                cliente.setRg(rs.getString("rgCliente"));
                cliente.setSexoCliente(rs.getString("sexoCliente").charAt(0));
                cliente.setSituacaoCliente(rs.getString("situacaoCliente"));
                cliente.setTelefoneCliente(rs.getString("telefoneCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));   
                cliente.setCepCliente(rs.getString("cepCliente"));
                cliente.setEmailCliente(rs.getString("emailCliente"));
                array.add(cliente);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }

}
