package conexao;

// Bibliotecas
import java.sql.Connection; //Import das Classes 
import java.sql.DriverManager; //Conexao SQL p/ Java
import java.sql.SQLException; //Tratamento de Exceções

// Classe Conexão com o Banco de Dados
public class ConnectionX {
    public Connection getConnection() {
        // Tenta...
        try {
            // ...iniciar a Conexão c/ o BD (Driver:Local:Porta/Nome do BD,Usuario,Senha)
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/contas?useTimezone=true&serverTimezone=UTC","root","");
        }
        // Tratamento de Exceção do SQL
        catch(SQLException excecao) {
            throw new RuntimeException(excecao);
        }

    }
}