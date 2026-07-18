package implementacaorobofuzzy.fuzzy.dominio;

public class Data {
    private final String nome;
    private double valor;
    private double centro;

    public Data(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public double getCentro() {
        return centro;
    }

    public void setCentro(double centro) {
        this.centro = centro;
    }
}
