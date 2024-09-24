package triangulo;
import ponto.*;
/**
 * Escreva uma descrição da classe Triangulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Triangulo
{
  Ponto p1,p2,p3;
  double h;
  double b;
  
  /**
     * Constroi um Triangulo com valores (int) de x1, y1, x2, y2 e x3, y3
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     * @param x3 coordenada x de p3
     * @param y3 coordenada y de p3
     */
    public Triangulo(int x1, int y1, int x2, int y2, int x3, int y3) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setP3(new Ponto(x3, y3));
    }
    
    public Triangulo(){
        setP1(new Ponto(10, 10));
        setP2(new Ponto(333, 280));
        setP3(new Ponto(520, 212));
    }
    
    /**
     * Constroi um Triangulo com valores (double) de x1, y1 e x2, y2
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     * @param x3 coordenada x de p3
     * @param y3 coordenada y de p3
     */
    public Triangulo(double x1, double y1, double x2, double y2, double x3, double y3) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setP3(new Ponto(x3, y3));
    }
    
    /**
     * Controi um Triangulo com valores de p1 e p2 (externos)
     *
     * @param p1 Um parâmetro
     * @param p2 Um parâmetro
     * @param p3 Um parâmetro
     */
    public Triangulo(Ponto p1, Ponto p2, Ponto p3) {
        setP1(p1);
        setP2(p2);
        setP3(p3);
    }
    
    /**
     * Constroi um Triangulo com dados de outro (externo)
     *
     * @param r Triangulo externa
     */
    public Triangulo(Triangulo t){
        setP1(t.getP1());
        setP2(t.getP2());
        setP2(t.getP3());
    }
    
    /**
     * Altera valor de p1 de acordo com o parametro
     *
     * @param p valor de p1 (externo)
     */
    public void setP1(Ponto p1){
        this.p1 = p1;
    }
    
    /**
     * Altera valor de p2 de acordo com o parametro
     *
     * @param p valor de p2 (externo)
     */
    public void setP2(Ponto p2){
        this.p2 = p2;
    }
    
    /**
     * Altera valor de p3 de acordo com o parametro
     *
     * @param p3 valor de p3 (externo)
     */
    public void setP3(Ponto p3){
        this.p3 = p3;
    }
    
    /**
     * Retorna valor de p1
     *
     * @return valor de p1
     */
    public Ponto getP1(){
        return this.p1;
    }
    
    /**
     * Retorna p2
     *
     * @return valor de p2
     */
    public Ponto getP2(){
        return this.p2;
    }
    
    /**
     * Retorna p3
     *
     * @return valor de p3
     */
    public Ponto getP3(){
        return this.p3;
    }
  
}
