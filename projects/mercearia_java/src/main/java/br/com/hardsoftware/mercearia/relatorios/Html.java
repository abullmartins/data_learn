/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.relatorios;

import br.com.hardsoftware.mercearia.model.Caixa;
import br.com.hardsoftware.mercearia.model.Cliente;
import br.com.hardsoftware.mercearia.model.Fornecedor;
import br.com.hardsoftware.mercearia.model.Funcionario;
import br.com.hardsoftware.mercearia.model.Produto;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author alyssonkelvim
 */
public class Html {

    public static String geradorHtml(Produto p) {
        String cabecalho = "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Relatório de Produto</title>\n"
                + "        <style>\n"
                + "            .relatorio{\n"
                + "                width: 800px;\n"
                + "                position: relative;\n"
                + "                left: -400px;\n"
                + "                margin-left: 50%;\n"
                + "            }\n"
                + "            img{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            h2,h3,h4{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            p{\n"
                + "                padding-left: 30px;\n"
                + "            }\n"
                + "            span{\n"
                + "                 margin-left: 20px;\n"
                + "}           \n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        ";

        String corpo = "<div class='relatorio'>\n"
                + "           <img src=\"Logo%20Provisoria.png\">\n"
                + "            <h2>Relatório de Produto</h2>\n"
                + "            <h3>#CODIGODEBARRA#</h3>\n"
                + "            <p><strong>Nome do Produto: </strong>#NOMEPRODUTO#</p>\n"
                + "            <p><strong>Marca do Produto: </strong>#MARCAPRODUTO#</p>\n"
                + "            <p><strong>Preço do Produto: </strong>R$ #PRECOPRODUTO#</p>\n"
                + "            <p><strong>Descrição do Produto: </strong>#DESCRICAOPRODUTO#</p>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "     <script>window.print();</script></html>";

        corpo = corpo.replace("#NOMEPRODUTO#", p.getNmProduto());
        corpo = corpo.replace("#MARCAPRODUTO#", p.getMarcaProduto());
        corpo = corpo.replace("#PRECOPRODUTO#", String.valueOf(p.getPrecoProduto()));
        corpo = corpo.replace("#DESCRICAOPRODUTO#", p.getDescricaoProduto());
        corpo = corpo.replace("#CODIGODEBARRA#", p.getCodigoBarraProduto());

        return cabecalho + corpo;
    }

    public static String geradorHtmlListaProduto(ArrayList<Produto> lista) {
        String cabecalho = "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Relatório de Produtos</title>\n"
                + "        <style>\n"
                + "            td{\n"
                + "              /*  padding: 5px;\n"
                + "                padding-left: 8px;\n"
                + "                padding-right: 8px; */\n"
                + "                padding-top: 4px;\n"
                + "                padding-bottom: 4px;\n"
                + "                padding-left: 3px;\n"
                + "                border: 1px solid white;\n"
                + "            }\n"
                + "            .tr1{\n"
                + "                background-color: #f2f2f2;\n"
                + "            }\n"
                + "            .tr0{\n"
                + "                background-color: #e2e2e2;\n"
                + "            }\n"
                + "            table{\n"
                + "                min-width: 700px;\n"
                + "            }\n"
                + "            .relatorio{\n"
                + "                width: 800px;\n"
                + "                position: relative;\n"
                + "                left: -400px;\n"
                + "                margin-left: 50%;\n"
                + "                \n"
                + "            }\n"
                + "            img{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            h2,h3,h4{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            p{\n"
                + "                padding-left: 30px;\n"
                + "            }\n"
                + "            span{\n"
                + "                 margin-left: 20px;\n"
                + "}           \n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class='relatorio'>\n"
                + "           <img src=\"Logo%20Provisoria.png\">\n"
                + "            <h2>Relatório de Produtos</h2>\n"
                + "            <table>\n"
                + "                <tr class=\"tr0\">\n"
                + "                    <td><strong>Código</strong>\n"
                + "                    <td><strong>Produto</strong>\n"
                + "                    <td><strong>Marca</strong>\n"
                + "                    <td><strong>Quantidade</strong>\n"
                + "                    <td><strong>Preço</strong>\n"
                + "                    <td><strong>Categoria</strong>\n";

        String corpo = "<tr class=\"#TR#\">\n"
                + "                    <td>#CODIGO#\n"
                + "                    <td>#NOME#\n"
                + "                    <td>#MARCA#\n"
                + "                    <td>#QUANTIDADE#\n"
                + "                    <td>#PRECO#\n"
                + "                    <td>#CATEGORIA#\n"
                + "                \n";
        String rodape = "            </table>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "     <script>window.print();</script>"
                + "     <script>//window.print();</script></html>";
        String novoCorpo = "";
        int cont = 0;
        for (Produto p : lista) {

            novoCorpo += corpo.replace("#CODIGO#", String.valueOf(p.getCodProduto()));
            novoCorpo = novoCorpo.replace("#NOME#", p.getNmProduto());
            novoCorpo = novoCorpo.replace("#MARCA#", p.getMarcaProduto());
            novoCorpo = novoCorpo.replace("#QUANTIDADE#", String.valueOf(p.getQtdProduto()));
            novoCorpo = novoCorpo.replace("#PRECO#", "R$ " + p.getPrecoProduto());
            novoCorpo = novoCorpo.replace("#CATEGORIA#", p.getCategoria().getNmCategoria());
            cont++;
            if (cont % 2 == 0) {
                novoCorpo = novoCorpo.replace("#TR#", "tr0");
            } else {
                novoCorpo = novoCorpo.replace("#TR#", "tr1");
            }
        }
        return cabecalho + novoCorpo + rodape;
    }

