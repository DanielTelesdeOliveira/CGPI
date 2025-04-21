import estruturadados.*;
import reta.*;
import ponto.*;
import triangulo.*;
import retangulo.*;
import circunferencia.*;
import java.awt.Dimension;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.lang.Math; 
import javax.swing.JColorChooser;
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
    
    // Para reta, retangulo, triangulo e circunferencia
    int x1, y1, x2, y2, x3, y3, xant, yant, xant2, yant2, xselect, yselect;
    
    //Pontos para destacar os atributos
    PontoGr pg1, pg2;
    
    // selecionar primeiro click do mouse
    boolean primeiraVezReta = true;
    boolean primeiraVezCirc = true;
    boolean primeiraVezTri = true, segundaVezTri = true;
    boolean primeiraVezTrans = true;
    //Ativado para permitir apagar os pontos anteriores do dragged
    boolean clearLine = false;
    
    //Ativado para verificar se e para aplicar alguma transformacao
    public boolean transladar = false;
    public boolean rotacionar = false;
    public boolean escala = false;
    
    //Recupera a cor antiga antes da figura ser selecionada
    Color cor_antiga;
    
    //Pontos de referencia para as transformacoes
    int xtclick, ytclick;
    int xrclick, yrclick;
    int xeclick, yeclick;
    
    //Ativado para verificar se e para deletar alguma figura
    public boolean deleteMode = false;
    //Lista com os primitivos
    IArmazenador armazen;
    
    //Argumentos para as transformacoes
    double angRotate = 45;
    double fatorX = 1, fatorY = 1;
    
    //Indice da figura salva na ED
    int indice; 
  
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
        //super.paintComponent(g);
        if(clearLine == true)
            apagarPrimitivosDragged(g);
        desenharPrimitivos(g);

    }

    /**
     * Evento: pressionar do mouse
     *
     * @param e dados do evento
     */
    public void mousePressed(MouseEvent e) { 
        Graphics g = getGraphics();  
         //System.out.println("deleteMode value: " + deleteMode);      
        if(tipo != TipoPrimitivo.NENHUM){
             deleteMode = false;
        }    
        if (tipo == TipoPrimitivo.PONTO){
            x = e.getX();
            y = e.getY();
            paint(g);
        } else if (tipo == TipoPrimitivo.RETA || tipo == TipoPrimitivo.RETANGULO){
            //Reseta os pontos colhidos em outros primitivos, caso tenha obtido um ponto e trocado de primitivo
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
            } 
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
            }

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
                salvarPrimitivos();
            }
            //Se for deletar ou realizar uma transformacao
        } else if(armazen.getQtd() != 0){       
                if(deleteMode == true){
                    destacarAtributos(g);
                    xselect = e.getX();
                    yselect = e.getY();
                    int pos = 0;
                    pos = buscarPrimitivos(xselect,yselect,g);
                    if(pos != -1) //Caso tenha selecionado algum primitivo
                        apagarPrimitivos(g, pos);
                } 
                
                else if (transladar == true){
                     
                     destacarAtributos(g);
                     xselect = e.getX();
                     yselect = e.getY(); 
                     int pos = buscarPrimitivos(xselect,yselect,g); 
                   
                     if(primeiraVezTrans == true){
                            
                         if(pos != -1){  //Caso tenha selecionado algum primitivo
                                primeiraVezTrans = false; //Indica que obteve o click de selecao da figura, permitindo o click do ponto referencia
                                indice = pos;
                         }          
                     }
                      else{
                             xtclick = e.getX();
                             ytclick = e.getY();
                             transladarObjeto(indice, g, xtclick, ytclick);
                     }                 
                } 
                
                else if(rotacionar == true){
                     destacarAtributos(g);
                     xselect = e.getX();
                     yselect = e.getY(); 
                     int pos = buscarPrimitivos(xselect,yselect,g); 
                 
                     if(primeiraVezTrans == true){
                         if(pos != -1){  //Caso tenha selecionado algum primitivo
                             primeiraVezTrans = false; //Indica que obteve o click de selecao da figura, permitindo o click do ponto referencia
                             indice = pos;
                            } 
                     }
                      else{ 
                             xrclick = e.getX();
                             yrclick = e.getY();
                            rotacionarObjeto(indice, g, xrclick, yrclick, angRotate);
                     }
                }
                
                else if(escala == true){
                     destacarAtributos(g);
                     xselect = e.getX();
                     yselect = e.getY(); 
                     int pos = buscarPrimitivos(xselect,yselect,g); 
                 
                     if(primeiraVezTrans == true){
                           
                         if(pos != -1){  //Caso tenha selecionado algum primitivo
                             primeiraVezTrans = false; //Indica que obteve o click de selecao da figura, permitindo o click do ponto referencia
                             indice = pos;
                        }
                     }
                      else{
                             xeclick = e.getX();
                             yeclick = e.getY();
                             escalarObjeto(indice, g, xeclick, yeclick, fatorX, fatorY);
                     }
                }                        
        }  
    }      
        
    public void mouseReleased(MouseEvent e) { 
        if(tipo != TipoPrimitivo.TRIANGULO){
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
    }           

    public void mouseClicked(MouseEvent e) {       
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    /**
     * Método mouseDragged
     *
     * @param e dados de um evento do mouse
     */
    public void mouseDragged(MouseEvent e) {
        if(tipo != TipoPrimitivo.TRIANGULO){ 
            clearLine = true; //Variavel qua ativa o metodo de apagar os primitivos enquanto eles sao arrastados na tela
            Graphics g = getGraphics();  
            xant = x2;
            yant = y2;
            x2 = (int)e.getX();
            y2 = (int)e.getY();
            paint(g);
            redesenharPrimitivos(g);
            //        System.out.println("Dragged ");
            this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
        }
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
        }

        else if (tipo == TipoPrimitivo.RETA){
            FiguraRetas.desenharReta(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
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
     * Apaga os primitivos
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void apagarPrimitivosDragged(Graphics g){
        
        if (tipo == TipoPrimitivo.RETA){
            FiguraRetas.desenharReta(g, x1, y1, xant, yant, "", getEsp(), getBackground());
        } 
        
        else if (tipo == TipoPrimitivo.RETANGULO){
            FiguraRetangulo.desenharRetangulo(g, x1, y1, xant, yant, "", getEsp(), getBackground());
        } 
        
        else if (tipo==TipoPrimitivo.CIRCULO){
            FiguraCircunferencia.desenharCircunferencia(g, x1, y1, xant, yant, "", getEsp(), getBackground());
        } 
        
        else if (tipo==TipoPrimitivo.TRIANGULO){
            FiguraTriangulos.desenharTriangulo(g, x1, y1, xant, yant, xant2, yant2, "", getEsp(), getBackground());
        }
    }

    /**
     * Método redesenharPrimitivos
     * Redesenha os primitivos salvos na estrutura de dados
     * @param g biblioteca para desenhar em modo grafico
     */
    public void redesenharPrimitivos(Graphics g){
        Object a[] = armazen.getArray();
        int tam = armazen.getQtd();
        for(int i = 0; i < tam; i++){
            apagarDestaqueAtributo(g, a[i]);
            
            if (a[i] instanceof Ponto) {
                PontoGr p = (PontoGr) a[i];
                FiguraPontos.desenharPonto(g,p);
                
            } else if(a[i] instanceof Reta){
                RetaGr r = (RetaGr) a[i];
                FiguraRetas.desenharReta(g, r);
                
            } else if(a[i] instanceof Retangulo){
                RetanguloGr ret = (RetanguloGr) a[i];
                FiguraRetangulo.desenharRetangulo(g, ret);
                
            } else if(a[i] instanceof Circunferencia){
                CircunferenciaGr c = (CircunferenciaGr) a[i];
                FiguraCircunferencia.desenharCircunferencia(g, c);
                
            } else if(a[i] instanceof Triangulo){
                TrianguloGr t = (TrianguloGr) a[i];
                FiguraTriangulos.desenharTriangulo(g, t);
            }
        }
    }

    /**
     * Método salvarPrimitivos
     * Armazena os primitivos na estrutura de dados
     * Adiciona os primitivos no VetDin sequencialmente, a partir da posicao 0
     */
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
            CircunferenciaGr c = new CircunferenciaGr(x1,y1, x2, y2, getCorAtual(), getEsp());
            armazen.adicionar(c);
        }

        else if(tipo == TipoPrimitivo.RETANGULO){
            RetanguloGr ret = new RetanguloGr(x1,y1,x2,y2, getCorAtual(), getEsp());
            armazen.adicionar(ret);
        }

        else if(tipo == TipoPrimitivo.TRIANGULO){
            TrianguloGr t = new TrianguloGr(x1,y1,x2,y2,x3,y3, getCorAtual(), getEsp());
            armazen.adicionar(t);
        }
    }  
    
    /**
     * Método buscarPrimitivos
     *
     * @param x X coordenada
     * @param y Y Coordenada
     * @param g Grapchis
     * @return posicao da figura no armazenador
     */
    public int buscarPrimitivos(int x, int y, Graphics g){
         Object a[] = armazen.getArray();
         int tam = armazen.getQtd();
         int i = 0;
         int indice = -1; //Valor retornado quando nao encontrado
         int xref, yref;
         int xref2, yref2;
         int xref3, yref3;
         int xref4, yref4;
         boolean pertenceA1, pertenceA2, pertenceA3, pertenceA4; //Verifica se o intervalo pertence a algum atributo do primitivo
         boolean select = false;
        
         while(i < tam && select == false){
                
                if(a[i] instanceof Ponto){
                    PontoGr p = (PontoGr) a[i];
                    xref = (int)(p.getX());
                    yref = (int)(p.getY());
                    
                    pertenceA1 = verificarCoordenada(x,y,xref,yref); //Verifica se o click esta proximo ao atributo
                    
                    if(pertenceA1 == true){
                        if(p.getCorPto() != Color.green) //Salva a cor anterior para repintar o primitivo quando realizar alguma transformacao
                            cor_antiga = p.getCorPto();
                        p.setCorPto(Color.green); //Indica que o primitivo foi selecionado visualmente
                        FiguraPontos.desenharPonto(g,p);
                        select = true;
                    }
                }
                
                else if(a[i] instanceof Reta){
                    RetaGr r = (RetaGr) a[i];
                    
                    xref =(int)(r.getP1().getX());
                    yref = (int)(r.getP1().getY());    
                    pertenceA1 = verificarCoordenada(x,y,xref,yref); //Verifica se o click esta proximo ao atributo
                                      
                    xref = (int)(r.getP2().getX());
                    yref = (int)(r.getP2().getY());
                    pertenceA2 = verificarCoordenada(x,y,xref,yref); //Verifica se o click esta proximo ao atributo
                                      
                    if(pertenceA1 == true || pertenceA2 == true){
                        if(r.getCorReta() != Color.green) //Salva a cor anterior para repintar o primitivo quando realizar alguma transformacao
                            cor_antiga = r.getCorReta();
                        r.setCorReta(Color.green); //Indica que o primitivo foi selecionado visualmente
                        FiguraRetas.desenharReta(g,r);
 
                        select = true;
                    }
                    
                } else if(a[i] instanceof Retangulo){
                    RetanguloGr ret = (RetanguloGr) a[i];
                    
                    xref = (int)(ret.getD1().getX());
                    yref = (int)(ret.getD1().getY());
                    
                    xref2 = (int)(ret.getD2().getX());
                    yref2 = (int)(ret.getD2().getY());
                    
                    xref3 = (int)(ret.getD3().getX());
                    yref3 = (int)(ret.getD3().getY());
                    
                    xref4 = (int)(ret.getD4().getX());
                    yref4 = (int)(ret.getD4().getY());
                    
                    pertenceA1 = verificarCoordenada(x,y,xref,yref); //Verifica se o click esta proximo ao atributo
                    pertenceA2 = verificarCoordenada(x,y,xref2,yref2); //Verifica se o click esta proximo ao atributo
                    
                    pertenceA3 = verificarCoordenada(x,y,xref3, yref3); //Verifica se o click esta proximo ao atributo
                    pertenceA4 = verificarCoordenada(x,y,xref4, yref4); //Verifica se o click esta proximo ao atributo
                    
                    if(pertenceA1 == true || pertenceA2 == true || pertenceA3 == true || pertenceA4 == true){
                        if(ret.getCorRetangulo() != Color.green) //Salva a cor anterior para repintar o primitivo quando realizar alguma transformacao
                            cor_antiga = ret.getCorRetangulo();
                        ret.setCorRetangulo(Color.green); //Indica que o primitivo foi selecionado visualmente
                        FiguraRetangulo.desenharRetangulo(g,ret);
                    
                        select = true;
                    }
                }
                
                else if(a[i] instanceof Triangulo){
                    TrianguloGr tri = (TrianguloGr) a[i];
                    xref = (int)(tri.getP1().getX());
                    yref = (int)(tri.getP1().getY());
                    pertenceA1 = verificarCoordenada(x,y,xref,yref); //Verifica se o click esta proximo ao atributo
                    
                    xref = (int)(tri.getP2().getX());
                    yref = (int)(tri.getP2().getY());
                    pertenceA2 = verificarCoordenada(x,y,xref,yref); //Verifica se o click esta proximo ao atributo
                    
                    xref = (int)(tri.getP3().getX());
                    yref= (int)(tri.getP3().getY());
                    pertenceA3 = verificarCoordenada(x,y,xref,yref); //Verifica se o click esta proximo ao atributo
                   
                    if(pertenceA1 == true || pertenceA2 == true || pertenceA3 == true){
                        if(tri.getCorTriangulo() != Color.green) //Salva a cor anterior para repintar o primitivo quando realizar alguma transformacao
                            cor_antiga = tri.getCorTriangulo();
                        tri.setCorTriangulo(Color.green); //Indica que o primitivo foi selecionado visualmente
                        FiguraTriangulos.desenharTriangulo(g, tri);
                        select = true;
                    }
                } 
                
                else if(a[i] instanceof Circunferencia){
                    CircunferenciaGr c = (CircunferenciaGr) a[i];
                    
                    xref = (int)(c.getCentro().getX());
                    yref = (int)(c.getCentro().getY());
                    
                    pertenceA1 = verificarCoordenada(x,y,xref,yref); //Verifica se o click esta proximo ao atributo
                    
                    if(pertenceA1 == true){
                        if(c.getCorCirc() != Color.green)  //Salva a cor anterior para repintar o primitivo quando realizar alguma transformacao
                            cor_antiga = c.getCorCirc(); 
                        c.setCorCirc(Color.green); //Indica que o primitivo foi selecionado visualmente
                        FiguraCircunferencia.desenharCircunferencia(g,c);
                        select = true;
                    }
                }
                i++;
            }
            
            paint(g);
            if(select == true) 
                indice = i-1; //Posicao no armazenador
            return indice;
        }


    /**
     * Método apagarPrimitivos
     * Apaga Primitivo da ED e visualmente
     * @param g Graphics
     * @param pos Um parâmetro
     */
    private void apagarPrimitivos(Graphics g, int pos){
        Object a = armazen.buscar(pos); //Salva o primitivo numa variavel antes de ser apagado na ED
        armazen.remover(pos); //Remove o primitivo da ED
        if(a instanceof Ponto){
            PontoGr p= (PontoGr) a;
            p.setCorPto(getBackground()); 
            FiguraPontos.desenharPonto(g,p); //Remove o primitivo visualmente
        }
        if(a instanceof Reta){
            RetaGr r = (RetaGr) a;
            r.setCorReta(getBackground());
            FiguraRetas.desenharReta(g, r);  //Remove o primitivo visualmente
            apagarDestaqueAtributo(g, r);     
        }  
        
        else if(a instanceof Retangulo){
            RetanguloGr ret = (RetanguloGr) a;
            ret.setCorRetangulo(getBackground());
            FiguraRetangulo.desenharRetangulo(g,ret);  //Remove o primitivo visualmente
            apagarDestaqueAtributo(g, ret);
        }
            
        else if(a instanceof Triangulo){
            TrianguloGr tri = (TrianguloGr) a;
            tri.setCorTriangulo(getBackground());
            FiguraTriangulos.desenharTriangulo(g, tri);  //Remove o primitivo visualmente
            apagarDestaqueAtributo(g, tri);
        }
        
        else if(a instanceof Circunferencia){
            CircunferenciaGr c = (CircunferenciaGr) a;
            c.setCorCirc(getBackground());
            FiguraCircunferencia.desenharCircunferencia(g, c);  //Remove o primitivo visualmente
            apagarDestaqueAtributo(g, c);
        } 
           
        redesenharPrimitivos(g);
        destacarAtributos(g);
    }

    /**
     * Método transladarObjeto
     * Translada o primitivo, tendo como parâmetro o ponto P1
     * @param pos Indice do armazenador
     * @param g Grapchics
     * @param xclick Coordenada X ponto qualquer
     * @param yclick  Coordenada Y ponto qualquer
     */
    public void transladarObjeto(int pos, Graphics g, int xclick, int yclick){
        int xp1, yp1, xp2, yp2, xp3, yp3, xp4, yp4; //Recupera os pontos dos atributos
        int xt, yt; //Fator de translacao
  
        Object a = armazen.buscar(pos); //Recupera o primitivo da ED
      
        apagarPrimitivos(g, pos);   
       
        if(a instanceof Reta){
            RetaGr r = (RetaGr) a;
            xp1 = (int)(r.getP1().getX());
            yp1 = (int)(r.getP1().getY());
            xp2 = (int)(r.getP2().getX());
            yp2 = (int)(r.getP2().getY());
            if(xclick <= xp1){ //Desloca para a direita
                xt = xclick - xp1;
                xp1 += xt;
                xp2 += xt;
            } else{ //Desloca para a esquerda
                xt = xp1 - xclick;
                xp1 -= xt;
                xp2 -= xt;
            }
            
            if(yclick >= yp1){ //Desloca para baixo
                yt = yclick - yp1;
                yp1 += yt;
                yp2 += yt;
            } else{ //Desloca para cima
                yt = yp1 - yclick;
                yp1 -= yt;
                yp2 -= yt;
            }                       
                           
            r.setP1(new Ponto(xp1, yp1));
            r.setP2(new Ponto(xp2, yp2));
            r.setCorReta(cor_antiga);
            armazen.adicionar(r);
       
            FiguraRetas.desenharReta(g, r);
        
            paint(g);
        } else if(a instanceof Retangulo){
            RetanguloGr ret = (RetanguloGr) a;
            xp1 = (int)(ret.getD1().getX());
            yp1 = (int)(ret.getD1().getY());
            xp2 = (int)(ret.getD2().getX());
            yp2 = (int)(ret.getD2().getY());
            xp3 = (int)(ret.getD3().getX());
            yp3 = (int)(ret.getD3().getY());
            xp4 = (int)(ret.getD4().getX());
            yp4 = (int)(ret.getD4().getY());
            
            if(xclick <= xp1){ //Desloca para a direita
            xt = xclick - xp1;
            xp1 += xt;
            xp2 += xt;
            xp3 += xt;
            xp4 += xt;
            } else{ //Desloca para a esquerda
                xt = xp1 - xclick;
                xp1 -= xt;
                xp2 -= xt;
                xp3 -= xt;
                xp4 -= xt;
            }
            
            if(yclick >= yp1){ //Desloca para baixo
                yt = yclick - yp1;
                yp1 += yt;
                yp2 += yt;
                yp3 += yt;
                yp4 += yt;
                
            } else{ //Desloca para cima
                yt = yp1 - yclick;
                yp1 -= yt;
                yp2 -= yt;
                yp3 -= yt;
                yp4 -= yt;
            }                       
               
            ret.setD1(new Ponto(xp1, yp1));
            ret.setD2(new Ponto(xp2, yp2));
            ret.setD3(new Ponto(xp3, yp3));
            ret.setD4(new Ponto(xp4, yp4));
            ret.setCorRetangulo(cor_antiga);
            armazen.adicionar(ret);
            FiguraRetangulo.desenharRetangulo(g, ret);
            paint(g); 
        } else if(a instanceof Triangulo){
            TrianguloGr tri = (TrianguloGr) a;
            xp1 = (int)(tri.getP1().getX());
            yp1 = (int)(tri.getP1().getY());
            xp2 = (int)(tri.getP2().getX());
            yp2 = (int)(tri.getP2().getY());
            xp3 = (int)(tri.getP3().getX());
            yp3 = (int)(tri.getP3().getY());
            if(xclick <= xp1){ //Desloca para a direita
            xt = xclick - xp1;
            xp1 += xt;
            xp2 += xt;
            xp3 += xt;
            } else{//Desloca para a esquerda
                xt = xp1 - xclick;
                xp1 -= xt;
                xp2 -= xt;
                xp3 -= xt;
            }
            
            if(yclick >= yp1){ //Desloca para baixo
                yt = yclick - yp1;
                yp1 += yt;
                yp2 += yt;
                yp3 += yt;
            } else{ //Desloca para cima
                yt = yp1 - yclick;
                yp1 -= yt;
                yp2 -= yt;
                yp3 -= yt;
            }                       
               
            tri.setP1(new Ponto(xp1, yp1));
            tri.setP2(new Ponto(xp2, yp2));
            tri.setP3(new Ponto(xp3, yp3));
            tri.setCorTriangulo(cor_antiga);
            armazen.adicionar(tri);
            FiguraTriangulos.desenharTriangulo(g, tri);     
           
        } else if(a instanceof Circunferencia){
            CircunferenciaGr c = (CircunferenciaGr) a;
            xp1 = (int)(c.getCentro().getX());
            yp1 = (int)(c.getCentro().getY());
            xp2 = (int)(c.getReferencia().getX());
            yp2 = (int)(c.getReferencia().getY());
    
            if(xclick <= xp1){ //Desloca para a direita
            xt = xclick - xp1;
            xp1 += xt;
            xp2 += xt;

            } else{ //Desloca para a esquerda
                xt = xp1 - xclick;
                xp1 -= xt;
                xp2 -= xt;
            }
            
            if(yclick >= yp1){ //Desloca para baixo
                yt = yclick - yp1;
                yp1 += yt;
                yp2 += yt;
            } else{ //Desloca para cima
                yt = yp1 - yclick;
                yp1 -= yt;
                yp2 -= yt;
            }                       
               
              c.setCentro(new Ponto(xp1, yp1));
              c.setReferencia(xp2,yp2);
              c.setCorCirc(cor_antiga);
              armazen.adicionar(c);
              FiguraCircunferencia.desenharCircunferencia(g, c);
         
        } else if(a instanceof Ponto){
            PontoGr pgr = (PontoGr) a;
            xp1 = (int)(pgr.getX());
            yp1 = (int)(pgr.getY());
        
            if(xclick <= xp1){ //Desloca para a direita
            xt = xclick - xp1;
            xp1 += xt;
           

            } else{ //Desloca para a esquerda
                xt = xp1 - xclick;
                xp1 -= xt;
                
            }
            
            if(yclick >= yp1){ //Desloca para baixo
                yt = yclick - yp1;
                yp1 += yt;
               
            } else{ //Desloca para a cima
                yt = yp1 - yclick;
                yp1 -= yt;
               
            }                       
               
              pgr.setX(xp1);
              pgr.setY(yp1);
              pgr.setCorPto(cor_antiga);
              armazen.adicionar(pgr);   
              FiguraPontos.desenharPonto(g, pgr);   
              
        }
            paint(g);
            //redesenharPrimitivos(g);
           primeiraVezTrans = true;
           
    }
    
     /**
      * Método rotacionarObjeto
      *
      * @param pos Indice da ED
      * @param g  biblioteca para desenhar em modo grafico
      * @param xref Coordenada X do click a ser usado como referencia para a rotacao
      * @param yref Coordenada Y do click a ser usado como referencia para a rotacao
      * @param angulo Angulacao da rotacao
      */
     public void rotacionarObjeto(int pos, Graphics g, int xref, int yref, double angulo){
           int xp1, yp1, xp2, yp2, xp3, yp3, xp4, yp4; //Recupa as coordenadas dos atributos do primitivo selecionado
           int xr, yr, xr2, yr2, xr3, yr3, xr4, yr4; //Novos pontos a serem calculados apos a rotacao
           
           double ang = Math.toRadians(angulo); 
        
           double angCos = Math.cos(ang);
           double angSeno = Math.sin(ang);
           Object a = armazen.buscar(pos); //Recupera o primitivo na ED a partir do indice
           apagarPrimitivos(g,pos);
           if(a instanceof Reta){
                RetaGr r = (RetaGr) a;
                xp1 = (int)(r.getP1().getX());
                yp1 = (int)(r.getP1().getY());
                xp2 = (int)(r.getP2().getX());
                yp2 = (int)(r.getP2().getY());
    
                xr = (int)(xp1*angCos - yp1*angSeno + xref*(1-angCos) + yref*angSeno);
                yr = (int)(xp1*angSeno + yp1*angCos + yref*(1-angCos) - xref*angSeno);
                xr2 = (int)(xp2*angCos - yp2*angSeno + xref*(1-angCos) + yref*angSeno);
                yr2 = (int)(xp2*angSeno + yp2*angCos + yref*(1-angCos) - xref*angSeno); 
            
                r.setP1(new Ponto(xr, yr));
                r.setP2(new Ponto(xr2, yr2));
               
                r.setCorReta(cor_antiga);
                FiguraRetas.desenharReta(g, r);
                armazen.adicionar(r);
                
           } else if(a instanceof Retangulo){
                RetanguloGr ret = (RetanguloGr) a;
                xp1 = (int)(ret.getD1().getX());
                yp1 = (int)(ret.getD1().getY());
                xp2 = (int)(ret.getD2().getX());
                yp2 = (int)(ret.getD2().getY());
                
                xp3 = (int)(ret.getD3().getX());
                yp3 = (int)(ret.getD3().getY());
                
                xp4 = (int)(ret.getD4().getX());
                yp4 = (int)(ret.getD4().getY());
        
                xr = (int)(xp1*angCos - yp1*angSeno + xref*(1-angCos) + yref*angSeno);
                yr = (int)(xp1*angSeno + yp1*angCos + yref*(1-angCos) - xref*angSeno);
                
                xr2 = (int)(xp2*angCos - yp2*angSeno + xref*(1-angCos) + yref*angSeno);
                yr2 = (int)(xp2*angSeno + yp2*angCos + yref*(1-angCos) - xref*angSeno); 
                
                xr3 = (int)(xp3*angCos - yp3*angSeno + xref*(1-angCos) + yref*angSeno);
                yr3 = (int)(xp3*angSeno + yp3*angCos + yref*(1-angCos) - xref*angSeno); 
                
                xr4 = (int)(xp4*angCos - yp4*angSeno + xref*(1-angCos) + yref*angSeno);
                yr4 = (int)(xp4*angSeno + yp4*angCos + yref*(1-angCos) - xref*angSeno); 
                
                ret.setD1(new Ponto(xr, yr));
                ret.setD2(new Ponto(xr2, yr2));
                ret.setD3(new Ponto(xr3, yr3));
                ret.setD4(new Ponto(xr4, yr4));
               
                ret.setCorRetangulo(cor_antiga);
                armazen.adicionar(ret);
                FiguraRetangulo.desenharRetangulo(g, ret);
                
           } else if (a instanceof Triangulo){
            TrianguloGr tri = (TrianguloGr) a;
            xp1 = (int) (tri.getP1().getX());
            yp1 = (int) (tri.getP1().getY());
            xp2 = (int) (tri.getP2().getX());
            yp2 = (int) (tri.getP2().getY());
            xp3 = (int) (tri.getP3().getX());
            yp3 = (int) (tri.getP3().getY());
    
            xr = (int)(xp1*angCos - yp1*angSeno + xref*(1-angCos) + yref*angSeno);
            yr = (int)(xp1*angSeno + yp1*angCos + yref*(1-angCos) - xref*angSeno);
            xr2 = (int)(xp2*angSeno - yp2*angSeno + xref*(1-angCos) + yref*angSeno);
            yr2 = (int)(xp2*angSeno + yp2*angCos + yref*(1-angCos) - xref*angSeno);
        
            xr3 = (int)(xref + (xp3 - xref) * angSeno - (yp3 - yref) * angSeno);
            yr3 = (int)(yref + (xp3 - xref) * angSeno + (yp3 - yref) * angSeno);
    
            tri.setP1(new Ponto(xr, yr));
            tri.setP2(new Ponto(xr2, yr2));
            tri.setP3(new Ponto(xr3, yr3));
            tri.setCorTriangulo(cor_antiga);
            armazen.adicionar(tri);
            FiguraTriangulos.desenharTriangulo(g, tri);
            
        } else if(a instanceof Circunferencia){
            CircunferenciaGr c = (CircunferenciaGr) a;
            xp1 = (int) (c.getCentro().getX());
            yp1 = (int) (c.getCentro().getY());
            xp2 = (int) (c.getReferencia().getX());
            yp2 = (int) (c.getReferencia().getY());
       
            xr = (int)(xp1*angCos - yp1*angSeno + xref*(1-angCos) + yref*angSeno);
            yr = (int)(xp1*angSeno + yp1*angCos + yref*(1-angCos) - xref*angSeno);
            xr2 = (int)(xp2*angSeno - yp2*angSeno + xref*(1-angCos) + yref*angSeno);
            yr2 = (int)(xp2*angSeno + yp2*angCos + yref*(1-angCos) - xref*angSeno);
          
            c.setCentro(new Ponto(xr, yr));
            c.setReferencia(xr2, yr2);
            
            c.setCorCirc(cor_antiga);
            armazen.adicionar(c);
            FiguraCircunferencia.desenharCircunferencia(g, c);
            
        } else if (a instanceof Ponto){
            PontoGr p = (PontoGr) a;
            xp1 = (int)(p.getX());
            yp1 = (int)(p.getY());
            
            xr = (int)(xp1*angCos - yp1*angSeno + xref*(1-angCos) + yref*angSeno);
            yr = (int)(xp1*angSeno + yp1*angCos + yref*(1-angCos) - xref*angSeno);
            
            p.setX(xr);
            p.setY(yr);
            
            p.setCorPto(cor_antiga);
            armazen.adicionar(p);
            FiguraPontos.desenharPonto(g, p);
        }
        
        primeiraVezTrans = true;
        paint(g);
    }
      
    /**
     * Método escalarObjeto
     *
     * @param pos Indice do primitivo selecionado na ED
     * @param g biblioteca para desenhar em modo grafico
     * @param xref Coordenada X do ponto que vai ser usado como referencia para escala
     * @param yref Coordenada Y do ponto que vai ser usado como referencia para escala
     * @param sx Fator de escala X
     * @param sy Fator de escala Y
     */
    
    public void escalarObjeto(int pos, Graphics g, int xref, int yref, double sx, double sy){
           int xp1, yp1, xp2, yp2, xp3, yp3, xp4, yp4; //Recupera as coordenadas dos atributos do primitivo
           int xe, ye, xe2, ye2, xe3, ye3, xe4, ye4; //Novos pontos a serem calculados pela escala
           
           int x,y; //Inverte as coordenadas do monitor
           x = 720 - xref;
           y = 600 - yref;
           Object a = armazen.buscar(pos); //Recupera o primitivo selecionado na ED
           apagarPrimitivos(g, pos);
           if(a instanceof Reta){
                RetaGr r = (RetaGr) a;
                xp1 = (int)(r.getP1().getX());
                yp1 = (int)(r.getP1().getY());
                xp2 = (int)(r.getP2().getX());
                yp2 = (int)(r.getP2().getY());
                
                r.getP1().setX(sx*xp1 + xref*(1-sx));
                r.getP1().setY(sy*yp1 + yref*(1-sy));
                r.getP2().setX(sx*xp2 + xref*(1-sx));
                r.getP2().setY(sy*yp2 + yref*(1-sy));
                r.setCorReta(cor_antiga);
                FiguraRetas.desenharReta(g, r);
                armazen.adicionar(r);
                paint(g);

           } else if(a instanceof Retangulo){
                RetanguloGr ret = (RetanguloGr) a;
                xp1 = (int) (ret.getD1().getX());
                yp1 = (int) (ret.getD1().getY());
                xp2 = (int) (ret.getD2().getX());
                yp2 = (int) (ret.getD2().getY());
                
                xp3 = (int) (ret.getD3().getX());
                yp3 = (int) (ret.getD3().getY());
                
                xp4 = (int) (ret.getD4().getX());
                yp4 = (int) (ret.getD4().getY());
            
            
                xe = (int)(sx*xp1 + xref*(1-sx));
                ye = (int)(sy*yp1 + yref*(1-sy));
                
                xe2 = (int)(sx*xp2 + xref*(1-sx));
                ye2 = (int)(sy*yp2 + yref*(1-sy));
                
                xe3 = (int)(sx*xp3 + xref*(1-sx));
                ye3 = (int)(sy*yp3 + yref*(1-sy));
                
                xe4 = (int)(sx*xp4 + xref*(1-sx));
                ye4 = (int)(sy*yp4 + yref*(1-sy));
  
                ret.setD1(new Ponto(xe, ye));
                ret.setD2(new Ponto(xe2, ye2));
                ret.setD3(new Ponto(xe3, ye3));
                ret.setD4(new Ponto(xe4, ye4));
            
                ret.setCorRetangulo(cor_antiga);
                FiguraRetangulo.desenharRetangulo(g, ret);
                armazen.adicionar(ret);
            
            } else if(a instanceof Triangulo){
                TrianguloGr tri = (TrianguloGr) a;
                xp1 = (int) (tri.getP1().getX());
                yp1 = (int) (tri.getP1().getY());
                
                xp2 = (int) (tri.getP2().getX());
                yp2 = (int) (tri.getP2().getY());
                
                xp3 = (int) (tri.getP3().getX());
                yp3 = (int) (tri.getP3().getY());        
            
                xe = (int)(sx*xp1 + xref*(1-sx));
                ye = (int)(sy*yp1 + yref*(1-sy));
                
                xe2 = (int)(sx*xp2 + xref*(1-sx));
                ye2 = (int)(sy*yp2 + yref*(1-sy));
                
                xe3 = (int)(sx*xp3 + xref*(1-sx));
                ye3 = (int)(sy*yp3 + yref*(1-sy));           
               
                tri.setP1(new Ponto(xe, ye));
                tri.setP2(new Ponto(xe2, ye2));
                tri.setP3(new Ponto(xe3, ye3));          
            
                tri.setCorTriangulo(cor_antiga);
                FiguraTriangulos.desenharTriangulo(g, tri);
                armazen.adicionar(tri);
            
            } else if(a instanceof Circunferencia){
                CircunferenciaGr c = (CircunferenciaGr) a;
                xp1 = (int) (c.getCentro().getX());
                yp1 = (int) (c.getCentro().getY());
                
                xp2 = (int) (c.getReferencia().getX());
                yp2 = (int) (c.getReferencia().getY());
                      
                xe = (int)(sx*xp1 + xref*(1-sx));
                ye = (int)(sy*yp1 + yref*(1-sy));
                
                xe2 = (int)(sx*xp2 + xref*(1-sx));
                ye2 = (int)(sy*yp2 + yref*(1-sy));
                
                c.setCentro(new Ponto(xe,ye));
                c.setReferencia(xe2,ye2);
                
                c.setCorCirc(cor_antiga);
                FiguraCircunferencia.desenharCircunferencia(g, c);
                armazen.adicionar(c);
            } else if(a instanceof Ponto){
                PontoGr p = (PontoGr) a;
                p.setCorPto(cor_antiga);
                FiguraPontos.desenharPonto(g, p);
                armazen.adicionar(p);
            }         
              primeiraVezTrans = true;
              paint(g);
    }
    
      
     /**
      * Método verificarCoordenada
      * Verifica se o click esta proximo de algum atributo do primitivo
      * @param xclick Coordenada X
      * @param yclick Coordenada Y
      * @param xfig Coordenada X do atributo
      * @param yfig Coordenada Y do atributo
      * @return true se estiver proximo / false caso contrario
      */
     public boolean verificarCoordenada(int xclick, int yclick, int xfig, int yfig){
        int distance = 8;
        boolean prox = false;
        for(int i = xfig-distance; i <= xfig+distance && prox == false; i++){ //Verifica se o click esta proximo a X
            for(int j = yfig-distance; j <= yfig+distance && prox == false; j++) //Verufuca se i ckucj esta proximo a Y
                if(xclick == i && yclick == j)
                    prox = true;
        }

        return prox;
    }
    
      /**
       * Método destacarAtributos
       * Destaca os atributos dos primitivos que podem ser selecionados
       * @param g biblioteca para desenhar em modo grafico
       */
      private void destacarAtributos(Graphics g){
        Object a[] = armazen.getArray();
        int tam = armazen.getQtd();
        PontoGr pg1, pg2, pg3, pg4;
        for(int i = 0; i < tam; i++){
            //System.out.println(a[i]);
            if(a[i] instanceof Reta){
                RetaGr r = (RetaGr) a[i];
                pg1 = new PontoGr((int)(r.getP1().getX()), (int)(r.getP1().getY()), Color.red, r.getEspReta()+2); //Destaca atributos das extremidades com a cor vermelha
                pg2 = new PontoGr((int)(r.getP2().getX()), (int)(r.getP2().getY()), Color.red, r.getEspReta()+2);
                FiguraPontos.desenharPonto(g, pg1);
                FiguraPontos.desenharPonto(g, pg2);
            } 
            
            else if(a[i] instanceof Retangulo){
                RetanguloGr ret = (RetanguloGr) a[i];
                pg1 = new PontoGr((int)(ret.getD1().getX()), (int)(ret.getD1().getY()), Color.red, ret.getEspRetangulo()+2); //Destaca atributos das extremidades com a cor vermelha
                pg2 = new PontoGr((int)(ret.getD2().getX()), (int)(ret.getD2().getY()), Color.red, ret.getEspRetangulo()+2);
                pg3 = new PontoGr((int)(ret.getD3().getX()), (int)(ret.getD3().getY()), Color.red, ret.getEspRetangulo()+2);
                pg4 = new PontoGr((int)(ret.getD4().getX()), (int)(ret.getD4().getY()), Color.red, ret.getEspRetangulo()+2);
                
                FiguraPontos.desenharPonto(g, pg1);
                FiguraPontos.desenharPonto(g, pg2);
                FiguraPontos.desenharPonto(g, pg3);
                FiguraPontos.desenharPonto(g, pg4);
                
                FiguraPontos.desenharPonto(g, pg1);
                FiguraPontos.desenharPonto(g, pg2);
                FiguraPontos.desenharPonto(g, pg3);
                FiguraPontos.desenharPonto(g, pg4);
            } 
            
            else if(a[i] instanceof Triangulo){
                TrianguloGr tri = (TrianguloGr) a[i];
                pg1 = new PontoGr((int)(tri.getP1().getX()), (int)(tri.getP1().getY()), Color.red, tri.getEspTriangulo()+2); //Destaca atributos das extremidades com a cor vermelha
                pg2 = new PontoGr((int)(tri.getP2().getX()), (int)(tri.getP2().getY()), Color.red, tri.getEspTriangulo()+2);
                pg3 = new PontoGr((int)(tri.getP3().getX()), (int)(tri.getP3().getY()), Color.red, tri.getEspTriangulo()+2);
                FiguraPontos.desenharPonto(g, pg1);
                FiguraPontos.desenharPonto(g, pg2);
                FiguraPontos.desenharPonto(g, pg3); 
            } 
            
            else if(a[i] instanceof Circunferencia){
                CircunferenciaGr c = (CircunferenciaGr) a[i];
                pg1 = new PontoGr((int)(c.getCentro().getX()), (int)(c.getCentro().getY()), Color.red, c.getEspCirc()+2); //Destaca atributos das extremidades com a cor vermelha
                FiguraPontos.desenharPonto(g, pg1);
            }
        }
        paint(g);
    }
    
    private void apagarDestaqueAtributo(Graphics g, Object a){
        PontoGr pg1;
        PontoGr pg2;
        PontoGr pg3;
        
        if(a instanceof Reta){
            RetaGr r = (RetaGr) a;
            pg1 = new PontoGr((int)(r.getP1().getX()), (int)(r.getP1().getY()), getBackground(), r.getEspReta()+2); // apaga o destaque dos atributos visualmente
            pg2 = new PontoGr((int)(r.getP2().getX()), (int)(r.getP2().getY()), getBackground(), r.getEspReta()+2);
            FiguraPontos.desenharPonto(g, pg1);
            FiguraPontos.desenharPonto(g, pg2);
        } 
        
        else if(a instanceof Retangulo){
                RetanguloGr ret = (RetanguloGr) a;
                pg1 = new PontoGr((int)(ret.getD1().getX()), (int)(ret.getD1().getY()), getBackground(), ret.getEspRetangulo()+2); // apaga o destaque dos atributos visualmente
                pg2 = new PontoGr((int)(ret.getD2().getX()), (int)(ret.getD2().getY()), getBackground(), ret.getEspRetangulo()+2);
                pg3 = new PontoGr(0,0);
                
                FiguraPontos.desenharPonto(g, pg1);
                FiguraPontos.desenharPonto(g, pg2);
                
                pg3.setX(pg2.getX());
                pg2.setX(pg1.getX());
                pg1.setX(pg3.getX());
                
                FiguraPontos.desenharPonto(g, pg2);
                FiguraPontos.desenharPonto(g, pg1);
        } 
        
        else if(a instanceof Triangulo){
                TrianguloGr tri = (TrianguloGr) a;
                pg1 = new PontoGr((int)(tri.getP1().getX()), (int)(tri.getP1().getY()), getBackground(), tri.getEspTriangulo()+2); // apaga o destaque dos atributos visualmente
                pg2 = new PontoGr((int)(tri.getP2().getX()), (int)(tri.getP2().getY()), getBackground(), tri.getEspTriangulo()+2);
                pg3 = new PontoGr((int)(tri.getP3().getX()), (int)(tri.getP3().getY()), getBackground(), tri.getEspTriangulo()+2);
                FiguraPontos.desenharPonto(g, pg1);
                FiguraPontos.desenharPonto(g, pg2);
                FiguraPontos.desenharPonto(g, pg3); 
        } 
        
        else if(a instanceof Circunferencia){
                CircunferenciaGr c = (CircunferenciaGr) a;
                pg1 = new PontoGr((int)(c.getCentro().getX()), (int)(c.getCentro().getY()), getBackground(), c.getEspCirc()+2); // apaga o destaque dos atributos visualmente
                FiguraPontos.desenharPonto(g, pg1);
        }
        paint(g);
    }
    
      
     /**
      * Método setAnguloRotacao
      * Define o angulo da rotacao a ser realizada
      * @param angulo Angulo de rotacao
      */
     public void setAnguloRotacao(double angulo){
        angRotate = angulo;
    }
    
    /**
     * Método setFatoresEscala
     * Define os valores dos fatores de escala a ser realizada
     * @param sx Fator de escala X
     * @param sy Fator de escala Y
     */
    public void setFatoresEscala(double sx, double sy){
        fatorX = sx;
        fatorY = sy;
    }
      
    /**
     * Método esvaziarLista
     * Esvazia a estrutura de dados
     */
    public void esvaziarLista(){
        int tam = armazen.getQtd()-1;
        while(!armazen.estaVazia()){
            armazen.remover(tam);
            tam--;
        }
    }
}

