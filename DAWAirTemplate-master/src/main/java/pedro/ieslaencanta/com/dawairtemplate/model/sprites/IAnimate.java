/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model.sprites;

/**
 *
 * @author Pedro
 */
public interface IAnimate {
    public enum AnimationState{
        STARTED,
        STOPPED,
        PAUSSED
    }
    public void next();
    public void start();
    public void stop();
    public void pause();
    public void setTitTac(int ciclos);
    public int getCurrent();
    public void setPositions(int[][] positions);
   
    public void setAnimatedByTime();
    public void unsetAnimatedByTime();
  
}
