package BackEnd;

import java.util.*;

public class Saltar {

    private char color; //llevar el conteo de los colores
    //private char[][] matriz;
    
    public Saltar(){
        color = 'R';
        
    }

    public char getColor() {
        return color;
    }

    public void setColor(char unColor) {
        char[] colores = {'R', 'A', 'V', 'M'};
        for (int i=0; i<colores.length; i++){
            if (unColor == colores[i]){
                this.color = colores[(i+1)%colores.length];
            }
        }
    }

    public char[][] crearMatriz() {
        char mat[][] = new char[11][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < mat[0].length; j++) {

            }
        }
        return mat;
    }

    public boolean[] indicarColumnasParaJugador(char[][] mat, char color) {
        boolean[] columnasQueSePuedenMover = new boolean[4];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == color) {
                    int posiciones = cantidadDePosicionesAMover(mat, i);
                    boolean sePuede = puedeMover(mat, posiciones, i);
                    if (sePuede) {
                        columnasQueSePuedenMover[j] = true;
                    }
                }
            }
        }
        return columnasQueSePuedenMover;
    }

    public int cantidadDePosicionesAMover(char[][] mat, int fila) {
        int contadorPosiciones = 0;
        for (int i = 0; i < 4; i++) {
            if (mat[fila][i] == 'R' || mat[fila][i] == 'A' || mat[fila][i] == 'V' || mat[fila][i] == 'M') {
                contadorPosiciones++;
            }
        }
        return contadorPosiciones;

    }

    public boolean puedeMover(char[][] mat, int cantidadPosiciones, int fila) {
        //Chequear que la posicion no este ocupada
        boolean sePuede = true;
        if (mat[fila][fila + cantidadPosiciones] == 'R' || mat[fila][fila + cantidadPosiciones] == 'A' || mat[fila][fila + cantidadPosiciones] == 'V' || mat[fila][fila + cantidadPosiciones] == 'M') {
            sePuede = false;

        } else {
            if (fila + cantidadPosiciones < 6) {
                //Chequeo que no se repitan dos fichas de un mismo color
                fila = fila + cantidadPosiciones;
                for (int i = 0; i < 4; i++) {
                    if (mat[fila][i] == this.getColor()) {
                        sePuede = false;
                    }
                }
            }
        }
        return sePuede;
    }

    public int calcularPuntaje(char[][] mat) {
        int puntaje = 0;
        int contador = 10;
        for(int i=0; i<mat.length; i++){
           for(int j=0; j<mat[0].length; j++){
               if(mat[i][j] == 'R' || mat[i][j] == 'A' || mat[i][j] == 'V' || mat[i][j] == 'M'){
                   puntaje += contador;
               }
           }
           if(i==9){
               contador += 20;
           }else{
               contador += 10; 
           }
            
        }
        return puntaje;
    }
    
    public char[][] matrizNueva(char[][] mat, int columna, int cantidadPosiciones){
        for (int i=0; i<mat.length; i++){
            if (mat[i][columna]==this.getColor()){
                mat[i+cantidadPosiciones][columna]=this.getColor();
                mat[i][columna] = ' ';
            }
        }
        return mat;
    }
    
    public String mostrarMensajeAlJugador(boolean[] array){
        String res = "Las columnas que se pueden mover son: ";
        int contador = 0;
        for (int i=0; i<array.length; i++){
            if (!array[i]){
                contador++;
            }
            else{
                res += array[i] + " ";
            }
        }
        if (contador == 4){
            res = "No hay posibles movimientos para el color " + this.getColor();
            this.setColor(this.color);
        }
        return res;  
    }
    
    public boolean seTerminaElJuego(char[][] mat){
        boolean seTermina = false;
        int contador = 0;
        for (int i=0; i<6; i++){
            for (int j=0; j<mat[0].length; j++){
                if (mat[i][j] == 'R' || mat[i][j] == 'A' || mat[i][j] == 'V' || mat[i][j] == 'M'){
                    contador++;
                }  
            }
        }
        if (contador==2){
            seTermina = true;
        }
        return seTermina;
    }
}
