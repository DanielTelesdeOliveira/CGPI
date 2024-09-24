package retangulo;
import java.awt.Graphics;
import java.awt.Color;
/**
 * Escreva uma descrição da classe FiguraRetangulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraRetangulo
{
    /**
     * Desenha uma reta de acordo com os pontos p1 e p2
     *
     * @param g biblioteca para desenhar o primitivo grafico
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     * @param nome nome da reta
     * @param esp espessura da reta
     * @param cor cor da reta
     */
    public static void desenharRetangulo(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        RetanguloGr r = new RetanguloGr(x1, y1, x2, y2, cor, nome, esp);
        //System.out.println("diagonal 1: " + x1 + " " + y1 + "\n" + "diagonal 2: " + x2 + " " + y2);
        r.desenharRetangulo(g);
    }

}
