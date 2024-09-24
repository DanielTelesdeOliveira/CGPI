import EstruturaDados.*;
import reta.*;
import ponto.*;
import triangulo.*;
import retangulo.*;
import circunferencia.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ponto.FiguraPontos;
import reta.FiguraRetas;
import circunferencia.FiguraCircunferencia;
import retangulo.FiguraRetangulo;
import triangulo.FiguraTriangulos;
/**
 * Cria desenhos de acordo com o tipo e eventos do mouse
 * 
 * @author Julio Arakaki 
 * @version 20220815
 */
public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {

    JLabel msg;           // Label para mensagens
    TipoPrimitivo tipo; // Tipo do primitivo
    Color corAtual;       // Cor atual do primitivo
    int esp;              // Diametro do ponto

    // Para ponto
    int x, y;

    // Para reta
    int x1, y1, x2, y2, x3, y3, xant, yant, xant2, yant2;

    // selecionar primeiro click do mouse
    boolean primeiraVezReta = true;
    boolean primeiraVezCirc = true;
    boolean primeiraVezTri = true, segundaVezTri = true;
    boolean clearLine = false;

    IArmazenador armazen;

    /**
     * Constroi o painel de desenho
     *
     * @param msg mensagem a ser escrita no rodape do painel
     * @param tipo tipo atual do primitivo
     * @param corAtual cor atual do primitivo
     * @param esp espessura atual do primitivo
     */
    public PainelDesenho(JLabel msg, TipoPrimitivo tipo, Color corAtual, int esp){
        setTipo(tipo);
        setMsg(msg);
        setCorAtual(corAtual);
        setEsp(esp);
        armazen = new VetDin();
        
        //armazen = new ListaArray();
        // Adiciona "ouvidor" de eventos de mouse
        this.addMouseListener(this); 
        this.addMouseMotionListener(this);

    }

    /**
     * Altera o tipo atual do primitivo
     *
     * @param tipo tipo do primitivo
     */
    public void setTipo(TipoPrimitivo tipo){
        this.tipo = tipo;
    }

    /**
     * Retorna o tipo do primitivo
     *
     * @return tipo do primitivo
     */
    public TipoPrimitivo getTipo(){
        return this.tipo;
    }

    /**
     * Altera a espessura do primitivo
     *
     * @param esp espessura do primitivo
     */
    public void setEsp(int esp){
        this.esp = esp;
    }

    /**
     * Retorna a espessura do primitivo
     *
     * @return espessura do primitivo
     */
    public int getEsp(){
        return this.esp;
    }

    /**
     * Altera a cor atual do primitivo
     *
     * @param corAtual cor atual do primitivo
     */
    public void setCorAtual(Color corAtual){
        this.corAtual = corAtual;
    }

    /**
     * retorna a cor atual do primitivo
     *
     * @return cor atual do primitivo
     */
    public Color getCorAtual(){
        return this.corAtual;
    }

    /**
     * Altera a msg a ser apresentada no rodape
     *
     * @param msg mensagem a ser apresentada
     */
    public void setMsg(JLabel msg){
        this.msg = msg;
    }

    /**
     * Retorna a mensagem
     *
     * @return mensagem as ser apresentada no rodape
     */
    public JLabel getMsg(){
        return this.msg;
    }

    /**
     * Metodo chamado quando o paint eh acionado
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void paintComponent(Graphics g) {  
        if(clearLine == true)
            apagarPrimitivos(g);
        desenharPrimitivos(g);
        
    }

    /**
     * Evento: pressionar do mouse
     *
     * @param e dados do evento
     */
    public void mousePressed(MouseEvent e) { 
        Graphics g = getGraphics();  
        if (tipo == TipoPrimitivo.PONTO){
            x = e.getX();
            y = e.getY();
            paint(g);
        } else if (tipo == TipoPrimitivo.RETA || tipo == TipoPrimitivo.RETANGULO){
            //Reseta os pontos colhidos em outros primitivos
            if(primeiraVezCirc == false)
                primeiraVezCirc = true;

            if(primeiraVezTri == false){
                primeiraVezTri = true;
            }

            if(segundaVezTri == false){
                segundaVezTri = true;
            }

            if (primeiraVezReta == true) {
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVezReta = false;
            } /*else {
            x2 = (int)e.getX();
            y2 = (int)e.getY();
            primeiraVezReta = true;
            paint(g);
            }*/
        } else if (tipo == TipoPrimitivo.CIRCULO){

            if(primeiraVezTri == false){
                primeiraVezTri = true;
            }

            if(segundaVezTri == false){
                segundaVezTri = true;
            }

            if(primeiraVezReta == false)
                primeiraVezReta = true;

            if (primeiraVezCirc == true) {
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVezCirc = false;
            }/* else {
            x2 = (int)e.getX();
            y2 = (int)e.getY();
            primeiraVezCirc = true;
            paint(g);
            }*/
        } else if(tipo == TipoPrimitivo.TRIANGULO){
            if(primeiraVezCirc == false)
                primeiraVezCirc = true;

            if(primeiraVezReta == false)
                primeiraVezReta = true;

            if(primeiraVezTri == true){
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVezTri = false;
            } else if(segundaVezTri == true){
                x2 = (int)e.getX();
                y2 = (int)e.getY();
                segundaVezTri = false;
            } else{
                x3 = (int)e.getX();
                y3 = (int)e.getY();
                primeiraVezTri = true;
                segundaVezTri = true;
                paint(g);
            }

        }
    }     

    public void mouseReleased(MouseEvent e) { 
        Graphics g = getGraphics();  
        //        System.out.println("Released ");
        x2 = (int)e.getX();
        y2 = (int)e.getY();
        if(primeiraVezReta == false)
            primeiraVezReta = true;
        else if(primeiraVezCirc == false)
            primeiraVezCirc = true;
        salvarPrimitivos();
        clearLine = false;
        paint(g);  
    }           

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        clearLine = true;
        Graphics g = getGraphics();  
        xant = x2;
        yant = y2;
        xant2 = x3;
        yant = y2;
        x2 = (int)e.getX();
        y2 = (int)e.getY();
        paint(g);
        //apagarPrimitivos(g);
        redesenharPrimitivos(g);
        //        System.out.println("Dragged ");
        this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
    }

    /**
     * Evento mouseMoved: escreve mensagem no rodape (x, y) do mouse
     *
     * @param e dados do evento do mouse
     */
    public void mouseMoved(MouseEvent e) {
        this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
    }

    /**
     * Desenha os primitivos
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void desenharPrimitivos(Graphics g){
        if (tipo == TipoPrimitivo.PONTO){
            FiguraPontos.desenharPonto(g, x, y, "", getEsp(), getCorAtual());
            //FiguraPontos.desenharPontos(g, 50, 20);
        }

        else if (tipo == TipoPrimitivo.RETA){
            FiguraRetas.desenharReta(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
            //FiguraRetas.desenharRetas(g, 10, 3);
        }

        else if (tipo==TipoPrimitivo.CIRCULO){
            FiguraCircunferencia.desenharCircunferencia(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
        }

        else if(tipo == TipoPrimitivo.RETANGULO){
            FiguraRetangulo.desenharRetangulo(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
        }

        else if(tipo == TipoPrimitivo.TRIANGULO){
            FiguraTriangulos.desenharTriangulo(g, x1, y1, x2, y2, x3, y3, "", getEsp(), getCorAtual());
        }
    }

    /**
     * Desenha os primitivos
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void apagarPrimitivos(Graphics g){

        if (tipo == TipoPrimitivo.RETA){
            FiguraRetas.desenharReta(g, x1, y1, xant, yant, "", getEsp(), getBackground());
            //FiguraRetas.desenharReta(g, x1, y1, x2, y2, "", getEsp(), getBackground());
            //FiguraRetas.desenharRetas(g, 10, 3);
        } else if (tipo == TipoPrimitivo.RETANGULO){
            FiguraRetangulo.desenharRetangulo(g, x1, y1, xant, yant, "", getEsp(), getBackground());
        } else if (tipo==TipoPrimitivo.CIRCULO){
            FiguraCircunferencia.desenharCircunferencia(g, x1, y1, xant, yant, "", getEsp(), getBackground());
            //FiguraCirculos.desenharCirculo(g, x1, y1, xant, yant, "", getEsp(), getBackground());
        } else if (tipo==TipoPrimitivo.TRIANGULO){
            FiguraTriangulos.desenharTriangulo(g, x1, y1, xant, yant, xant2, yant2, "", getEsp(), getBackground());
        }
    }

    public void redesenharPrimitivos(Graphics g){
        Object a[] = armazen.getArray();
        int tam = armazen.getQtd();
       
        for(int i = 0; i < tam; i++){
          //  System.out.println(a[i]);
            if (a[i] instanceof PontoGr) {
                PontoGr p = (PontoGr) a[i];
                FiguraPontos.desenharPonto(g, (int)p.getX(), (int)p.getY(), "", p.getDiametro(), p.getCorPto());
            } else if(a[i] instanceof Reta){
                RetaGr r = (RetaGr) a[i];
                FiguraRetas.desenharReta(g, (int)(r.getP1().getX()), (int)(r.getP1().getY()), (int)(r.getP2().getX()),(int)(r.getP2().getY()), "", r.getEspReta(), r.getCorReta());
            } else if(a[i] instanceof Retangulo){
                Retangulo ret = (Retangulo) a[i];
                FiguraRetangulo.desenharRetangulo(g, (int)(ret.getD1().getX()), (int)(ret.getD1().getY()), (int)(ret.getD2().getX()), (int)(ret.getD2().getY()), "", getEsp(), Color.green);
            } else if(a[i] instanceof Circunferencia){
                Circunferencia c = (Circunferencia) a[i];
                i++;
                Ponto p = (Ponto) a[i];
                FiguraCircunferencia.desenharCircunferencia(g, (int)(c.getCentro().getX()), (int)(c.getCentro().getY()), (int)(p.getX()), (int)(p.getY()), "", getEsp(), Color.green);
            } else if(a[i] instanceof Triangulo){
                Triangulo t = (Triangulo) a[i];
                FiguraTriangulos.desenharTriangulo(g, (int)(t.getP1().getX()), (int)(t.getP1().getY()), (int)(t.getP2().getX()), (int)(t.getP2().getY()), (int)(t.getP3().getX()), (int)(t.getP3().getY()), "", getEsp(), Color.green);
            }
        }
    }
    
    public void salvarPrimitivos(){
            if (tipo == TipoPrimitivo.PONTO){
            PontoGr p = new PontoGr((int)x,(int)y, getCorAtual(), getEsp());
            armazen.adicionar(p);
            }
    
            else if (tipo == TipoPrimitivo.RETA){
               RetaGr r = new RetaGr((int)x1,(int)y1,(int)x2,(int)y2, getCorAtual(), getEsp());
               armazen.adicionar(r);
            }
    
            else if (tipo==TipoPrimitivo.CIRCULO){
                Circunferencia c = new Circunferencia(x1,y1);
                Ponto p = new Ponto(x2,y2);
                armazen.adicionar(c);
                armazen.adicionar(p);
            }
    
            else if(tipo == TipoPrimitivo.RETANGULO){
                Retangulo ret = new Retangulo(x1,y1,x2,y2);
                armazen.adicionar(ret);
            }
    
            else if(tipo == TipoPrimitivo.TRIANGULO){
                Triangulo t = new Triangulo(x1,y1,x2,y2,x3,y3);
                armazen.adicionar(t);
            }
    }   
}




