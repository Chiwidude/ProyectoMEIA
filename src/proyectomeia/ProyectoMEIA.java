/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import proyectomeia.Clases.Singleton;

/**
 *
 * @author Pancho
 */
public class ProyectoMEIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Singleton datos = new Singleton();
        Inicio start = new Inicio(datos);
        start.setVisible(true);
    }
    
}
