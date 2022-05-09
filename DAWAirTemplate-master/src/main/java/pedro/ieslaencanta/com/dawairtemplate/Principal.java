/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import pedro.ieslaencanta.com.dawairtemplate.model.Size;



/**
 *
 * @author Pedro
 */
public class Principal extends Application {

    private Game game;
    Scene scene;
    private Canvas canvas;
    private Canvas bg_canvas;
    private int width = 1000;
    private int height = 480;//410;

    @Override
    public void start(Stage stage) throws Exception {
        this.canvas = new Canvas(this.width, this.height);
        this.bg_canvas= new Canvas(this.width,this.height);
        StackPane panel = new StackPane(this.bg_canvas,this.canvas);
        this.scene = new Scene(panel, this.width, this.height);

        stage.setTitle("DAW Game");
        stage.setResizable(false);
        stage.setScene(scene);
        //para que cierre al pulsar el icono
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        this.initGame();
        this.registerKeys();
        stage.show();
    }

    private void registerKeys() {
        scene.setOnKeyReleased(e -> {
            this.game.onKeyReleased(
        
                    e.getCode());
        });
        scene.setOnKeyPressed(e -> {
            this.game.onKeyPressed(e.getCode());
           
        });

    }

    private void initGame() {
        try {
            //se crea el texto
            String path=getClass().getResource("/WONDER.TTF").toURI().toString();
            System.out.println(path);
            Font f= Font.loadFont(path, 10);
        
            this.game= new Game(this.canvas.getGraphicsContext2D(),this.bg_canvas.getGraphicsContext2D(), new Size(this.width,this.height));
            game.start();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
