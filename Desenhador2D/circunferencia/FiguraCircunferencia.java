package circunferencia;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Escreva uma descrição da classe FiguraCircunferencia aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraCircunferencia
{
    
    public static void desenharCircunferencia(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
       CircunferenciaGr c = new CircunferenciaGr(x1,y1,x2,y2, cor, nome, esp);
       //c.desenharCirculo1(g);
       //c.desenharCirculo2(g);
       c.desenharCirculoSimetria(g);
    }
    
    
    
}
