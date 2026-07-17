package implementacaorobofuzzy.fuzzy;

public class Defuzzyficacao {

    public Double moveCurtoY(Double a) {
        return 2 * a + 1;
    }

    public Double moveLongoY(Double a) {
        return 2 * a + 3;
    }

    public Double moveCurtoX(Double a) {
        return a;
    }

    public Double moveLongoX(Double a) {
        return 1.0;
    }
}
