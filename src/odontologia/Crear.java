package odontologia;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Crear {

    /**
     * COntador interno de objetos
     */
    private int contador_de_objetos = 0;
    private final int x;
    private final int y;
    /**
     * Los objetos se almacenaran en un MAP
     */
    private final Map map = new HashMap();
    /**
     * JPanel
     */
    private final JPanel contenedor;

    /**
     * Constructor de clase
     *
     * @param jpanel
     * @param JButton
     */
    public Crear(JPanel jpanel,JButton JButton) {
        this.contenedor = jpanel;
        this.x=JButton.getX();
        this.y=JButton.getY();
    }

    /**
     * Metodo que crea un nuevo objeto
     * @param url
     * @param num
     */
    public void Nuevo_Objeto(String url,int num) {
        //aumenta contador en +1        
        this.contador_de_objetos += 1;
        //Crea una nueva instancia de "MiObjeto"
        Mover tmp = new Mover(url, num);
        //coloca al objeto creado en una posicion aleatoria
        tmp.setLocation(x, y);
        //agrega el objeto en el MAP
        map.put("Objeto " + this.contador_de_objetos, tmp);
        
        //agrega el objeto en el JPanel
        this.contenedor.add(tmp);
        //actualiza graficos
        this.contenedor.repaint();
    }

}//->fin de clase
