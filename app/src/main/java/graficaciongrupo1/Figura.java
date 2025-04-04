/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficaciongrupo1;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import javax.swing.DefaultListModel;

/**
 *
 * @author contr
 */
public class Figura {
    
    public static int escala = 20; 
    
    private String nombre;
    private DefaultListModel<Punto> puntos;
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DefaultListModel<Punto> getPuntos() {
        if(puntos == null){
            puntos = new DefaultListModel<>();
        }
        return puntos;
    }

    public void setPuntos(DefaultListModel<Punto> puntos) {
        this.puntos = puntos;
    }

    public Figura(String nombre) {
        this.nombre = nombre;
    }
    
    public void Dibujar(ShapeRenderer shpRenderer){
        for(int i = 0; i < getPuntos().size(); i++){
            Punto p =getPuntos().get(i);
            p.Dibujar(shpRenderer);
        }
        
        if(getPuntos().size() > 1){
            shpRenderer.setColor(Color.RED);
            for(int i = 0; i < getPuntos().size()-1; i++){
                Punto p1 =getPuntos().get(i);
                Punto p2 =getPuntos().get(i+1);
                shpRenderer.rectLine(p1.getpX()*Figura.escala, p1.getpY()*Figura.escala, p2.getpX()*Figura.escala, p2.getpY()*Figura.escala,5);
            }
        } 
        
        if(getPuntos().size() > 2){
            Punto p1 =getPuntos().get(getPuntos().size()-1);
            Punto p2 =getPuntos().get(0);
            shpRenderer.rectLine(p1.getpX()*Figura.escala, p1.getpY()*Figura.escala, p2.getpX()*Figura.escala, p2.getpY()*Figura.escala,5);
        }
    }
    
    public void Tranformar(Matriz33 m_trans){
        for(int i = 0; i < getPuntos().size(); i++){
            Punto p =getPuntos().get(i);
           p.Tranformar(m_trans);
        }
    }
    
    public String serialiar(){
        String s = getNombre() + ",";
         for(int i = 0; i < getPuntos().size(); i++){
            Punto p =getPuntos().get(i);
            
            s += p.getpX() + ",";
            s += p.getpY() + ",";
        }
         
         return s;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
    
    
}
