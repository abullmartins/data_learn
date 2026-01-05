/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.control;

import br.com.hardsoftware.mercearia.dao.CaixaDao;
import br.com.hardsoftware.mercearia.dao.VendaDao;
import br.com.hardsoftware.mercearia.model.Caixa;
import br.com.hardsoftware.mercearia.model.Funcionario;
import br.com.hardsoftware.mercearia.model.Produto;
import br.com.hardsoftware.mercearia.model.Venda;
import br.com.hardsoftware.mercearia.relatorios.Html;
import br.com.hardsoftware.mercearia.view.TelaVenda;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alyssonkelvim
 */
public class CaixaControle {
    
    private static Caixa caixa;
    private static String status = "Fechado";
    
    public static String getStatus(){
        return status;
    }
    
    public static Calendar abrirCaixa(Funcionario f){
        caixa = new Caixa();
        Calendar c = Calendar.getInstance();
        caixa.setFuncionario(f);
        caixa.setDataAbertura(c);
        status = "Aberto";
        return c;
    }
    
    public static Venda fecharVenda(Venda v){
        try{
            VendaDao vd = new VendaDao();
            v = vd.insertVenda(v);
            double fluxo = v.getValorTotal();
            caixa.setValor(caixa.getValor() + fluxo);
            return v;
        }catch(Exception e){
            return null;
        }   
    }
    
    public static void abrirVenda(){
        TelaVenda.inciar();
    }
    
    public static boolean fecharCaixa(ArrayList<Venda> listaVenda){
       double ultimoSaldo = CaixaDao.getUltimoSaldo();
       double saldo = ultimoSaldo + caixa.getValor();
       caixa.setSaldo(saldo);
       Calendar c = Calendar.getInstance();
       caixa.setDataFechamento(c);
       caixa.setVendas(listaVenda);
       boolean r = false;
        try {
            r = CaixaDao.insert(caixa);
        } catch (SQLException ex) {
            Logger.getLogger(CaixaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
       status = "Fechado";
       return r;
    }
    
    public static void gerarHtml (ArrayList<Caixa> lista ){
        String html = Html.geradorHtmlListaCaixa(lista);
        try {
            FileWriter fl = new FileWriter("relatorio_lista_caixa.html");
            fl.append(html);
            fl.close();
            
            Desktop desktop = null;  
            desktop = Desktop.getDesktop();  
            File fl2 = new File("relatorio_lista_caixa.html");
            desktop.open(fl2);
            
        } catch (IOException ex) {
            //Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
