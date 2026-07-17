package implementacaorobofuzzy.ambiente;

import java.util.Random;

public final class CriaAmbiente {

    public static Integer ambiente[][] = new Integer[50][50];
    public static String amb = "";
    public static int posicaoDaporta;
    private final Random gerador = new Random();

    public void criaAmbiente() {
        criaAmbienteVazio();
        criaParedes();
        amb = "";
        criaBarreirasInternas();
        imprime();
    }

    public void criaAmbienteVazio() {
        for (int linha = 0; linha < ambiente.length; linha++) {
            for (int coluna = 0; coluna < ambiente.length; coluna++) {
                ambiente[linha][coluna] = 0;
            }
        }
    }

    public void criaParedes() {
        for (int linha = 0; linha < ambiente.length; linha++) {
            ambiente[linha][0] = 1;
        }
        for (int linha = 0; linha < ambiente.length; linha++) {
            ambiente[linha][ambiente.length - 1] = 1;
        }
        for (int coluna = 0; coluna < ambiente.length; coluna++) {
            ambiente[0][coluna] = 1;
        }
        for (int coluna = 0; coluna < ambiente.length; coluna++) {
            ambiente[ambiente.length - 1][coluna] = 1;
        }
        posicaoDaporta = 1 + gerador.nextInt(48);
        ambiente[ambiente.length - 1][posicaoDaporta] = 2;
    }

    public void criaBarreirasInternas() {
        int aberturaAnterior = -1;

        for (int linha = 6; linha < ambiente.length - 4; linha = linha + 6) {
            int abertura = sorteiaAbertura(aberturaAnterior);
            aberturaAnterior = abertura;
            int larguraAbertura = 3;

            for (int coluna = 1; coluna < ambiente.length - 1; coluna++) {
                if (coluna < abertura || coluna >= abertura + larguraAbertura) {
                    ambiente[linha][coluna] = 1;
                }
            }
        }
    }

    private int sorteiaAbertura(int aberturaAnterior) {
        int abertura = 2 + gerador.nextInt(ambiente.length - 6);
        int tentativas = 0;

        while (aberturaAnterior != -1
                && Math.abs(abertura - aberturaAnterior) < 10
                && tentativas < 20) {
            abertura = 2 + gerador.nextInt(ambiente.length - 6);
            tentativas++;
        }

        return abertura;
    }

    public void imprime() {
        for (int linha = 0; linha < ambiente.length; linha++) {
            for (int coluna = 0; coluna < ambiente.length; coluna++) {
                amb = amb + ambiente[linha][coluna].toString();
                amb = amb + "\t";
            }
            amb = amb + "\n";
        }
    }
}
