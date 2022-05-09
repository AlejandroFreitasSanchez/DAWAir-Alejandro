/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.stages;

import pedro.ieslaencanta.com.dawairtemplate.model.sprites.*;
import pedro.ieslaencanta.com.dawairtemplate.IWarnClock;



/**
 *
 * @author Pedro
 */
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
