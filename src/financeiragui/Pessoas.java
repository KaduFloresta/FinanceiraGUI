package financeiragui;

//Biblioteca
import java.util.Objects;

//Biblioteca Interna
import dao.UsuarioDAO;

public class Pessoas {
    // Variáveis Privadas (Uso da Classe)
    private int id;
    private String nomePessoa;
    private String emailPessoa;

    /* Construtores + Pessoa(): Pessoas 
        + Pessoa(id: int): Pessoas 
        + Pessoa(id: int, nome: String, email: String): Pessoas + Pessoa(nome: String, email: String): Pessoas 
        + Pessoas(): Set<Pessoas>
    */
    public Pessoas() {
    }

    public Pessoas(int id) {
        this.id = id;
    }

    public Pessoas(int id, String nomePessoa, String emailPessoa) {
        this.id = id;
        this.nomePessoa = nomePessoa;
        this.emailPessoa = emailPessoa;
    }

    public Pessoas(String nomePessoa, String emailPessoa) {
        this.nomePessoa = nomePessoa;
        this.emailPessoa = emailPessoa;

        // Create Pessoa no BD
        UsuarioDAO dao = new UsuarioDAO();
        dao.adicionaPessoa(this);
        dao.endConection();
    }

    // Coletando "Relação de Pessoas" do BD
    public static void getPessoas() {
        UsuarioDAO dao = new UsuarioDAO();
        dao.getPessoas();
        dao.endConection();
    }

    // Coletando "Pessoa" do BD
    public static Pessoas getPessoa(int id) {
        UsuarioDAO dao = new UsuarioDAO();
        Pessoas pessoa = dao.getPessoa(id);
        dao.endConection();
        return pessoa;
    }

    /* Métodos Get - Set + getId(): int 
        + setId(id: int): void 
        + getNome(): String 
        + setNome(nome: String): void 
        + getEmail(): String 
        + setEmail(email: String): void
    */
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePessoa() {
        return this.nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getEmailPessoa() {
        return this.emailPessoa;
    }

    public void setEmailPessoa(String emailPessoa) {
        this.emailPessoa = emailPessoa;
    }

    public Pessoas id(int id) {
        this.id = id;
        return this;
    }

    public Pessoas nomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
        return this;
    }

    public Pessoas emailPessoa(String emailPessoa) {
        this.emailPessoa = emailPessoa;
        return this;
    }

    /* Métodos Equals - Hashcode - ToString 
        + equals(o: Object): boolean 
        + hashCode(): int 
        + toString(): String
    */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pessoas)) {
            return false;
        }
        Pessoas pessoas = (Pessoas) o;
        return id == pessoas.id && Objects.equals(nomePessoa, pessoas.nomePessoa)
                && Objects.equals(emailPessoa, pessoas.emailPessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomePessoa, emailPessoa);
    }

    @Override
    public String toString() {
        return 
            "===========================================\n" +
            "ID do Cliente: " + getId() + "\n" +
            "Nome: " + getNomePessoa() + "\n" +
            "Email: " + getEmailPessoa() + "\n" +
            "-------------------------------------------";
    }
}