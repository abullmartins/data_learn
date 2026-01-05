/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.control;

import br.com.hardsoftware.mercearia.dao.LoginDao;
import br.com.hardsoftware.mercearia.model.Funcionario;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alyssonkelvim
 */
public class LoginControle {
    private static Funcionario funcOnline;
    private static Funcionario funcSuperOnline;
    
    public static Funcionario getFuncOnline(){
        return funcOnline;
    }
    
    public static Funcionario getFuncSuperOnline(){
        return funcSuperOnline;
    }
    
    public static Funcionario logar(String usuario, String senha){
        
        try {
            funcOnline = LoginDao.login(usuario, senha);
            return funcOnline;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Funcionario logarSuper(String usuario, String senha){
        
        try {
            funcSuperOnline = LoginDao.login(usuario, senha);
            return funcSuperOnline;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Funcionario testeLogin(String usuario, String senha){
        
        try {
            return LoginDao.login(usuario, senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
