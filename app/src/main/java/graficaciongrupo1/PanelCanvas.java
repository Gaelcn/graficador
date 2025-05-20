/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficaciongrupo1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import javax.swing.DefaultListModel;

/**
 *
 * @author libookami
 */
public class PanelCanvas implements ApplicationListener {
    
    SpriteBatch sptBatch;
    BitmapFont bmpFont;
    ShapeRenderer shpRenderer;
    
    //objetos vista 3D
    Environment env;
    ModelBatch modelBatch;
    public PerspectiveCamera cam;
    public CameraInputController camController;
    ModelBuilder modelBuilder;
    
    VentanaPrincipal padre;

    DefaultListModel<Figura> listaFiguras;
    DefaultListModel<Figura3D> listaFiguras3D;
    
    public PanelCanvas(VentanaPrincipal padre) {
        this.padre = padre;
        listaFiguras = new DefaultListModel<>();
        listaFiguras3D = new DefaultListModel<>();
    }
    
    Model m1;
    ModelInstance m1Inst;
    
    @Override
    public void create() {
        System.out.println("create()");
        sptBatch = new SpriteBatch();
        bmpFont = new BitmapFont();
        shpRenderer = new ShapeRenderer();
        
        //inicializacion entorno
        env = new Environment();
        env.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f,1f));
        env.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
        //luces y propiedaades del ambiente
        DefaultShader.Config shader_condifg = new DefaultShader.Config();
        shader_condifg.numDirectionalLights = 1;
        shader_condifg.numPointLights = 1;
        shader_condifg.numBones = 16;
        
        modelBatch = new ModelBatch(new DefaultShaderProvider(shader_condifg));
        
        //inicializar camara
        cam = new PerspectiveCamera(67,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
        
        //inicializar model builder
        modelBuilder = new ModelBuilder();
        
        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);
        
        m1 = modelBuilder.createBox(5, 2, 4, 
                new Material(ColorAttribute.createDiffuse(Color.GOLD)), 
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        m1Inst = new ModelInstance(m1);
    }

    @Override
    public void resize(int i, int i1) {
    }
    
    public void render2D() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shpRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        for(int j = 0; j < listaFiguras.getSize(); j++){
            listaFiguras.get(j).Dibujar(shpRenderer);
        }
        shpRenderer.end();
       
        shpRenderer.begin(ShapeRenderer.ShapeType.Line);
        shpRenderer.setColor(Color.WHITE);
        for(int i = 0; i*Figura.escala < Gdx.graphics.getHeight(); i++){
            shpRenderer.line(i*Figura.escala,0,i*Figura.escala,Gdx.graphics.getHeight());
            shpRenderer.line(0,i*Figura.escala,Gdx.graphics.getWidth(),i*Figura.escala);
        }
        shpRenderer.end();
    }
    
    public void render3D() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        if(padre.BtnCamArriba.getModel().isPressed()){
            cam.position.y -= 0.1;
        } else if(padre.BtnCamAbajo.getModel().isPressed()){
            cam.position.y += 0.1;
        } else if(padre.BtnCamIzquierda.getModel().isPressed()){
            cam.position.x += 0.1;
        } else if(padre.BtnCamDerecha.getModel().isPressed()){
            cam.position.x -= 0.1;
        } else if(padre.BtnCamFrente.getModel().isPressed()){
            cam.position.z -= 0.1;
        } else if(padre.BtnCamAtras.getModel().isPressed()){
            cam.position.z += 0.1;
        }
        
        modelBatch.begin(cam);
        for(int j = 0; j < listaFiguras3D.size(); j++){
            Figura3D f3D = listaFiguras3D.get(j);
            if(!f3D.isInicializado())
                f3D.inicializar(modelBuilder);
            
            f3D.Dibujar(modelBatch, env);
        }
        modelBatch.end();
        
        cam.update();
        camController.update();
    }
    
    @Override
    public void render() {
       if(padre.radio2D.isSelected())
        render2D();
       else 
        render3D();
    }

    @Override
    public void pause() {
        System.out.println("pause()");
        System.out.println("prueba()");
    }

    @Override
    public void resume() {
        System.out.println("resume()");
    }

    @Override
    public void dispose() {
        System.out.println("dispose()");
        sptBatch.dispose();
        bmpFont.dispose();
        shpRenderer.dispose();
    }
    
}
