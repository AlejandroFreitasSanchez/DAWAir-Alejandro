/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

/**
 *
 * @author Pedro
 */
public class Rectangle {
    private Coordenada start;
    private Coordenada end;
    public Rectangle (Coordenada start, Coordenada end){
        this.start=start;
        this.end=end;
    }
    public Rectangle(int startx,int starty, int endx, int endy){
        this.start= new Coordenada(startx,starty);
        this.end= new Coordenada(endx,endy);
    }

    /**
     * @return the start
     */
    public Coordenada getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public Coordenada getEnd() {
        return end;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Coordenada start) {
        this.start = start;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Coordenada end) {
        this.end = end;
    }
}
