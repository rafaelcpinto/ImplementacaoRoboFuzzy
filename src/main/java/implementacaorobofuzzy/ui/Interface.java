package implementacaorobofuzzy.ui;

public class Interface {
    public void imprimePorta(int valor) {
        System.out.println("A PORTA ESTA LOCALIZADA NA POSICACAO: " + valor);
    }

    public void coordenadas(int valorX, int valorY) {
        System.out.println("coordenadaX: " + valorX);
        System.out.println("coordenadaY: " + valorY + "\n");
    }

    public void resultado(boolean chegou, int valorX, int valorY) {
        if (chegou) {
            System.out.println("O ROBO CHEGOU NA PORTA.");
        } else {
            System.out.println("O ROBO NAO CHEGOU NA PORTA.");
        }
        System.out.println("posicaoFinalX: " + valorX);
        System.out.println("posicaoFinalY: " + valorY);
    }
}