    public static String geradorHtml(Cliente c) {
        String cabecalho = "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Relatório de Cliente</title>\n"
                + "        <style>\n"
                + "            .relatorio{\n"
                + "                width: 800px;\n"
                + "                position: relative;\n"
                + "                left: -400px;\n"
                + "                margin-left: 50%;\n"
                + "            }\n"
                + "            img{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            h2,h3,h4{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            p{\n"
                + "                padding-left: 30px;\n"
                + "            }\n"
                + "            span{\n"
                + "                 margin-left: 20px;\n}"
                + "           \n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n";

        String corpo = ""
                + "        <div class='relatorio'>\n"
                + "           <img src=\"Logo%20Provisoria.png\">\n"
                + "            <h2>Relatório de Cliente</h2>\n"
                + "            <h3>#NOME#</h3>\n"
                + "            <p><strong>CPF:</strong> #CPF#<span><strong>RG:</strong> #RG#</p>\n"
                + "            <p><strong>Data de Nascimento:</strong> #DATANASC#    <span><strong>Sexo:</strong> #SEXO#</p>\n"
                + "            <p><strong>Telefone:</strong> #TELEFONE#  <span><strong>Celular:</strong> #CELULAR#</p>\n"
                + "            <p><strong>E-mail:</strong> #EMAIL#</p><br>\n"
                + "            <p><strong>CEP:</strong>#CEP#</p>\n"
                + "            <p><strong>UF:</strong>#UF#  <span><strong>Cidade:</strong>#CIDADE#</p>\n"
                + "            <p><strong>Endereço:</strong>#ENDERECO#</p>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "     <script>window.print();</script>"
                + "</html>";

        corpo = corpo.replace("#NOME#", c.getNmCliente());
        corpo = corpo.replace("#CPF#", c.getCpfCliente());
        corpo = corpo.replace("#RG#", c.getRg());
        corpo = corpo.replace("#DATANASC#", c.getDtNascCliente());
        corpo = corpo.replace("#SEXO#", String.valueOf(c.getSexoCliente()));
        corpo = corpo.replace("#TELEFONE#", c.getTelefoneCliente());
        corpo = corpo.replace("#CELULAR#", c.getCelularCliente());
        corpo = corpo.replace("#EMAIL#", c.getEmailCliente());
        corpo = corpo.replace("#CEP#", c.getCepCliente());
        corpo = corpo.replace("#UF#", c.getCidadeCliente().getEstado().getNmEstado());
        corpo = corpo.replace("#CIDADE#", c.getCidadeCliente().getNmCidade());
        corpo = corpo.replace("#ENDERECO#", c.getEndereco());
        return cabecalho + corpo;
    }

