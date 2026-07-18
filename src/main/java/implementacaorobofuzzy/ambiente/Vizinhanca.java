package implementacaorobofuzzy.ambiente;

public class Vizinhanca {
    private final Integer[][] matriz;

    public Vizinhanca(Integer[][] matriz) {
        this.matriz = matriz;
    }

    public Integer[][] getMatriz() {
        return matriz;
    }
}
