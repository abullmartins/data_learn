/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.CaixaDao;
import br.com.hardsoftware.mercearia.dao.VendaDao;
import br.com.hardsoftware.mercearia.model.Caixa;
import br.com.hardsoftware.mercearia.model.Funcionario;
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
public class ModeloTabelaCaixa extends JTable{
    
    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }

    public DefaultTableModel findAll() {
        CaixaDao cd = new CaixaDao();
        ArrayList listaCaixa = cd.findAll();
        //Colunas da Tabela
        String[] colunas = new String[]{"Funcionáro","Saldo","Data de Abertura", "Hora de Abertura", "Hora de Fechamento"};
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
        for (int i = 0; i < listaCaixa.size(); i++) {
            Caixa c = (Caixa) listaCaixa.get(i);
            
            Calendar cal = c.getDataAbertura();
            String data = cal.get(Calendar.DAY_OF_MONTH) +"/" +cal.get(Calendar.MONTH) +"/"+ cal.get(Calendar.YEAR) + "" ;
            String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) +":" +cal.get(Calendar.SECOND) ;
            cal = c.getDataFechamento();
            String hora2 = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) +":" +cal.get(Calendar.SECOND) ;
            linha = new Object[]{c.getFuncionario(),c,data, hora, hora2};
            model.addRow(linha);
        }

        return model;
    }
    
    public DefaultTableModel findAllAndNow(Calendar ca, Funcionario f) {
        CaixaDao cd = new CaixaDao();
        ArrayList listaCaixa = cd.findAll();
        //Colunas da Tabela
        String[] colunas = new String[]{"Funcionáro","Saldo","Data de Abertura", "Hora de Abertura", "Hora de Fechamento"};
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
        for (int i = 0; i < listaCaixa.size(); i++) {
            Caixa c = (Caixa) listaCaixa.get(i);
            
            Calendar cal = c.getDataAbertura();
            String data = cal.get(Calendar.DAY_OF_MONTH) +"/" +cal.get(Calendar.MONTH) +"/"+ cal.get(Calendar.YEAR) + "" ;
            String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) +":" +cal.get(Calendar.SECOND) ;
            cal = c.getDataFechamento();
            String hora2 = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) +":" +cal.get(Calendar.SECOND) ;
            linha = new Object[]{c.getFuncionario(),c,data, hora, hora2};
            model.addRow(linha);
        }
        
        String data = ca.get(Calendar.DAY_OF_MONTH) +"/" +ca.get(Calendar.MONTH) +"/"+ ca.get(Calendar.YEAR) + "" ;
        String hora = ca.get(Calendar.HOUR_OF_DAY) + ":" + ca.get(Calendar.MINUTE) +":" +ca.get(Calendar.SECOND) ;
        
        linha = new Object[]{f, "", data, hora, ""};
        model.addRow(linha);
        return model;
    }
    
    
    
}
