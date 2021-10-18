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

    public Jugador crearJugador(String nombre, int edad, String alias) {
        Jugador j = new Jugador(nombre, edad, alias);
        return j;
    }

    public ArrayList<Juego> getListaJuegos() {
        return listaJuegos;
    }

    public void agregarJuego(Juego j) {
        listaJuegos.add(j);
    }

    public Saltar crearSaltar(int nroJugador, char configuracion, String hora) {
        Jugador jug = null;
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (nroJugador == i) {
                jug = listaJugadores.get(i);
            }
        }
        Saltar s = new Saltar(jug, configuracion, hora);
        return s;
    }

    public Rectangulo crearRectangulo(int nroJugador, char configuracion, String hora) {
        Jugador jug = null;
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (nroJugador == i) {
                jug = listaJugadores.get(i);
            }
        }
        Rectangulo r = new Rectangulo(jug, configuracion, hora);
        return r;

    }

    public int[] mostrarColumnasAUsuario(Saltar s) {
        int[] columnasAMover = s.indicarColumnasParaJugador();
        return columnasAMover;
    }

    public boolean aliasRepetido(Jugador j) {
        boolean esta = false;
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            if (listaJugadores.get(i).getAlias().equalsIgnoreCase(j.getAlias())) {
                esta = true;
            }
        }
        return esta;
    }
    
    public ArrayList<Juego> ordenarXAlias(){
       Collections.sort(listaJuegos, new Comparator<Juego>(){
           public int compare(Juego j1, Juego j2){
            return j1.getJugador().getAlias().compareTo(j2.getJugador().getAlias());
           }
       }); 
       return listaJuegos;
   }
   
    public class criterioXPuntaje implements Comparator<Juego>{
       @Override
       public int compare(Juego j1, Juego j2){
           return (int)(j2.getPuntaje() - j1.getPuntaje());
       }
   }
    
    public ArrayList<Juego> ordenarXPuntaje (){
        Collections.sort(listaJuegos, new criterioXPuntaje());
        return listaJuegos;
    }
}
