/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.CategoriaDao;
import br.com.hardsoftware.mercearia.dao.ClienteDao;
import br.com.hardsoftware.mercearia.model.Categoria;
import br.com.hardsoftware.mercearia.model.Cliente;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alysson
 */
public class ModeloTabelaCategoria extends JTable {

    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel findAll() {
        CategoriaDao cd = new CategoriaDao();
        ArrayList listaCategoria = (ArrayList) cd.findAll();
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Categoria"};
        //Dados Iniciais
        String[][] dados = new String[][]{
            {"", ""}
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
        for (int i = 0; i < listaCategoria.size(); i++) {
            Categoria c = new Categoria();
            c = (Categoria) listaCategoria.get(i);
            linha = new Object[]{c.getCdCategoria(), c};
            model.addRow(linha);
        }

        return model;
    }

   

}
