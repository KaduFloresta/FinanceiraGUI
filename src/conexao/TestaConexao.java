package conexao;

// Bibliotecas
import conexao.ConnectionX;
import java.sql.Connection;
import java.sql.SQLException;

// Classe p/ Testa a Conexão
public class TestaConexao {

    public static void main(String[] args) throws SQLException { // Main do Teste
        Connection connection = new ConnectionX().getConnection();

        // Impressão Caso Haja Conexão c/ o BD
        System.out.println("Conexão Aberta!");
        connection.close();

    }
}