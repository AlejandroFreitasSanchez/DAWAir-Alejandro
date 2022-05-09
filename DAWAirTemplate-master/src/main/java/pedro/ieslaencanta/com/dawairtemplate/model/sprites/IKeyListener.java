/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model.sprites;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Pedro
 */
public interface IKeyListener {
    public void onKeyPressed(KeyCode code);
    public void onKeyReleased(KeyCode code);  
}
