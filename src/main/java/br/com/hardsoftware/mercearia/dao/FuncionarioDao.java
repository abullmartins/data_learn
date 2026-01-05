package br.com.hardsoftware.mercearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.hardsoftware.mercearia.conexao.Conexao;
import br.com.hardsoftware.mercearia.model.Cidade;
import br.com.hardsoftware.mercearia.model.Funcionario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FuncionarioDao implements IFuncionarioDao {

    Connection con = Conexao.getConexao();
    PreparedStatement stm = null;
    ResultSet rs = null;

    @Override
    public Funcionario insert(Funcionario f) {

        try {
            String sql = "insert into tbfuncionario("
                    + "nmFuncionario, cpfFuncionario, emailFuncionario, rgFuncionario, "
                    + "situacaoFuncionario, enderecoFuncionario, dtNascFuncionario, "
                    + "sexoFuncionario, tbCidade_cdCidade, telefoneFuncionario, celularFuncionario, "
                    + "cargoFuncionario, loginFuncionario, senhaFuncionario, salarioFuncionario, cepFuncionario)"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            stm = con.prepareStatement(sql);
            stm.setString(1, f.getNmFuncionario());
            stm.setString(2, f.getCpfFuncionario());
            stm.setString(3, f.getEmailFuncionario());
            stm.setString(4, f.getRgFuncionario());
            stm.setString(5, f.getSituacaoFuncionario());
            stm.setString(6, f.getEnderecoFuncionario());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date data = new java.sql.Date(format.parse(f.getDtNascFuncionario()).getTime());
            stm.setDate(7, data);

            stm.setString(8, String.valueOf(f.getSexoFuncionario()));
            stm.setInt(9, f.getCidadeFuncionario().getCdCidade());
            stm.setString(10, f.getTelefoneFuncionario());
            stm.setString(11, f.getCelularFuncionario());
            stm.setString(12, f.getCargoFuncionario());
            stm.setString(13, f.getLoginFunconario());
            stm.setString(14, f.getSenhaFuncionario());
            stm.setDouble(15, f.getSalarioFuncionario());
            stm.setString(16, f.getCepFuncionario());

            stm.execute();
            stm.close();

            return f;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param f
     * @return
     */
    public Funcionario alter(Funcionario f) {

        try {
            String sql = "update tbfuncionario set " + "nmFuncionario = ?, " + " cpfFuncionario = ?, "
                    + " emailFuncionario = ?, " + " rgFuncionario = ?, " + " situacaoFuncionario = ?, "
                    + " enderecoFuncionario = ?, " + " dtNascFuncionario = ?, " + " sexoFuncionario = ?, "
                    + " tbCidade_cdCidade = ?, " + " telefoneFuncionario = ?, " + " celularFuncionario = ?, "
                    + " cargoFuncionario = ?, " + " loginFuncionario = ?, " + " senhaFuncionario = ?, "
                    + " salarioFuncionario = ?, " + " cepFuncionario = ? " + " where cdFuncionario = ? ";

            stm = con.prepareStatement(sql);

            stm.setString(1, f.getNmFuncionario());
            stm.setString(2, f.getCpfFuncionario());
            stm.setString(3, f.getEmailFuncionario());
            stm.setString(4, f.getRgFuncionario());
            stm.setString(5, f.getSituacaoFuncionario());
            stm.setString(6, f.getEnderecoFuncionario());
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date data = new java.sql.Date(format.parse(f.getDtNascFuncionario()).getTime());
            stm.setDate(7, data);
            
            
            stm.setString(8, String.valueOf(f.getSexoFuncionario()));
            stm.setInt(9, f.getCidadeFuncionario().getCdCidade());
            stm.setString(10, f.getTelefoneFuncionario());
            stm.setString(11, f.getCelularFuncionario());
            stm.setString(12, f.getCargoFuncionario());
            stm.setString(13, f.getLoginFunconario());
            stm.setString(14, f.getSenhaFuncionario());
            stm.setDouble(15, f.getSalarioFuncionario());
            stm.setString(16, f.getCepFuncionario());
            stm.setInt(17, f.getCodFuncionario());

            stm.execute();
            stm.close();

            return f;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
       
    }

    @Override
    public boolean delete(Funcionario f) {
        try {
            String sql = "update tbfuncionario set situacaoFuncionario = \"Inativo\" where cdFuncionario = ?";

            stm = con.prepareStatement(sql);
            stm.setInt(1, f.getCodFuncionario());
            stm.execute();
            stm.close();

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public List<Funcionario> findAll() {

        try {
            ArrayList<Funcionario> array = new ArrayList<Funcionario>();
            stm = con.prepareStatement("SELECT * FROM tbfuncionario where situacaoFuncionario = \"Ativo\"");
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();
                CidadeDao cidadeDao = new CidadeDao();

                funcionario.setNmFuncionario(rs.getString("nmFuncionario"));
                funcionario.setCodFuncionario(rs.getInt("cdFuncionario"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setCidadeFuncionario(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));

                Date data = rs.getDate("dtNascFuncionario");
                SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                funcionario.setDtNascFuncionario("" + d.format(data));

                funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
                funcionario.setRgFuncionario(rs.getString("rgFuncionario"));
                funcionario.setSexoFuncionario(rs.getString("sexoFuncionario").charAt(0));
                funcionario.setTelefoneFuncionario(rs.getString("telefoneFuncionario"));
                funcionario.setCelularFuncionario(rs.getString("celularFuncionario"));
                funcionario.setCargoFuncionario(rs.getString("cargoFuncionario"));
                funcionario.setLoginFunconario(rs.getString("loginFuncionario"));
                funcionario.setSenhaFuncionario(rs.getString("senhaFuncionario"));
                funcionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                funcionario.setSituacaoFuncionario(rs.getString("situacaoFuncionario"));
                funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
                funcionario.setCepFuncionario(rs.getString("cepFuncionario"));

                array.add(funcionario);
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
    public List<Funcionario> findByName(String nome) {

        try {
            ArrayList<Funcionario> array = new ArrayList<Funcionario>();
            stm = con.prepareStatement(
                    "SELECT * FROM tbfuncionario where situacaoFuncionario = \"Ativo\" and nmFuncionario like ?");
            nome = "%" + nome + "%";
            stm.setString(1, nome);
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();
                CidadeDao cidadeDao = new CidadeDao();

                funcionario.setNmFuncionario(rs.getString("nmFuncionario"));
                funcionario.setCodFuncionario(rs.getInt("cdFuncionario"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setCidadeFuncionario(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));

                Date data = rs.getDate("dtNascFuncionario");
                SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                funcionario.setDtNascFuncionario("" + d.format(data));

                funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
                funcionario.setRgFuncionario(rs.getString("rgFuncionario"));
                funcionario.setSexoFuncionario(rs.getString("sexoFuncionario").charAt(0));
                funcionario.setTelefoneFuncionario(rs.getString("telefoneFuncionario"));
                funcionario.setCelularFuncionario(rs.getString("celularFuncionario"));
                funcionario.setCargoFuncionario(rs.getString("cargoFuncionario"));
                funcionario.setLoginFunconario(rs.getString("loginFuncionario"));
                funcionario.setSenhaFuncionario(rs.getString("senhaFuncionario"));
                funcionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                funcionario.setSituacaoFuncionario(rs.getString("situacaoFuncionario"));
                funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
                funcionario.setCepFuncionario(rs.getString("cepFuncionario"));

                array.add(funcionario);
            }
            rs.close();
            stm.close();
            return array;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }


    public List<Funcionario> findInativeByName(String nome) {

        try {
            ArrayList<Funcionario> array = new ArrayList<Funcionario>();
            stm = con.prepareStatement(
                    "SELECT * FROM tbfuncionario where situacaoFuncionario = \"Inativo\" and nmFuncionario like ?");
            nome = "%" + nome + "%";
            stm.setString(1, nome);
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();
                CidadeDao cidadeDao = new CidadeDao();

                funcionario.setNmFuncionario(rs.getString("nmFuncionario"));
                funcionario.setCodFuncionario(rs.getInt("cdFuncionario"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setCidadeFuncionario(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));

                Date data = rs.getDate("dtNascFuncionario");
                SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                funcionario.setDtNascFuncionario("" + d.format(data));

                funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
                funcionario.setRgFuncionario(rs.getString("rgFuncionario"));
                funcionario.setSexoFuncionario(rs.getString("sexoFuncionario").charAt(0));
                funcionario.setTelefoneFuncionario(rs.getString("telefoneFuncionario"));
                funcionario.setCelularFuncionario(rs.getString("celularFuncionario"));
                funcionario.setCargoFuncionario(rs.getString("cargoFuncionario"));
                funcionario.setLoginFunconario(rs.getString("loginFuncionario"));
                funcionario.setSenhaFuncionario(rs.getString("senhaFuncionario"));
                funcionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                funcionario.setSituacaoFuncionario(rs.getString("situacaoFuncionario"));
                funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
                funcionario.setCepFuncionario(rs.getString("cepFuncionario"));

                array.add(funcionario);
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
    public Funcionario findByCod(int cod) {

        try {
            stm = con.prepareStatement(
                    "SELECT * FROM tbfuncionario where situacaoFuncionario = \"Ativo\" and cdFuncionario like ?");
            stm.setInt(1, cod);
            stm.execute();
            rs = stm.executeQuery();
            Funcionario funcionario = new Funcionario();

            while (rs.next()) {

                funcionario = new Funcionario();
                CidadeDao cidadeDao = new CidadeDao();

                funcionario.setNmFuncionario(rs.getString("nmFuncionario"));
                funcionario.setCodFuncionario(rs.getInt("cdFuncionario"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setCidadeFuncionario(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));

                Date data = rs.getDate("dtNascFuncionario");
                SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                funcionario.setDtNascFuncionario("" + d.format(data));

                funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
                funcionario.setRgFuncionario(rs.getString("rgFuncionario"));
                funcionario.setSexoFuncionario(rs.getString("sexoFuncionario").charAt(0));
                funcionario.setTelefoneFuncionario(rs.getString("telefoneFuncionario"));
                funcionario.setCelularFuncionario(rs.getString("celularFuncionario"));
                funcionario.setCargoFuncionario(rs.getString("cargoFuncionario"));
                funcionario.setLoginFunconario(rs.getString("loginFuncionario"));
                funcionario.setSenhaFuncionario(rs.getString("senhaFuncionario"));
                funcionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                funcionario.setSituacaoFuncionario(rs.getString("situacaoFuncionario"));
                funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
                funcionario.setCepFuncionario(rs.getString("cepFuncionario"));
            }
            rs.close();
            stm.close();
            return funcionario;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
     public Funcionario findInativeByCod(int cod) {

        try {
            stm = con.prepareStatement(
                    "SELECT * FROM tbfuncionario where situacaoFuncionario = \"Inativo\" and cdFuncionario like ?");
            stm.setInt(1, cod);
            stm.execute();
            rs = stm.executeQuery();
            Funcionario funcionario = new Funcionario();

            while (rs.next()) {

                funcionario = new Funcionario();
                CidadeDao cidadeDao = new CidadeDao();

                funcionario.setNmFuncionario(rs.getString("nmFuncionario"));
                funcionario.setCodFuncionario(rs.getInt("cdFuncionario"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setCidadeFuncionario(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));

                Date data = rs.getDate("dtNascFuncionario");
                SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                funcionario.setDtNascFuncionario("" + d.format(data));

                funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
                funcionario.setRgFuncionario(rs.getString("rgFuncionario"));
                funcionario.setSexoFuncionario(rs.getString("sexoFuncionario").charAt(0));
                funcionario.setTelefoneFuncionario(rs.getString("telefoneFuncionario"));
                funcionario.setCelularFuncionario(rs.getString("celularFuncionario"));
                funcionario.setCargoFuncionario(rs.getString("cargoFuncionario"));
                funcionario.setLoginFunconario(rs.getString("loginFuncionario"));
                funcionario.setSenhaFuncionario(rs.getString("senhaFuncionario"));
                funcionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                funcionario.setSituacaoFuncionario(rs.getString("situacaoFuncionario"));
                funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
                funcionario.setCepFuncionario(rs.getString("cepFuncionario"));
            }
            rs.close();
            stm.close();
            return funcionario;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Funcionario findByCpf(String cpf) {

        try {

            stm = con.prepareStatement(
                    "SELECT * FROM tbfuncionario where situacaoFuncionario = \"Ativo\" and cpfFuncionario like ?");
            stm.setString(1, cpf);
            stm.execute();
            rs = stm.executeQuery();

            Funcionario funcionario = new Funcionario();

            while (rs.next()) {

                funcionario = new Funcionario();
                CidadeDao cidadeDao = new CidadeDao();

                funcionario.setNmFuncionario(rs.getString("nmFuncionario"));
                funcionario.setCodFuncionario(rs.getInt("cdFuncionario"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setCidadeFuncionario(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));

                Date data = rs.getDate("dtNascFuncionario");
                SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                funcionario.setDtNascFuncionario("" + d.format(data));

                funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
                funcionario.setRgFuncionario(rs.getString("rgFuncionario"));
                funcionario.setSexoFuncionario(rs.getString("sexoFuncionario").charAt(0));
                funcionario.setTelefoneFuncionario(rs.getString("telefoneFuncionario"));
                funcionario.setCelularFuncionario(rs.getString("celularFuncionario"));
                funcionario.setCargoFuncionario(rs.getString("cargoFuncionario"));
                funcionario.setLoginFunconario(rs.getString("loginFuncionario"));
                funcionario.setSenhaFuncionario(rs.getString("senhaFuncionario"));
                funcionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                funcionario.setSituacaoFuncionario(rs.getString("situacaoFuncionario"));
                funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
                funcionario.setCepFuncionario(rs.getString("cepFuncionario"));
            }
            rs.close();
            stm.close();
            return funcionario;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public List<Funcionario> findByCity(Cidade cidade) {
        try {

            ArrayList<Funcionario> array = new ArrayList<Funcionario>();
            stm = con.prepareStatement(
                    "SELECT * FROM tbfuncionario where situacaoFuncionario = \"Ativo\" and tbCidade_cdCidade like ?");
            stm.setInt(1, cidade.getCdCidade());
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();
                CidadeDao cidadeDao = new CidadeDao();

                funcionario.setNmFuncionario(rs.getString("nmFuncionario"));
                funcionario.setCodFuncionario(rs.getInt("cdFuncionario"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setCidadeFuncionario(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));

                Date data = rs.getDate("dtNascFuncionario");
                SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                funcionario.setDtNascFuncionario("" + d.format(data));

                funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
                funcionario.setRgFuncionario(rs.getString("rgFuncionario"));
                funcionario.setSexoFuncionario(rs.getString("sexoFuncionario").charAt(0));
                funcionario.setTelefoneFuncionario(rs.getString("telefoneFuncionario"));
                funcionario.setCelularFuncionario(rs.getString("celularFuncionario"));
                funcionario.setCargoFuncionario(rs.getString("cargoFuncionario"));
                funcionario.setLoginFunconario(rs.getString("loginFuncionario"));
                funcionario.setSenhaFuncionario(rs.getString("senhaFuncionario"));
                funcionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                funcionario.setSituacaoFuncionario(rs.getString("situacaoFuncionario"));
                funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
                funcionario.setCepFuncionario(rs.getString("cepFuncionario"));

                array.add(funcionario);
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
    public List<Funcionario> findInative() {
        try {

            ArrayList<Funcionario> array = new ArrayList<Funcionario>();
            stm = con.prepareStatement("SELECT * FROM tbfuncionario where situacaoFuncionario = \"Inativo\"");
            stm.execute();
            rs = stm.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();
                CidadeDao cidadeDao = new CidadeDao();

                funcionario.setNmFuncionario(rs.getString("nmFuncionario"));
                funcionario.setCodFuncionario(rs.getInt("cdFuncionario"));
                funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                funcionario.setCidadeFuncionario(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));

                Date data = rs.getDate("dtNascFuncionario");
                SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                funcionario.setDtNascFuncionario("" + d.format(data));

                funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
                funcionario.setRgFuncionario(rs.getString("rgFuncionario"));
                funcionario.setSexoFuncionario(rs.getString("sexoFuncionario").charAt(0));
                funcionario.setTelefoneFuncionario(rs.getString("telefoneFuncionario"));
                funcionario.setCelularFuncionario(rs.getString("celularFuncionario"));
                funcionario.setCargoFuncionario(rs.getString("cargoFuncionario"));
                funcionario.setLoginFunconario(rs.getString("loginFuncionario"));
                funcionario.setSenhaFuncionario(rs.getString("senhaFuncionario"));
                funcionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                funcionario.setSituacaoFuncionario(rs.getString("situacaoFuncionario"));
                funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
                funcionario.setCepFuncionario(rs.getString("cepFuncionario"));

                array.add(funcionario);
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
