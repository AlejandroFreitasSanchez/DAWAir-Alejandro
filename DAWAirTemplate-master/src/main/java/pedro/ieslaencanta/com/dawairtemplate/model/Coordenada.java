/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

/**
 *
 * @author Pedro
 */
public class Coordenada implements Cloneable, Comparable<Coordenada> {

    private int x;
    private int y;
    private int inc;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
        this.inc = 1;
    }

    public Coordenada(int x, int y,int inc) {
        this.x = x;
        this.y = y;
        this.inc = inc;
    }

    public Coordenada() {
        this.inc=1;
    }

    public Object clone() throws CloneNotSupportedException {
        return new Coordenada(this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordenada)) {
            return false;
        }
        if (this.x == ((Coordenada) (o)).x && this.y == ((Coordenada) (o)).y) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int compareTo(Coordenada o) {
        if (this.x == o.x && this.y == o.y) {
            return 0;
        }
        if (this.x < o.x) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * @return the inc
     */
    public int getInc() {
        return inc;
    }

    /**
     * @param inc the inc to set
     */
    public synchronized void setInc(int inc) {
        this.inc = inc;
    }

    public synchronized  void incX() {
        this.x += this.inc;
    }

    public synchronized  void decX() {
        this.x -= this.inc;
    }

    public synchronized  void incY() {
        this.y += this.inc;
    }

    public synchronized  void decY() {
        this.y -= this.inc;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    public String toString(){
        return "x:"+this.x+",y:"+this.y;
    }

}
