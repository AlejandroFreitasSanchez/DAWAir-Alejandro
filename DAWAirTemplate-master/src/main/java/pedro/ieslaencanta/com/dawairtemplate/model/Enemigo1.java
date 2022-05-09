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

/**
 *
 * @author freit
 */
public class Enemigo1 extends AEnemigo{
    private int contador=0;
   
    public Enemigo1(){
        super();
        this.pathurl="enemigos/e1.png";
        this.img = new Image(getClass().getResourceAsStream("/" + this.pathurl));
        
       
    }
   
    
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, 0, 0, 31, 14, this.getPosicion().getX(),this.getPosicion().getY(),this.getSize().getWidth(), this.getSize().getHeight());
             
             
    }

    @Override
    public void TicTac() {

       
        contador++;
        if(contador==75){
            this.disparar();
            contador=0;
        }
         this.getBulletsE().removeIf(c -> {
            
             return (c.getPosicion().getX()==10);});
        this.getBulletsE().removeIf(c -> {
          
             return (c.isLive()==false);});
       this.move(Direction.LEFT);
        
    }
      @Override
    public void disparar() {
         
         ABala tempo;
         tempo=FactoriaBalas.create("Bala1");
          tempo.init(new Coordenada(this.getX(), this.getY()+this.getHeight()/2),  new Rectangle(new Coordenada(0,0),new Coordenada(1000,480)));
          tempo.setSize(new Size(40,40));
                this.getBulletsE().add(tempo); 
                 
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

  
   


    
    
}
