package implementacaorobofuzzy.fuzzy.functions;

/**
 * Contrato para uma funcao que transforma um valor de entrada em um grau de
 * pertinencia no intervalo de 0 a 1.
 */
public sealed interface FuncaoPertinencia
        permits FuncaoTriangular, FuncaoTrapezoidal {

    double calcular(double entrada);
    double getCentroide();
}
