package br.com.hardsoftware.mercearia.dao;

import br.com.hardsoftware.mercearia.infra.ConnectionFactory;
import br.com.hardsoftware.mercearia.model.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EstadoDao {

    public Estado findByCod(int cod) {
        String sql = "SELECT * FROM tbestado where cdEstado = ?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setInt(1, cod);
            
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Estado c = new Estado();
                    c.setNmEstado(rs.getString("nmEstado"));
                    c.setCodEstado(rs.getInt("cdEstado"));
                    c.setSiglaEstado(rs.getString("siglaEstado"));
                    return c;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding state by code", e);
        }

        return null;
    }

    public ArrayList<Estado> findAll() {
        String sql = "SELECT * FROM tbestado";
        ArrayList<Estado> array = new ArrayList<>();
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setCodEstado(rs.getInt("cdEstado"));
                estado.setNmEstado(rs.getString("nmEstado"));
                estado.setSiglaEstado(rs.getString("siglaEstado"));
                array.add(estado);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding all states", e);
        }

        return array;
    }

}
