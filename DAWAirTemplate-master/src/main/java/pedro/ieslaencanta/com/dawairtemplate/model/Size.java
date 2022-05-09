/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

/**
 *
 * @author Pedro
 */
public class Size implements Cloneable, Comparable<Size> {
    private int width;
    private int height;
   
    public Size(){
    }

    public Size(int width,int height){
        this.width=width;
        this.height=height;
    }

   
    public Object clone() throws CloneNotSupportedException{
        return new Size(this.getWidth(), this.getHeight());
    }
    @Override
    public boolean equals(Object o){
        if(! (o instanceof Size)){
            return false;
        }
        if (this.getWidth()==((Size)(o)).getWidth() && this.getHeight()==((Size)(o)).getHeight()){
            return true;
        }else{
            return false;
        }
        
    }
     @Override
    public int compareTo(Size o) {
        if(this.getWidth()==o.getWidth() && this.getHeight()==o.getHeight())
            return 0;
        if(this.getWidth()<o.getWidth())
            return -1;
        else
            return 1;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
 

}
