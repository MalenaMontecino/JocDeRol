package forms;

import java.util.List;

public class Sacerdote extends Personaje{

    public Sacerdote(String nombre,List<String> objetos, Posicion posicion) {
        super(nombre, 4, 0, 5, objetos, posicion);
    }
}
