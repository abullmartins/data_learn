/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.view;

import br.com.hardsoftware.mercearia.dao.ProdutoDao;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author alyssonkelvim
 */
public class ModeloListaFaltando {
    
    public static DefaultListModel listar(){
        ProdutoDao pd = new ProdutoDao();
        ArrayList lista = pd.findAllOrderByQtd();
        DefaultListModel model = new DefaultListModel();
            
        for (int i = 0; i < lista.size(); i++) {
             model.addElement(lista.get(i));
        }
        return model;
    }
}
