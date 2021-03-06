/**
 * @author Luis Juan Sanchez A01183634
 * @author Alfredo Hinojosa Huerta A01036053
 * @version 1.00 02/24/2014
 */
package breakingbad;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Base {

    private int posX;    //posicion en x.       
    private int posY;	//posicion en y.
    private ImageIcon icono;    //icono.
    Animacion animacion;

    /**
     * Metodo constructor usado para crear el objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param animacion es la <code>animacion</code> del objeto.
     */
    public Base(int posX, int posY, Animacion animacion) {
        this.posX = posX;
        this.posY = posY;
        this.animacion = animacion;
    }

    /**
     * Metodo modificador usado para cambiar la posicion en x del objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Metodo de acceso que regresa la posicion en x del objeto
     *
     * @return posX es la <code>posicion en x</code> del objeto.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Metodo modificador usado para cambiar la posicion en y del objeto
     *
     * @param posY es la <code>posicion en y</code> del objeto.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Metodo de acceso que regresa la posicion en y del objeto
     *
     * @return posY es la <code>posicion en y</code> del objeto.
     */
    public int getPosY() {
        return posY;
    }

    /**
     *Metodo setAnimacion
     * 
     * Metodo que cambia la animacion
     * 
     * @param anim que es de la clase <code>Animacion</code> que es la animacion a la cual se desea cambiar
     */
    public void setAnimacion(Animacion anim) {
        this.animacion = anim;
    }

    /**
     * Metodo getAnimacion
     * Metodo que regresa la animacion
     * 
     * @return animacion que es de la clase <code>Animacion</code> que es la animacion del objeto
     */
    public Animacion getAnimacion() {
        return this.animacion;
    }

    /**
     * Metodo de acceso que regresa el ancho del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del
     * icono.
     */
    public int getAncho() {
        return new ImageIcon(this.animacion.getImagen()).getIconWidth();
    }

    /**
     * Metodo de acceso que regresa el alto del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el alto del
     * icono.
     */
    public int getAlto() {
        return new ImageIcon(this.animacion.getImagen()).getIconHeight();
    }

    /**
     * Metodo de acceso que regresa un nuevo rectangulo
     *
     * @return un objeto de la clase <code>Rectangle</code> que es el perimetro
     * del rectangulo
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getPosX(), getPosY(), getAncho(), getAlto());
    }

    /**
     * Checa si el objeto <code>Movement</code> intersecta a otro
     * <code>Movement</code>
     *
     * @return un valor boleano <code>true</code> si lo intersecta
     * <code>false</code> en caso contrario
     */
    public boolean intersecta(Base obj) {
        return getPerimetro().intersects(obj.getPerimetro());
    }

}
