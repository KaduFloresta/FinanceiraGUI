package dao;

//Bibliotecas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Bibliotecas Internas
import conexao.ConnectionX;
import financeiragui.Dividas;
import financeiragui.Pessoas;
import financeiragui.Proventos;

// Classe CRUD
public class UsuarioDAO {
    private Connection connection;
    int id;
    String nomePessoa;
    String emailPessoa;
    double percDesconto;
    double imposto;
    int idConta;
    int mes;
    int ano;
    double valor;

    // Construtor CRUD (Criação, Leitura/Consulta, Alteração/Atualiação e Exclusão)
    public UsuarioDAO() {
        this.connection = new ConnectionX().getConnection();
    }

    // Cadastro de Pessoas no BD
    public void adicionaPessoa(Pessoas pessoa) {
        // Comando SQL p/ Coleta de Dados do BD
        String sql = "INSERT INTO pessoas (nome,email) VALUES (?,?)";
        // Tenta...
        try {
            // ...Statement p/Executar Instruções do SQL ou...
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomePessoa());
            stmt.setString(2, pessoa.getEmailPessoa());
            stmt.execute();
            // ...tratamento de Exceção do SQL
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

    }

    // Cadastro de Proventos no BD
    public void adicionarProventos(Proventos provento) {
        // Comando SQL p/ Coleta de Dados do BD
        String sql1 = "INSERT INTO proventos (pessoas_id,mes,ano,valor,imposto) VALUES (?,?,?,?,?)";
        try {
            // Statement p/Executar Instruções do SQL
            PreparedStatement stmt = connection.prepareStatement(sql1);
            stmt.setInt(1, provento.getPessoa().getId());
            stmt.setInt(2, provento.getMes());
            stmt.setInt(3, provento.getAno());
            stmt.setDouble(4, provento.getValor());
            stmt.setDouble(5, provento.getImposto());
            stmt.execute();
            // Tratamento de Exceção do SQL
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    // Cadastro de Dividas no BD
    public void adicionarDividas(Dividas divida) {
        // Comando SQL p/ Coleta de Dados do BD
        String sql2 = "INSERT INTO dividas (pessoas_id,mes,ano,valor,percentual_desconto) VALUES (?,?,?,?,?)";
        try {
            // Statement p/Executar Instruções do SQL
            PreparedStatement stmt = connection.prepareStatement(sql2);
            stmt.setInt(1, divida.getPessoa().getId());
            stmt.setInt(2, divida.getMes());
            stmt.setInt(3, divida.getAno());
            stmt.setDouble(4, divida.getValor());
            stmt.setDouble(5, divida.getPercDesconto());
            stmt.execute();
            // Tratamento de Exceção do SQL
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    // Coletando Pessoa Cadastrada no BD pela PK
    public Pessoas getPessoa(int id) {
        // Comando SQL p/ Coleta de Dados do BD
        String sql = "SELECT * FROM pessoas WHERE id = " + id;
        try {
            // Statement p/Executar Instruções do SQL
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                return new Pessoas(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"));
            }
            // Tratamento de Exceção do SQL
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return null;
    }

    // Coletando Relação de Pessoas Cadastradas no BD
    public void getPessoas() {
        // Comando SQL p/ Coleta de Dados do BD
        String sql = "SELECT * FROM pessoas";
        try {
            // Statement p/Executar Instruções do SQL
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Pessoas pessoa = new Pessoas(resultSet.getInt("id"), resultSet.getString("nome"),
                        resultSet.getString("email"));
                // Impressão do ToString (Classe Pessoa)
                System.out.println(pessoa.toString());
            }
            // Tratamento de Exceção do SQl
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    // Coletando Relação de Dividas Cadastradas no BD
    public Pessoas getDividas(int id) {
        // Comando SQL p/ Coleta de Dados do BD
        String sql = "SELECT * FROM dividas WHERE pessoas_id = " + id;
        try {
            // Statement p/Executar Instruções do SQL
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                // Inserção de Dados "Dívidas" no BD pelo ID da Pessoa
                Pessoas pessoa = getPessoa(resultSet.getInt("pessoas_id"));

                Dividas divida = new Dividas(resultSet.getInt("id"), resultSet.getInt("mes"), resultSet.getInt("ano"),
                        resultSet.getDouble("valor"), pessoa, resultSet.getDouble("percentual_desconto"));

                System.out.println(divida.toString());
            }
            // Tratamento de Exceção do SQL
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return null;
    }

    // Coletando Relação de Proventos Cadastrados no BD
    public Pessoas getProventos(int id) {
        // Comando SQL p/ Coleta de Dados do BD
        String sql = "SELECT * FROM proventos WHERE pessoas_id = " + id;
        try {
            // Statement p/Executar Instruções do SQL
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                // Inserção de Dados "Proventos" no BD pelo ID da Pessoa
                Pessoas pessoa = getPessoa(resultSet.getInt("pessoas_id"));

                Proventos provento = new Proventos(resultSet.getInt("id"), resultSet.getInt("mes"),
                        resultSet.getInt("ano"), resultSet.getDouble("valor"), pessoa, resultSet.getDouble("imposto"));

                System.out.println(provento.toString());
            }
            // Tratamento de Exceção do SQL
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return null;
    }

    // Final da Conexão c/ o BD
    public void endConection() {
        try {
            // Encerra a Conexão ou...
            this.connection.close();
            // ...Tratamento de Exceção do SQL
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}