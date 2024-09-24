package triangulo;
import ponto.*;
import reta.RetaGr;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descrição da classe TrianguloGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class TrianguloGr extends Triangulo
{
    Color corTriangulo = Color.black;
    Color corNomeTriangulo = Color.black;
    String nomeTriangulo = "";
    int espTriangulo = 1;
    
    TrianguloGr(){
        super();
    }
    
    /**
     * TrianguloGr Construtor
     *
     * @param x1 int - coordenada x1
     * @param y1 int - coordenada y1
     * @param x2 int - coordenada x2
     * @param y2 int - coordenada y2
     * @param x3 int - coordenada x3
     * @param y3 int - coordenada y3
     * @param cor Color - cor do triangulo
     * @param nome String - nome do triangulo
     * @param esp int - espessura do triangulo
     */
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, Color cor, String nome, int esp){
        super(x1,y1,x2,y2,x3,y3);
        setCorTriangulo(cor);
        setNomeTriangulo(nome);
        setEspTriangulo(esp);
    }
    
    /**
     * TrianguloGr Construtor
     *
     * @param x1 int - coordenada x1
     * @param y1 int - coordenada y1
     * @param x2 int - coordenada x2
     * @param y2 int - coordenada y2
     * @param x3 int - coordenada x3
     * @param y3 int - coordenada y3
     * @param cor Color - cor do triangulo
     */
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, Color cor){
        super(x1,y1,x2,y2,x3,y3);
        setCorTriangulo(cor);
        setNomeTriangulo("");
        setEspTriangulo(3);
    }
    
    /**
     * TrianguloGr Construtor
     *
     * @param x1 int - coordenada x1
     * @param y1 int - coordenada y1
     * @param x2 int - coordenada x2
     * @param y2 int - coordenada y2
     * @param x3 int - coordenada x3
     * @param y3 int - coordenada y3
     * @param cor Color - cor do triangulo
     * @param esp int - espessura do triangulo
     */
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, Color cor, int esp){
        super(x1,y1,x2,y2,x3,y3);
        setCorTriangulo(cor);
        setNomeTriangulo("");
        setEspTriangulo(esp);
    }
    
    /**
     * TrianguloGr Construtor
     *
     * @param x1 int - coordenada x1
     * @param y1 int - coordenada y1
     * @param x2 int - coordenada x2
     * @param y2 int - coordenada y2
     * @param x3 int - coordenada x3
     * @param y3 int - coordenada y3
     */
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3){
        super(x1,y1,x2,y2,x3,y3);
        setCorTriangulo(Color.black);
        setNomeTriangulo("");
        setEspTriangulo(3);
    }
    
    /**
     * TrianguloGr Construtor
     *
     * @param p1 PontoGr - ponto grafico p1 (x1,y1)
     * @param p2 PontoGr - ponto grafico p2 (x2,y2)
     * @param p3 PontoGr - ponto grafico p3 (x3,y3)
     */
    public TrianguloGr(PontoGr p1, PontoGr p2,  PontoGr p3){
        super(p1,p2, p3);
        setCorTriangulo(Color.black);
        setNomeTriangulo("");
        setEspTriangulo(3);
    }
    
     /**
      * TrianguloGr Construtor
      *
      * @param p1 PontoGr - ponto grafico p1 (x1,y1)
      * @param p2 PontoGr - ponto grafico p2 (x2,y2)
      * @param p3 PontoGr - ponto grafico p3 (x3,y3)
      * @param cor Color - cor do triangulo
      */
     public TrianguloGr(PontoGr p1, PontoGr p2,  PontoGr p3, Color cor){
        super(p1,p2, p3);
        setCorTriangulo(cor);
        setNomeTriangulo("");
        setEspTriangulo(3);
    }
    
     /**
      * TrianguloGr Construtor
      *
      * @param p1 PontoGr - ponto grafico p1 (x1,y1)
      * @param p2 PontoGr - ponto grafico p2 (x2,y2)
      * @param p3 PontoGr - ponto grafico p3 (x3,y3)
      * @param cor Color - cor do triangulo
      * @param nome String - nome do triangulo
      */
     public TrianguloGr(PontoGr p1, PontoGr p2,  PontoGr p3, Color cor, String nome){
        super(p1,p2, p3);
        setCorTriangulo(Color.black);
        setNomeTriangulo(nome);
        setEspTriangulo(3);
    }
    
     
    /**
     * Método getCorTriangulo
     *
     * @return Color - cor do triangulo
     */
    public Color getCorTriangulo(){
        return corTriangulo;
    }
    
    /**
     * Método getCorNomeTriangulo
     *
     * @return Color - cor do nome do triangulo
     */
    public Color getCorNomeTriangulo(){
        return corNomeTriangulo;
    }
    
    /**
     * Método getNomeTriangulo
     *
     * @return String - o nome do triangulo
     */
    public String getNomeTriangulo(){
        return nomeTriangulo;
    }
    
    /**
     * Método getEspTriangulo
     *
     * @return int - espessura do triangulo
     */
    public int getEspTriangulo(){
        return espTriangulo;
    }

    
    /**
     * Método setCorTriangulo
     * Altera a cor do triangulo
     * @param Color - cor do triangulo
     */
    public void setCorTriangulo(Color cor){
        corTriangulo = cor;
    }
    
    /**
     * Método setCorNomeTriangulo
     * Altera a cor do nome do triangulo
     * @param Color - cor do nome do triangulo
     */
    public void setCorNomeTriangulo(Color cor){
        corNomeTriangulo = cor;
    }
    
    /**
     * Método setEspTriangulo
     * Altera a espessura do triangulo
     * @param int - espessura do triangulo
     */
    public void setEspTriangulo(int esp){
        espTriangulo = esp;
    }
    
    /**
     * Método setNomeTriangulo
     * Altera o nome do triangulo
     * @param String - nome do triangulo
     */
    public void setNomeTriangulo(String nome){
        nomeTriangulo = nome;
    }
    
    public void desenharTriangulo(Graphics g){
        Ponto p1,p2,p3;
        int x1,y1,x2,y2,x3,y3;
        p1 = getP1();
        p2 = getP2();
        p3 = getP3();
        
        x1 = (int)p1.getX();
        y1 = (int)p1.getY();
        x2 = (int)p2.getX();
        y2 = (int)p2.getY();
        x3 = (int)p3.getX();
        y3 = (int)p3.getY();
        
        RetaGr r = new RetaGr(0,0,0,0,getCorTriangulo(), getNomeTriangulo(),  getEspTriangulo());
        
        if(x1 == x2 && y1 == y2){
            r.setP1(p1);
            r.setP2(p3);
            r.desenharReta(g);
        } else if(x1 == x3 && y1 == y3){
            r.setP1(p1);
            r.setP2(p2);
            r.desenharReta(g);
        } else if(x2 == x3 && y2 == y3){
            r.setP1(p1);
            r.setP2(p3);
            r.desenharReta(g);
        }else{
            r.setP1(p1);
            r.setP2(p2);
            r.desenharReta(g);
            
            r.setP2(p3);
            r.desenharReta(g);
            
            r.setP1(p2);
            r.desenharReta(g);
        }
          
        //System.out.println(p1);
        //System.out.println(p2);
        //System.out.println(p3);

    }
}
