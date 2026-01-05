/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.infra.ConnectionFactory;
import br.com.hardsoftware.mercearia.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginDao {

    public static Funcionario login(String usuario, String senha) {
        String sql = "SELECT * FROM tbfuncionario where loginFuncionario=? and senhaFuncionario=?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setString(1, usuario);
            stm.setString(2, senha);
            
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    CidadeDao cidadeDao = new CidadeDao();

                    funcionario.setNmFuncionario(rs.getString("nmFuncionario"));
                    funcionario.setCodFuncionario(rs.getInt("cdFuncionario"));
                    funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
                    funcionario.setCidadeFuncionario(cidadeDao.findByCod(rs.getInt("tbCidade_cdCidade")));

                    Date data = rs.getDate("dtNascFuncionario");
                    if (data != null) {
                        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
                        funcionario.setDtNascFuncionario("" + d.format(data));
                    }

                    funcionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
                    funcionario.setRgFuncionario(rs.getString("rgFuncionario"));
                    String sexo = rs.getString("sexoFuncionario");
                    if (sexo != null && !sexo.isEmpty()) {
                        funcionario.setSexoFuncionario(sexo.charAt(0));
                    }
                    funcionario.setTelefoneFuncionario(rs.getString("telefoneFuncionario"));
                    funcionario.setCelularFuncionario(rs.getString("celularFuncionario"));
                    funcionario.setCargoFuncionario(rs.getString("cargoFuncionario"));
                    funcionario.setLoginFunconario(rs.getString("loginFuncionario"));
                    funcionario.setSenhaFuncionario(rs.getString("senhaFuncionario"));
                    funcionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
                    funcionario.setSituacaoFuncionario(rs.getString("situacaoFuncionario"));
                    funcionario.setEmailFuncionario(rs.getString("emailFuncionario"));
                    funcionario.setCepFuncionario(rs.getString("cepFuncionario"));
                    
                    return funcionario;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing login", e);
        }

        return null;
    }
}
