/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.conexao.Conexao;
import br.com.hardsoftware.mercearia.model.Categoria;
import br.com.hardsoftware.mercearia.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Alysson
 */
public class ProdutoDao implements IProdutoDao {

    Connection con = Conexao.getConexao();
    PreparedStatement stm = null;
    ResultSet rs = null;

    public Produto insert(Produto p) {

        try {
            String sql = "insert into tbproduto("
                    + "nmProduto, codigoBarraProduto, marcaProduto,"
                    + " precoProduto, tbCategoria_CdCategoria, descricaoProduto, "
                    + "qtdProduto) "
                    + "values (?,?,?,?,?,?,?);";

            stm = con.prepareStatement(sql);

            stm.setString(1, p.getNmProduto()); // ('posi��o da interroga��o que quer substituir', valor )
            stm.setString(2, p.getCodigoBarraProduto());
            stm.setString(3, p.getMarcaProduto());
            stm.setString(4, p.getPrecoProduto() + "");
            stm.setString(5, p.getCategoria().getCdCategoria() + "");
            stm.setString(6, p.getDescricaoProduto());
            stm.setString(7, p.getQtdProduto() + "");
            stm.execute();

            sql = "select * from tbproduto order by cdProduto desc limit 1";
            stm = con.prepareStatement(sql);
            stm.execute(); // Executa a SQL
            rs = stm.executeQuery();
            int cdProduto = 1000;
            while(rs.next()){
                cdProduto = rs.getInt("cdProduto");
            }
            sql = "insert into tbprodutounidade(tbproduto_cdProduto, tbunidade_cdUnidade, valorProdutoUnidade)"
                    + "values (?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setInt(1, cdProduto);
            stm.setInt(2, p.getUnidadeProduto().getCodUnidade());
            stm.setDouble(3, p.getValorProduto());

            stm.execute();

            stm.close(); // Fecha o statement
            return p;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Produto alter(Produto p) {
        try {
            String sql = "update tbproduto set " + "nmProduto = ?, " + "codigoBarraProduto = ? ," + "marcaProduto = ? ,"
                    + "precoProduto = ?, " + " tbCategoria_CdCategoria = ? ," + " descricaoProduto = ?, " + " qtdProduto = ? where cdProduto = ?" ;

            stm = con.prepareStatement(sql);

            stm.setString(1, p.getNmProduto()); // ('posi��o da interroga��o que quer substituir', valor )
            stm.setString(2, p.getCodigoBarraProduto());
            stm.setString(3, p.getMarcaProduto());
            stm.setString(4, p.getPrecoProduto() + "");
            stm.setString(5, p.getCategoria().getCdCategoria() + "");
            stm.setString(6, p.getDescricaoProduto());
            stm.setString(7, p.getQtdProduto() + "");
            stm.setInt(8, p.getCodProduto());
            
            stm.execute();
            stm.close();
            
            sql = "update tbprodutounidade "
                    + "set tbunidade_cdUnidade = ?, "
                    + "valorProdutoUnidade = ?"
                    + " where tbproduto_cdProduto = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(3, p.getCodProduto());
            stm.setInt(1, p.getUnidadeProduto().getCodUnidade());
            stm.setDouble(2, p.getValorProduto());

            stm.execute();

            stm.close(); // Fecha o statement

            return p;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public List<Produto> findAll() {
        try {

            ArrayList<Produto> array = new ArrayList<Produto>();
            stm = con.prepareStatement("select * from tbproduto");
            rs = stm.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                CategoriaDao cd = new CategoriaDao();
                UnidadeProdutoDao ud = new UnidadeProdutoDao();
                produto.setCodProduto(rs.getInt("cdProduto"));
                produto.setNmProduto(rs.getString("nmProduto"));
                produto.setCodigoBarraProduto(rs.getString("codigoBarraProduto"));
                produto.setMarcaProduto(rs.getString("marcaProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setCategoria( cd.findByCod(rs.getInt("tbCategoria_cdCategoria")));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setValorProduto(ud.findValor(produto));
                produto.setUnidadeProduto(ud.findUnidade(produto));
                array.add(produto);
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
    public List<Produto> findByName(String nome) {
        try {

            ArrayList<Produto> lista = new ArrayList<Produto>();
            stm = con.prepareStatement("select * from tbproduto where nmproduto like  ?");
            nome = "%" + nome + "%";
            stm.setString(1, nome);
            stm.execute();
            rs = stm.executeQuery();
            Produto produto = new Produto();
            while (rs.next()) {
                produto = new Produto();
                CategoriaDao cd = new CategoriaDao();
                UnidadeProdutoDao ud = new UnidadeProdutoDao();
                produto.setCodProduto(rs.getInt("cdProduto"));
                produto.setNmProduto(rs.getString("nmProduto"));
                produto.setCodigoBarraProduto(rs.getString("codigoBarraProduto"));
                produto.setMarcaProduto(rs.getString("marcaProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setCategoria( cd.findByCod(rs.getInt("tbCategoria_cdCategoria")));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setValorProduto(ud.findValor(produto));
                produto.setUnidadeProduto(ud.findUnidade(produto));
                lista.add(produto);
            }

            rs.close();
            stm.close();

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    public List<Produto> findByVenda(String nome) {
        try {

            ArrayList<Produto> lista = new ArrayList<Produto>();
            stm = con.prepareStatement("select * from tbproduto where nmproduto like  ?");
            nome = "%" + nome + "%";
            stm.setString(1, nome);
            stm.execute();
            rs = stm.executeQuery();
            Produto produto = new Produto();
            while (rs.next()) {
                produto = new Produto();
                CategoriaDao cd = new CategoriaDao();
                UnidadeProdutoDao ud = new UnidadeProdutoDao();
                produto.setCodProduto(rs.getInt("cdProduto"));
                produto.setNmProduto(rs.getString("nmProduto"));
                produto.setCodigoBarraProduto(rs.getString("codigoBarraProduto"));
                produto.setMarcaProduto(rs.getString("marcaProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setCategoria( cd.findByCod(rs.getInt("tbCategoria_cdCategoria")));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setValorProduto(ud.findValor(produto));
                produto.setUnidadeProduto(ud.findUnidade(produto));
                lista.add(produto);
            }

            rs.close();
            stm.close();

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Produto findByCod(int cod) {
        try {

            stm = con.prepareStatement("select * from tbproduto where cdproduto = ?");
            stm.setInt(1, cod);
            stm.execute();
            rs = stm.executeQuery();
            Produto produto = new Produto();

            while (rs.next()) {
                produto = new Produto();
                CategoriaDao cd = new CategoriaDao();
                UnidadeProdutoDao ud = new UnidadeProdutoDao();
                produto.setCodProduto(rs.getInt("cdProduto"));
                produto.setNmProduto(rs.getString("nmProduto"));
                produto.setCodigoBarraProduto(rs.getString("codigoBarraProduto"));
                produto.setMarcaProduto(rs.getString("marcaProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setCategoria( cd.findByCod(rs.getInt("tbCategoria_cdCategoria")));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setValorProduto(ud.findValor(produto));
                produto.setUnidadeProduto(ud.findUnidade(produto));
            }

            rs.close();
            stm.close();

            return produto;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Produto findByCodBarras(String codBarras) {
        try {

            stm = con.prepareStatement("select * from tbproduto where codigoBarraProduto like ?");
            stm.setString(1, codBarras);
            stm.execute();
            rs = stm.executeQuery();
            Produto produto = new Produto();

            while (rs.next()) {
                produto = new Produto();
                CategoriaDao cd = new CategoriaDao();
                UnidadeProdutoDao ud = new UnidadeProdutoDao();
                produto.setCodProduto(rs.getInt("cdProduto"));
                produto.setNmProduto(rs.getString("nmProduto"));
                produto.setCodigoBarraProduto(rs.getString("codigoBarraProduto"));
                produto.setMarcaProduto(rs.getString("marcaProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setCategoria( cd.findByCod(rs.getInt("tbCategoria_cdCategoria")));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setValorProduto(ud.findValor(produto));
                produto.setUnidadeProduto(ud.findUnidade(produto));

            }

            rs.close();
            stm.close();

            return produto;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    public ArrayList<Produto> findByCategoria(Categoria categoria) {
        try {
            ArrayList lista = new ArrayList();
            stm = con.prepareStatement("select * from tbproduto where tbCategoria_cdCategoria = ?");
            stm.setInt(1, categoria.getCdCategoria());
            rs = stm.executeQuery();
            Produto produto = new Produto();

            while (rs.next()) {
                
                produto = new Produto();
                CategoriaDao cd = new CategoriaDao();
                UnidadeProdutoDao ud = new UnidadeProdutoDao();
                produto.setCodProduto(rs.getInt("cdProduto"));
                produto.setNmProduto(rs.getString("nmProduto"));
                produto.setCodigoBarraProduto(rs.getString("codigoBarraProduto"));
                produto.setMarcaProduto(rs.getString("marcaProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setCategoria( cd.findByCod(rs.getInt("tbCategoria_cdCategoria")));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setValorProduto(ud.findValor(produto));
                produto.setUnidadeProduto(ud.findUnidade(produto));
                lista.add(produto);
            }

            rs.close();
            stm.close();

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public ArrayList<Produto> findByNameCategoria(String nome, Categoria categoria) {
        try {
            ArrayList lista = new ArrayList();
            stm = con.prepareStatement("select * from tbproduto where tbCategoria_cdCategoria = ? and nmproduto like  ? ");
            stm.setInt(1, categoria.getCdCategoria());
            nome = "%"+nome+"%";
            stm.setString(2, nome);
            rs = stm.executeQuery();
            Produto produto = new Produto();

            while (rs.next()) {
                
                produto = new Produto();
                CategoriaDao cd = new CategoriaDao();
                UnidadeProdutoDao ud = new UnidadeProdutoDao();
                produto.setCodProduto(rs.getInt("cdProduto"));
                produto.setNmProduto(rs.getString("nmProduto"));
                produto.setCodigoBarraProduto(rs.getString("codigoBarraProduto"));
                produto.setMarcaProduto(rs.getString("marcaProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setCategoria( cd.findByCod(rs.getInt("tbCategoria_cdCategoria")));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setValorProduto(ud.findValor(produto));
                produto.setUnidadeProduto(ud.findUnidade(produto));
                lista.add(produto);
            }

            rs.close();
            stm.close();

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public void delete(Produto p) {
        try {
            String sql = "DELETE FROM `tbprodutounidade` WHERE `tbprodutounidade`.`tbProduto_cdProduto` = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, p.getCodProduto());
            stm.execute();
            stm.close();
            
            stm = con.prepareStatement("delete from tbproduto where cdProduto = ?");
            stm.setInt(1, p.getCodProduto());
            stm.execute();
            stm.close();
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao Executar SQL: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<Produto> findAllOrderByQtd() {
        try {

            ArrayList<Produto> array = new ArrayList<Produto>();
            stm = con.prepareStatement("select * from tbproduto order by qtdProduto");
            rs = stm.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                CategoriaDao cd = new CategoriaDao();
                UnidadeProdutoDao ud = new UnidadeProdutoDao();
                produto.setCodProduto(rs.getInt("cdProduto"));
                produto.setNmProduto(rs.getString("nmProduto"));
                produto.setCodigoBarraProduto(rs.getString("codigoBarraProduto"));
                produto.setMarcaProduto(rs.getString("marcaProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setCategoria( cd.findByCod(rs.getInt("tbCategoria_cdCategoria")));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produto.setQtdProduto(rs.getInt("qtdProduto"));
                produto.setValorProduto(ud.findValor(produto));
                produto.setUnidadeProduto(ud.findUnidade(produto));
                array.add(produto);
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
