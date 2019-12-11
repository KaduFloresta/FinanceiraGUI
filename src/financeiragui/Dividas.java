package financeiragui;

//Biblioteca
import java.text.DecimalFormat;

//Biblioteca Interna
import dao.UsuarioDAO;

// Classe Derivada da Classe-Pai
public class Dividas extends Contas {
    // Variável Privada (Uso Exclusivo da Classe)
    private double percDesconto;

    // Formatando p/ 2 Casas Decimais nos Valor
    DecimalFormat formato = new DecimalFormat("0.00");

    /* Construtores 
        + Divida(id: int, mes: int, ano: int, valor: double, pessoa: Pessoas, percentualDesconto: double): Dividas 
        + Divida(mes: int, ano: int, valor: double, pessoa: Pessoas, percentualDesconto: double): Dividas 
        + Dividas(idPessoa: int): Set<Dividas>
    */
    public Dividas(int idConta, int mes, int ano, double valor, Pessoas pessoa, double percDesconto) {
        super(idConta, mes, ano, valor, pessoa);
        this.percDesconto = percDesconto;
    }

    public Dividas(int mes, int ano, double valor, Pessoas pessoa, double percDesconto) {
        this(0, mes, ano, valor, pessoa, percDesconto);

        // Create Divida no BD
        UsuarioDAO dao = new UsuarioDAO();
        dao.adicionarDividas(this);
        dao.endConection();
    }

    // public static Set<Dividas> Dividas(int idPessoa) {
    // Set<Dividas> dividas = new HashSet<>();
    // }

    public static void getDividas(int idPessoa) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.getDividas(idPessoa);
        dao.endConection();
    }

    /* Métodos Get - Set 
        + getPercentualDesconto(): double 
        + setPercentualDesconto(percentualDesconto: double): void
    */
    public double getPercDesconto() {
        return this.percDesconto;
    }

    public void setPercDesconto(double percDesconto) {
        this.percDesconto = percDesconto;
    }

    public Dividas percDesconto(double percDesconto) {
        this.percDesconto = percDesconto;
        return this;
    }

    /* Método ToString 
        + toString(): String
    */
    @Override
    public String toString() {
        return 
        "Cliente: " + pessoa.getNomePessoa() + "\n" +
        ">> Divida >>===============================" +  "\n" +
        "Valor da Dívida: R$ " + getValor() + " %\n" +
        "Desconto na Dívida: " + getPercDesconto() + " %\n" +
        "Valor c/ Desconto: R$ " + valorDesconto() + "\n" +
        "-------------------------------------------";
    }

    public void imprimirDividas() {
        System.out.println("Cliente: " + this.getNomePessoa());
        System.out.println("Quantidade de Dividas: " + this.quantidadeDeDividas());
        valorDividas();
    }

    private void valorDividas() {
    }

    private String getNomePessoa() {
        return null;
    }

    private int quantidadeDeDividas() {
        return 0;
    }
    /* Método Calculo Desconto da Divida 
        - calculaDesconto(): double
    */
    private String valorDesconto() {
        double valorDesconto = getValor() - (getValor() * (percDesconto / 100));
        return this.formato.format(valorDesconto);
    }

}