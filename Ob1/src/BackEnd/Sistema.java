
package BackEnd;
import java.util.*;

public class Sistema {
    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Juego> listaJuegos;
    
    public ArrayList<Jugador> getListaJugadores(){
        return listaJugadores;
    }
    
    public void agregarJugador(String nombre, int edad, String alias){
        Jugador j = Jugador(nombre, edad, alias);
        this.listaJugadores.add(j);
    }
    
    public ArrayList<Juego> getListaJuegos(){
        return listaJuegos;
    }
    
    public void agregarJuego(Juego j){
        this.listaJuegos.add(j);
    }
    
    public void JuegoSaltar(){
        //metodo que contiene toda la logica del juego Saltar, implementando los metodos de la clase saltar
        //a este metodo lo llama el metodo Opcion2 desde el front
    }
    
}
