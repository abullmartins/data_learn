/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.FornecedorDao;
import br.com.hardsoftware.mercearia.model.Fornecedor;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alysson
 */
public class ModeloTabelaFornecedor extends JTable {

    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel findAll() {
        FornecedorDao fo = new FornecedorDao();
        ArrayList listaFornecedor = (ArrayList) fo.findAll();
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CNPJ", "Telefone", "Celular"};
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
        for (int i = 0; i < listaFornecedor.size(); i++) {
            Fornecedor f = new Fornecedor();
            f = (Fornecedor) listaFornecedor.get(i);
            linha = new Object[]{f.getCodFornecedor(), f, f.getCnpjFornecedor(), f.getTelefoneFornecedor(), f.getCelularFornecedor()};
            model.addRow(linha);
        }

        return model;
    }

    public DefaultTableModel findAllInative() {
        FornecedorDao fo = new FornecedorDao();
        ArrayList listaFornecedor = (ArrayList) fo.findInativeByName("");
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CNPJ", "Telefone", "Celular"};
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
        for (int i = 0; i < listaFornecedor.size(); i++) {
            Fornecedor f = new Fornecedor();
            f = (Fornecedor) listaFornecedor.get(i);
            linha = new Object[]{f.getCodFornecedor(), f, f.getCnpjFornecedor(), f.getTelefoneFornecedor(), f.getCelularFornecedor()};
            model.addRow(linha);
        }

        return model;
    }

    public DefaultTableModel findByCod(int cod) {
        FornecedorDao fo = new FornecedorDao();
        Fornecedor f = fo.findByCod(cod);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CNPJ", "Telefone", "Celular"};
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

        linha = new Object[]{f.getCodFornecedor(), f, f.getCnpjFornecedor(), f.getTelefoneFornecedor(), f.getCelularFornecedor()};
        model.addRow(linha);

        return model;
    }

    public DefaultTableModel findByName(String nome) {
        FornecedorDao fo = new FornecedorDao();
        ArrayList listaFornecedor = (ArrayList) fo.findByName(nome);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CNPJ", "Telefone", "Celular"};
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
        for (int i = 0; i < listaFornecedor.size(); i++) {
            Fornecedor f = new Fornecedor();
            f = (Fornecedor) listaFornecedor.get(i);
            linha = new Object[]{f.getCodFornecedor(), f, f.getCnpjFornecedor(), f.getTelefoneFornecedor(), f.getCelularFornecedor()};
            model.addRow(linha);
        }

        return model;
    }

    public DefaultTableModel findInativeByCod(int cod) {
        FornecedorDao fo = new FornecedorDao();
        Fornecedor f = fo.findInativeByCod(cod);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CNPJ", "Telefone", "Celular"};
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

        linha = new Object[]{f.getCodFornecedor(), f, f.getCnpjFornecedor(), f.getTelefoneFornecedor(), f.getCelularFornecedor()};
        model.addRow(linha);

        return model;
    }

    public DefaultTableModel findInativeByName(String nome) {
        FornecedorDao fo = new FornecedorDao();
        ArrayList listaFornecedor = (ArrayList) fo.findInativeByName(nome);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CNPJ", "Telefone", "Celular"};
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
        for (int i = 0; i < listaFornecedor.size(); i++) {
            Fornecedor f = new Fornecedor();
            f = (Fornecedor) listaFornecedor.get(i);
            linha = new Object[]{f.getCodFornecedor(), f, f.getCnpjFornecedor(), f.getTelefoneFornecedor(), f.getCelularFornecedor()};
            model.addRow(linha);
        }

        return model;
    }

}
