package circunferencia;
import ponto.Ponto;
/**
 * Escreva uma descrição da classe Circunferencia aqui.
 * 
 * @author Daniel Teles de Oliveira, Joao Victor Torres Soares, Larissa Hipolito Santana, Rubens Rodrigues Maranesi
 * @version (um número da versão ou uma data)
 */
public class Circunferencia
{
  private double r;
  private Ponto centro;
  
  /**
   * Circunferencia Construtor
   * Constroi unm circunferencia com ponto central (120,120) e raio = 10
   */
  public Circunferencia(){
      setCentro(new Ponto(120,120));
      setRaio(10);
  }
  
  /**
   * Circunferencia Construtor
   *
   * @param centro Ponto central (x1,y1)
   * @param raio Valor do raio
   */
  public Circunferencia(Ponto centro, double raio){
      setCentro(centro);
      setRaio(raio);
  }
  
  /**
   * Circunferencia Construtor
   * Constroi uma circunferencia no ponto (10,10)
   * @param raio Valor do raio
   */
  public Circunferencia(double raio){
      setCentro(new Ponto(10,10));
      setRaio(raio);
  }
  
  /**
   * Circunferencia Construtor
   * Constroi uma circunferencia a partir de um ponto externo e o raio com valor 15
   * @param centro Ponto central (x1,y1)
   */
  public Circunferencia(Ponto centro){
      setCentro(centro);
      setRaio(15);
  }
  
  /**
   * Circunferencia Construtor
   * Constroi uma circunferencia com os valores das coordenadas e com raio com valor 15
   * @param xc Coordenada x1
   * @param yc Coordenada y1
   */
  public Circunferencia(int xc, int yc){
      setCentro(new Ponto(xc,yc));
      setRaio(15);
  }
  
  /**
   * Circunferencia Construtor
   * Constroi uma circunferencia com os valores das coordenadas e com raio com valor 15
   * @param xc Coordenada x1
   * @param yc Coordenada y1
   */
  public Circunferencia(double xc, double yc){
      setCentro(new Ponto(xc,yc));
      setRaio(15);
  }
  
   /**
    * Circunferencia Construtor
    * Constroi uma circunferencia com os valores das coordenadas e do raio
    * @param xc Coordenada x1
    * @param yc Coordenada y1
    * @param raio Valor do raio
    */
   public Circunferencia(int xc, int yc, double raio){
      setCentro(new Ponto(xc,yc));
      setRaio(raio);
  }
  
   /**
    * Circunferencia Construtor
    * Constroi uma circunferencia com os valores das coordenadas e do raio
    * @param xc Coordenada x1
    * @param yc Coordenada y1
    * @param raio Valor do raio
    */
   public Circunferencia(double xc, double yc, double raio){
      setCentro(new Ponto(xc,yc));
      setRaio(15);
  }
  
  
  /**
   * Método setCentro
   * Altera o valor do ponto central a partir de um ponto externo
   * @param p Ponto centro (x1,y1)
   */
  public void setCentro(Ponto p){
      centro = p;
  }
  
  /**
   * Método setRaio
   * Altera o valor do raio a partir de um valor externo
   * @param r double. Valor do raio
   */
  public void setRaio(double r){
      this.r = r;
  }
  
  /**
   * Método getCentro
   *
   * @return Ponto. Ponto centro(x1,y1)
   */
  public Ponto getCentro(){
      return centro;
  }
  
  /**
   * Método getRaio
   *
   * @return double. Valor do raio
   */
  public double getRaio(){
      return r;
  }
  
   /**
    * Método calcularArea
    * Calcula a area da circunferencia
    * @return double. Valor da area
    */
   public double calcularArea(){
      double area = Math.PI * (r*r);
      return area;
  }
  
  /**
   * Método calcularPerimetro
   * Calcula o perimetro da circunferencia
   * @return double. Valor do perimetro
   */
  public double calcularPerimetro(){
      double per = 2 * Math.PI * r;
      return per;
  }
  
  /**
   * Método calcularRaio
   * Calcula o valor do raio
   * @param p Ponto central (x1,y1)
   * @return double. Valor do raio
   */
  public double calcularRaio(Ponto p){
      double raio = centro.calcularDistancia(p);
      return raio;
  }
  
}
