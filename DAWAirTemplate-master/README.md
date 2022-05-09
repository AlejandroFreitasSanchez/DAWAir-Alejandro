# Colecciones en Java

En esta práctica se trabaja con las colecciones, el uso de generalización y la programación funcional.

![image](https://github.com/pass1enator/DAWAirTemplate/blob/master/captura.png?raw=true)
   
## Enunciado

Se tiene la estructura básica del típico juego de aviones de los 80 y 90, nos han encargado terminar el mismo.
Las características que ha de soportar el juego son:
 - Se tiene que poder tener un conjunto de escenas con la introducción, una demo, el juego, mejores jugadores... para ello se ha definido la interfaz abstracta IScene implementada por AbstractScene y de la que heredarán el resto de escenas, se facilita la escena del juego GameStage. 
 - En la escena GameStage se han de definir niveles, y a medida que se pase el nivel a de comenzar el siguiente, se facilita la clase Level en el paquete modelo.
 - En cada nivel se tendrá un avión y este a su vez un conjunto de balas, además irán apareciendo diferentes tipos de enemigos de forma aleatoria en función de una probabilidad, de igual forma los enemigos disparán de forma aleatoria a partir de una probabilidad. Se ha definido una jerarquía de clases e interfaces para implementar el avión, las balas y los enemigos, por ejemplo Sprite que es el elemento básico e implementa la interfaz IDragable y SpriteMove que hereda de Sprite y además implementa IMove para poder moverse  
 - Se tiene la clase player que representa el jugador con las vidas y la puntuación, que se deberá dibujar.
## Interfaces.
Se definen una serie de interfaces para poder trabajar con diferentes clases de forma unificada, por ejemplo a la hora de dibujar se han de dibujar fondos, jugador, enemigos, balas... y para poder gestionar todos los objetos de define la interfaz IDraw

### IWarnClock
Las clases que implementen esta interfaz han de tener un método TicTac y que permite evolucionar (mover principalmente).

### IAnimate
Permite crear animaciones para los elementos del juego, por ejemplo una bala que va cambiando a medida que avanza, se recomiendoa implementarlo al final de la práctica.

### ICollision
Para la detección de colisiones, tiene un método default que devuelve cierto o falso en caso de que el objeto que se le pase ha colisionado o no, así como un enumerado para saber el estado en que se encuentra el elemento, entro otros

```Java
public interface ICollision {

    public enum State {
        COLLISION,
        DEAD,
        FREE
    };

    public int getX();
    public int getY();
    public int getHeight();
    public int getWidht();
    public default boolean isCollision(ICollision another) {
        boolean colision = false;
        
        //si el punto x mas a la izquerida esta en el margen
        if (another.getX() > this.getX() && another.getX() < this.getX() + this.getWidht()
                || //o si el punto mas a la derecha esta en el margen
                another.getX() + another.getWidht() > this.getX() && another.getX() + another.getWidht() < this.getX() + this.getWidht()) {
            if (another.getY() > this.getY() && another.getY() < this.getY() + this.getHeight()
                    || //o si el punto mas a la derecha esta en el margen
                    another.getY() + another.getHeight()> this.getY() && another.getY() + another.getHeight()< this.getY() + this.getHeight()) {

                colision = true;
                this.setColision();
                another.setColision();
            }
        }
        return colision;
    }

    public boolean hascollided();
    public void setColision();
    public void setFree();

}
```
### IDrawable

Para poder pintar un objeto en el canvas.

### IKeyListener

Reaccionar a los eventos de teclado, va descenciendo desde Game hasta el lugar que sea necesario.
```Java
public interface IKeyListener {
    public void onKeyPressed(KeyCode code);
    public void onKeyReleased(KeyCode code);  
}
```
### IMove

Interfaz para poder mover los diferentes elementos del juego y un enumerado con las posibles direcciones

### Sprite y SpriteMove

Clases abstractas (la segunda hereda de la primera) que implementan las interfaces  IDrawable e IMove y sobre las que se construyen el resto de clases como el avión, los enemigos o las balas

```Java
public abstract class Sprite implements IDrawable {

    private Coordenada posicion;
    private Size size;
    private boolean visible;
    private boolean live;

    public Sprite() {
        this.posicion = null;
        this.size=null;
        
        this.visible = false;
        this.live = false;
    }
    public Sprite(Size s,Coordenada p,boolean visible, boolean live){
        this.posicion=p;
        this.size=s;
        this.visible=visible;
        this.live=live;
    }

    /**
     * @return the posicion
     */
    public Coordenada getPosicion() {
        return posicion;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @return the live
     */
    public boolean isLive() {
        return live;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(Coordenada posicion) {
        this.posicion = posicion;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @param live the live to set
     */
    public void setLive(boolean live) {
        this.live = live;
    }

    /**
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    void init(Size s, Coordenada p, boolean visible, boolean live) {
        this.posicion=p;
        this.size=s;
        this.visible=visible;
        this.live=live;
    }

}
```

## Game 

Gestiona el reloj, cada vez que se produce un ciclo se llama al método TicTac, este método se pasa a la escena actual y así sucesivamente.
Posee una variable de tipo AbstracScene, y en el ejemplo un objeto de clase GameScene, es necesario ir cambiando de escena a medida que el estado de la actual cambie.
En el juego se reciben las teclas pulsadas, y las pasa a la escena actual.
**Implementa las interfaces IWarnClock, IKeyListener**

## Scenes

 - La clase Game posee en el ejemplo un AbstractScene escena_actual, es necesario definir la estructura e inicializarla para tener diferentes escenas
 - IScene define diferentes métodos y estados para gestionar, en cada ciclo del reloj la clase Game evalua el estado de la escena y toma la decisión.
```Java
public interface IScene extends IKeyListener, IWarnClock, IDrawable{
    public enum SceneState{
        PRE_STARTED,
        STARTED,
        PAUSED,
        PRE_END,
        END
    }
    public SceneState getState();
    public void start();
    public void stop();
    public void pause();
    public void reset();
}
```

## Level

Representa un nivel del juego, en este caso se facilita parte del código, aunque el alumno habrá de completar ciertos métodos y puede ser necesario crear otros así como atributos.
Al igual que con las escenas, el nivel tiene un estado interno, que informará en cada ciclo a  la escena que implemente el juego de la situación en que se encuentra y la escena del juego
cambiará al siguiente nivel si procede.

**El nivel implementa las intefaces IDrawable, IWarnClock, IKeyListener**
```Java
 public enum Estado {
        PRE_STARTED,
        RUNNING,
        STOPPED,
        PAUSED,
        PRE_END,
        END
    }
```
En el método TicTac se tendrá que actualizar todos los elementos necesarios, de igual forma en el draw se tendrá que llamara a pintar de todos los elementos que se desee.

## Recursos
En la carpeta resources existen ficheros multimedia como imágenes o canciones, el alumno puede usar otras si lo desea.

## Recomendaciones de desarrollo.

### Parte 1. (4 puntos)
En la clase nivel:
 - Crear el avión a partir de la clase SpriteMove y moverlo por la pantalla.
 - Crear un enemigo y que se mueva por la pantalla (hacer abstracto y heredar de este, en el futuro se necesita más enemigos).
 - Crear una bala y que el avión dispare esa bala.
 - Definir una estructura que gestione las balas del avión. 
 - Definir en el enemigo una estructura para gestionar las balas y hacer que se generen de forma aleatoria.
 - Establecer la estructura necesaria para tener un conjunto de enemigos y gestionarlo (por ejemplo cuando salen de la pantalla borrarlos).
Implementar lo necesario para que se produzcan colisiones entre las balas del avión y los enemigos (desapareciendo ambos e incrementándose el contador).
 - Hacer que las balas del enemigo puedan colisionar con el avión, descontando vidas.

### Parte 2. (2 puntos)

 - Crear al menos 3 niveles en el juego, cada vez que se termine el nivel pasará al siguiente.
 - Establecer en la clase Game la estructura para soportar diferentes escenas.
 - Crear al menos las escenas de Intro, GameScene(ya se tiene), EndGame y PlayerScore.
 - Hacer que dependiendo del estado de la escena se pase a una u otra.

### Parte 3. (4 puntos)
 - Crear una factoria de enemigos usando Supplier, la creación de los enemigos se hara a partir de un probabilidad pasada al nivel por ejempo [0,2 0,6 1], y se tiene 3 enemigos pequeños, medianos y grandes, los pequeños aparecen con probabildiad 0,2, los medianos con 0,4 (0,6-0,2) y los grandes con 0,4 (1-0,6)
 - Establecer diferentes tipos de balas (normal, fuego, laser)... al inicio de cada nivel se tendrá que asignar a cada enemigo el tipo de bala que dispara (Supplier en la clase Bullet y pasar constructor).
 - Se desea que cada enemigo tenga diferentes movimientos, se define una factoria de movimientos y al crear el enemigo se define que movimiento tendrá (lineal, diagonal, kamikaze...)

### Parte 4. (2 puntos)
Extras referidos a colecciones, interfaces funcionales o streams, por ejemplo número de balas del Figther o poder cambiar de arma en el Figther dependiendo de una tecla o al obtener un PowerUP.
## Ejemplo iniciar.

En este ejemplo se crea el avión para el niveleste avión llamado Fighter hereda de SpriteMove e implementa las interfaces IKeyListener, IWarnClock
```Java
public class Fighter extends SpriteMove implements IKeyListener, IWarnClock {

    private boolean[] keys_presed;
    private Image img;
    //path para la imagen
    private static String pathurl="avion.png";
    //para la animación
    private int original_height;
    /**
     * 
     * @param inc incremento del movimiento
     * @param s tamaño del avión
     * @param p coordenadas iniciales
     * @param board rectangulo con las dimensiones del juego para no salirse
     */
    public Fighter(int inc, Size s, Coordenada p, Rectangle board) {
        super(inc, s, p, true, true, board);
        this.keys_presed = new boolean[5];
        this.img = new Image(getClass().getResourceAsStream("/" + Fighter.pathurl));
        //cambia al mover arriba y abajo
        this.original_height=s.getHeight();
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

    }
    /**
     * acciones al soltar el teclado
     * @param code 
     */
    @Override
    public void onKeyReleased(KeyCode code) {
       
        if (code == KeyCode.SPACE) {
         //crear una bala y añadirla
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
    }
    /**
     * dibujar, es algo más complejo al moverse las alas
     * @param gc 
     */
    @Override
    public void draw(GraphicsContext gc) {
        if (keys_presed[2]) {
            gc.drawImage(img, 163, 7, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                    this.getPosicion().getX(), this.getPosicion().getY(),
                    this.getSize().getWidth(), this.getSize().getHeight());
        } else {
            if (keys_presed[3]) {
                gc.drawImage(img, 54, 7, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                        this.getPosicion().getX(), this.getPosicion().getY(),
                        this.getSize().getWidth(), this.getSize().getHeight());
            } else {
                gc.drawImage(img, 105, 8, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                        this.getPosicion().getX(), this.getPosicion().getY(),
                        this.getSize().getWidth(), this.getSize().getHeight());
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
    }
    /** 
     * cada vez que se recibe un tictac se mueve, faltan las balas del fighter
     */
    @Override
    public void TicTac() {
        this.move();
       //mover las balas 
    }  
}
```

Además se modifican diferentes métodos del nivel, como definir un atributo Figther e instancarlo en el constructor y llamar a Tittac,OnKeyPressed,OnKeyReleased para informar de las pulsaciones y en draw para que llame a pintar el Fighter:
Atributo Fighter en el nivel

```Java
 private Fighter fighter;
 ```
 
 Creación del Fighter en el constructor
 
 ```Java
 //crear el avion
this.fighter = new Fighter(
                3,
                new Size(74, 26),
                new Coordenada(20, s.getHeight() / 2),
                new Rectangle(new Coordenada(0, 0), new Coordenada(s.getWidth(), s.getHeight())));

 ```
 
 Para pasar el ciclo de reloj a los hijos 

```Java
@Override
    public void TicTac() {
        if (this.getEstado() == Estado.RUNNING) {
            //llamar a tictac de los hijos
            this.TicTacChildrens();
            //posicion en la que termina
            if (this.position < this.fin) {
                this.position += this.speed;
            } else {
                this.EndLevel();
            }
        }
    }
    private void TicTacChildrens() {
        //pintar el fondo
        this.background.TicTac();
        this.fighter.TicTac();
    }
 ```   
 Pintar 
 
 
```Java
    @Override
    public void draw(GraphicsContext gc) {

        this.background.paint(gc);
        this.fighter.draw(gc);

        if (this.estado == Estado.PRE_STARTED) {
            gc.setFill(Color.BROWN);
            gc.setStroke(Color.WHITE);
            gc.strokeText(Level.msg[0], 100, 200);
            gc.fillText(Level.msg[0], 100, 200);

        } 
    }
 ```
