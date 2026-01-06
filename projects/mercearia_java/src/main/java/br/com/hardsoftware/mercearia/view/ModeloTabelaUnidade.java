/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.UnidadeProdutoDao;
import br.com.hardsoftware.mercearia.dao.ClienteDao;
import br.com.hardsoftware.mercearia.model.UnidadeProduto;
import br.com.hardsoftware.mercearia.model.Cliente;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alysson
 */
public class ModeloTabelaUnidade extends JTable {

    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel findAll() {
        UnidadeProdutoDao cd = new UnidadeProdutoDao();
        ArrayList listaUnidade = (ArrayList) cd.findAll();
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Unidade"};
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
        for (int i = 0; i < listaUnidade.size(); i++) {
            UnidadeProduto c = new UnidadeProduto();
            c = (UnidadeProduto) listaUnidade.get(i);
            linha = new Object[]{c.getCodUnidade(), c};
            model.addRow(linha);
        }

        return model;
    }

   

}
