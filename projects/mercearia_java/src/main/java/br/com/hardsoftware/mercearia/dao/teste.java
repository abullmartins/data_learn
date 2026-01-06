/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.model.Estado;
import java.util.ArrayList;

/**
 *
 * @author Alysson
 */
public class teste {
    public static void main(String[] args) {
    
        
        CidadeDao cd = new CidadeDao();
        Estado e = new Estado();
        e.setCodEstado(1);
        ArrayList cidadeList = cd.findByEstado(e);
        for (int i = 0; i <cidadeList.size(); i++) {
            System.out.println(i);
        }
        
        /*
        EstadoDao cd = new EstadoDao();
        Estado e = new Estado();
        e.setCodEstado(1);
        ArrayList cidadeList = cd.findAll();
        for (int i = 0; i <cidadeList.size(); i++) {
            System.out.println(i);
        }*/
    }
    
}
