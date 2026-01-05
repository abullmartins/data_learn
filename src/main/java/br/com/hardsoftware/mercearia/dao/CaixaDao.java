/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.conexao.Conexao;
import br.com.hardsoftware.mercearia.model.Caixa;
import br.com.hardsoftware.mercearia.model.Funcionario;
import br.com.hardsoftware.mercearia.model.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author alyssonkelvim
 */
public class CaixaDao {
    
    static Connection con = Conexao.getConexao();
    static PreparedStatement stm = null;
    static ResultSet rs = null;
    
    public static double getUltimoSaldo() {
        try {
            stm = con.prepareStatement("SELECT * FROM tbfluxo order by cdFLuxo desc limit 1");
            rs = stm.executeQuery();
            double retorno = 0;
            while (rs.next()) {
                retorno = rs.getDouble("SaldoFluxo");
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return retorno;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return 0;
    }
    
    public ArrayList<Caixa> findAll() {
        try {
            ArrayList<Caixa> array = new ArrayList();
            stm = con.prepareStatement("SELECT * FROM tbfluxo ");
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                Caixa c = new Caixa();
                Calendar ca = Calendar.getInstance();
                c.setCodigo(rs.getInt("cdFluxo"));
                
                java.sql.Timestamp timestamp;
                timestamp = java.sql.Timestamp.valueOf(rs.getString("abertura"));  
                Date data = new Date(timestamp.getTime()) ;
                ca.setTime(data);
                c.setDataAbertura(ca);
                
                Calendar ca2 = Calendar.getInstance();
                
                timestamp = java.sql.Timestamp.valueOf(rs.getString("fechamento"));  
                data = new Date(timestamp.getTime());
                ca2.setTime(data);
                c.setDataFechamento(ca2);
                
                c.setSaldo(rs.getDouble("saldoFluxo"));
                FuncionarioDao fd = new FuncionarioDao();
                Funcionario f = fd.findByCod(rs.getInt("cdFuncionario"));
                c.setFuncionario(f);
                
                array.add(c);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }
   
    public static boolean insert(Caixa c) throws SQLException{
        
        try {
            con.setAutoCommit(false);
            stm = con.prepareStatement("Insert into tbfluxo(ValorFluxo, SaldoFluxo, abertura, fechamento, cdFuncionario)"
                    + "values (?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            stm.setDouble(1, c.getValor());
            stm.setDouble(2, c.getSaldo());
            
            
            Calendar ca = c.getDataAbertura();
            String data = ca.get(Calendar.YEAR) +"/" +ca.get(Calendar.MONTH) +"/"+ ca.get(Calendar.DAY_OF_MONTH) + "" ;
            String hora = ca.get(Calendar.HOUR_OF_DAY) + ":" + ca.get(Calendar.MINUTE) +":" +ca.get(Calendar.SECOND) ;
            stm.setString(3, data +" "+ hora);
            
            ca = c.getDataFechamento();
            data = ca.get(Calendar.YEAR) +"/" +ca.get(Calendar.MONTH) +"/"+ ca.get(Calendar.DAY_OF_MONTH) + "" ;
            hora = ca.get(Calendar.HOUR_OF_DAY) + ":" + ca.get(Calendar.MINUTE) +":" +ca.get(Calendar.SECOND) ;
            
            stm.setString(4, data +" "+ hora);
            stm.setInt(5, c.getFuncionario().getCodFuncionario());
            ArrayList<Venda> lista = c.getVendas();
            stm.executeUpdate();
            ResultSet results = stm.getGeneratedKeys();
            try{
            if (results.next()) 
                c.setCodigo(results.getInt(1));
            }catch(Exception e){
            }
            for (int i = 0; i < lista.size(); i++) {
                Venda v = lista.get(i);
                stm = con.prepareStatement("Insert into tbvenda_has_tbfluxo(tbVenda_cdVenda,tbfluxo_cdFluxo)"
                    + "values (?,?) ");
                stm.setInt(1, v.getCodVenda());
                stm.setInt(2, c.getCodigo());
                stm.executeUpdate();
            }
            con.commit();
            stm.close(); // Fecha o statement
            return true;
        } catch (Exception e) {
            con.rollback();
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
            return false;
        }
    }
    
    
}
