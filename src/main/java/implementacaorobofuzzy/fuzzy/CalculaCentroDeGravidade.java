package implementacaorobofuzzy.fuzzy;

public class CalculaCentroDeGravidade {

    public double calculaCentroDeGravidade(double a, double b, double aa, double bb) {
        double num = a * aa + b * bb;
        double den = a + b;

        if (den == 0) {
            return 0;
        }

        return num / den;
    }
}
