/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.FuncionarioDao;
import br.com.hardsoftware.mercearia.model.Funcionario;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Erbert
 */
public class ModeloTabelaFuncionario extends JTable {
    
    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel findAll() {
        FuncionarioDao cd = new FuncionarioDao();
        ArrayList listaFuncionario = (ArrayList) cd.findAll();
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CPF", "Telefone", "Celular"};
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
        for (int i = 0; i < listaFuncionario.size(); i++) {
            Funcionario f = new Funcionario();
            f = (Funcionario) listaFuncionario.get(i);
            linha = new Object[]{f.getCodFuncionario(), f, f.getCpfFuncionario(), f.getTelefoneFuncionario(), f.getCelularFuncionario()};
            model.addRow(linha);
        }

        return model;
    }

    public DefaultTableModel findAllInative() {
        FuncionarioDao fo = new FuncionarioDao();
        ArrayList listaFuncionario = (ArrayList) fo.findInativeByName("");
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CPF", "Telefone", "Celular"};
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
        for (int i = 0; i < listaFuncionario.size(); i++) {
            Funcionario f = new Funcionario();
            f = (Funcionario) listaFuncionario.get(i);
            linha = new Object[]{f.getCodFuncionario(), f, f.getCpfFuncionario(), f.getTelefoneFuncionario(), f.getCelularFuncionario()};
            model.addRow(linha);
        }

        return model;
    }

    public DefaultTableModel findByCod(int cod) {
        FuncionarioDao fo = new FuncionarioDao();
        Funcionario f = fo.findByCod(cod);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CPF", "Telefone", "Celular"};
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

        linha = new Object[]{f.getCodFuncionario(), f, f.getCpfFuncionario(), f.getTelefoneFuncionario(), f.getCelularFuncionario()};
        model.addRow(linha);

        return model;
    }

    public DefaultTableModel findByName(String nome) {
        FuncionarioDao fo = new FuncionarioDao();
        ArrayList listaFuncionario = (ArrayList) fo.findByName(nome);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CPF", "Telefone", "Celular"};
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
        for (int i = 0; i < listaFuncionario.size(); i++) {
            Funcionario f = new Funcionario();
            f = (Funcionario) listaFuncionario.get(i);
            linha = new Object[]{f.getCodFuncionario(), f, f.getCpfFuncionario(), f.getTelefoneFuncionario(), f.getCelularFuncionario()};
            model.addRow(linha);
        }

        return model;
    }

    public DefaultTableModel findInativeByCod(int cod) {
        FuncionarioDao fo = new FuncionarioDao();
        Funcionario f = fo.findInativeByCod(cod);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CPF", "Telefone", "Celular"};
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

        linha = new Object[]{f.getCodFuncionario(), f, f.getCpfFuncionario(), f.getTelefoneFuncionario(), f.getCelularFuncionario()};
        model.addRow(linha);

        return model;
    }

    public DefaultTableModel findInativeByName(String nome) {
        FuncionarioDao fo = new FuncionarioDao();
        ArrayList listaFuncionario = (ArrayList) fo.findInativeByName(nome);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome", "CPF", "Telefone", "Celular"};
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
        for (int i = 0; i < listaFuncionario.size(); i++) {
            Funcionario f = new Funcionario();
            f = (Funcionario) listaFuncionario.get(i);
            linha = new Object[]{f.getCodFuncionario(), f, f.getCpfFuncionario(), f.getTelefoneFuncionario(), f.getCelularFuncionario()};
            model.addRow(linha);
        }

        return model;
    }
    
}
