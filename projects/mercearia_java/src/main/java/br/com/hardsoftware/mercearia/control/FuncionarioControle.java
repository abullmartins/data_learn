/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.control;

import br.com.hardsoftware.mercearia.dao.FuncionarioDao;
import br.com.hardsoftware.mercearia.model.Funcionario;
import br.com.hardsoftware.mercearia.relatorios.Html;
import br.com.hardsoftware.mercearia.view.CadastroFuncionario;
import br.com.hardsoftware.mercearia.view.GerenciaFuncionario;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erbert
 */
public class FuncionarioControle {

    public static Funcionario inserir() {

        FuncionarioDao cd = new FuncionarioDao();
        Funcionario f = new Funcionario();
        f = CadastroFuncionario.inserir();
        f.setSituacaoFuncionario("Ativo");
        cd.insert(f);
        return f;

    }

    public static Funcionario editar(Funcionario f) {

        FuncionarioDao fo = new FuncionarioDao();
        int cod = f.getCodFuncionario();
        f = CadastroFuncionario.editar(f);
        f.setSituacaoFuncionario("Ativo");
        f.setCodFuncionario(cod);
        fo.alter(f);
        return f;

    }

    public static Funcionario ativar(Funcionario f) {

        FuncionarioDao fo = new FuncionarioDao();
        f.setSituacaoFuncionario("Ativo");
        fo.alter(f);
        return f;

    }

    public static Funcionario mostrar(Funcionario f) {
        CadastroFuncionario.mostrar(f);
        return f;
    }

    public static Funcionario excluir(Funcionario f) {

        FuncionarioDao fo = new FuncionarioDao();
        fo.delete(f);
        return f;

    }

    public static void iniciarFuncionario() {
        GerenciaFuncionario.inciar();
    }

    public static void gerarHtml(Funcionario f) {
        String html = Html.geradorHtml(f);
        try {
            FileWriter fl = new FileWriter("relatorio_funcionario.html");
            fl.append(html);
            fl.close();

            Desktop desktop = null;
            desktop = Desktop.getDesktop();
            File fl2 = new File("relatorio_funcionario.html");
            desktop.open(fl2);

        } catch (IOException ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gerarHtml(ArrayList<Funcionario> f) {
        String html = Html.geradorHtmlListaFuncionario(f);
        try {
            FileWriter fl = new FileWriter("relatorio_funcionarios.html");
            fl.append(html);
            fl.close();

            Desktop desktop = null;
            desktop = Desktop.getDesktop();
            File fl2 = new File("relatorio_funcionarios.html");
            desktop.open(fl2);

        } catch (IOException ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
