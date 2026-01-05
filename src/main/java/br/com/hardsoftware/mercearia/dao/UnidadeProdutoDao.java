/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.conexao.Conexao;
import br.com.hardsoftware.mercearia.model.Produto;
import br.com.hardsoftware.mercearia.model.UnidadeProduto;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author erbert
 */
public class UnidadeProdutoDao {

    Connection con = Conexao.getConexao();
    PreparedStatement stm = null;
    ResultSet rs = null;

    public UnidadeProduto insert(UnidadeProduto up) {

        try {
            String sql = "insert into tbunidade(descricaoUnidade) value (?);";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, up.getDescricaoUnidade());
            
            stm.execute();
            stm.close();
            
            return up;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public UnidadeProduto alter(UnidadeProduto up){
        
        try{
            String sql = "update tbunidade set " + "descricaoUnidade = ? where cdUnidade = ?";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, up.getDescricaoUnidade());
            stm.setString(2, String.valueOf(up.getCodUnidade()));
            
            stm.execute();
            stm.close();
            
            return up;            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public boolean delete(UnidadeProduto up){
        
        boolean deleted = false;
        try{
            String sql = "DELETE FROM tbunidade WHERE cdUnidade=?";
            
            stm = con.prepareStatement(sql);
            stm.clearParameters();
            stm.setInt(1, up.getCodUnidade());
            int result = stm.executeUpdate();
            if (result > 0){
                deleted = true;
            } 
        }catch (SQLException e) {
            if(e instanceof MySQLIntegrityConstraintViolationException){
                 JOptionPane.showMessageDialog(null, "Erro ao excluir unidade:\nExistem produtos cadastrados com essa unidade." ,"Erro", JOptionPane.ERROR_MESSAGE);
            }else{
               JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return deleted;  
    }

    public ArrayList<UnidadeProduto> findAll() {
        try {
            ArrayList<UnidadeProduto> array = new ArrayList<UnidadeProduto>();
            stm = con.prepareStatement("SELECT * FROM tbunidade ");
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                UnidadeProduto u = new UnidadeProduto();

                u.setCodUnidade(rs.getInt("cdUnidade"));
                u.setDescricaoUnidade(rs.getString("descricaoUnidade"));
                array.add(u);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public UnidadeProduto findUnidade(Produto p) {
        UnidadeProduto u = new UnidadeProduto();
        try {
            stm = con.prepareStatement("SELECT * FROM tbprodutounidade WHERE tbProduto_cdProduto = " + p.getCodProduto());
            rs = stm.executeQuery();
            int cdUnidade = 0;
            while (rs.next()) {
                cdUnidade = rs.getInt("tbUnidade_cdUnidade");
            }

            stm = con.prepareStatement("SELECT * FROM tbunidade WHERE cdUnidade = " + cdUnidade);
            rs = stm.executeQuery();
            while (rs.next()) {
                u = new UnidadeProduto();
                u.setCodUnidade(rs.getInt("cdUnidade"));
                u.setDescricaoUnidade(rs.getString("descricaoUnidade"));
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public double findValor(Produto p) {
        double valor = 0;
        try {
            stm = con.prepareStatement("SELECT * FROM tbprodutounidade WHERE tbProduto_cdProduto = " + p.getCodProduto());
            rs = stm.executeQuery();

            while (rs.next()) {
                valor = rs.getDouble("valorProdutoUnidade");
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

}
