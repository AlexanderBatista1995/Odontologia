package odontologia;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Mover extends JLabel implements MouseListener, MouseMotionListener {

    /**
     * Identificador de objeto
     */
    public  String cod="";
    private String key = "";
    /**
     * Posicion de imagen
     */
    private final Point posicion = new Point(0, 0);
    /**
     * Tamaño de imagen
     */
    private final Dimension d = new Dimension(36,36);
    /**
     * variable que sirve para calcular el movimiento del objeto
     */
    private Point start_loc;
    /**
     * variable que sirve para calcular el movimiento del objeto
     */
    private Point start_drag;
    /**
     * variable que sirve para calcular el movimiento del objeto
     */
    private Point offset;
    /**
     * variables auxiliares para el desplazamiento del objeto
     */

    /**
     * Constructor de clase
     * @param url
     * @param num
     */
    public Mover(String url, int num) {
        //se inician propiedades de objeto
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setSize(d);
        this.setPreferredSize(d);
        this.setIcon(new ImageIcon(getClass().getResource(url)));
        this.setText("");
        this.setVisible(true);
        this.setLocation(posicion);
        url = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf(".")).concat("Diente")+num;
        //System.out.println(url);
        this.setName(url);

    }
    public String codigo (){
        return getCod();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * metodo para obtener la posicion del frame en la pantalla
     *
     * @param MouseEvent evt
     */
    private Point getScreenLocation(MouseEvent evt) {
        Point cursor = evt.getPoint();
        Point target_location = this.getLocationOnScreen();
        return new Point((int) (target_location.getX() + cursor.getX()),
                (int) (target_location.getY() + cursor.getY()));
    }

    /**
     * @return the cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

}//-> fin clase
