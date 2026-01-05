/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.control;

import br.com.hardsoftware.mercearia.dao.ClienteDao;
import br.com.hardsoftware.mercearia.model.Cliente;
import br.com.hardsoftware.mercearia.relatorios.Html;
import br.com.hardsoftware.mercearia.view.CadastroCliente;
import br.com.hardsoftware.mercearia.view.GerenciaCliente;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alysson
 */
public class ClienteControle {
    
    public static Cliente inserir(){
        
        ClienteDao cd = new ClienteDao();
        Cliente c = new Cliente();
        c = CadastroCliente.inserir();
        c.setSituacaoCliente("Ativo");
        cd.insert(c);
        return c;
        
    }
    
    
    public static Cliente editar(Cliente c){
        
        ClienteDao cd = new ClienteDao();
        int cod = c.getCodCliente();
        c = CadastroCliente.editar(c);
        c.setSituacaoCliente("Ativo");
        c.setCodCliente(cod);
        cd.alter(c);
        return c;
        
    }
    
    public static Cliente ativar(Cliente c){
        
        ClienteDao cd = new ClienteDao();
        c.setSituacaoCliente("Ativo");
        cd.alter(c);
        return c;
        
    }
    
    public static Cliente mostrar(Cliente c){
        CadastroCliente.mostrar(c);
        return c;
    }
    
    public static Cliente excluir(Cliente c){
        
        ClienteDao cd = new ClienteDao();
        cd.delete(c);
        return c;
        
    }
    
    public static void iniciarCliente(){
        GerenciaCliente.inciar();
    }
    
    public static void gerarHtml(Cliente c){
        String html = Html.geradorHtml(c);
        try {
            FileWriter fl = new FileWriter("relatorio_cliente.html");
            fl.append(html);
            fl.close();
            
            Desktop desktop = null;  
            desktop = Desktop.getDesktop();  
            File fl2 = new File("relatorio_cliente.html");
            desktop.open(fl2);
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void gerarHtml(ArrayList<Cliente> c){
        String html = Html.geradorHtmlListaCliente(c);
        try {
            FileWriter fl = new FileWriter("relatorio_clientes.html");
            fl.append(html);
            fl.close();
            
            Desktop desktop = null;  
            desktop = Desktop.getDesktop();  
            File fl2 = new File("relatorio_clientes.html");
            desktop.open(fl2);
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
