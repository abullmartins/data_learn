/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.conexao.Conexao;
import br.com.hardsoftware.mercearia.model.Caixa;
import br.com.hardsoftware.mercearia.model.Funcionario;
import br.com.hardsoftware.mercearia.model.Produto;
import br.com.hardsoftware.mercearia.model.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author alyssonkelvim
 */
public class VendaDao {
    
    Connection con = Conexao.getConexao();
    PreparedStatement stm = null, stm2 = null;
    ResultSet rs = null, rs2 = null;
    
    public ArrayList<Venda> findAll() {
        try {
            ArrayList<Venda> array = new ArrayList();
            stm = con.prepareStatement("SELECT * FROM tbvenda ");
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                ClienteDao cd = new ClienteDao();
                
                venda.setCliente(cd.findByCod(rs.getInt("tbCliente_cdCliente")));
                venda.setCodVenda(rs.getInt("cdVenda"));
                Date d = new Date (rs.getDate("dtVenda").getTime());
                venda.setDataVenda(d);
                ArrayList<Produto> prodList = listarProdutos(rs.getInt("tbCliente_cdCliente"));
                venda.setProdList(prodList);
                FuncionarioDao fd = new FuncionarioDao();
                Funcionario f = fd.findByCod(rs.getInt("tbFuncionario_cdFuncionario"));
                venda.setVendedor(f);
                venda.setValorTotal(rs.getDouble("valorTotalVenda"));
                array.add(venda);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }
    
    public Venda findByCod(int cod) {
        try {
            ArrayList<Venda> array = new ArrayList();
            stm = con.prepareStatement("SELECT * FROM tbvenda where cdVenda = " + cod);
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                ClienteDao cd = new ClienteDao();
                
                venda.setCliente(cd.findByCod(rs.getInt("tbCliente_cdCliente")));
                venda.setCodVenda(rs.getInt("cdVenda"));
                Date d = new Date (rs.getDate("dtVenda").getTime());
                venda.setDataVenda(d);
                ArrayList<Produto> prodList = listarProdutos(rs.getInt("tbCliente_cdCliente"));
                venda.setProdList(prodList);
                FuncionarioDao fd = new FuncionarioDao();
                Funcionario f = fd.findByCod(rs.getInt("tbFuncionario_cdFuncionario"));
                venda.setVendedor(f);
                venda.setValorTotal(rs.getDouble("valorTotalVenda"));
                return venda;
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }
    
    public ArrayList<Venda> findByCaixa(Caixa c) {
        try {            
            ArrayList<Venda> array = new ArrayList();
            
            stm = con.prepareStatement("SELECT * \n" +
                    "FROM tbvenda v, tbvenda_has_tbfluxo vf, tbfluxo f\n" +
                    "WHERE vf.tbVenda_cdVenda = v.cdVenda and  vf.tbfluxo_cdFluxo = f.cdFluxo and f.cdFluxo = ?");
            stm.setInt(1, c.getCodigo());
            rs = stm.executeQuery();
            while (rs.next()) {
                Venda venda = findByCod(rs.getInt("cdVenda"));
                array.add(venda);
            }
            rs.close(); // Fecha result set
            stm.close(); // Fecha o statement
            return array;
        } catch (Exception e) {
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
        }

        return null;
    }
    
    private ArrayList<Produto> listarProdutos(int codVenda) throws SQLException{
        stm = con.prepareStatement("SELECT * FROM tbprodutovenda where tbVenda_cdVenda = "+ codVenda);
        rs2 = stm.executeQuery();
        ArrayList l = new ArrayList();
        while(rs2.next()){
            ProdutoDao pd = new ProdutoDao();
            Produto p = pd.findByCod(rs2.getInt("tbProduto_cdProduto"));
            p.setQtdProduto(rs2.getInt("qtProduto"));
            p.setPrecoProduto(rs2.getDouble("precoProduto"));
            l.add(p);
        }
        return l;
    }
    
    
     public Venda insertVenda(Venda v) throws SQLException{
        try {
            con.setAutoCommit(false);
            stm = con.prepareStatement("Insert into tbvenda(tbFuncionario_cdFuncionario, tbCliente_cdCliente, dtVenda, valorTotalVenda)"
                    + "values (?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, v.getVendedor().getCodFuncionario());
            if(v.getCliente() != null){
                stm.setInt(2, v.getCliente().getCodCliente());
            }else{
                stm.setInt(2, 0);
            }
            
            long dataVenda = v.getDataVenda().getTime();
            Date data = new Date(dataVenda);
            
            stm.setDate(3, data);
            stm.setDouble(4, v.getValorTotal());
            ArrayList<Produto> lista = v.getProdList();
            stm.executeUpdate();
            ResultSet results = stm.getGeneratedKeys();
            try{
            if (results.next()) 
                v.setCodVenda(results.getInt(1));
            }catch(Exception e){
            }
            for (int i = 0; i < lista.size(); i++) {
                Produto p = lista.get(i);
                stm = con.prepareStatement("Insert into tbprodutovenda(tbProduto_cdProduto, tbVenda_cdVenda, qtProduto, precoProduto)"
                    + "values (?,?,?,?) ");
                stm.setInt(1, p.getCodProduto());
                stm.setInt(2, results.getInt(1));
                stm.setInt(3, p.getQtdProduto());
                stm.setDouble(4, p.getPrecoProduto());
                stm.executeUpdate();
                
                stm = con.prepareStatement("SELECT * FROM tbproduto where cdProduto = ?");
                stm.setInt(1, p.getCodProduto());
                rs = stm.executeQuery();
                int qtd = p.getQtdProduto();
                while(rs.next()){
                    qtd = rs.getInt("qtdProduto");
                    qtd-= p.getQtdProduto();
                    if(qtd < 0){
                        int opc = JOptionPane.showConfirmDialog(null, "Quantidade insuficiente de "+p+" no estoque!\nDeseja Continuar?", "ERRO - Produto em falta no Estoque",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                        if(opc == JOptionPane.NO_OPTION){
                            throw new Exception("Quantidade Insuficiente no Estoque!");
                        }
                        qtd = 0;
                    }  
                }
                stm = con.prepareStatement("UPDATE tbproduto SET qtdProduto = ? WHERE cdProduto = ?");
                stm.setInt(1, qtd);
                stm.setInt(2, p.getCodProduto());
                stm.executeUpdate();
            }
            con.commit();
            stm.close(); // Fecha o statement
            return v;
        } catch (Exception e) {
            con.rollback();
            System.out.println("Erro ao Executar SQL: " + e.getMessage());
            return null;
        }

    }
    
}
