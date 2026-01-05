/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.ProdutoDao;
import br.com.hardsoftware.mercearia.model.Categoria;
import br.com.hardsoftware.mercearia.model.Produto;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alysson
 */
public class ModeloTabelaProduto extends JTable {

    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel findAll() {
        ProdutoDao pd = new ProdutoDao();
        ArrayList listaProduto = (ArrayList) pd.findAll();
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome do Produto", "Marca", "Quantidade", "Preço"};
        //Dados Iniciais
        String[][] dados = new String[][]{
            {"", "", "", "", ""}
        };

        JTable tabela = new JTable();
        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabela.setModel(model);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabela);
        this.add(scroll);

        model.removeRow(0);
        Object[] linha = null;
        for (int i = 0; i < listaProduto.size(); i++) {
            Produto p = new Produto();
            p = (Produto) listaProduto.get(i);
            linha = new Object[]{p.getCodProduto(), p, p.getMarcaProduto(), p.getQtdProduto(), p.getPrecoProduto()};
            model.addRow(linha);
        }

        return model;
    }
    
     public DefaultTableModel findByCategoria(Categoria c) {
        ProdutoDao pd = new ProdutoDao();
        ArrayList listaProduto = (ArrayList) pd.findByCategoria(c);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome do Produto", "Marca", "Quantidade", "Preço"};
        //Dados Iniciais
        String[][] dados = new String[][]{
            {"", "", "", "", ""}
        };

        JTable tabela = new JTable();
        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabela.setModel(model);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabela);
        this.add(scroll);

        model.removeRow(0);
        Object[] linha = null;
        for (int i = 0; i < listaProduto.size(); i++) {
            Produto p = new Produto();
            p = (Produto) listaProduto.get(i);
            linha = new Object[]{p.getCodProduto(), p, p.getMarcaProduto(), p.getQtdProduto(), p.getPrecoProduto()};
            model.addRow(linha);
        }

        return model;
    }
     
     public DefaultTableModel findByCod(int cod) {
        ProdutoDao pd = new ProdutoDao();
        ArrayList listaProduto = new ArrayList();
        listaProduto.add(pd.findByCod(cod));
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome do Produto", "Marca", "Quantidade", "Preço"};
        //Dados Iniciais
        String[][] dados = new String[][]{
            {"", "", "", "", ""}
        };

        JTable tabela = new JTable();
        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabela.setModel(model);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabela);
        this.add(scroll);

        model.removeRow(0);
        Object[] linha = null;
        for (int i = 0; i < listaProduto.size(); i++) {
            Produto p = new Produto();
            p = (Produto) listaProduto.get(i);
            linha = new Object[]{p.getCodProduto(), p, p.getMarcaProduto(), p.getQtdProduto(), p.getPrecoProduto()};
            model.addRow(linha);
        }

        return model;
    }
     
     public DefaultTableModel findByName(String nome) {
        ProdutoDao pd = new ProdutoDao();
        ArrayList listaProduto = (ArrayList) pd.findByName(nome);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome do Produto", "Marca", "Quantidade", "Preço"};
        //Dados Iniciais
        String[][] dados = new String[][]{
            {"", "", "", "", ""}
        };

        JTable tabela = new JTable();
        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabela.setModel(model);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabela);
        this.add(scroll);

        model.removeRow(0);
        Object[] linha = null;
        for (int i = 0; i < listaProduto.size(); i++) {
            Produto p = new Produto();
            p = (Produto) listaProduto.get(i);
            linha = new Object[]{p.getCodProduto(), p, p.getMarcaProduto(), p.getQtdProduto(), p.getPrecoProduto()};
            model.addRow(linha);
        }

        return model;
    }
     
     public DefaultTableModel findByNameCategoria( String nome, Categoria c) {
        ProdutoDao pd = new ProdutoDao();
        ArrayList listaProduto = (ArrayList) pd.findByNameCategoria(nome, c);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome do Produto", "Marca", "Quantidade", "Preço"};
        //Dados Iniciais
        String[][] dados = new String[][]{
            {"", "", "", "", ""}
        };

        JTable tabela = new JTable();
        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabela.setModel(model);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabela);
        this.add(scroll);

        model.removeRow(0);
        Object[] linha = null;
        for (int i = 0; i < listaProduto.size(); i++) {
            Produto p = new Produto();
            p = (Produto) listaProduto.get(i);
            linha = new Object[]{p.getCodProduto(), p, p.getMarcaProduto(), p.getQtdProduto(), p.getPrecoProduto()};
            model.addRow(linha);
        }

        return model;
    }
     
     public DefaultTableModel setByList(ArrayList<Produto> listaProduto) {
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome do Produto", "Marca", "Quantidade", "Preço"};
        //Dados Iniciais
        String[][] dados = new String[][]{
            {"", "", "", "", ""}
        };

        JTable tabela = new JTable();
        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabela.setModel(model);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabela);
        this.add(scroll);

        model.removeRow(0);
        Object[] linha = null;
        for (int i = 0; i < listaProduto.size(); i++) {
            Produto p = new Produto();
            p = (Produto) listaProduto.get(i);
            linha = new Object[]{p.getCodProduto(), p, p.getMarcaProduto(), p.getQtdProduto(), p.getPrecoProduto()};
            model.addRow(linha);
        }

        return model;
    }
     

}
