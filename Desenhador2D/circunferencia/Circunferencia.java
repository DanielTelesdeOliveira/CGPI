package circunferencia;
import ponto.Ponto;
/**
 * Escreva uma descrição da classe Circunferencia aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Circunferencia
{
  private double r;
  private Ponto centro;
  
  public Circunferencia(){
      setCentro(new Ponto(120,120));
      setRaio(10);
  }
  
  public Circunferencia(Ponto centro, double raio){
      setCentro(centro);
      setRaio(raio);
  }
  
  public Circunferencia(double raio){
      setCentro(new Ponto(10,10));
      setRaio(raio);
  }
  
  public Circunferencia(Ponto centro){
      setCentro(centro);
      setRaio(15);
  }
  
  public Circunferencia(int xc, int yc){
      setCentro(new Ponto(xc,yc));
      setRaio(15);
  }
  
  public Circunferencia(double xc, double yc){
      setCentro(new Ponto(xc,yc));
      setRaio(15);
  }
  
   public Circunferencia(int xc, int yc, double raio){
      setCentro(new Ponto(xc,yc));
      setRaio(raio);
  }
  
   public Circunferencia(double xc, double yc, double raio){
      setCentro(new Ponto(xc,yc));
      setRaio(15);
  }
  
  
  public void setCentro(Ponto p){
      centro = p;
  }
  
  public void setRaio(double r){
      this.r = r;
  }
  
  public Ponto getCentro(){
      return centro;
  }
  
  public double getRaio(){
      return r;
  }
  
   public double calcularArea(){
      double area = Math.PI * (r*r);
      return area;
  }
  
  public double calcularPerimetro(){
      double per = 2 * Math.PI * r;
      return per;
  }
  
  public double calcularRaio(Ponto p){
      double raio = centro.calcularDistancia(p);
      return raio;
  }
  
}
