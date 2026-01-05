/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.control;

import br.com.hardsoftware.mercearia.dao.ProdutoDao;
import br.com.hardsoftware.mercearia.model.Produto;
import br.com.hardsoftware.mercearia.relatorios.Html;
import br.com.hardsoftware.mercearia.view.CadastroProduto;
import br.com.hardsoftware.mercearia.view.ConsultarProduto;
import br.com.hardsoftware.mercearia.view.GerenciaProduto;
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
public class ProdutoControle {
    
    public static void inserir(){
        
        Produto p = new Produto();
        ProdutoDao pd= new ProdutoDao();
        p =CadastroProduto.inserir();
        if(p != null)
            p = pd.insert(p);
    }

    public static void alter(Produto p){
        
        int cod = p.getCodProduto();
        ProdutoDao pd= new ProdutoDao();
        p =CadastroProduto.alterar(p);
        if(p != null){
            p.setCodProduto(cod);
            p = pd.alter(p);
        }
    }
        
        
        public static void delete(Produto p){
        
        ProdutoDao pd= new ProdutoDao();
        pd.delete(p);
    }

    public static void iniciarProduto(){
        GerenciaProduto.iniciar();
    }
     public static Produto show(Produto p){
        CadastroProduto.mostrar(p);
        return p;
    }
     
     public static void gerarHtml(Produto p){
        String html = Html.geradorHtml(p);
        try {
            FileWriter fl = new FileWriter("relatorio_produto.html");
            fl.append(html);
            fl.close();
            
            Desktop desktop = null;  
            desktop = Desktop.getDesktop();  
            File fl2 = new File("relatorio_produto.html");
            desktop.open(fl2);
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void gerarHtml (ArrayList<Produto> lista ){
        String html = Html.geradorHtmlListaProduto(lista);
        try {
            FileWriter fl = new FileWriter("relatorio_lista_produto.html");
            fl.append(html);
            fl.close();
            
            Desktop desktop = null;  
            desktop = Desktop.getDesktop();  
            File fl2 = new File("relatorio_lista_produto.html");
            desktop.open(fl2);
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Produto selecionarProduto(){
        return ConsultarProduto.selecionar();
    }
}
