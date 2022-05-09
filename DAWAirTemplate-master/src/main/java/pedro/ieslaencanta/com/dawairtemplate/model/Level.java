/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

import java.util.ArrayList;
import java.util.function.Predicate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import pedro.ieslaencanta.com.dawairtemplate.Background;
import pedro.ieslaencanta.com.dawairtemplate.IWarnClock;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IDrawable;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IKeyListener;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IMove;

/**
 *
 * @author Pedro
 */
public class Level implements IDrawable, IWarnClock, IKeyListener {

    public enum Estado {
        PRE_STARTED,
        RUNNING,
        STOPPED,
        PAUSED,
        PRE_END,
        END
    }
    //ENEMIGOS Y BALAS 
    private ArrayList<AEnemigo> enemigos;
    private ArrayList<ABala> balas;

    private static String[] msg = {"\"Pulsar una tecla para empezar", "Siguiente nivel..."};
    private String background_path;
    private int speed;
    private int position;
    private int fin;
    private Background background;
    private Fighter fighter;
    private GraphicsContext bg_ctx;
    private MediaPlayer player;
    private float[] probabilidadenemigos;
    private Size s;
    private int vidas=3;
    private Estado estado;
    private Player p;
    private int contador = 0;

    public Level(String image_path, String music_path, Size s, int speed, Coordenada start_position, GraphicsContext bg_ctx, float[] probabilidad_enemigos, int fin) {
        this.background = new Background(image_path, s, speed, start_position);
        this.background.setBg(bg_ctx);
        this.position = 0;
        this.speed = speed;
        this.estado = Estado.PRE_STARTED;
        this.fin = fin;
        this.s = s;
        this.InitFactorias();
        //crear el avion
        this.probabilidadenemigos = probabilidad_enemigos;
        this.initSound(music_path);
        this.fighter = new Fighter(
                3,
                new Size(74, 26),
                new Coordenada(20, s.getHeight() / 2),
                new Rectangle(new Coordenada(0, 0), new Coordenada(s.getWidth(), s.getHeight())));

        //enemigos creados de la factoria
        this.enemigos = new ArrayList<AEnemigo>();
        //ArrayList que guarda todas las balas de todos los enemigos
        this.balas = new ArrayList<ABala>();

    }

