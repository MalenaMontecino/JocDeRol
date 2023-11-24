package forms;

import java.util.List;

public class Guerrero extends Personaje{
    public Guerrero(String nombre, List<String> objetos, Posicion posicion) {
        super(nombre, 5, 0, 3, objetos, posicion);
    }
}
