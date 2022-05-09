package pedro.ieslaencanta.com.dawairtemplate.model;


import java.util.ArrayList;
import javafx.scene.image.Image;
import pedro.ieslaencanta.com.dawairtemplate.IWarnClock;

import pedro.ieslaencanta.com.dawairtemplate.model.sprites.ICollision;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.SpriteMove;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author freit
 */
public abstract class ABala extends SpriteMove implements IWarnClock, ICollision {
   protected Image img;
    //path para la imagen
   protected  String pathurl="enemigos/e1.png";
    //para la animación
   protected int original_height;
  
   
    public ABala(){
        super();
        this.img = new Image(getClass().getResourceAsStream("/" + this.pathurl));
        this.setLive(true);
    }
   
    public void init(Coordenada c, Rectangle board){
        super.init(10, new Size(20, 20), c, true, true, board);
      
    }
    //metodo para pasar las balas al level
    public abstract void setBala(ArrayList e);
   public int setInc(int a){
       this.inc=a;
       return this.inc;
   }
}