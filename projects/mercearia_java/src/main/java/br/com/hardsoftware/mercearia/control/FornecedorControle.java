/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.control;

import br.com.hardsoftware.mercearia.dao.FornecedorDao;
import br.com.hardsoftware.mercearia.model.Fornecedor;
import br.com.hardsoftware.mercearia.relatorios.Html;
import br.com.hardsoftware.mercearia.view.CadastroFornecedor;
import br.com.hardsoftware.mercearia.view.GerenciaFornecedor;
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
public class FornecedorControle {

    public static Fornecedor inserir() {

        FornecedorDao fo = new FornecedorDao();
        Fornecedor f = new Fornecedor();
        f = CadastroFornecedor.inserir();
        f.setSituacaoFornecedor("Ativo");
        fo.insert(f);
        return f;

    }

    public static Fornecedor editar(Fornecedor f) {

        FornecedorDao fo = new FornecedorDao();
        int cod = f.getCodFornecedor();
        f = CadastroFornecedor.editar(f);
        f.setSituacaoFornecedor("Ativo");
        f.setCodFornecedor(cod);
        fo.alter(f);
        return f;

    }

    public static Fornecedor ativar(Fornecedor f) {

        FornecedorDao fo = new FornecedorDao();
        f.setSituacaoFornecedor("Ativo");
        fo.alter(f);
        return f;

    }

    public static Fornecedor mostrar(Fornecedor fo) {
        CadastroFornecedor.mostrar(fo);
        return fo;
    }

    public static Fornecedor excluir(Fornecedor f) {

        FornecedorDao fo = new FornecedorDao();
        fo.delete(f);
        return f;

    }

    public static void iniciarFornecedor() {
        GerenciaFornecedor.inciar();
    }
    
    public static void gerarHtml(Fornecedor f) {
        String html = Html.geradorHtml(f);
        try {
            FileWriter fl = new FileWriter("relatorio_fornecedor.html");
            fl.append(html);
            fl.close();

            Desktop desktop = null;
            desktop = Desktop.getDesktop();
            File fl2 = new File("relatorio_fornecedor.html");
            desktop.open(fl2);

        } catch (IOException ex) {
            Logger.getLogger(FornecedorControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gerarHtml(ArrayList<Fornecedor> f) {
        String html = Html.geradorHtmlListaFornecedor(f);
        try {
            FileWriter fl = new FileWriter("relatorio_funcionarios.html");
            fl.append(html);
            fl.close();

            Desktop desktop = null;
            desktop = Desktop.getDesktop();
            File fl2 = new File("relatorio_funcionarios.html");
            desktop.open(fl2);

        } catch (IOException ex) {
            Logger.getLogger(FornecedorControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
