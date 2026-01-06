/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.conexao.Conexao;
import br.com.hardsoftware.mercearia.model.Categoria;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author erbert
 */
public class CategoriaDao {

    Connection con = Conexao.getConexao();
    PreparedStatement stm = null;
    ResultSet rs = null;

    public Categoria insert(Categoria cat) {
        try {

            String sql = "insert into tbcategoria( nmCategoria, descricaoCategoria) values (?, ?);";

            stm = con.prepareStatement(sql);
            stm.setString(1, cat.getNmCategoria());
            stm.setString(2, cat.getDescricaoCategoria());

            stm.execute();
            stm.close();

            return cat;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public Categoria alter(Categoria cat) {
        try {
            String sql = "update tbcategoria set " + "nmCategoria = ? " + ", descricaoCategoria = ? where cdCategoria = ? ";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, cat.getNmCategoria());
            stm.setString(2, cat.getDescricaoCategoria());
            stm.setString(3, String.valueOf(cat.getCdCategoria()));
            
            stm.execute();
            stm.close();
            
            return cat;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public boolean delete(Categoria cat){
        boolean deleted = false;
        try{
            String sql = "delete from tbcategoria where cdCategoria = ?";
            stm = con.prepareStatement(sql);
            stm.clearParameters();
            stm.setInt(1, cat.getCdCategoria());
            int result = stm.executeUpdate();
            if(result > 0){
                deleted = true;
            } 
        }catch (Exception e) {
            if(e instanceof MySQLIntegrityConstraintViolationException){
                 JOptionPane.showMessageDialog(null, "Erro ao excluir categoria:\nExistem produtos cadastrados com essa categoria." ,"Erro", JOptionPane.ERROR_MESSAGE);
            }else{
               JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return deleted;
    }

    public ArrayList<Categoria> findAll() {
        try {
            ArrayList<Categoria> array = new ArrayList<Categoria>();
            stm = con.prepareStatement("SELECT * FROM tbcategoria ");
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setCdCategoria(rs.getInt("cdCategoria"));
                categoria.setNmCategoria(rs.getString("nmCategoria"));
                categoria.setDescricaoCategoria(rs.getString("descricaoCategoria"));
                array.add(categoria);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }

    public Categoria findByCod(int cod) {
        try {
            stm = con.prepareStatement("SELECT * FROM tbcategoria where cdCategoria = ?");
            stm.setInt(1, cod);
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            Categoria c = new Categoria();
            while (rs.next()) {
                c = new Categoria();
                c.setCdCategoria(cod);
                c.setDescricaoCategoria(rs.getString("descricaoCategoria"));
                c.setNmCategoria(rs.getString("nmCategoria"));
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return c;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }

}
