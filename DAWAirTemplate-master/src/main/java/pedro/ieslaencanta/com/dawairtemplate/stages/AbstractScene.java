/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.stages;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import pedro.ieslaencanta.com.dawairtemplate.model.Size;


/**
 *
 * @author Pedro
 */
public abstract class AbstractScene implements IScene{
    protected IScene.SceneState state;
    protected GraphicsContext ctx;
    protected GraphicsContext ctxbg;
    protected MediaPlayer player;
    protected String soundpath;
    protected Size s;
    public AbstractScene(GraphicsContext context, GraphicsContext bg_context, Size s,String soundpath){
        this.ctx=context;
        this.ctxbg=bg_context;
        this.s=s;
        this.soundpath=soundpath;
        this.player=null;
    }
    @Override
    public SceneState getState() {
        return this.state;
    }

    @Override
    public void start() {
        this.state=SceneState.STARTED;
        if(this.soundpath!=null)
            this.initSound(soundpath);
        
    }

    @Override
    public void stop() {
        this.state=SceneState.END;
        if(this.soundpath!=null && this.player!=null )
            this.player.stop();
    }

    @Override
    public void pause() {
        this.state=SceneState.PAUSED;
   }

    @Override
    public void reset() {
        this.state=SceneState.PRE_STARTED;
    }
     protected void initSound(String music_path) {
         if(this.player==null){
            this.player = new MediaPlayer(new Media(getClass().getResource(music_path).toString()));
         }
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player.seek(Duration.ZERO);
            }
        });
        player.play();
    }

}
