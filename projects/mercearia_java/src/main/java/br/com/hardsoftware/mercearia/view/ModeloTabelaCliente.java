/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.ClienteDao;
import br.com.hardsoftware.mercearia.model.Cliente;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alysson
 */
public class ModeloTabelaCliente extends JTable {

    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel findAll() {
        ClienteDao cd = new ClienteDao();
        ArrayList listaCliente = (ArrayList) cd.findAll();
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
        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente c = new Cliente();
            c = (Cliente) listaCliente.get(i);
            linha = new Object[]{c.getCodCliente(), c, c.getCpfCliente(), c.getTelefoneCliente(), c.getCelularCliente()};
            model.addRow(linha);
        }

        return model;
    }

    public DefaultTableModel findAllInative() {
        ClienteDao cd = new ClienteDao();
        ArrayList listaCliente = (ArrayList) cd.findInativeByName("");
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
        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente c = new Cliente();
            c = (Cliente) listaCliente.get(i);
            linha = new Object[]{c.getCodCliente(), c, c.getCpfCliente(), c.getTelefoneCliente(), c.getCelularCliente()};
            model.addRow(linha);
        }

        return model;
    }

    public DefaultTableModel findByCod(int cod) {
        ClienteDao cd = new ClienteDao();
        Cliente c = cd.findByCod(cod);
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

        linha = new Object[]{c.getCodCliente(), c, c.getCpfCliente(), c.getTelefoneCliente(), c.getCelularCliente()};
        model.addRow(linha);

        return model;
    }

    public DefaultTableModel findByName(String nome) {
        ClienteDao cd = new ClienteDao();
        ArrayList listaCliente = (ArrayList) cd.findByName(nome);
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
        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente c = new Cliente();
            c = (Cliente) listaCliente.get(i);
            linha = new Object[]{c.getCodCliente(), c, c.getCpfCliente(), c.getTelefoneCliente(), c.getCelularCliente()};
            model.addRow(linha);
        }

        return model;
    }

    public DefaultTableModel findInativeByCod(int cod) {
        ClienteDao cd = new ClienteDao();
        Cliente c = cd.findInativeByCod(cod);
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

        linha = new Object[]{c.getCodCliente(), c, c.getCpfCliente(), c.getTelefoneCliente(), c.getCelularCliente()};
        model.addRow(linha);

        return model;
    }

    public DefaultTableModel findInativeByName(String nome) {
        ClienteDao cd = new ClienteDao();
        ArrayList listaCliente = (ArrayList) cd.findInativeByName(nome);
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
        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente c = new Cliente();
            c = (Cliente) listaCliente.get(i);
            linha = new Object[]{c.getCodCliente(), c, c.getCpfCliente(), c.getTelefoneCliente(), c.getCelularCliente()};
            model.addRow(linha);
        }

        return model;
    }

}
