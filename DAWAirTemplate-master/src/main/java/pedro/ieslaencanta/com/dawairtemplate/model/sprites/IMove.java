/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model.sprites;






/**
 *
 * @author Pedro
 */
public interface IMove {
    //public boolean moveHorizontal(Board b,Direction direction);
    //public boolean moveVertical(Board b, Direction direction);
    public void move(Direction d);
    public static enum Direction{
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
    
}
