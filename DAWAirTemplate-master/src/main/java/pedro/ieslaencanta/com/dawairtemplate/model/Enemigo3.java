package pedro.ieslaencanta.com.dawairtemplate.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.ICollision;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IMove;

/**
 *
 * @author freit
 */
public class Enemigo3 extends AEnemigo{
    private int contador=0;
    public Enemigo3(){
        super();
        this.pathurl="enemigos/e3.png";
        this.img = new Image(getClass().getResourceAsStream("/" + this.pathurl));
       
    }
   
    
  @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, 388, 0, 117, 27, this.getPosicion().getX(),this.getPosicion().getY(),this.getSize().getWidth(), this.getSize().getHeight());
       
    }

    @Override
    public void TicTac() {
        
       
        contador++;
        if(contador==30){
            this.disparar();
            contador=0;
        }
         this.getBulletsE().removeIf(c -> {
            
             return (c.getPosicion().getX()==10);});
        this.getBulletsE().removeIf(c -> {
          
             return (c.isLive()==false);});
       this.move(IMove.Direction.LEFT);
        
    }
  @Override
    public void disparar() {
         
          ABala tempo1;
          ABala tempo2;
          ABala tempo3;
           
          tempo1=FactoriaBalas.create("Bala3");
          tempo1.init(new Coordenada(this.getX(), this.getY()+this.getHeight()/2),  new Rectangle(new Coordenada(0,0),new Coordenada(1000,480)));
          tempo1.setInc(15);
          tempo1.setSize(new Size(62,14));
          this.getBulletsE().add(tempo1); 
          
          tempo2=FactoriaBalas.create("Bala3");
          tempo2.init(new Coordenada(this.getX(), this.getY()+this.getHeight()/2-30),  new Rectangle(new Coordenada(0,0),new Coordenada(1000,480)));
          tempo2.setInc(15);
          tempo2.setSize(new Size(62,14));
          this.getBulletsE().add(tempo2); 
          
          tempo3=FactoriaBalas.create("Bala3");
          tempo3.init(new Coordenada(this.getX(), this.getY()+this.getHeight()/2+30),  new Rectangle(new Coordenada(0,0),new Coordenada(1000,480)));
          tempo3.setInc(15);
          tempo3.setSize(new Size(62,14));
          this.getBulletsE().add(tempo3); 
                 
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