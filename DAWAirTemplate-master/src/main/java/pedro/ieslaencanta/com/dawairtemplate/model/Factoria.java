/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * @author freit
 */
public class Factoria {

    private static HashMap<String, Supplier<AEnemigo>> enemigos;
    private static ArrayList<String> names;

    static {
        enemigos = new HashMap<>();
        names = new ArrayList<>();
    }

    public static void addEnemy(String name, Supplier< AEnemigo> s) {
        Factoria.enemigos.put(name, s);
        Factoria.names.add(name);
    }

    public static AEnemigo get(Supplier<? extends AEnemigo> s) {
        return s.get();
    }

    public static List<String> getKeyNames() {
        return Factoria.names;
        // return new ArrayList<String>(FactoryEnemigos.enemigos.keySet());
    }

    public static AEnemigo create(String nombre) {
        if (Factoria.enemigos.get(nombre) != null) {
            return Factoria.enemigos.get(nombre).get();
        } else {
            return null;
        }
    }
}
