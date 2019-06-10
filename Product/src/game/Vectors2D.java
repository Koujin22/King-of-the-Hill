        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author world
 */
public class Vectors2D {
    
    //metodos para el manejo de vectores para el movimiento inteligente de los enemigos.
    
    public double[] subs(double x, double y, double a, double b){
        
        double x1 = x-a;
        double y1 = y-b;
        return new double[]{x1,y1};
        
    }
    
    public double[] normalize(double x, double y){
        
        double c = Math.sqrt(((Math.pow(x, 2))+(Math.pow(y,2))));
        
        double x1 = x/c;
        double y1 = y/c;
        return new double[]{x1,y1};
        
    }
    
    public double[] sum(double x, double y, double a, double b){
        
        double x1 = x+a;
        double y1 = y+b;
        return new double[]{x1,y1};
        
    }    
    
    public double[] mult(double x, double y, double a){
        
        return new double[]{(x*a),(y*a)};
    
    }
        
}
