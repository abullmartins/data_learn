package br.com.hardsoftware.mercearia.conexao;

import br.com.hardsoftware.mercearia.infra.ConnectionFactory;
import java.sql.Connection;

public class Conexao {

    public static String status = "Conectado via ConnectionFactory";

    /**
     * @deprecated Use ConnectionFactory.getConnection() inside a try-with-resources block instead.
     */
    @Deprecated
    public static Connection getConexao() {
        return ConnectionFactory.getConnection();
    }
    
    public static String statusConection() {
        return status;
    }

    public static boolean FecharConexao() {
        return true;
    }

    public static Connection ReiniciarConexao() {
        return getConexao();
    }
}
