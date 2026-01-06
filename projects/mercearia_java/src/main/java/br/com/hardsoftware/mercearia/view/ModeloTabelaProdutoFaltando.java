/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.ProdutoDao;
import br.com.hardsoftware.mercearia.model.Categoria;
import br.com.hardsoftware.mercearia.model.Produto;
import java.awt.Color;
import java.awt.Label;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alysson
 */
public class ModeloTabelaProdutoFaltando extends JTable {

    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel findAll() {
        ProdutoDao pd = new ProdutoDao();
        ArrayList listaProduto = (ArrayList) pd.findAllOrderByQtd();
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", " Produto", "Marca", "Quantidade", "Preço"};
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
        int cont = 0;
        for (int i = 0; i < listaProduto.size(); i++) {
            Produto p = new Produto();
            p = (Produto) listaProduto.get(i);
            Label a = new Label(""+p.getCodProduto());
            linha = new Object[]{p.getCodProduto(), p, p.getMarcaProduto(), p.getQtdProduto(), p.getPrecoProduto()};
            model.addRow(linha);
            cont++;
            if(cont == 4) break;
            
        }

        return model;
    }
    
     

}
