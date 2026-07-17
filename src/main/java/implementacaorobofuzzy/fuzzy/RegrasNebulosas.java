package implementacaorobofuzzy.fuzzy;

import implementacaorobofuzzy.ambiente.Memoria;

public class RegrasNebulosas {

    public static Double valorFuzzyXn;
    public static Double valorFuzzyXp;
    public static Double valorFuzzyY;
    public static Double valorFuzzyY2;

    public static boolean logicXpPerto;
    public static boolean logicXpLonge;
    public static boolean logicXnPerto;
    public static boolean logicXnLonge;
    public static boolean logicYperto;
    public static boolean logicYlonge;

    static boolean salvaPos;

    public void executaRegras(Memoria memoria) {
        valorFuzzyXn = 0.0;
        valorFuzzyXp = 0.0;
        valorFuzzyY = 0.0;
        valorFuzzyY2 = 0.0;

        logicXpPerto = false;
        logicXpLonge = false;
        logicXnPerto = false;
        logicXnLonge = false;
        logicYperto = false;
        logicYlonge = false;

        Fuzzyficacao fuzzyficacao = new Fuzzyficacao();
        VariaveisLinguisticas variaveis = new VariaveisLinguisticas();

        if (variaveis.pertoY(memoria.diferencaYn)) {
            valorFuzzyY = fuzzyficacao.perto(memoria.diferencaYn);
            executaFuzzyXn(memoria);
            executaFuzzyXp(memoria);
        } else if (variaveis.medioY(memoria.diferencaYn)) {
            double aux = fuzzyficacao.medio(memoria.diferencaYn);
            if (valorFuzzyY < aux || valorFuzzyY == 0) {
                valorFuzzyY = aux;
            }
            logicYperto = true;
        } else if (variaveis.longeY(memoria.diferencaYn)) {
            double aux = fuzzyficacao.longe(memoria.diferencaYn);
            if (valorFuzzyY < aux || valorFuzzyY == 0) {
                valorFuzzyY = aux;
            }
            logicYlonge = true;
        }
        valorFuzzyY2 = valorFuzzyY;
    }

    public void executaFuzzyXn(Memoria memoria) {
        Fuzzyficacao fuzzyficacao = new Fuzzyficacao();
        VariaveisLinguisticas variaveis = new VariaveisLinguisticas();

        if (variaveis.pertoX(memoria.diferencaXn)) {
            valorFuzzyXn = fuzzyficacao.perto(memoria.diferencaXn);
            logicXnPerto = true;
            salvaPos = true;
        } else if (variaveis.longeX(memoria.diferencaXn) && !salvaPos) {
            double aux = fuzzyficacao.longe(memoria.diferencaXn);

            if (valorFuzzyXn < aux || valorFuzzyXn == 0) {
                valorFuzzyXn = aux;
            }
            logicXnLonge = true;
        }
    }

    public void executaFuzzyXp(Memoria memoria) {
        Fuzzyficacao fuzzyficacao = new Fuzzyficacao();
        VariaveisLinguisticas variaveis = new VariaveisLinguisticas();

        if (variaveis.pertoX(memoria.diferencaXp)) {
            valorFuzzyXp = fuzzyficacao.perto(memoria.diferencaXp);
            logicXpPerto = true;
            salvaPos = false;
        } else if (variaveis.longeX(memoria.diferencaXp) && salvaPos) {
            double aux = fuzzyficacao.longe(memoria.diferencaXp);
            if (valorFuzzyXp < aux || valorFuzzyXp == 0) {
                valorFuzzyXp = aux;
            }
            logicXpLonge = true;
        }
    }
}
