package BackEnd;
import java.util.*;

public class Sistema {

    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Juego> listaJuegos;

    public Sistema() {
        listaJugadores = new ArrayList();
        listaJuegos = new ArrayList();
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void agregarJugador(String nombre, int edad, String alias) {
        Jugador j = new Jugador(nombre, edad, alias);
        this.listaJugadores.add(j);
    }

    public ArrayList<Juego> getListaJuegos() {
        return listaJuegos;
    }

    public void agregarJuego(int nroJugador, char configuracion, String hora, String tipoDeJuego) {
        Jugador jug = null;
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (nroJugador == i) {
                jug = listaJugadores.get(i);
            }
        }
        if (tipoDeJuego.equalsIgnoreCase("saltar")){
            Saltar s = new Saltar (jug, configuracion, hora);
            this.listaJuegos.add(s);
        }
        else {
            Rectangulo r = new Rectangulo (jug, configuracion, hora);
            this.listaJuegos.add(r);
        }
    }

    public String mostrarColumnasAUsuario(Saltar s) {
        boolean[] columnasAMover = s.indicarColumnasParaJugador();
        return s.mostrarMensajeAlJugador(columnasAMover);
    }
    
    public Juego buscarJuegoPorHora(String hora){
        Juego ret = null;
        for (int i=0; i<listaJuegos.size(); i++){
            Juego j = listaJuegos.get(i);
            if (j.getHoraComienzo().equalsIgnoreCase(hora)){
                ret = j;   
            }
        }
        return ret;
    }
    
     /*public boolean validarColumnaIngresadaXUsuario(int nroColumna, Saltar s){
        boolean[] columnas = s.indicarColumnasParaJugador();
        boolean esCorrecto = false;
        for (int i=0; i<columnas.length; i++){
            if (columnas[i]){
                if (i==nroColumna){
                    esCorrecto = true;
                }    
            }
        }
        return esCorrecto;
    }
*/

}
