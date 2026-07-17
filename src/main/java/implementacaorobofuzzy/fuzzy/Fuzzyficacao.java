package implementacaorobofuzzy.fuzzy;

public class Fuzzyficacao {

    public double perto(int a) {
        double retorno = 0.0;
        if (a < 2) {
            retorno = (2 - (double) a) / 2;
        }
        return retorno;
    }

    public double medio(int a) {
        double retorno = 0;
        if (a > 1 && a < 3) {
            retorno = ((double)a - 1) / (3 - 1);
        }
        if (a >= 3 && (double)a <= 4) {
            retorno = (4 - (double)a) / (4 - 3);
        }
        return retorno;
    }

    public double longe(int a) {
        double retorno = 0;
        if (a >= 3 && a <= 5) {
            retorno = ((double)a - 3) / (5 - 3);
        }
        return retorno;
    }
}
