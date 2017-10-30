/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontologia;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alexander Batista
 */
public class Validaciones {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validarCedula(String cedula) {
        int suma = 0;
        final char[] peso = {'1', '2', '1', '2', '1', '2', '1', '2', '1', '2'};
        // Comprobaciones iniciales
        if ((cedula == null) || (cedula.length() != 11)) {
            return false;
        }
        // Si no es un nº => la descartamos
        try {
            Long.parseLong(cedula);
        } catch (Exception e) {
            return false;
        }
        for (int i = 0; i < 10; i++) {
            int a = Character.getNumericValue(cedula.charAt(i));
            int b = Character.getNumericValue(peso[i]);
            char[] mult = Integer.toString(a * b).toCharArray();
            if (mult.length > 1) {
                a = Character.getNumericValue(mult[0]);
                b = Character.getNumericValue(mult[1]);
            } else {
                a = 0;
                b = Character.getNumericValue(mult[0]);
            }
            suma = suma + a + b;
        }
        int digito = (10 - (suma % 10)) % 10;
        // Comprobamos que el dígito de control coincide
        return digito == Character.getNumericValue(cedula.charAt(10));
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
