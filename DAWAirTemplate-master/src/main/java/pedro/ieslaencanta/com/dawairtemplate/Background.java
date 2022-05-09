/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pedro.ieslaencanta.com.dawairtemplate.model.Coordenada;
import pedro.ieslaencanta.com.dawairtemplate.model.Size;


/**
 *
 * @author Pedro
 */
public class Background implements IWarnClock {

    private String path;
    private int speed;
    private Image background;
    private Size size;
    private Coordenada c;
    private Coordenada c2;
    private Font font;//, Font.ITALIC, 50);
    private GraphicsContext bg;

    public Background(String path_img, Size s, int speed, Coordenada start_position) {
        this.size = s;
        this.speed = speed;
        this.background = new Image(getClass().getResourceAsStream(path_img));//"/level1/background.png"));
        font = new Font("8BIT WONDER Nominal", 30);//, Font.ITALIC, 50);
        this.c = start_position;//new Coordenada(0,0);
        this.c2 = new Coordenada((int) this.background.getWidth(), 0);//this.speed, 0);

    }

    public void paint(GraphicsContext g) {
        //las dos imágenes
        g.drawImage(this.background, 0, 0, this.size.getWidth() / 2, this.background.getHeight(),// this.c.getY(),
                this.c.getX(), 0,
                this.background.getWidth(), this.size.getHeight());
        g.drawImage(this.background, 0, 0, this.size.getWidth() / 2, this.background.getHeight(),// this.c.getY(),
                this.c2.getX(), 0,
                this.background.getWidth(), this.size.getHeight());

        g.setFill(Color.BLUE);
        g.setStroke(Color.BROWN);
        g.setFont(font);

      //  g.strokeText("SCORE "+this.c2.getX(), this.size.getWidth()/2-100, 40);
      

    }

    private void move() {
        this.c.setX(this.c.getX() - this.speed);
        this.c2.setX(this.c2.getX() - this.speed);
        if (this.c2.getX() <= -this.background.getWidth()) {
            this.c2.setX((int) this.background.getWidth());
        }
        if (this.c.getX() <= -this.background.getWidth()) {
            this.c.setX((int) this.background.getWidth());
        }

      }

    @Override
    public void TicTac() {
        this.move();
    }

    /**
     * @return the bg
     */
    public GraphicsContext getBg() {
        return bg;
    }

    /**
     * @param bg the bg to set
     */
    public void setBg(GraphicsContext bg) {
         this.bg = bg;
    }
}
