/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.ICollision;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IMove;

/**
 *
 * @author freit
 */
public class Bala3 extends ABala {

    private int contador = 0;

    public Bala3() {
        super();
        this.pathurl = "bullets/misiles.png";
        this.img = new Image(getClass().getResourceAsStream("/" + this.pathurl));
      
        
    }

    public void draw(GraphicsContext gc) {

         gc.drawImage(img, 0, 3, 31, 8, this.getX(), this.getY(), this.getWidht(),this.getHeight() );

    }

    @Override
    public void TicTac() {
        this.move(IMove.Direction.LEFT);

    }

    

    @Override
    public int getX() {
        return this.getPosicion().getX();
    }

    @Override
    public int getY() {
        return this.getPosicion().getY();
    }

    @Override
    public int getHeight() {
        return this.getSize().getHeight();
    }

    @Override
    public int getWidht() {
        return this.getSize().getWidth();
    }

    @Override
    public boolean hascollided() {
        return true;
    }

    @Override
    public void setColision() {

    }

    @Override
    public void setFree() {

    }

    @Override
    public void setBala(ArrayList e) {
       e.add(this);
       this.setLive(false);
    }
   
}