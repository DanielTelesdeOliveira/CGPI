package triangulo;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descrição da classe FiguraTriangulos aqui.
 * 
 * @author Daniel Teles de Oliveira, Joao Victor Torres Soares, Larissa Hipolito Santana, Rubens Rodrigues Maranesi
 * @version (um número da versão ou uma data)
 */
public class FiguraTriangulos
{
    /**
     * Método desenharTriangulo
     *
     * @param g Classe com os metodos graficos do Java
     * @param x1 Coordenada x1
     * @param y1 Coordenada y1
     * @param x2 Coordenada x2
     * @param y2 Coordenada y2
     * @param x3 Coordenada x3
     * @param y3 Coordenada y2
     * @param nome Nome do triangulo
     * @param esp Espessura do triangulo
     * @param cor Cor do triangulo
     */
    public static void desenharTriangulo(Graphics g,int x1, int y1, int x2, int y2, int x3, int y3, String nome, int esp, Color cor){
        TrianguloGr t = new TrianguloGr(x1,y1,x2,y2,x3,y3, cor, nome, esp);
        //System.out.println("x1: " +x1+ "\n" +"y1: " +y1+ "\n" +"x2: " +x2+ "\n" +"y2: " +y2+ "\n" +"x3: " +x3+ "\n" +"y3: " +y3+ "\n");
        t.desenharTriangulo(g);
    }
    
    /**
     * Método desenharTriangulo
     *
     * @param g biblioteca grafica para desenhar elementos gráficos
     * @param tri Triangulo a ser desenhado
     */
    public static void desenharTriangulo(Graphics g,TrianguloGr tri){
        tri.desenharTriangulo(g);
    }
}
