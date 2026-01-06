/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.VendaDao;
import br.com.hardsoftware.mercearia.model.Caixa;
import br.com.hardsoftware.mercearia.model.Venda;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alyssonkelvim
 */
public class ModeloTabelaVenda extends JTable{
    
    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel findByCaixa(Caixa ca) {
        VendaDao vd = new VendaDao();
        ArrayList listaVenda = (ArrayList) vd.findByCaixa(ca);
        //Colunas da Tabela
        String[] colunas = new String[]{"Código","Cliente","Saldo", "Hora da Venda", "Data da Venda"};
        //Dados Iniciais
        String[][] dados = new String[][]{
            {"", "", "", ""}
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
        for (int i = 0; i < listaVenda.size(); i++) {
            Venda v = new Venda();
            v = (Venda) listaVenda.get(i);
            Calendar c = Calendar.getInstance();
            //c.setTime(v.getDataVenda());
            String data = c.get(Calendar.DAY_OF_MONTH) +"/" +c.get(Calendar.MONTH) +"/"+ c.get(Calendar.YEAR) + "" ;
            String hora = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +":" +c.get(Calendar.SECOND) ;
            linha = new Object[]{v,v.getCliente(),v.getValorTotal(),hora, data};
            model.addRow(linha);
        }

        return model;
    }
    
    public DefaultTableModel addVenda(Venda v, DefaultTableModel modelAntigo) {
        
        JTable tabela = new JTable();
        tabela.setModel(modelAntigo);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabela);
        this.add(scroll);
        Object[] linha = null;
        Date d = v.getDataVenda();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
            //c.setTime(v.getDataVenda());
            String data = c.get(Calendar.DAY_OF_MONTH) +"/" +c.get(Calendar.MONTH) +"/"+ c.get(Calendar.YEAR) + "" ;
            String hora = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +":" +c.get(Calendar.SECOND) ;
            linha = new Object[]{v,v.getCliente(),v.getValorTotal(),hora, data};
        modelAntigo.addRow(linha);
        return modelAntigo;
    }
    public DefaultTableModel limpar(DefaultTableModel modelAntigo) {
        
        JTable tabela = new JTable();
        tabela.setModel(modelAntigo);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tabela);
        this.add(scroll);
        Object[] linha = null;
        for (int i = 0; i < modelAntigo.getRowCount(); i++) {
            modelAntigo.removeRow(i);
        }
        return modelAntigo;
    }

     public DefaultTableModel iniciar() {
        //Colunas da Tabela
        String[] colunas = new String[]{"Código", "Nome do Cliente", "Valor da Venda", "Hora", "Data"};
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
         

    

  
    
}
