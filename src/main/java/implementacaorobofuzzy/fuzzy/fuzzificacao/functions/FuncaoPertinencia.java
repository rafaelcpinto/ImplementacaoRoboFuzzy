package implementacaorobofuzzy.fuzzy.fuzzificacao.functions;

/**
 * Contrato para uma funcao que transforma um valor de entrada em um grau de
 * pertinencia no intervalo de 0 a 1.
 */
public interface FuncaoPertinencia {

    double calcular(double entrada);
    double getCentroide();
}
