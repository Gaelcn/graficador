/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficaciongrupo1;

/**
 *
 * @author contr
 */
public class Matriz33 {
    public float[][] datos;

    public Matriz33() {
        datos = new float[3][3];
    }
    
    public Matriz33(float n00,
                    float n01,
                    float n02,
                    float n10,
                    float n11,
                    float n12,
                    float n20,
                    float n21,
                    float n22
    ) {
        datos = new float[3][3];
        datos[0][0] = n00;
        datos[0][1] = n01;
        datos[0][2] = n02;
        datos[1][0] = n10;
        datos[1][1] = n11;
        datos[1][2] = n12;
        datos[2][0] = n20;
        datos[2][1] = n21;
        datos[2][2] = n22;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s+= datos[j][i] + ", ";
            }
            s += "\n";
        }
        
        return s;
    }
}
