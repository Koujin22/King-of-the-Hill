    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Component;

/**
 *
 * @author world
 */
public class CharacterIndex {
    // Se encarga de tener todos los componentes o personajes para poder 
    // ordenarlos en profundiad con JLayeredPane
    private Component c;
    private String name; 
    public CharacterIndex(Component c, String name) {
        this.c = c;
        this.name = name;
    }
    
    // Evitar conseguir referencias de enemigos muertos
    public static String valueOf(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }
    
    public  String toString(){
        return "Component: "+ name;
    }

    public Component getC() {
        return c;
    }

    

    public String getName() {
        return name;
    }
    
    
    
}
