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

public class ModeloTabelaProdutoComprado extends JTable{

    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel iniciar() {
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
       

        return model;
    }
     
     public DefaultTableModel addProduto(String codBarra,int qtd, DefaultTableModel modelAntigo){
         
        ProdutoDao pd = new ProdutoDao();
        ArrayList listaProduto = new ArrayList();
        Produto q = pd.findByCodBarras(codBarra);
        q.setQtdProduto(qtd);
        if(q.getCodigoBarraProduto() != null)
            listaProduto.add(q);
        JTable tabela = new JTable();
        tabela.setModel(modelAntigo);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabela);
        this.add(scroll);
        Object[] linha = null;
        for (int i = 0; i < listaProduto.size(); i++) {
            Produto p = (Produto) listaProduto.get(i);
            double precoTotal = qtd * p.getPrecoProduto();
            linha = new Object[]{p.getCodProduto(), p, p.getMarcaProduto(), qtd, precoTotal};
            modelAntigo.addRow(linha);
        }

        return modelAntigo;
         
     }
     
     public DefaultTableModel removeProduto(int linha, DefaultTableModel modelAntigo){
         modelAntigo.removeRow(linha);
        return modelAntigo;   
     }
}

    

