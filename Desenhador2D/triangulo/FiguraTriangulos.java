package triangulo;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descrição da classe FiguraTriangulos aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraTriangulos
{
    public static void desenharTriangulo(Graphics g,int x1, int y1, int x2, int y2, int x3, int y3, String nome, int esp, Color cor){
        TrianguloGr t = new TrianguloGr(x1,y1,x2,y2,x3,y3, cor, nome, esp);
        //System.out.println("x1: " +x1+ "\n" +"y1: " +y1+ "\n" +"x2: " +x2+ "\n" +"y2: " +y2+ "\n" +"x3: " +x3+ "\n" +"y3: " +y3+ "\n");
        t.desenharTriangulo(g);
    }
}
