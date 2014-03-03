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

    /**
     * El metodo constructor de la clase BreakingBad
     */
    public BreakingBad() {

        addMouseListener(this);
        addKeyListener(this);
        Thread th = new Thread(this);
        th.start();
    }

        public void run() {
            while(true){
                
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
