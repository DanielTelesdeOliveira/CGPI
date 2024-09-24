package retangulo;
import ponto.Ponto;

/**
 * Escreva uma descrição da classe Retangulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Retangulo
{
    Ponto d1, d2; //Diagonais
   
    /**
     * Constroi um retangulo com valores (int) de x1, y1 e x2, y2
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     */
    public Retangulo(int x1, int y1, int x2, int y2) {
        setD1(new Ponto(x1, y1));
        setD2(new Ponto(x2, y2));
    }
    
    /**
     * Constroi um retangulo com valores (double) de x1, y1 e x2, y2
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     */
    public Retangulo(double x1, double y1, double x2, double y2) {
        setD1(new Ponto(x1, y1));
        setD2(new Ponto(x2, y2));
    }
    
    /**
     * Controi um retangulo com valores de p1 e p2 (externos)
     *
     * @param p1 Um parâmetro
     * @param p2 Um parâmetro
     */
    public Retangulo(Ponto p1, Ponto p2) {
        setD1(p1);
        setD2(p2);
    }
    
    /**
     * Constroi um retangulo com dados de outra (externa)
     *
     * @param r retangulo externa
     */
    public Retangulo(Retangulo r){
        setD1(r.getD1());
        setD2(r.getD2());
    }
    
    /**
     * Altera valor de d1 de acordo com o parametro
     *
     * @param p valor de d1 (externo)
     */
    public void setD1(Ponto p1){
        this.d1 = p1;
    }
    
    /**
     * Altera valor de d2 de acordo com o parametro
     *
     * @param p valor de d2 (externo)
     */
    public void setD2(Ponto p2){
        this.d2 = p2;
    }
    
    /**
     * Retorna valor de d1
     *
     * @return valor de d1
     */
    public Ponto getD1(){
        return this.d1;
    }
    
    /**
     * Retorna d2
     *
     * @return valor de d2
     */
    public Ponto getD2(){
        return this.d2;
    }
}
