package implementacaorobofuzzy.fuzzy;

public class Fuzzyficacao {

    public double calcPerto(double input) {
        if (input >= 0.0 && input <= 0.25) {
            return 1.0;
        }

        if (input > 0.25 && input <= 0.50) {
            return (0.50 - input) / 0.25;
        }

        return 0.0;
    }

    public double calcMedio(double input) {
        if (input >= 0.25 && input <= 0.50) {
            return (input - 0.25) / 0.25;
        }

        if (input > 0.50 && input <= 0.75) {
            return (0.75 - input) / 0.25;
        }

        return 0.0;
    }

    public double calcLonge(double input) {
        if (input >= 0.50 && input < 0.75) {
            return (input - 0.50) / 0.25;
        }

        if (input >= 0.75 && input <= 1.0) {
            return 1.0;
        }

        return 0.0;
    }
}