    public static String geradorHtmlListaCliente(ArrayList<Cliente> lista) {
        String situacao = lista.get(0).getSituacaoCliente();
        String cabecalho = "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Relatório de Clientes</title>\n"
                + "        <style>\n"
                + "            td{\n"
                + "              /*  padding: 5px;\n"
                + "                padding-left: 8px;\n"
                + "                padding-right: 8px; */\n"
                + "                padding-top: 4px;\n"
                + "                padding-bottom: 4px;\n"
                + "                padding-left: 3px;\n"
                + "                border: 1px solid white;\n"
                + "            }\n"
                + "            .tr1{\n"
                + "                background-color: #f2f2f2;\n"
                + "            }\n"
                + "            .tr0{\n"
                + "                background-color: #e2e2e2;\n"
                + "            }\n"
                + "            table{\n"
                + "                min-width: 700px;\n"
                + "            }\n"
                + "            .relatorio{\n"
                + "                width: 800px;\n"
                + "                position: relative;\n"
                + "                left: -400px;\n"
                + "                margin-left: 50%;\n"
                + "                \n"
                + "            }\n"
                + "            img{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            h2,h3,h4{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            p{\n"
                + "                padding-left: 30px;\n"
                + "            }\n"
                + "            span{\n"
                + "                 margin-left: 20px;\n"
                + "}           \n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class='relatorio'>\n"
                + "           <img src=\"Logo%20Provisoria.png\">\n"
                + "            <h2>Relatório de Clientes " + situacao + "s</h2>\n"
                + "            <table>\n"
                + "                <tr class=\"tr0\">\n"
                + "                    <td><strong>Codigo</strong>\n"
                + "                    <td><strong>Nome</strong>\n"
                + "                    <td><strong>CPF</strong>\n"
                + "                    <td><strong>Telefone</strong>\n"
                + "                    <td><strong>Celular</strong>\n";

        String corpo = "<tr class=\"#TR#\">\n"
                + "                    <td>#CODIGO#\n"
                + "                    <td>#NOME#\n"
                + "                    <td>#CPF#\n"
                + "                    <td>#TELEFONE#\n"
                + "                    <td>#CELULAR#\n"
                + "                \n";
        String rodape = "            </table>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "     <script>window.print();</script>"
                + "     <script>//window.print();</script></html>";
        String novoCorpo = "";
        int cont = 0;
        for (Cliente c : lista) {

            novoCorpo += corpo.replace("#CODIGO#", String.valueOf(c.getCodCliente()));
            novoCorpo = novoCorpo.replace("#NOME#", c.getNmCliente());
            novoCorpo = novoCorpo.replace("#CPF#", c.getCpfCliente());
            novoCorpo = novoCorpo.replace("#TELEFONE#", c.getTelefoneCliente());
            novoCorpo = novoCorpo.replace("#CELULAR#", c.getCelularCliente());
            cont++;
            if (cont % 2 == 0) {
                novoCorpo = novoCorpo.replace("#TR#", "tr0");
            } else {
                novoCorpo = novoCorpo.replace("#TR#", "tr1");
            }
        }
        return cabecalho + novoCorpo + rodape;
    }
    
    public static String geradorHtmlListaCaixa(ArrayList<Caixa> lista) {
        String cabecalho = "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Relatório de Vendas</title>\n"
                + "        <style>\n"
                + "            td{\n"
                + "              /*  padding: 5px;\n"
                + "                padding-left: 8px;\n"
                + "                padding-right: 8px; */\n"
                + "                padding-top: 4px;\n"
                + "                padding-bottom: 4px;\n"
                + "                padding-left: 3px;\n"
                + "                border: 1px solid white;\n"
                + "            }\n"
                + "            .tr1{\n"
                + "                background-color: #f2f2f2;\n"
                + "            }\n"
                + "            .tr0{\n"
                + "                background-color: #e2e2e2;\n"
                + "            }\n"
                + "            table{\n"
                + "                min-width: 700px;\n"
                + "            }\n"
                + "            .relatorio{\n"
                + "                width: 800px;\n"
                + "                position: relative;\n"
                + "                left: -400px;\n"
                + "                margin-left: 50%;\n"
                + "                \n"
                + "            }\n"
                + "            img{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            h2,h3,h4{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            p{\n"
                + "                padding-left: 30px;\n"
                + "            }\n"
                + "            span{\n"
                + "                 margin-left: 20px;\n"
                + "}           \n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class='relatorio'>\n"
                + "           <img src=\"Logo%20Provisoria.png\">\n"
                + "            <h2>Relatório de Caixa</h2>\n"
                + "            <table>\n"
                + "                <tr class=\"tr0\">\n"
                + "                    <td><strong>Funcionário</strong>\n"
                + "                    <td><strong>Saldo</strong>\n"
                + "                    <td><strong>Data de Abertura</strong>\n"
                + "                    <td><strong>Hora de Abertura</strong>\n"
                + "                    <td><strong>Hora de Fechamento</strong>\n";

        String corpo = "<tr class=\"#TR#\">\n"
                + "                    <td>#FUNCIONARIO#\n"
                + "                    <td>#SALDO#\n"
                + "                    <td>#DATA#\n"
                + "                    <td>#HORAA#\n"
                + "                    <td>#HORAF#\n"
                + "                \n";
        String rodape = "            </table>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "     <script>window.print();</script>"
                + "     <script>//window.print();</script></html>";
        String novoCorpo = "";
        int cont = 0;
        for (Caixa c : lista) {
            Calendar cal = c.getDataAbertura();
            String data = cal.get(Calendar.DAY_OF_MONTH) +"/" +cal.get(Calendar.MONTH) +"/"+ cal.get(Calendar.YEAR) + "" ;
            String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) +":" +cal.get(Calendar.SECOND) ;
            cal = c.getDataFechamento();
            String hora2 = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) +":" +cal.get(Calendar.SECOND) ;
            
