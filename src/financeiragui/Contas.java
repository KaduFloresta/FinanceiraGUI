package financeiragui;

// Biblioteca
import java.util.Objects;

// Classe-Pai
public class Contas {
    // Variáveis: "Herança" p/ as Classes Dividas e Proventos
    private int id;
    private int mes;
    private int ano;
    private double valor;
    Pessoas pessoa;

    /*
     * Métodos / Construtores + Conta(): Contas + Conta(id: int, mes: int,ano: int,
     * valor: double, pessoa: Pessoas): Contas
     */
    public Contas() {
    }

    public Contas(int id, int mes, int ano, Double valor, Pessoas pessoa) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.pessoa = pessoa;
    }
    /* Métodos Get - Set 
        + getId(): int 
        + setId(id: int): void 
        + getMes(): int 
        + setMes(mes: int): void 
        + getAno(): int + setAno(ano: int): void 
        + getValor(): double 
        + setValor(valor: double): void 
        + getPessoa(): Pessoas 
        + setPessoa(pessoa: Pessoas): void
    */
    public Contas(int id, int mes, int ano, double valor, Pessoas pessoa) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.pessoa = pessoa;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMes() {
        return this.mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Pessoas getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
    }

    public Contas id(int id) {
        this.id = id;
        return this;
    }

    public Contas mes(int mes) {
        this.mes = mes;
        return this;
    }

    public Contas ano(int ano) {
        this.ano = ano;
        return this;
    }

    public Contas valor(double valor) {
        this.valor = valor;
        return this;
    }

    public Contas pessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
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
        if (!(o instanceof Contas)) {
            return false;
        }
        Contas contas = (Contas) o;
        return id == contas.id && mes == contas.mes && ano == contas.ano && valor == contas.valor
                && Objects.equals(pessoa, contas.pessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mes, ano, valor, pessoa);
    }
    @Override
    public String toString() {
        return 
            "ID da Conta: " + getId() + "\n" +
            "Mes: " + getMes() + "\n" +
            "Ano: " + getAno() + "\n" +
            "Valor: " + getValor() + "\n" +
            "Pessoa: " + getPessoa() + "\n" +
            "}";
    } 
}