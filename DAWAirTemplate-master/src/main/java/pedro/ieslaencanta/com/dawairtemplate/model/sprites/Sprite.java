/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model.sprites;

import pedro.ieslaencanta.com.dawairtemplate.model.Coordenada;
import pedro.ieslaencanta.com.dawairtemplate.model.Size;





/**
 *
 * @author Pedro
 */
public abstract class Sprite implements IDrawable {

    private Coordenada posicion;
    private Size size;
    private boolean visible;
    private boolean live;

    public Sprite() {
        this.posicion = null;
        this.size=null;
        
        this.visible = true;
        this.live = true;
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
