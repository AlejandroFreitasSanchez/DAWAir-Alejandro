/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.NUMPAD1;
import static javafx.scene.input.KeyCode.NUMPAD2;
import javafx.scene.input.KeyEvent;
import pedro.ieslaencanta.com.dawairtemplate.IWarnClock;
import pedro.ieslaencanta.com.dawairtemplate.model.Coordenada;
import pedro.ieslaencanta.com.dawairtemplate.model.Rectangle;
import pedro.ieslaencanta.com.dawairtemplate.model.Size;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.ICollision;

import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IKeyListener;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IMove;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IMove;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.SpriteMove;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.SpriteMove;

/**
 *
 * @author freit
 */
enum ENUMBALA{
        T1,
        T2,
        T3,
        T4;
    }
public class Fighter extends SpriteMove implements IKeyListener, IWarnClock , ICollision{

   

    private boolean[] keys_presed;
    private Image img;
    //path para la imagen
    static String pathurl="avion.png";
    //para la animación
    private int original_height;
    private ArrayList<ABala> bullets;
    private ENUMBALA BalaSeleccionada;
   private Level.Estado estado;
   
    
    /**
     * 
     * @param inc incremento del movimiento
     * @param s tamaño del avión
     * @param p coordenadas iniciales
     * @param board rectangulo con las dimensiones del juego para no salirse
     */
    public Fighter(int inc, Size s, Coordenada p, Rectangle board) {
        super(inc, s, p, true, true, board);
        this.keys_presed = new boolean[10];
        this.img = new Image(getClass().getResourceAsStream("/" + Fighter.pathurl));
        //cambia al mover arriba y abajo
        this.original_height=s.getHeight();
        this.bullets=new ArrayList<ABala>();
        //Para poder elegir el tipo de bala
        this.BalaSeleccionada=ENUMBALA.T1;
        
        
        this.estado = Level.Estado.PRE_STARTED;
    }
    /**
     * acciones al pulsar las teclas
     * @param code 
     */
    @Override
    public void onKeyPressed(KeyCode code) {

       if (code == KeyCode.RIGHT) {
            this.keys_presed[0] = true;
            
        }
        if (code == KeyCode.LEFT) {
            this.keys_presed[1] = true;
        }
        if (code == KeyCode.UP) {
            this.keys_presed[2] = true;
            this.getSize().setHeight(40);
        }
        if (code == KeyCode.DOWN) {
            this.keys_presed[3] = true;
            this.getSize().setHeight(40);
        }
        //Nuevas teclas asignadas
        if (code == KeyCode.DIGIT1) {
            this.keys_presed[4] = true;
           this.setBalaSeleccionada(BalaSeleccionada.T1);
            
        }
        if (code == KeyCode.DIGIT2) {
            this.keys_presed[5] = true;
            this.setBalaSeleccionada(BalaSeleccionada.T2);
        }
        if (code == KeyCode.DIGIT3) {
            this.keys_presed[6] = true;
            this.setBalaSeleccionada(BalaSeleccionada.T3);
        }
       
      
        

    }
    /**
     * acciones al soltar el teclado
     * @param code 
     */
    @Override
    public void onKeyReleased(KeyCode code) {
        
        if (code == KeyCode.SPACE) {
           this.crearBala();
       
        }
        if (code == KeyCode.RIGHT) {
            this.keys_presed[0] = false;
        }
        if (code == KeyCode.LEFT) {
            this.keys_presed[1] = false;
        }
        if (code == KeyCode.UP) {
            this.keys_presed[2] = false;
            this.getSize().setHeight(original_height);
        }
        if (code == KeyCode.DOWN) {
            this.keys_presed[3] = false;
            this.getSize().setHeight(original_height);
        }
        //Nuevas teclas asignadas para las balas
        if (code == KeyCode.DIGIT1) {
            this.keys_presed[4] = false;
            
        }
        if (code == KeyCode.DIGIT2) {
            this.keys_presed[5] = false;
           
        }
        if (code == KeyCode.DIGIT3) {
            this.keys_presed[6] = false;
            
        }
      
    }
    /**
     * dibujar, es algo más complejo al moverse las alas
     * @param gc 
     */
    @Override
    public void draw(GraphicsContext gc) {
        if (keys_presed[2]) {
            gc.drawImage(getImg(), 163, 7, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                    this.getPosicion().getX(), this.getPosicion().getY(),
                    this.getSize().getWidth(), this.getSize().getHeight());
        } else {
            if (keys_presed[3]) {
                gc.drawImage(getImg(), 54, 7, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                        this.getPosicion().getX(), this.getPosicion().getY(),
                        this.getSize().getWidth(), this.getSize().getHeight());
            } else {
                gc.drawImage(getImg(), 105, 8, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                        this.getPosicion().getX(), this.getPosicion().getY(),
                        this.getSize().getWidth(), this.getSize().getHeight());
            }
            
        }
        for (ABala bala : this.getBullets()){
            if(bala.isLive()==true){
               bala.draw(gc); 
            }
                
            }
      
    }
    //movimiento del avión
    private void move() {

        if (this.keys_presed[0]) {
            this.move(IMove.Direction.RIGHT);
           
        }
        if (this.keys_presed[1]) {
            this.move(IMove.Direction.LEFT);
        }
        if (this.keys_presed[2]) {
            this.move(IMove.Direction.UP);
        }
        if (this.keys_presed[3]) {
            this.move(IMove.Direction.DOWN);
        }
        
        this.getBullets().forEach(b->b.move(Direction.RIGHT ));
        
        this.getBullets().removeIf(c -> {
             //System.out.println(c.getX());
             return ((c.getX()+c.getWidht())>950);});
        this.getBullets().removeIf(c -> {
             //System.out.println(c.getX());
             return (c.isLive()==false);});
    }
    /** 
     * cada vez que se recibe un tictac se mueve, faltan las balas del fighter
     */
    @Override
    public void TicTac() {
        this.move();
        
        
    }  
        public void crearBala(){
            //Se crean usando la factoria y teniendo la bala seleccionada con las teclas del 1 al 3
            ABala tempo;
            if(this.estado==estado.RUNNING){
            if(this.BalaSeleccionada==BalaSeleccionada.T2){
                 tempo=FactoriaBalas.create("Bala1");
                 tempo.init(new Coordenada(this.getPosicion().getX()+this.getSize().getWidth(), this.getPosicion().getY()),board);
                 tempo.setInc(5);
                 tempo.setSize(new Size(40,40));
                 this.getBullets().add(tempo);
            }
            if(this.BalaSeleccionada==BalaSeleccionada.T1){
            tempo=FactoriaBalas.create("Bala2");
            tempo.init(new Coordenada(this.getPosicion().getX()+this.getSize().getWidth(), this.getPosicion().getY()),board);
            tempo.setInc(15);
            this.getBullets().add(tempo);
            }
            if(this.BalaSeleccionada==BalaSeleccionada.T3){
            tempo=FactoriaBalas.create("Bala3");
            tempo.init(new Coordenada(this.getPosicion().getX()+this.getSize().getWidth(), this.getPosicion().getY()),board);
            tempo.setInc(10);
            tempo.setSize(new Size(62,14));
            this.getBullets().add(tempo);
            }
            }
            
            //System.out.println("Bala Creada");
        }

    /**
     * @return the bullets
     */
    public ArrayList<ABala> getBullets() {
        return bullets;
    }

    /**
     * @param bullets the bullets to set
     */
   

    /**
     * @return the BalaSeleccionada
     */
    public ENUMBALA getBalaSeleccionada() {
        return BalaSeleccionada;
    }

    /**
     * @param BalaSeleccionada the BalaSeleccionada to set
     */
    public void setBalaSeleccionada(ENUMBALA BalaSeleccionada) {
        this.BalaSeleccionada = BalaSeleccionada;
    }

    /**
     * @param bullets the bullets to set
     */
    public void setBullets(ArrayList<ABala> bullets) {
        this.bullets = bullets;
    }

    /**
     * @return the estado
     */
    public Level.Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Level.Estado estado) {
        this.estado = estado;
    }

    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Image img) {
        this.img = img;
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
