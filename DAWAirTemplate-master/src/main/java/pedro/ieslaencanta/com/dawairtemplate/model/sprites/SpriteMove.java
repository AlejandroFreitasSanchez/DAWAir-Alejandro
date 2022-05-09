/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model.sprites;

import javafx.scene.canvas.GraphicsContext;
import pedro.ieslaencanta.com.dawairtemplate.model.Coordenada;
import pedro.ieslaencanta.com.dawairtemplate.model.Rectangle;
import pedro.ieslaencanta.com.dawairtemplate.model.Size;



/**
 *
 * @author Pedro
 */
/**
 *
 * @author Pedro
 */
public abstract class SpriteMove extends Sprite implements IMove {

    protected int inc;
    protected Rectangle board;
   
    public SpriteMove(){
        super();
    }
    protected void init(int inc,Size s,Coordenada p,boolean visible, boolean live, Rectangle board){
        super.init(s,p,visible,live);
        this.inc=inc;
        this.board=board;
    }
    public SpriteMove(int inc,Size s,Coordenada p,boolean visible, boolean live, Rectangle board ){
        super(s,p,visible,live);
        this.inc=inc;
        this.board=board;
}
   
    public void move( Direction d) {
        //se mueve hacia abajo
        if(d==Direction.DOWN && this.getPosicion().getY()+this.getSize().getHeight()+this.inc<board.getEnd().getY()){
            this.getPosicion().setY(this.getPosicion().getY()+this.inc);
        }
        //se mueve hacia arriba
        if(d==Direction.UP && this.getPosicion().getY()-this.inc>board.getStart().getY()){
            this.getPosicion().setY(this.getPosicion().getY()-this.inc);
        }
        //se mueve hacia izquierda
        if(d==Direction.LEFT && this.getPosicion().getX()>=this.inc){//board.getStart().getX()){
            this.getPosicion().setX(this.getPosicion().getX()-this.inc);
        }
        //se mueve hacia derecha
        if(d==Direction.RIGHT && this.getPosicion().getX()+this.getSize().getWidth()+this.inc<board.getEnd().getX()){
            this.getPosicion().setX(this.getPosicion().getX()+this.inc);
           
        }
    }
    public int getInc(){
        return this.inc;
    }

}