            novoCorpo += corpo.replace("#FUNCIONARIO#", String.valueOf(c.getFuncionario()));
            novoCorpo = novoCorpo.replace("#SALDO#", "R$ "+c.getSaldo());
            novoCorpo = novoCorpo.replace("#DATA#", data);
            novoCorpo = novoCorpo.replace("#HORAA#", hora);
            novoCorpo = novoCorpo.replace("#HORAF#", hora2);
            cont++;
            if (cont % 2 == 0) {
                novoCorpo = novoCorpo.replace("#TR#", "tr0");
            } else {
                novoCorpo = novoCorpo.replace("#TR#", "tr1");
            }
        }
        return cabecalho + novoCorpo + rodape;
    }


    public static String geradorHtml(Funcionario f) {
        String cabecalho = "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Relatório de Funcionários</title>\n"
                + "        <style>\n"
                + "            .relatorio{\n"
                + "                width: 800px;\n"
                + "                position: relative;\n"
                + "                left: -400px;\n"
                + "                margin-left: 50%;\n"
                + "            }\n"
                + "            img{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            h2,h3,h4{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            p{\n"
                + "                padding-left: 30px;\n"
                + "            }\n"
                + "            span{\n"
                + "                 margin-left: 20px;\n}"
                + "           \n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n";

        String corpo = ""
                + "        <div class='relatorio'>\n"
                + "           <img src=\"Logo%20Provisoria.png\">\n"
                + "            <h2>Relatório de Funcionários</h2>\n"
                + "            <h3>#NOME#</h3>\n"
                + "            <p><strong>CPF:</strong> #CPF#<span><strong>RG:</strong> #RG#</p>\n"
                + "            <p><strong>Data de Nascimento:</strong> #DATANASC#    <span><strong>Sexo:</strong> #SEXO#</p>\n"
                + "            <p><strong>Telefone:</strong> #TELEFONE#  <span><strong>Celular:</strong> #CELULAR#</p>\n"
                + "            <p><strong>E-mail:</strong> #EMAIL#</p><br>\n"
                + "            <p><strong>CEP:</strong>#CEP#</p>\n"
                + "            <p><strong>UF:</strong>#UF#  <span><strong>Cidade:</strong>#CIDADE#</p>\n"
                + "            <p><strong>Endereço:</strong>#ENDERECO#</p>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "     <script>window.print();</script>"
                + "</html>";

        corpo = corpo.replace("#NOME#", f.getNmFuncionario());
        corpo = corpo.replace("#CPF#", f.getCpfFuncionario());
        corpo = corpo.replace("#RG#", f.getRgFuncionario());
        corpo = corpo.replace("#DATANASC#", f.getDtNascFuncionario());
        corpo = corpo.replace("#SEXO#", String.valueOf(f.getSexoFuncionario()));
        corpo = corpo.replace("#TELEFONE#", f.getTelefoneFuncionario());
        corpo = corpo.replace("#CELULAR#", f.getCelularFuncionario());
        corpo = corpo.replace("#EMAIL#", f.getEmailFuncionario());
        corpo = corpo.replace("#CEP#", f.getCepFuncionario());
        corpo = corpo.replace("#UF#", f.getCidadeFuncionario().getEstado().getNmEstado());
        corpo = corpo.replace("#CIDADE#", f.getCidadeFuncionario().getNmCidade());
        corpo = corpo.replace("#ENDERECO#", f.getEnderecoFuncionario());
        return cabecalho + corpo;
    }

    public static String geradorHtmlListaFuncionario(ArrayList<Funcionario> lista) {
        String situacao = lista.get(0).getSituacaoFuncionario();
        String cabecalho = "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Relatório de Funcionários</title>\n"
                + "        <style>\n"
                + "            td{\n"
                + "              /*  padding: 5px;\n"
                + "                padding-left: 8px;\n"
                + "                padding-right: 8px; */\n"
                + "                padding-top: 4px;\n"
                + "                padding-bottom: 4px;\n"
                + "                padding-left: 3px;\n"
                + "                border: 1px solid white;\n"
                + "            }\n"
                + "            .tr1{\n"
                + "                background-color: #f2f2f2;\n"
                + "            }\n"
                + "            .tr0{\n"
                + "                background-color: #e2e2e2;\n"
                + "            }\n"
                + "            table{\n"
                + "                min-width: 700px;\n"
                + "            }\n"
                + "            .relatorio{\n"
                + "                width: 800px;\n"
                + "                position: relative;\n"
                + "                left: -400px;\n"
                + "                margin-left: 50%;\n"
                + "                \n"
                + "            }\n"
                + "            img{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            h2,h3,h4{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            p{\n"
                + "                padding-left: 30px;\n"
                + "            }\n"
                + "            span{\n"
                + "                 margin-left: 20px;\n"
                + "}           \n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class='relatorio'>\n"
                + "           <img src=\"Logo%20Provisoria.png\">\n"
                + "            <h2>Relatório de Funcionários " + situacao + "s</h2>\n"
                + "            <table>\n"
                + "                <tr class=\"tr0\">\n"
                + "                    <td><strong>Codigo</strong>\n"
                + "                    <td><strong>Nome</strong>\n"
                + "                    <td><strong>CPF</strong>\n"
                + "                    <td><strong>Telefone</strong>\n"
                + "                    <td><strong>Celular</strong>\n";

        String corpo = "<tr class=\"#TR#\">\n"
                + "                    <td>#CODIGO#\n"
                + "                    <td>#NOME#\n"
                + "                    <td>#CPF#\n"
                + "                    <td>#TELEFONE#\n"
                + "                    <td>#CELULAR#\n"
                + "                \n";
        String rodape = "            </table>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "     <script>window.print();</script>"
                + "     <script>//window.print();</script></html>";
        String novoCorpo = "";
        int cont = 0;
        for (Funcionario f : lista) {

            novoCorpo += corpo.replace("#CODIGO#", String.valueOf(f.getCodFuncionario()));
            novoCorpo = novoCorpo.replace("#NOME#", f.getNmFuncionario());
            novoCorpo = novoCorpo.replace("#CPF#", f.getCpfFuncionario());
            novoCorpo = novoCorpo.replace("#TELEFONE#", f.getTelefoneFuncionario());
            novoCorpo = novoCorpo.replace("#CELULAR#", f.getCelularFuncionario());
            cont++;
            if (cont % 2 == 0) {
                novoCorpo = novoCorpo.replace("#TR#", "tr0");
            } else {
                novoCorpo = novoCorpo.replace("#TR#", "tr1");
            }
        }
        return cabecalho + novoCorpo + rodape;
    }
    
       public static String geradorHtml(Fornecedor f) {
        String cabecalho = "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Relatório de Fornecedorres</title>\n"
                + "        <style>\n"
                + "            .relatorio{\n"
                + "                width: 800px;\n"
                + "                position: relative;\n"
                + "                left: -400px;\n"
                + "                margin-left: 50%;\n"
                + "            }\n"
                + "            img{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            h2,h3,h4{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            p{\n"
                + "                padding-left: 30px;\n"
                + "            }\n"
                + "            span{\n"
                + "                 margin-left: 20px;\n}"
                + "           \n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n";

        String corpo = ""
                + "        <div class='relatorio'>\n"
                + "           <img src=\"Logo%20Provisoria.png\">\n"
                + "            <h2>Relatório de Fornecedores</h2>\n"
                + "            <h3>#NOME#</h3>\n"
                + "            <p><strong>CNPJ:</strong> #CNPJ#<span></p>\n"
                + "            <p><strong>Telefone:</strong> #TELEFONE#  <span><strong>Celular:</strong> #CELULAR#</p>\n"
                + "            <p><strong>E-mail:</strong> #EMAIL#</p><br>\n"
                + "            <p><strong>CEP:</strong>#CEP#</p>\n"
                + "            <p><strong>UF:</strong>#UF#  <span><strong>Cidade:</strong>#CIDADE#</p>\n"
                + "            <p><strong>Endereço:</strong>#ENDERECO#</p>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "     <script>window.print();</script>"
                + "</html>";

        corpo = corpo.replace("#NOME#", f.getNmFornecedor());
        corpo = corpo.replace("#CNPJ#", f.getCnpjFornecedor());
        corpo = corpo.replace("#TELEFONE#", f.getTelefoneFornecedor());
        corpo = corpo.replace("#CELULAR#", f.getCelularFornecedor());
        corpo = corpo.replace("#EMAIL#", f.getEmailFornecedor());
        corpo = corpo.replace("#CEP#", f.getCepFornecedor());
        corpo = corpo.replace("#UF#", f.getCidadeFornecedor().getEstado().getNmEstado());
        corpo = corpo.replace("#CIDADE#", f.getCidadeFornecedor().getNmCidade());
        corpo = corpo.replace("#ENDERECO#", f.getEnderecoFornecedor());
        return cabecalho + corpo;
    }

    public static String geradorHtmlListaFornecedor(ArrayList<Fornecedor> lista) {
        String situacao = lista.get(0).getSituacaoFornecedor();
        String cabecalho = "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Relatório de Fornecedores</title>\n"
                + "        <style>\n"
                + "            td{\n"
                + "              /*  padding: 5px;\n"
                + "                padding-left: 8px;\n"
                + "                padding-right: 8px; */\n"
                + "                padding-top: 4px;\n"
                + "                padding-bottom: 4px;\n"
                + "                padding-left: 3px;\n"
                + "                border: 1px solid white;\n"
                + "            }\n"
                + "            .tr1{\n"
                + "                background-color: #f2f2f2;\n"
                + "            }\n"
                + "            .tr0{\n"
                + "                background-color: #e2e2e2;\n"
                + "            }\n"
                + "            table{\n"
                + "                min-width: 700px;\n"
                + "            }\n"
                + "            .relatorio{\n"
                + "                width: 800px;\n"
                + "                position: relative;\n"
                + "                left: -400px;\n"
                + "                margin-left: 50%;\n"
                + "                \n"
                + "            }\n"
                + "            img{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            h2,h3,h4{\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "            p{\n"
                + "                padding-left: 30px;\n"
                + "            }\n"
                + "            span{\n"
                + "                 margin-left: 20px;\n"
                + "}           \n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class='relatorio'>\n"
                + "           <img src=\"Logo%20Provisoria.png\">\n"
                + "            <h2>Relatório de Fornecedores " + situacao + "s</h2>\n"
                + "            <table>\n"
                + "                <tr class=\"tr0\">\n"
                + "                    <td><strong>Codigo</strong>\n"
                + "                    <td><strong>Nome</strong>\n"
                + "                    <td><strong>CNPJ</strong>\n"
                + "                    <td><strong>Telefone</strong>\n"
                + "                    <td><strong>Celular</strong>\n";

        String corpo = "<tr class=\"#TR#\">\n"
                + "                    <td>#CODIGO#\n"
                + "                    <td>#NOME#\n"
                + "                    <td>#CNPJ#\n"
                + "                    <td>#TELEFONE#\n"
                + "                    <td>#CELULAR#\n"
                + "                \n";
        String rodape = "            </table>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "     <script>window.print();</script>"
                + "     <script>//window.print();</script></html>";
        String novoCorpo = "";
        int cont = 0;
        for (Fornecedor f : lista) {

            novoCorpo += corpo.replace("#CODIGO#", String.valueOf(f.getCodFornecedor()));
            novoCorpo = novoCorpo.replace("#NOME#", f.getNmFornecedor());
            novoCorpo = novoCorpo.replace("#CNPJ#", f.getCnpjFornecedor());
            novoCorpo = novoCorpo.replace("#TELEFONE#", f.getTelefoneFornecedor());
            novoCorpo = novoCorpo.replace("#CELULAR#", f.getCelularFornecedor());
            cont++;
            if (cont % 2 == 0) {
                novoCorpo = novoCorpo.replace("#TR#", "tr0");
            } else {
                novoCorpo = novoCorpo.replace("#TR#", "tr1");
            }
        }
        return cabecalho + novoCorpo + rodape;
    }
}