    private void initSound(String music_path) {
        this.player = new MediaPlayer(new Media(getClass().getResource(music_path).toString()));

        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player.seek(Duration.ZERO);
            }
        });

    }
    
    public void InitFactorias() {
        Factoria.addEnemy("Enemigo1", Enemigo1::new);
        Factoria.addEnemy("Enemigo2", Enemigo2::new);
        FactoriaBalas.addBala("Bala1", Bala1::new);
        FactoriaBalas.addBala("Bala2", Bala2::new);
        FactoriaBalas.addBala("Bala3", Bala3::new);
        Factoria.addEnemy("Enemigo3",Enemigo3::new);

    }

    public void CrearEnemigo() {
        AEnemigo tempo;
        int numeroAleatorio = 0;
        int coordeyAleatoria = (int) (Math.random() * 410 + 20);
        //PROBABILIDADES
        //TABLA PROBABILIDADES:
        //Enemigo 1= 50%
        //Enemigo 2= 30%
        //Enemigo 3= 20%
        int probabilidad = (int)(Math.random()*100 +1);
        if(probabilidad>50){
            numeroAleatorio=1;
        }else if(probabilidad>20 && probabilidad<50){
            numeroAleatorio=2;
        }else{
            numeroAleatorio=3;
        }
        
         //CREACION
        switch (numeroAleatorio) {
            case 1:
                tempo = Factoria.create("Enemigo1");

                tempo.init(new Coordenada(1000, coordeyAleatoria), new Rectangle(new Coordenada(0, 0), new Coordenada(s.getWidth(), s.getHeight())));
               
                enemigos.add(tempo);
               // System.out.println(probabilidad);
                break;
            case 2:
                tempo = Factoria.create("Enemigo2");
                tempo.init(new Coordenada(1000, coordeyAleatoria), new Rectangle(new Coordenada(0, 0), new Coordenada(s.getWidth(), s.getHeight())));
                tempo.setSize(new Size(148, 51));
                enemigos.add(tempo);
               // System.out.println(probabilidad);
                break;
             case 3:
                tempo = Factoria.create("Enemigo3");
                tempo.init(new Coordenada(1000, coordeyAleatoria), new Rectangle(new Coordenada(0, 0), new Coordenada(s.getWidth(), s.getHeight())));
                tempo.setSize(new Size(148, 51));
                enemigos.add(tempo);
                 //System.out.println(probabilidad);
                break;
            default:
                break;

        }
    }

    @Override
    public void draw(GraphicsContext gc) {

        this.background.paint(gc);
        //Figter, enemigos y balas
        this.fighter.draw(gc);
        enemigos.forEach(a -> a.draw(gc));
        this.balas.forEach(b -> b.draw(gc));
        //Pinta las vidas
         for (int i = 1; i <= this.getVidas(); i++) {
                gc.drawImage(this.fighter.getImg(),52,  6, 37, 22, 50*i*1.2, 420, 50, 30);

            }
        if (this.estado == Estado.PRE_STARTED) {
            gc.setFill(Color.BROWN);
            gc.setStroke(Color.WHITE);
            gc.strokeText(Level.msg[0], 100, 200);
            gc.fillText(Level.msg[0], 100, 200);

        }
    }

    @Override
    public void TicTac() {
        //init factoria

        if (this.getEstado() == Estado.RUNNING) {
            //llamar a tictac de los hijos
            this.TicTacChildrens();
            this.fighter.setEstado(estado.RUNNING);
            //posicion en la que termina
            if (this.position < this.fin) {
                this.position += this.speed;
            } else {
                this.EndLevel();
            }

            //crea enemigos cada x tiempo
            this.contador++;
            if (contador == 100) {
                this.CrearEnemigo();
                contador = 0;
            }

            this.detectCollisions();
            this.eliminarEnemigos();
            this.moveBullets();
            //Pasa las balas de los enemigos al ArrayList de level
            enemigos.forEach(a -> a.getBulletsE().forEach(b -> b.setBala(balas)));
         
           
        }
    }

    private void TicTacChildrens() {
        //pintar el fondo
        this.background.TicTac();

        this.fighter.TicTac();
        enemigos.forEach(a -> a.TicTac());

    }

    public void eliminarEnemigos() {
        //Elimina los enemigos si se salen o si los matamos
        this.enemigos.removeIf(a -> {
            return (a.getX() < a.getInc());
        });
        this.enemigos.removeIf(a -> {
            return (a.isLive() == false);
        });
        //tambien con las balas
        this.balas.removeIf(c -> {
          
             return (c.isCollision(fighter));});
    }

    private void detectCollisions() {
        //detectar colision de las balas con los aviones  enemigos   
        this.enemigos.forEach(a -> {
            this.fighter.getBullets().forEach(b -> {
                if (a.isCollision(b)) {
                    a.setLive(false);
                    b.setLive(false);
                    
                }
            });
        });
        //detectar colision de las balas enemigas con mi avion
        this.balas.forEach(a -> {
            
                if (a.isCollision(this.fighter)) {
                    this.setVidas(this.getVidas() - 1);
                  
                    
                }
          
        });
        
       
       

    }

    private void moveBullets() {
        //Mueve las balas enemigas
        this.balas.forEach(a -> a.TicTac());

        //Eliminar balas enemigas
        this.balas.removeIf(c -> {
            return (c.getPosicion().getX() < c.getInc());
        });

    }

    public boolean isEnd() {
        return this.getEstado() == Estado.STOPPED;
    }

    private void EndLevel() {
        this.player.stop();
        this.setEstado(Estado.END);
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @return the p
     */
    public Player getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Player p) {
        this.p = p;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public void onKeyPressed(KeyCode code) {
        //pasar el key code al avion
        if (this.getEstado() == Level.Estado.RUNNING) {
            this.fighter.onKeyPressed(code);
        }
    }

    @Override
    public void onKeyReleased(KeyCode code) {
        //para iniciar el juego
        if (this.getEstado() == Level.Estado.PRE_STARTED) {
            this.setEstado(Level.Estado.RUNNING);
        }
        if (this.getEstado() == Level.Estado.RUNNING) {
            this.fighter.onKeyReleased(code);
            if (player.getStatus() == MediaPlayer.Status.READY) {
                player.play();

            }

        }

    }

    /**
     * @return the vidas
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * @param vidas the vidas to set
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}
