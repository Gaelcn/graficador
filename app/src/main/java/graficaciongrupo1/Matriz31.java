/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficaciongrupo1;

/**
 *
 * @author contr
 */
public class Matriz31 {
    public float[] datos;

    public Matriz31() {
        datos = new float[3];
    }
    
    public Matriz31(float n00,
                    float n01,
                    float n02
    ) {
        datos = new float[3];
        datos[0] = n00;
        datos[1] = n01;
        datos[2] = n02;
    }

    @Override
    public String toString() {
        String s = "";
        for (int j = 0; j < 3; j++) {
            s+= datos[j] + ", ";
        }
        
        return s;
    }  
    
}
