/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.stages;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pedro.ieslaencanta.com.dawairtemplate.model.Coordenada;
import pedro.ieslaencanta.com.dawairtemplate.model.Level;
import pedro.ieslaencanta.com.dawairtemplate.model.Player;
import pedro.ieslaencanta.com.dawairtemplate.model.Size;

/**
 *
 * @author Pedro
 */
public class GameStage extends AbstractScene {

    //private Iterator it= this.niveles.iterator();
    private Level actual;
    private Size s;
    private static Font font;//, Font.ITALIC, 50);
    private Player player;
    private ArrayList<Level> niveles;
    private Level level1;
    private Level level2;
    private Level level3;

    private static float[][] probabilidades = {
        {0.2f, 1f},
        {0.6f, 1f},
        {0.5f, 1f}
    };

    static {
        font = new Font("8BIT WONDER Nominal", 30);//, Font.ITALIC, 50);
    }

    //0 delante, 1 detras,2 arriba, 3 abajo, 4 disparo
    //private boolean[] keys_presed;
    public GameStage(GraphicsContext context, GraphicsContext bg_context, Size s) {
        super(context, bg_context, s, null);
        this.s = s;
        this.niveles = new ArrayList<>();
        this.state = SceneState.PRE_STARTED;
        this.level1 = new Level("/level1/fondo.png", "/level1/music.mp3", s, 2, new Coordenada(0, 0), bg_context, GameStage.probabilidades[0], 1500);
        this.level2 = new Level("/level2/fondo.png", "/level1/music.mp3", s, 2, new Coordenada(0, 0), bg_context, GameStage.probabilidades[0], 2000);
        this.level3 = new Level("/level3/fondo.png", "/level1/music.mp3", s, 2, new Coordenada(0, 0), bg_context, GameStage.probabilidades[0], 2500);
        this.niveles.add(level1);
        this.niveles.add(level2);
        this.niveles.add(level3);
        this.actual = niveles.get(0);

        this.initFactory();
        this.player = new Player();

    }

    //se inicializan la factoria de enemigos
    private void initFactory() {

    }

    @Override
    public void TicTac() {
        if (this.state == SceneState.STARTED) {
            this.actual.TicTac();
            //para pasar de nivel
            if (this.actual == niveles.get(0) && this.actual.getEstado() == Level.Estado.END) {
                this.actual = niveles.get(1);

            } else if (this.actual == niveles.get(1) && this.actual.getEstado() == Level.Estado.END) {
                this.actual = niveles.get(2);

                //no quedan niveles  
                if (this.actual.getEstado() == Level.Estado.END) {
                    System.out.println("FIN DEL JUEGO");
                    this.stop();
                   
                }
            }
            //Si no quedan vidas avaba el juego
            if(this.actual.getVidas()==0){
                System.out.println("GAME OVER");
                this.stop();
            }
        }
        
    }

    @Override
    public void onKeyPressed(KeyCode code) {
        if (this.state == SceneState.STARTED) {
            this.actual.onKeyPressed(code);
        }
    }

    @Override
    public void onKeyReleased(KeyCode code) {
        if (GameStage.SceneState.PRE_STARTED == this.state) {
            this.state = GameStage.SceneState.STARTED;
        }

        if (this.state == SceneState.STARTED) {
            this.actual.onKeyReleased(code);
        }

    }

    @Override
    public void draw(GraphicsContext gc) {
        this.actual.draw(gc);
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BROWN);
        gc.setFont(GameStage.font);
        gc.strokeText("SCORE " + this.player.getScore(), this.s.getWidth() / 2 - 100, 40);

    }

}
