/**
 * @author Alfredo Hinojosa Huerta A01036053
 * @author Luis Juan Sanchez A01183634
 * @version 1.0 3/3/2014
 */
package breakingbad;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class BreakingBad extends JFrame implements Runnable, MouseListener, KeyListener {

    private Animacion animPaleta; // Animacion de la Paleta (Jugador)
    private Animacion animDEA; // Animacion de DEA
    private Animacion animMeth; // Animacion de Meth
    private LinkedList link; // Lista enlazada para los cuadros
    private Image background; // Imagen del Background
    private Image backScore; // Imagen del score
    private Image imgOver; // Imagen de Game Over
    private Image imgPause; // Imagen de la pausa
    private long tiempoActual;  // tiempo actual
    private long tiempoInicial; // tiempo inicial
    private Paleta paleta; // Objeto de la Paleta
    private Animacion animPelota; // Animacion de la Pelota
    private Pelota pelota; // Objeto de Pelota
    private Meth meth; // Objeto de la clase Meth
    private int peMovy; // Movimiento de la Pelota en el eje y
    private int peMovx; // Movimiento de la Pelota en el eje x
    private int paMov; // Movimiento de la Paleta
    private int score; // Variable del score
    private Image dbImage; // Imagen
    private Graphics dbg; // Objeto Grafico
    private boolean pausa; // Flag de pausa
    private boolean inicio; // Flag de inicio
    private boolean tecla; // flag del teclado
    private boolean gOver; // Bandera de juego perdido
    private Font myFont;

    /**
     * El metodo constructor de la clase BreakingBad
     */
    public BreakingBad() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setTitle("Breaking Bad: The Game");
        myFont = new Font("Serif", Font.BOLD, 34); // Estilo de fuente
        link = new LinkedList(); // Creacion de la lista enlazada
        score = 0;
        pausa = false;
        inicio = false;
        tecla = false;
        gOver = false;
        peMovx = paMov = 0;
        peMovy = 5;

        background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/backBad.gif"));
        backScore = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/backScore.png"));
        imgOver = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/gOver.png"));
        imgPause = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pause.png"));

        // Animacion de la Paleta
        Image b0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fedora.png"));
        animPaleta = new Animacion();
        animPaleta.sumaCuadro(b0, 100);

        // Animacion del bloque de meth
        Image m1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/meth.png"));
        animMeth = new Animacion();
        animMeth.sumaCuadro(m1, 100);

        // Genera la cuadricula de cuadros de meth
        for (int i = 50; i < 236; i += 59) {
            for (int j = 45; j < 1130; j += 150) {
                link.add(new Meth(j, i, animMeth));
            }
        }

        // Animacion de la Pelota
        b0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota1.png"));
        Image b1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota2.png"));
        Image b2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota3.png"));
        Image b3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota4.png"));
        Image b4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota5.png"));
        Image b5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota6.png"));
        Image b6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota7.png"));
        Image b7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota8.png"));
        Image b8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota9.png"));
        Image b9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota10.png"));
        Image b10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota11.png"));
        Image b11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota12.png"));
        Image b12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota13.png"));
        Image b13 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota14.png"));
        Image b14 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota15.png"));
        Image b15 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota16.png"));
        Image b16 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota17.png"));
        Image b17 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota18.png"));

        animPelota = new Animacion();
        animPelota.sumaCuadro(b0, 100);
        animPelota.sumaCuadro(b1, 100);
        animPelota.sumaCuadro(b2, 100);
        animPelota.sumaCuadro(b3, 100);
        animPelota.sumaCuadro(b4, 100);
        animPelota.sumaCuadro(b5, 100);
        animPelota.sumaCuadro(b6, 100);
        animPelota.sumaCuadro(b7, 100);
        animPelota.sumaCuadro(b8, 100);
        animPelota.sumaCuadro(b9, 100);
        animPelota.sumaCuadro(b10, 100);
        animPelota.sumaCuadro(b11, 100);
        animPelota.sumaCuadro(b12, 100);
        animPelota.sumaCuadro(b13, 100);
        animPelota.sumaCuadro(b14, 100);
        animPelota.sumaCuadro(b15, 100);
        animPelota.sumaCuadro(b16, 100);
        animPelota.sumaCuadro(b17, 100);

        // Animacion de DEA
        Image dea1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/1.PNG"));
        Image dea2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/2.PNG"));
        Image dea3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/3.PNG"));
        Image dea4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/4.PNG"));
        Image dea5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/5.PNG"));
        Image dea6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/6.PNG"));
        Image dea7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/7.PNG"));
        Image dea8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/8.PNG"));
        Image dea9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/9.PNG"));
        Image dea10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/10.PNG"));
        animDEA = new Animacion();
        animDEA.sumaCuadro(dea1, 100);
        animDEA.sumaCuadro(dea2, 100);
        animDEA.sumaCuadro(dea3, 100);
        animDEA.sumaCuadro(dea4, 100);
        animDEA.sumaCuadro(dea5, 100);
        animDEA.sumaCuadro(dea6, 100);
        animDEA.sumaCuadro(dea7, 100);
        animDEA.sumaCuadro(dea8, 100);
        animDEA.sumaCuadro(dea9, 100);
        animDEA.sumaCuadro(dea10, 100);

        //paleta = new Paleta(0, 0, animPaleta);
        paleta = new Paleta(0, 0, animDEA);
        paleta.setPosX(this.getWidth() / 2 - paleta.getAncho() / 2);
        paleta.setPosY((this.getHeight() - paleta.getAlto()) - 8);

        pelota = new Pelota(0, 0, animPelota);
        pelota.setPosX(paleta.getPosX() + paleta.getAncho() / 2 - pelota.getAncho() / 2);
        pelota.setPosY(paleta.getPosY() - pelota.getAlto());

        addMouseListener(this);
        addKeyListener(this);
        Thread th = new Thread(this);
        th.start();
    }

    /**
     * Maneja los eventos que suceden al accionar algun boton del JMenuBar
     *
     * @param e evento del MenuItem
     */
    public void reinicia() {
        score = 0;
        pausa = false;
        inicio = false;
        tecla = false;
        gOver = false;
        peMovx = paMov = 0;
        peMovy = 5;

        paleta.setPosX(this.getWidth() / 2 - paleta.getAncho() / 2);
        paleta.setPosY((this.getHeight() - paleta.getAlto()) - 8);
        pelota.setPosX(paleta.getPosX() + paleta.getAncho() / 2 - pelota.getAncho() / 2);
        pelota.setPosY(paleta.getPosY() - pelota.getAlto());
        link.clear();
        for (int i = 50; i < 236; i += 59) {
            for (int j = 45; j < 1130; j += 150) {
                link.add(new Meth(j, i, animMeth));
            }
        }

    }

    public void run() {
        tiempoActual = System.currentTimeMillis();
        while (true) {
            if (!pausa && inicio && !gOver) {
                checaColision();
                actualiza();
            }
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
    }

    /**
     * 
     */
    public void actualiza() {
        if (paleta.getPosX() + paMov > 0
                && paleta.getPosX() + paMov + paleta.getAncho() < this.getWidth()) {
            paleta.setPosX(paleta.getPosX() + paMov);
        }

        pelota.setPosX(pelota.getPosX() + peMovx);
        pelota.setPosY(pelota.getPosY() + peMovy);

        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
        tiempoActual += tiempoTranscurrido;
        paleta.getAnimacion().actualiza(tiempoTranscurrido);
        pelota.getAnimacion().actualiza(tiempoTranscurrido);

    }

    /**
     * Este metodo controla los eventos de colision de la paleta
     * y de los cuadros.
     */
    public void checaColision() {
        if (pelota.getPosX() < 15) {
            peMovx = Math.abs(peMovx);
        }
        if (pelota.getPosX() + pelota.getAncho() > this.getWidth() - 15) {
            peMovx = -Math.abs(peMovx);
        }
        if (pelota.getPosY() < 50) {
            peMovy = Math.abs(peMovy);
        }
        if (pelota.getPosY() + pelota.getAlto() - 35 > this.getHeight()) {
            gOver = true; // Acabo el juego
        }
        if (paleta.getPerimetro().intersects(pelota.getPerimetro())) { // Colision de pelota
            int ca = (pelota.getPosX() + pelota.getAncho() / 2)
                    - (paleta.getPosX() + paleta.getAncho() / 2);
            int co = (pelota.getPosY())
                    - (paleta.getPosY());
            int h = (int) Math.sqrt(Math.pow(ca, 2) + Math.pow(co, 2));
            peMovx = (int) Math.ceil(20 * ca / h);
            peMovy = (int) Math.ceil(20 * co / h);
        }

        for (int i = 0; i < link.size(); i++) { // Colision de la pelota con la paleta
            meth = (Meth) (link.get(i));
            if (pelota.intersecta(meth)) {
                if (meth.arr().intersects(pelota.getPerimetro())
                        || meth.aba().intersects(pelota.getPerimetro())) {
                    peMovy *= -1;
                } else {
                    peMovx *= -1;
                }
                score++;
                link.remove(i);
            }
        }
        
        if (score == 32) { // Acabo el juego
            gOver = true;
        }

    }

    /**
     * Metodo que actualiza las animaciones.
     * @param g es la imagen del objeto
     */
    public void paint(Graphics g) {
        // Inicializa el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }

    /**
     * Se encarga de pintar los elementos graficos del juego.
     * Despliega un mensaje cuando el juego termina.
     * Despliega un icono de pausa cuando la bandera de pausa
     * se activa.
     * Pinta el valor del score.
     * @param g grafico
     */
    public void paint1(Graphics g) {

        g.drawImage(background, 0, 0, this);
        g.drawImage(backScore, 0, 0, this);
        g.setFont(myFont); // Aplica el estilo fuente a las string
        g.setColor(Color.white);
        g.drawString("" + score, 25, 694);

        if (paleta.getAnimacion() != null) {
            g.drawImage(paleta.animacion.getImagen(), paleta.getPosX(), paleta.getPosY(), this);
        }
        if (pelota.getAnimacion() != null) {
            g.drawImage(pelota.animacion.getImagen(), pelota.getPosX(), pelota.getPosY(), this);
        }

        for (int i = 0; i < link.size(); i++) {
            meth = (Meth) (link.get(i));
            g.drawImage(meth.animacion.getImagen(), meth.getPosX(), meth.getPosY(), this);
        }

        if (pausa) {
           g.drawImage(imgPause, 545, 200, this); 
        }
        
        if (gOver) {
            g.drawImage(imgOver, 0, 0, this);
        }
    }

    /**
     *
     * @param e
     */
    public void mouseReleased(MouseEvent e) {

    }

    /**
     *
     * @param e
     */
    public void mousePressed(MouseEvent e) {

    }

    /**
     *
     * @param e
     */
    public void mouseClicked(MouseEvent e) {

    }

    /**
     *
     * @param e
     */
    public void mouseEntered(MouseEvent e) {

    }

    /**
     *
     * @param e
     */
    public void mouseExited(MouseEvent e) {

    }

    /**
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        if (inicio) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                paMov = -30;
                tecla = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                paMov = 30;
                tecla = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_P) {
                pausa = !pausa;
                paMov = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            inicio = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            if(gOver) {
                reinicia();
            }
        }
    }

    /**
     *
     * @param e
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        paMov = 0;
        tecla = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BreakingBad meth = new BreakingBad();
        meth.setVisible(true);
    }

}
