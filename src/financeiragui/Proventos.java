package financeiragui;

//Biblioteca
import java.util.Set;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

//Biblioteca Interna
import dao.UsuarioDAO;
import java.sql.Connection;

// Classe Derivada da Classe-Pai
public class Proventos extends Contas {
    // Variável Privada (Uso Exclusivo da Classe)
    private double imposto;
    private Connection connection;

    // Formatando p/ 2 Casas Decimais nos Valor
    DecimalFormat formato = new DecimalFormat("0.00");

    /* Construtores
        + Proventos(id: int, mes: int, ano: int, valor: double, pessoa: Pessoas, imposto: double): Proventos 
        + Proventos(mes: int, ano: int, valor: double, pessoa: Pessoas, imposto: double): Proventos 
        + Proventos(idPessoa: int): Set<Proventos>
    */
    public Proventos(final int id, final int mes, final int ano, final double valor, final Pessoas pessoa,
            final double imposto) {
        // Variáveis de Herança da Classe-Pai
        super(id, mes, ano, valor, pessoa);
        this.imposto = imposto;
    }

    public Proventos(final int mes, final int ano, final double valor, final Pessoas pessoa, final double imposto) {
        this(0, mes, ano, valor, pessoa, imposto);

        // Create Provento no BD
        final UsuarioDAO dao = new UsuarioDAO();
        dao.adicionarProventos(this);
        dao.endConection();
    }

    public static Set<Proventos> Provento(final int idPessoa) {
        // Select no banco e retornar o Set<Dividas>
        return null;
    }

    public static void getProventos(final int idPessoa) {
        final UsuarioDAO dao = new UsuarioDAO();
        dao.getProventos(idPessoa);
        dao.endConection();
    }

    /* Métodos Get - Set 
        + getImposto(): double 
        + setImposto(imposto: double): void
    */
    public Proventos(final double imposto) {
        this.imposto = imposto;
    }

    public double getImposto() {
        return this.imposto;
    }

    public void setImposto(final double imposto) {
        this.imposto = imposto;
    }

    public Proventos imposto(final double imposto) {
        this.imposto = imposto;
        return this;
    }

    /* Métodos ToString 
        + toString(): String 
    */
    @Override
    public String toString() {
        return 
            "Pessoa: " + pessoa.getNomePessoa() + "\n" +
            ">> Proventos >>=============================" +  "\n" +
            "Valor do Provento: R$ " + getValor() + " %\n" +
            "Imposto de " + getImposto() + " %\n" +
            "Valor Com Imposto R$ : " + valorImposto() + "\n" +
            "-------------------------------------------" +
            "Valor Total dos Proventos :" + calcularValorFinal();
    }

    // Impressão dos Proventos da Pessoa
    public void imprimirProventos() {
        System.out.println("Cliente: " + this.getNomePessoa());
        System.out.println("Quantidade de Proventos: " + this.quantidadeDeProventos());
        valorProventos();
    }

    private String getNomePessoa() {
        return null;
    }

    private int quantidadeDeProventos() {
        return 0;
    }

    private void valorProventos() {
    }
    /* Método Calculo do Imposto dos Proventos
        - calculaImposto(): double
    */
    private String valorImposto() {
        final double valorImposto = getValor() + (getValor() * (imposto / 100));
        return this.formato.format(valorImposto);
    }

    private String calcularValorFinal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}