package circunferencia;

import ponto.Ponto;
import ponto.PontoGr;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descrição da classe CircunferenciaGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class CircunferenciaGr extends Circunferencia
{
    Color corCir = Color.BLACK;   // cor da circulo
    String nomeCirc = ""; // nome da circulo
    Color corNomeCirc  = Color.BLACK;
    int espCirc = 5; // espessura do circulo 
    Ponto ref;
    
    /**
     * CircunferenciaGr - Constroi um circulo grafico
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor do circulo
     * @param nome String. Nome do circulo
     * @param esp int. Espessura do circulo
     */
    public CircunferenciaGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp){
        super (x1, y1);
        setCorCirc(cor);
        setNomeCirc(nome);
        setEspCirc(esp);
        setReferencia(x2,y2);
    }  
    
     /**
     * CircunferenciaGr - Constroi uma reta grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor do circulo
     */
    public CircunferenciaGr(int x1, int y1, int x2, int y2, Color cor){
        super(x1, y1);
        setCorCirc(cor);
        setNomeCirc("");
        setReferencia(x2,y2);
    }   
    
    /**
     * CircunferenciaGr - Constroi uma reta grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor do circulo
     * @param esp int. Espessura do circulo
     */
    public CircunferenciaGr(int x1, int y1, int x2, int y2, Color cor, int esp){
        super (x1, y1);
        setCorCirc(cor);
        setNomeCirc("");
        setEspCirc(esp);
        setReferencia(x2,y2);
    } 
    
      
     /**
     * CircunferenciaGr - Constroi um circulo grafico
     *
     * @param p1 Ponto. Ponto p1 (x1, y1)
     * @param p2 Ponto. Ponto p2 (x2, y2)
     * 
     */
    public CircunferenciaGr(Ponto p1, Ponto p2){
        super(p1);
        setCorCirc(Color.black);
        setNomeCirc("");
        setReferencia((int)p2.getX(), (int)p2.getY());
    }    
    
     /**
     * CircunferenciaGr - Constroi um circulo grafico
     *
     * @param p1 Ponto. Ponto p1 (x1, y1)
     * @param p2 Ponto. Ponto p2 (x2, y2)
     * @param cor Color. Cor do circulo
     */
    public CircunferenciaGr(Ponto p1, Ponto p2, Color cor){
        super(p1);
        setCorCirc(cor);
        setNomeCirc("");
        setReferencia((int)p2.getX(), (int)p2.getY());
    }     
    
   /**
     * CircunferenciaGr - Constroi um circulo grafico
     *
     * @param p1 Ponto. Ponto p1 (x1, y1)
     * @param p2 Ponto. Ponto p2 (x2, y2)
     * @param cor Color. Cor do circulo
     * @param nome String. Nome do circulo
     */
    public CircunferenciaGr(Ponto p1, Ponto p2, Color cor, String str){
        super(p1);
        setCorCirc(cor);
        setNomeCirc(str);
        setReferencia((int)p2.getX(), (int)p2.getY());
    }    
   
   public CircunferenciaGr(int pontoCentralX, int pontoCentralY, int x2, int y2){
       super(pontoCentralX,pontoCentralY); // Pontos Centrais
       setReferencia(x2, y2);
       setCorCirc(Color.BLACK);
       setNomeCirc("");
   }
   
    /**
     * Altera a espessura do circulo.
     *
     * @param esp int. Espessura do circulo.
     */
    public void setEspCirc(int esp) {
        this.espCirc = esp;
    }

    /**
     * Retorna a espessura do circulo.
     *
     * @return int. Espessura do circulo.
     */
    public int getEspCirc(){
        return(this.espCirc);
    }  
    
     /**
     * Retorna a cor da circulo.
     *
     * @return Color. Cor do circulo.
     */
    public Color getCorCirc() {
        return corCir;
    }
    
      /**
     * Altera a cor do circulo.
     *
     * @param cor Color. Cor do circulo.
     */
    public void setCorCirc(Color cor) {
        this.corCir = cor;
    }
 

    /**
     * @return the corNomeCirc
     */
    public Color getCorNomeCirc() {
        return corNomeCirc;
    }

    
     /**
     * @param corNomeCirc the corNomeCirc to set
     */
    public void setCorNomeCirc(Color corNomeCirc){
        this.corNomeCirc = corNomeCirc;
    }
    
     /**
     * Retorna o nome do circulo.
     *
     * @return String. Nome do circulo.
     */
    public String getNomeReta() {
        return nomeCirc;
    }
    
     /**
     * Altera o nome do circulo.
     *
     * @param str String. Nome do circulo.
     */
    public void setNomeCirc(String str) {
        this.nomeCirc = str;
    }
    
    public void setReferencia(int xRef, int yRef){
        ref = new Ponto(xRef, yRef);
    }
   
   public void desenharCirculo1(Graphics g){
         PontoGr pgr = new PontoGr(0,0,getCorCirc(), getEspCirc()); //Instancia do ponto para economia de memoria
         double xc = getCentro().getX(); //Coordenadas centrais
         double yc = getCentro().getY();
         double xMouse = ref.getX(); //Coordenada p2
         double yMouse = ref.getY();
         double r = calcularRaio(ref);
         double raizes;
         
         //Loop para refeencher circulo
         double extremidade_inicial =  getCentro().getX() - r;
         double extremidade_final =  getCentro().getX() + r;
         double y;
         double y2; 
         double raiz;
         
         for(double x = extremidade_inicial; x <= extremidade_final; x += 0.01){
             // Calculo das raizes
             raiz = Math.sqrt(Math.pow(r,2) - Math.pow(x - xc, 2));
             if(raiz >= 0){
                 // Metade Direita do círculo
                 y = (int) (yc + raiz); 
                 //pgr = new PontoGr((int)x,(int)y, getCorCirc(), getEspCirc());
                 pgr.setX(x);
                 pgr.setY(y);
                 pgr.desenharPonto(g);
                
                //Metade Esqueda do Círculo
                y2 = (int) (yc - raiz);
                pgr.setX(x);
                pgr.setY(y2);
                //pgr = new PontoGr((int)x,(int)y2, getCorCirc(), getEspCirc());
                pgr.desenharPonto(g);
             }
                
             
         }
         
    }
    
    
       public void desenharCirculo2(Graphics g){
         PontoGr pgr = new PontoGr(0,0, getCorCirc(), getEspCirc());
         double xc = getCentro().getX(); //Coordenadas centrais
         double yc = getCentro().getY();
         double r = calcularRaio(ref);
         
         //Loop para refeencher circulo
         double y;
         double x;         

         for(double theta = 0; theta <= 2*Math.PI; theta += 0.01){
             // Calculo das raizes
             x = xc + r * Math.cos(theta);
             y = yc + r * Math.sin(theta); 
             pgr.setX(x);
             pgr.setY(y);
             //pgr = new PontoGr((int)x,(int)y, getCorCirc(), getEspCirc());
             pgr.desenharPonto(g);
           
         }
         
    }
    
    /**
     * Método desenharCirculoSimetria
     * Desenha o círculo atravès das equaçpões paramétricas
     * Laço for vai de 0 até pi/4, completando assim a circunferência
     */
    
     public void desenharCirculoSimetria(Graphics g) {
        PontoGr pgr2 = new PontoGr(0,0,getCorCirc(), getEspCirc());
        double xc = getCentro().getX(); // Coordenadas centrais
        double yc = getCentro().getY();
        double r = calcularRaio(ref);
    
        // Loop para preencher o círculo
        for (double theta = 0; theta <= Math.PI / 4; theta += 0.001) {
            double x = r * Math.cos(theta);
            double y = r * Math.sin(theta);
    
            // Adiciona os pontos simétricos para todos os octantes
            // 1° Octante
            //pgr2 = new PontoGr((int) (xc + x), (int) (yc + y), getCorCirc(), getEspCirc());
            pgr2.setX(xc+x);
            pgr2.setY(yc+y);
            pgr2.desenharPonto(g);
    
            // 2° Octante
            //pgr2 = new PontoGr((int) (xc - x), (int) (yc + y), getCorCirc(), getEspCirc());
            pgr2.setX(xc-x);
            pgr2.setY(yc+y);
            pgr2.desenharPonto(g);
    
            // 3° Octante
            //pgr2 = new PontoGr((int) (xc - x), (int) (yc - y), getCorCirc(), getEspCirc());
            pgr2.setX(xc-x);
            pgr2.setY(yc-y);
            pgr2.desenharPonto(g);
    
            // 4° Octante
            //pgr2 = new PontoGr((int) (xc + x), (int) (yc - y), getCorCirc(), getEspCirc());
            pgr2.setX(xc+x);
            pgr2.setY(yc-y);
            pgr2.desenharPonto(g);
    
            // 5° Octante
            //pgr2 = new PontoGr((int) (xc + y), (int) (yc + x), getCorCirc(), getEspCirc());
            pgr2.setX(xc+y);
            pgr2.setY(yc+x);
            pgr2.desenharPonto(g);
    
            // 6° Octante
            //pgr2 = new PontoGr((int) (xc - y), (int) (yc + x), getCorCirc(), getEspCirc());
            pgr2.setX(xc-y);
            pgr2.setY(yc+x);
            pgr2.desenharPonto(g);
    
            // 7° Octante
            //pgr2 = new PontoGr((int) (xc - y), (int) (yc - x), getCorCirc(), getEspCirc());
            pgr2.setX(xc-y);
            pgr2.setY(yc-x);
            pgr2.desenharPonto(g);
    
            // 8° Octante
            //pgr2 = new PontoGr((int) (xc + y), (int) (yc - x), getCorCirc(), getEspCirc());
            pgr2.setX(xc+y);
            pgr2.setY(yc-x);
            pgr2.desenharPonto(g);
        }
     }
    
}
