package retangulo;
import ponto.*;
import reta.RetaGr;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descrição da classe RetanguloGui aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class RetanguloGr extends Retangulo{
    // Atributos da retangulo grafica
    Color corRetangulo = Color.BLACK;   // cor do retanguol
    String nomeRetangulo = ""; // nome do retangulo
    Color corNomeRetangulo  = Color.BLACK;
    int espRetangulo = 1; // espessura do retangulo

    // Construtores
    /**
     * RetanguloGr - Constroi um retangulo grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor do retangulo
     * @param nome String. Nome do retangulo
     * @param esp int. Espessura do retangulo
     */
    public RetanguloGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp){
        super (x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo(nome);
        setEspRetangulo(esp);
    }    

    /**
     * RetaGr - Constroi um retangulo grafico
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor do retangulo
     */
    public RetanguloGr(int x1, int y1, int x2, int y2, Color cor){
        super (x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo("");
    }   

    /**
     * RetaGr - Constroi um retangulo grafico
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor do retangulo
     * @param esp int. Espessura do retangulo
     */
    public RetanguloGr(int x1, int y1, int x2, int y2, Color cor, int esp){
        super (x1, y1, x2, y2);
        setCorRetangulo(cor);
        setNomeRetangulo("");
        setEspRetangulo(esp);
    }   

    /**
     * RetanguloGr - Constroi uma Retangulo grafico
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     */
    public RetanguloGr(int x1, int y1, int x2, int y2){
        super (x1, y1, x2, y2);
        setCorRetangulo(Color.black);
        setEspRetangulo(8);
        setNomeRetangulo("");
    }   

    /**
     * RetanguloGr - Constroi uma Retangulo grafico
     *
     * @param p1 PontoGr. Ponto grafico p1 (x1, y1)
     * @param p2 PontoGr. Ponto grafico p2 (x2, y2)
     */
    public RetanguloGr(PontoGr p1, PontoGr p2){
        super(p1, p2);
        setCorRetangulo(Color.black);
        setNomeRetangulo("");
    }    

    /**
     * RetanguloGr - Constroi uma Retangulo grafico
     *
     * @param p1 PontoGr. Ponto grafico p1 (x1, y1)
     * @param p2 PontoGr. Ponto grafico p2 (x2, y2)
     * @param cor Color. Cor do Retangulo
     */
    public RetanguloGr(PontoGr p1, PontoGr p2, Color cor){
        super(p1, p2);
        setCorRetangulo(cor);
        setNomeRetangulo("");
    }    

    /**
     * RetanguloGr - Constroi um Retangulo grafico
     *
     * @param p1 PontoGr. Ponto grafico p1 (x1, y1)
     * @param p2 PontoGr. Ponto grafico p2 (x2, y2)
     * @param cor Color. Cor do Retangulo
     * @param nome String. Nome do Retangulo
     */
    public RetanguloGr(PontoGr p1, PontoGr p2, Color cor, String str){
        super(p1, p2);
        setCorRetangulo(cor);
        setNomeRetangulo(str);
    }    

    /**
     * Altera a cor do Retangulo.
     *
     * @param cor Color. Cor da Retangulo.
     */
    public void setCorRetangulo(Color cor) {
        this.corRetangulo = cor;
    }

    /**
     * Altera o nome do Retangulo.
     *
     * @param str String. Nome do Retangulo.
     */
    public void setNomeRetangulo(String str) {
        this.nomeRetangulo = str;
    }

    /**
     * Altera a espessura do Retangulo.
     *
     * @param esp int. Espessura do Retangulo.
     */
    public void setEspRetangulo(int esp) {
        this.espRetangulo = esp;
    }

    /**
     * Retorna a espessura do Retangulo.
     *
     * @return int. Espessura do Retangulo.
     */
    public int getEspRetangulo() {
        return(this.espRetangulo);
    }

    /**
     * Retorna a cor do Retangulo.
     *
     * @return Color. Cor do Retangulo.
     */
    public Color getCorRetangulo() {
        return corRetangulo;
    }

    /**
     * Retorna o nome do Retangulo.
     *
     * @return String. Nome do Retangulo.
     */
    public String getNomeRetangulo() {
        return nomeRetangulo;
    }

    /**
     * @return the corNomeRetangulo
     */
    public Color getCorNomeRetangulo() {
        return corNomeRetangulo;
    }

    /**
     * @param corNomeRetangulo the corNomeRetangulo to set
     */
    public void setCorNomeRetangulo(Color corNomeRetangulongulo) {
        this.corNomeRetangulo = corNomeRetangulo;
    }
      
    public void desenharRetangulo(Graphics g){  
        int diagonal_simetrica1;
        int diagonal_simetrica2;
        PontoGr pg1, pg2;
        
        Ponto p1, p2;
        p1 = getD1();
        p2 = getD2();
        
        int x1 = (int)p1.getX();
        int y1 = (int)p1.getY(); //Diagonal 1
        
        int x2 = (int)p2.getX(); //Diagonal 2
        int y2 = (int)p2.getY();
         
        RetaGr r = new RetaGr(x1, y1, x2, y2, getCorRetangulo(), getNomeRetangulo(),  getEspRetangulo());
        if(x1 == x2 || y1 == y2){
            //r.setP1(p1);
            //r.setP2(p2);
            r.desenharReta(g);  
        }else{
            //r = new RetaGr(x1,y1,x1,y2);
            //r.setP1(new Ponto(x1,y1));
            p2.setX(x1);
            r.setP2(p2);
            r.desenharReta(g);
            
           // r = new RetaGr(x1,y2,x2,y2);
            p1.setY(y2);
            p2.setX(x2);
            r.setP1(p1);
            r.setP2(p2);
            r.desenharReta(g);
            
            //r = new RetaGr(x2,y2,x2,y1);
            p1.setX(x2);
            p1.setY(y2);
            p2.setY(y1);
            r.setP1(p1);
            r.setP2(p2);
            r.desenharReta(g);
            
            //r = new RetaGr(x2,y1,x1,y1);
            p1.setY(y1);
            p2.setX(x1);
            r.setP1(p1);
            r.setP2(p2);
            r.desenharReta(g);
                   
        }
        
    }

}
