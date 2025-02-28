/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graficaciongrupo1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    DefaultListModel<Figura> listaFiguras;

    public PanelCanvas() {
        listaFiguras = new DefaultListModel<>();
    }
    
    @Override
    public void create() {
        System.out.println("create()");
        sptBatch = new SpriteBatch();
        bmpFont = new BitmapFont();
        shpRenderer = new ShapeRenderer();
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void render() {
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
