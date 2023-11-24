package forms;

import java.util.ArrayList;
import java.util.List;

public class Personaje  {
    protected String nombre;
    protected int vidas;
    protected int cantidadOro;
    protected int velocidad;
    protected List<String> objetos;
    protected Posicion posicion; // una clase a parte

    public Personaje(String nombre) {
        this.nombre = nombre;
    }

    public Personaje(String nombre, int vidas, int cantidadOro, int velocidad, List<String> objetos, Posicion posicion) {

        this.nombre = nombre;
        this.vidas = vidas;
        this.cantidadOro = cantidadOro;
        this.velocidad = velocidad;
        this.objetos = new ArrayList<String>();
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getCantidadOro() {
        return cantidadOro;
    }

    public void setCantidadOro(int cantidadOro) {
        this.cantidadOro = cantidadOro;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public List<String> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<String> objetos) {
        this.objetos = objetos;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", vidas=" + vidas +
                ", cantidadOro=" + cantidadOro +
                ", velocidad=" + velocidad +
                ", objetos=" + objetos +
                ", posicion=" + posicion +
                '}';
    }
}
