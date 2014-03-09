/**
 * @author Luis Juan Sanchez A01183634
 * @author Alfredo Hinojosa Huerta A01036053
 * @version 1.00 03/03/2014
 */
package breakingbad;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Meth extends Base {

    public Meth(int posX, int posY, Animacion animacion) {
        super(posX, posY, animacion);
    }

    public Rectangle arr() {
        return (new Rectangle(this.getPosX() + 19, this.getPosY(),
                this.getAncho() - 19, 20));
    }

    public Rectangle aba() {
        return (new Rectangle(this.getPosX() + 19, this.getPosY() + this.getAlto() - 20,
                this.getAncho() - 19, 20));
    }

}
