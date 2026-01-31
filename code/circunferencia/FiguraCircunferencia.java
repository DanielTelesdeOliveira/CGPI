package circunferencia;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Escreva uma descrição da classe FiguraCircunferencia aqui.
 * 
 * @author Daniel Teles de Oliveira, Joao Victor Torres Soares, Larissa Hipolito Santana, Rubens Rodrigues Maranesi
 * @version (um número da versão ou uma data)
 */
public class FiguraCircunferencia
{
    
    /**
     * Método desenharCircunferencia
     *
     * @param g biblioteca para desenhar o primitivo grafico
     * @param x1 coordenada x de ponto central
     * @param y1 coordenada y de ponto central
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     * @param nome Nome da circunferencia
     * @param esp Espessura da circunferencia
     * @param cor Cor da circunferencia
     */
    public static void desenharCircunferencia(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
       CircunferenciaGr c = new CircunferenciaGr(x1,y1,x2,y2, cor, nome, esp);
       //c.desenharCirculoEquacao(g);
       //c.desenharCirculoAng(g);
       c.desenharCirculoSimetria(g);
    }
    
     /**
      * Método desenharCircunferencia
      *
      * @param g biblioteca grafica para desenhar elementos gráficos
      * @param c Circulo a ser desenhado
      */
     public static void desenharCircunferencia(Graphics g, CircunferenciaGr c){
       c.desenharCirculoSimetria(g);
    }

}
