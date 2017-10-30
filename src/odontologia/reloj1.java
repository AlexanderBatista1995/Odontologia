/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontologia;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Fausino Ayala
 */
public class reloj1 extends Thread {

    private final JLabel ldb;

    public reloj1(JLabel lbl) {
        this.ldb = lbl;
    }

    @Override
    public void run() {
        while (true) {
            Date hoy = new Date();
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
            ldb.setText(s.format(hoy));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }
}
