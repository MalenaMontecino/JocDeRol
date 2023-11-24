package forms;

public class Partida {
    private String nombre;
    private String tipoPersonaje;
    private int tiempo;
    private int vidas;
    private int oro;
    private int puntuacion;

    public Partida(String nombre, String tipoPersonaje, int tiempo, int vidas, int oro) {
        this.nombre = nombre;
        this.tipoPersonaje = tipoPersonaje;
        this.tiempo = tiempo;
        this.vidas = vidas;
        this.oro = oro;
    }

    public int getPuntuacion() {
        return puntuacion = calcularPuntuacion();
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoPersonaje() {
        return tipoPersonaje;
    }

    public void setTipoPersonaje(String tipoPersonaje) {
        this.tipoPersonaje = tipoPersonaje;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }


    public int calcularPuntuacion() {
        // Supongamos que asignamos 1 punto por cada segundo de duración de la partida
        int puntuacionDuracion = tiempo;

        // Supongamos que asignamos 10 puntos por cada moneda obtenida
        int puntuacionMonedas = oro * 10;

        // Verificar si la partida ha sido completada y asignar puntos adicionales
        int puntuacionCompletada = 0;
        if (oro >= 50) {
            puntuacionCompletada = 100;
        }

        // Sumamos las puntuaciones parciales y adicionales para obtener la puntuación total
        int puntuacionTotal = puntuacionDuracion + puntuacionMonedas + puntuacionCompletada;

        return puntuacionTotal;
    }
}
