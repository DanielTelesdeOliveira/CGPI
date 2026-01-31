import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.JOptionPane; 

@SuppressWarnings("serial")
/**
 * Cria a interface com o usuario (GUI)
 * 
 * @author Julio Arakaki 
 * @version 20220815
 */
class Gui extends JFrame {
    // Tipo Atual de primitivo
    private TipoPrimitivo tipoAtual = TipoPrimitivo.NENHUM;

    // Cor atual
    private Color corAtual = Color.BLACK;

    // Espessura atual do primitivo
    private int espAtual = 5;

    // Componentes de GUI
    // barra de menu (inserir componente)
    private JToolBar barraComandos = new JToolBar();

    // mensagens
    private JLabel msg = new JLabel("Msg: ");

    // Painel de desenho
    private PainelDesenho areaDesenho = new PainelDesenho(msg, tipoAtual, corAtual, 10);

    // Botoes
    private JButton jbPonto = new JButton("Ponto");
    private JButton jbReta = new JButton("Reta");
    private JButton jbCirculo = new JButton("Circulo");
    private JButton jbRetangulo= new JButton("Retangulo");
    private JButton jbTriangulo = new JButton("Triangulo");
    private JButton jbDelete = new JButton("Deletar");
    private JButton jbLimpar = new JButton("Limpar");
    private JButton jbCor = new JButton("Cor");
    private JButton jbSair = new JButton("Sair");
    private JButton jbTransformacao = new JButton("Transformacoes");
    private JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem miTransalacao = new JMenuItem("Translacao");
    private JMenuItem miRotacao = new JMenuItem("Rotacao");
    private JMenuItem miEscala = new JMenuItem("Escala");
    private JButton jbTranslacao = new JButton("Translacao");
    private JButton jbRotacao = new JButton("Rotacao");
    // Entrada (slider) para definir espessura dos primitivos
    private JLabel jlEsp = new JLabel("   Espessura: " + String.format("%-5s", 5));
    private JSlider jsEsp = new JSlider(1, 50, 5);

    /**
     * Constroi a GUI
     *
     * @param larg largura da janela
     * @param alt altura da janela
     */
    public Gui(int larg, int alt) {
        /**
         * Definicoes de janela
         */
        super("Testa Primitivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(larg, alt);
        setResizable(false);
        setVisible(true);  
        popupMenu.add(miTransalacao);
        popupMenu.add(miRotacao);
        popupMenu.add(miEscala);

        // Adicionando os componentes
        barraComandos.add(jbPonto);
        barraComandos.add(jbReta);
        barraComandos.add(jbCirculo);
        barraComandos.add(jbRetangulo);
        barraComandos.add(jbTriangulo);
        barraComandos.add(jbLimpar); // Botao de Limpar
        barraComandos.add(jbDelete);
        barraComandos.add(jbCor); // Botao de Cores
        barraComandos.add(jbTransformacao);
        //barraComandos.add(jbTranslacao); 
        barraComandos.add(jlEsp); // Label para espessura
        barraComandos.add(jsEsp);    // Slider para espacamento
        areaDesenho.setEsp(espAtual); // define a espessura inicial
        barraComandos.add(jbSair); // Botao de Cores
        
        // adiciona os componentes com os respectivos layouts
        add(barraComandos, BorderLayout.NORTH);                
        add(areaDesenho, BorderLayout.CENTER);                
        add(msg, BorderLayout.SOUTH);

        // Adiciona "tratador" ("ouvidor") de eventos para 
        // cada componente
        jbPonto.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.PONTO;
            areaDesenho.setTipo(tipoAtual);
        });        
        
        jbReta.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.RETA;
            areaDesenho.setTipo(tipoAtual);
        });     
        
        jbCirculo.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.CIRCULO;
            areaDesenho.setTipo(tipoAtual);
        });    
        
        jbRetangulo.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.RETANGULO;
            areaDesenho.setTipo(tipoAtual);
        }); 
        
        jbTriangulo.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.TRIANGULO;
            areaDesenho.setTipo(tipoAtual);
        }); 
        
        jbLimpar.addActionListener(e -> {
            areaDesenho.deleteMode = false;
            areaDesenho.removeAll();
            areaDesenho.esvaziarLista();
            jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivoda tela) 
            repaint();    
        });   
    
         jbTransformacao.addMouseListener(new MouseAdapter() { 
             public void mousePressed(MouseEvent e){
                popupMenu.show(e.getComponent(), 3, 25);
            }
                
        });
           
        jbDelete.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.setTipo(tipoAtual); 
            //areaDesenho.setDeleteMode(true);
            areaDesenho.deleteMode = true;
            areaDesenho.transladar = false;
            areaDesenho.rotacionar = false;
            areaDesenho.escala = false;
        }); 
        
        miTransalacao.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.setTipo(tipoAtual); 
            //areaDesenho.setDeleteMode(true);
            areaDesenho.deleteMode = false;
            areaDesenho.rotacionar = false;
            areaDesenho.escala = false;
            areaDesenho.transladar = true;
        }); 

        miRotacao.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.setTipo(tipoAtual); 
            areaDesenho.deleteMode = false;
            areaDesenho.escala = false;
            areaDesenho.transladar = false;
            areaDesenho.rotacionar = true;
        
            // Solicitar ângulo ao usuário
            String input = JOptionPane.showInputDialog(null, "Digite o ângulo de rotação:", "Rotação", JOptionPane.PLAIN_MESSAGE);
            if (input != null) {
                try {
                    double angulo = Double.parseDouble(input);
                    // Aqui você pode aplicar o ângulo no seu método de rotação
                    areaDesenho.setAnguloRotacao(angulo); // Método fictício, você precisa implementar isso
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        miEscala.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.setTipo(tipoAtual);
            areaDesenho.deleteMode = false;
            areaDesenho.transladar = false;
            areaDesenho.rotacionar = false;
            areaDesenho.escala = true;
        
            // Solicitar fatores de escala ao usuário
            String inputX = JOptionPane.showInputDialog(null, "Digite o fator de escala X:", "Escala", JOptionPane.PLAIN_MESSAGE);
            String inputY = JOptionPane.showInputDialog(null, "Digite o fator de escala Y:", "Escala", JOptionPane.PLAIN_MESSAGE);
        
            if (inputX != null && inputY != null) {
                try {
                    double fatorX = Double.parseDouble(inputX);
                    double fatorY = Double.parseDouble(inputY);
                    // Aqui você pode aplicar os fatores de escala nos métodos de escala
                    areaDesenho.setFatoresEscala(fatorX, fatorY); // Método fictício, você precisa implementar isso
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        jbCor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(null, "Escolha uma cor", msg.getForeground()); 
            if (c != null){ 
                corAtual = c; // pega do chooserColor 
            }
            areaDesenho.setCorAtual(corAtual); // cor atual
        });  
        
        jsEsp.addChangeListener(e -> {
            espAtual = jsEsp.getValue();
            jlEsp.setText("   Espessura: " + String.format("%-5s", espAtual));
            areaDesenho.setEsp(espAtual);        
        });        

        jbSair.addActionListener(e -> {
            System.exit(0);
        });        
        
    }
    
}
