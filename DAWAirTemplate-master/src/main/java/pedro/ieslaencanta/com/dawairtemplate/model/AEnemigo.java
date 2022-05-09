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
public abstract class AEnemigo extends SpriteMove implements IWarnClock, ICollision {
   protected Image img;
    //path para la imagen
   protected  String pathurl="enemigos/e1.png";
    //para la animación
   protected int original_height;
   private ArrayList<ABala> bulletsE;
  
    public AEnemigo(){
        super();
        this.img = new Image(getClass().getResourceAsStream("/" + this.pathurl));
        this.bulletsE=new ArrayList<ABala>();
    }
   
    public void init(Coordenada c, Rectangle board){
        super.init( 3,new Size(74, 26), c, true, true, board);
     
    }
   
    public abstract void disparar();
   

    /**
     * @return the bulletsE
     */
    public ArrayList<ABala> getBulletsE() {
        return bulletsE;
    }

    /**
     * @param bulletsE the bulletsE to set
     */
    public void setBulletsE(ArrayList<ABala> bulletsE) {
        this.bulletsE = bulletsE;
    }
    /**
     * @return the tipoBala
     */
  
   
}
