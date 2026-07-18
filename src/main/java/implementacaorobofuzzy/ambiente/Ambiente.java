package implementacaorobofuzzy.ambiente;

import java.util.concurrent.ThreadLocalRandom;

public class Ambiente {
    Sala sala;
    ConsultaVizinhanca cv;
    Posicao posicao;
    Posicao porta;
    public Ambiente() {
        sala= new Sala();
        cv = new ConsultaVizinhanca();
        posicao = new Posicao(0, 0);
        int posicaoPortaX = ThreadLocalRandom.current().nextInt(sala.getMatriz()[0].length);
        porta = new Posicao(posicaoPortaX, sala.getMatriz().length - 1);
        criaObstaculos();
        sala.getMatriz()[porta.getY()][porta.getX()] = 2;
    }
    //public Posicao getPosicao() {return posicao;}
    public ConsultaVizinhanca getCv() {return cv;}
    public void addDeslocamento(int x, int y){
        int origemX = posicao.getX();
        int origemY = posicao.getY();
        int destinoX = origemX + x;
        int destinoY = origemY + y;

        if (passaPelaPorta(origemX, origemY, destinoX, destinoY)) {
            posicao = new Posicao(porta.getX(), porta.getY());
            return;
        }

        this.posicao.addDesloc(x, y);
    }
    public boolean chegouNaPorta() {
        return posicao.getX() == porta.getX() && posicao.getY() == porta.getY();
    }
    public Posicao getPosicao() {
        return new Posicao(posicao.getX(), posicao.getY());
    }
    public Posicao getPorta() {
        return new Posicao(porta.getX(), porta.getY());
    }
    public Vizinhanca getVizinhanca(){
        return cv.consultar(sala,posicao,10);
    }

    private boolean passaPelaPorta(int origemX, int origemY, int destinoX, int destinoY) {
        boolean movimentoHorizontal = origemY == porta.getY()
                && destinoY == porta.getY()
                && estaEntre(porta.getX(), origemX, destinoX);
        boolean movimentoVertical = origemX == porta.getX()
                && destinoX == porta.getX()
                && estaEntre(porta.getY(), origemY, destinoY);

        return movimentoHorizontal || movimentoVertical;
    }

    private boolean estaEntre(int valor, int inicio, int fim) {
        return valor >= Math.min(inicio, fim) && valor <= Math.max(inicio, fim);
    }

    private void criaObstaculos() {
        int[][] faixasDeLinhas = {
                {6, 12},
                {14, 20},
                {22, 28},
                {30, 36},
                {38, 44}
        };
        int larguraAbertura = 11;
        int larguraSala = sala.getMatriz()[0].length;

        for (int[] faixa : faixasDeLinhas) {
            int linha = ThreadLocalRandom.current().nextInt(faixa[0], faixa[1] + 1);
            int inicioAbertura = ThreadLocalRandom.current()
                    .nextInt(larguraSala - larguraAbertura + 1);
            criaBarreiraHorizontal(linha, inicioAbertura, larguraAbertura);
        }
    }

    private void criaBarreiraHorizontal(int linha, int inicioAbertura, int larguraAbertura) {
        Integer[][] matriz = sala.getMatriz();
        for (int x = 0; x < matriz[linha].length; x++) {
            boolean abertura = x >= inicioAbertura && x < inicioAbertura + larguraAbertura;
            matriz[linha][x] = abertura ? 0 : 1;
        }
    }
}
