/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficaciongrupo1;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 *
 * @author contr
 */
public class Figura3D {
    enum TiposFigura3D { NINGUNO,CUBO, ESFERA, CONO, CILINDRO }
    
    private String Nombre;
    private float sx;
    private float sy;
    private float sz;
    private TiposFigura3D tipo = TiposFigura3D.NINGUNO;
    private Color Color;
    
    Model m1;
    ModelInstance m1instance;

    public Figura3D(String Nombre, float sx, float sy, float sz, Color Color, TiposFigura3D tipo) {
        this.Nombre = Nombre;
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;
        this.Color = Color;
        this.tipo = tipo;
    }
    
    public boolean isInicializado(){
        return m1instance != null;
    }

    public void inicializar(ModelBuilder builder){
        switch (tipo){
            case CUBO:
                m1 = builder.createBox(sx, sy, sz,  new Material(ColorAttribute.createDiffuse(this.Color)), 
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                break;
            case ESFERA:
                m1 = builder.createSphere(sx, sx, sx, 16,16,
                   new Material(ColorAttribute.createDiffuse(this.Color)),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                break;
            case CILINDRO:
                m1 = builder.createCylinder(sx, sx, sx, 16,
                   new Material(ColorAttribute.createDiffuse(this.Color)),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                break;
            case CONO:
                 m1 = builder.createCone(sx, sx, sx, 16,
                   new Material(ColorAttribute.createDiffuse(this.Color)),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                break;
            default:
                
        }
        
        m1instance = new ModelInstance(m1);
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public float getSx() {
        return sx;
    }

    public void setSx(float sx) {
        this.sx = sx;
    }

    public float getSy() {
        return sy;
    }

    public void setSy(float sy) {
        this.sy = sy;
    }

    public float getSz() {
        return sz;
    }

    public void setSz(float sz) {
        this.sz = sz;
    }

    public TiposFigura3D getTipo() {
        return tipo;
    }

    public void setTipo(TiposFigura3D tipo) {
        this.tipo = tipo;
    }

    public Color getColor() {
        return Color;
    }

    public void setColor(Color Color) {
        this.Color = Color;
    }  
    
    public void Dibujar(ModelBatch modelBatch, Environment env){
        modelBatch.render(m1instance, env);
    }

    @Override
    public String toString() {
        return getNombre(); 
    }
    
}
