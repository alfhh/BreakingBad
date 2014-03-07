/**
 * @author Alfredo Hinojosa Huerta A01036053
 * @author Luis Juan Sanchez A01183634
 * @version 1.0 3/3/2014
 */
package breakingbad;

import java.awt.Color;
import javax.swing.JFrame;
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

public class BreakingBad extends JFrame implements Runnable, MouseListener, KeyListener {

    private Animacion animPaleta; // Animacion de la Paleta (Jugador)
    private Paleta paleta; // Objeto de la Paleta
    private Animacion animPelota; // Animacion de la Pelota
    private Pelota pelota; // Objeto de Pelota
    private int peMovy; // Movimiento de la Pelota en el eje y
    private int peMovx; // Movimiento de la Pelota en el eje x
    private int paMov; // Movimiento de la Paleta
    private Image dbImage; // Imagen
    private Graphics dbg; // Objeto Grafico
    private boolean pausa; // Flag de pausa
    private boolean inicio; // Flag de inicio

    /**
     * El metodo constructor de la clase BreakingBad
     */
    public BreakingBad() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setTitle("Breaking Bad: The Game");
        pausa = false;
        inicio = false;
        peMovx = paMov = 0;
        peMovy = 5;

        // Animacion de la Paleta
        Image b0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fedora.png"));
        animPaleta = new Animacion();
        animPaleta.sumaCuadro(b0, 100);

        // Animacion de la Pelota
        b0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pelota.png"));
        animPelota = new Animacion();
        animPelota.sumaCuadro(b0, 100);

        paleta = new Paleta(0, 0, animPaleta);
        paleta.setPosX(this.getWidth() / 2 - paleta.getAncho() / 2);
        paleta.setPosY(this.getHeight() - paleta.getAlto() - 10);

        pelota = new Pelota(0, 0, animPelota);
        pelota.setPosX(paleta.getPosX() + paleta.getAncho() / 2 - pelota.getAncho() / 2);
        pelota.setPosY(paleta.getPosY() - pelota.getAlto());

        addMouseListener(this);
        addKeyListener(this);
        Thread th = new Thread(this);
        th.start();
    }

    public void run() {
        while (true) {
            if (!pausa && inicio) {
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

    public void actualiza() {
        if (paleta.getPosX() + paMov > 0
                && paleta.getPosX() + paMov + paleta.getAncho() < this.getWidth()) {
            paleta.setPosX(paleta.getPosX() + paMov);
            paMov = 0;
        }
        pelota.setPosX(pelota.getPosX()+peMovx);
        pelota.setPosY(pelota.getPosY()+peMovy);
    }

    public void checaColision() {
        if (pelota.getPosX() < 0 || pelota.getPosX() + pelota.getAncho() > this.getWidth()) {
            peMovx = -peMovx;
        }
        if (pelota.getPosY() < 0) {
            peMovy = -peMovy;
        }
        if (pelota.getPosY() + pelota.getAlto() > this.getHeight()) {

        }
        if (paleta.getPerimetro().intersects(pelota.getPerimetro())) {
            int ca = (pelota.getPosX() + pelota.getAncho() / 2)
                    - (paleta.getPosX() + paleta.getAncho() / 2);
            int co = (pelota.getPosY())
                    - (paleta.getPosY());
            int h = (int) Math.sqrt(Math.pow(ca, 2) + Math.pow(co, 2));
            peMovx = 20 * ca / h;
            peMovy = 20 * co / h;
        }
    }

    /**
     * Metodo que actualiza las animaciones.
     *
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

    public void paint1(Graphics g) {

        if (paleta.getAnimacion() != null) {
            g.drawImage(paleta.animacion.getImagen(), paleta.getPosX(), paleta.getPosY(), this);
        }
        if (pelota.getAnimacion() != null) {
            g.drawImage(pelota.animacion.getImagen(), pelota.getPosX(), pelota.getPosY(), this);
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
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                paMov = 30;
            }

            if (e.getKeyCode() == KeyEvent.VK_P) {
                pausa = !pausa;
                paMov = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            inicio = true;
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

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BreakingBad meth = new BreakingBad();
        meth.setVisible(true);
    }

}
