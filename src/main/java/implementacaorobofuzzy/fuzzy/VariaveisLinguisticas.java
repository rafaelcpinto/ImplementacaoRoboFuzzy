package implementacaorobofuzzy.fuzzy;

public class VariaveisLinguisticas {

    public boolean pertoY(int valor) {
        return 0 <= valor && valor < 2;
    }

    public boolean medioY(int valor) {
        return 1 <= valor && valor < 4;
    }

    public boolean longeY(int valor) {
        return 3 <= valor && valor <= 5;
    }

    public boolean pertoX(int valor) {
        return valor == 0;
    }

    public boolean longeX(int valor) {
        return valor > 0;
    }
}
