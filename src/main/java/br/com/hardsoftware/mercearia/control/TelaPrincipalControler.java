/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.control;

/**
 *
 * @author Erbert
 */
public class TelaPrincipalControler {
    
    public static void iniciarCliente() {
        ClienteControle.iniciarCliente();
    }
    
    public static void iniciarFuncionario() {
        FuncionarioControle.iniciarFuncionario();
    }
    
    public static void iniciarProduto() {
        ProdutoControle.iniciarProduto();
    }
    
    public static void iniciarFornecedor() {
        FornecedorControle.iniciarFornecedor();
    }
}
