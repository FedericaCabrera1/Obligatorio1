
package BackEnd;
import java.util.*;

public class Jugador {
    private String nombre;
    private int edad;
    private String alias;

    public Jugador(String unNombre, int unaEdad, String unAlias){
        nombre = unNombre;
        edad = unaEdad;
        alias = unAlias;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    @Override
    public String toString(){
        //Metodo que imprime los datos de cada jugador
        return this.getNombre() + " (" + this.getAlias() + ")";
    }
    
    
    
}
