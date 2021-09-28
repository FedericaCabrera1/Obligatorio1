
package BackEnd;
import java.util.*;

public class Sistema {
    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Juego> listaJuegos;
    
    public ArrayList<Jugador> getListaJugadores(){
        return listaJugadores;
    }
    
    public void agregarJugador(Jugador j){
        this.listaJugadores.add(j);
    }
    
    public ArrayList<Juego> getListaJuegos(){
        return listaJuegos;
    }
    
    public void agregarJuego(Juego j){
        this.listaJuegos.add(j);
    }
    
}
