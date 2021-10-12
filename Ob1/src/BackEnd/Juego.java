
package BackEnd;
import java.util.*;

public abstract class Juego {
    private Jugador jugador;
    private char configuracion;
    private int puntaje;
    private String horaComienzo;
    private char[][] matriz;
    
    public Juego(Jugador j, char c,  String hora){
        this.setJugador(j);
        this.setConfiguracion(c);
        this.setHoraComienzo(hora);
        this.setPuntaje(0);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public char getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(char configuracion) {
        this.configuracion = configuracion;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(String horaComienzo) {
        this.horaComienzo = horaComienzo;
    }
    
    public char[][] getMatriz(){
        return matriz;
    }
    
    public void setMatriz(char[][] unaMatriz){
        this.matriz = unaMatriz;
    }
    
    public String toString(){
        return "Jugador: " + "\n Nombre: " + this.getJugador().getNombre() + "\n Alias: " + this.getJugador().getAlias() + "\n Edad: " + this.getJugador().getNombre() + "\nPuntaje: " + this.getPuntaje() + "\nHora comienzo: " + this.getHoraComienzo() + "\nTipo de juego: ";
    }
    
   
    
    

    
    
    
    
}
