package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.infra.ConnectionFactory;
import br.com.hardsoftware.mercearia.model.Cidade;
import br.com.hardsoftware.mercearia.model.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CidadeDao {

    public Cidade findByCod(int cod) {
        String sql = "SELECT * FROM tbcidade where cdCidade = ?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, cod);
            
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Cidade c = new Cidade();
                    c.setNmCidade(rs.getString("nmCidade"));
                    c.setCdCidade(rs.getInt("cdCidade"));
                    EstadoDao e = new EstadoDao();
                    c.setEstado(e.findByCod(rs.getInt("tbEstado_cdEstado")));
                    return c;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding city by code", e);
        }

        return null;
    }

    public ArrayList<Cidade> findByEstado(Estado estado) {
        String sql = "SELECT * FROM tbcidade where tbestado_cdEstado = ?";
        ArrayList<Cidade> array = new ArrayList<>();
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, estado.getCodEstado());
            
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Cidade cidade = new Cidade();
                    cidade.setCdCidade(rs.getInt("cdCidade"));
                    cidade.setNmCidade(rs.getString("nmCidade"));
                    cidade.setEstado(estado);
                    array.add(cidade);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding cities by state", e);
        }

        return array;
    }

}
