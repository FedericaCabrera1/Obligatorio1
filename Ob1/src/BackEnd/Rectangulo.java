package BackEnd;

import java.util.*;

public class Rectangulo extends Juego {
    private char color;
    private char[][] matrizAnterior;

    public Rectangulo(Jugador j, char configuracion, String hora) {
        super(j, configuracion, hora);
        this.color = 'R';
        this.setMatriz(this.crearMatriz());
    }

    public char getColor() {
        return color;
    }

    public void setColor(char unColor) {
        char[] colores = {'R', 'A', 'V', 'M', 'B', 'G', 'H', 'I', 'E', 'P'};
        for (int i = 0; i < colores.length; i++) {
            if (unColor == colores[i]) {
                this.color = colores[(i + 1) % colores.length];
            }
        }
    }

    public char[][] getMatrizAnterior() {
        return matrizAnterior;
    }

    public void setMatrizAnterior(char[][] unaMatrizAnterior) {
        this.matrizAnterior = unaMatrizAnterior;
    }
    
    public char[][] crearMatriz() {
        char[][] mat = new char[20][20];
        if (this.getConfiguracion() == 'A' || this.getConfiguracion() == 'a') {
            boolean repetido = true;
            int filaRandom = 0;
            int columnaRandom = 0;
            int contadorAsteriscos = 0;
            while (contadorAsteriscos < 20) {
                repetido = true;
                while (repetido) {
                    repetido = false;
                    filaRandom = (int) ((Math.random()) * 20);
                    columnaRandom = (int) ((Math.random()) * 20);
                    if (mat[filaRandom][columnaRandom] == '*') {
                        repetido = true;
                    } else {
                        mat[filaRandom][columnaRandom] = '*';
                        contadorAsteriscos++;
                    }
                }
            }
        } else {
            mat[0][2] = '*';
            mat[2][2] = '*';
            mat[3][4] = '*';
            mat[5][15] = '*';
            mat[8][9] = '*';
            mat[8][10] = '*';
            mat[11][9] = '*';
            mat[11][19] = '*';
            mat[12][17] = '*';
            mat[13][16] = '*';
            mat[14][5] = '*';
            mat[14][10] = '*';
            mat[14][19] = '*';
            mat[15][16] = '*';
            mat[16][4] = '*';
            mat[17][3] = '*';
            mat[17][10] = '*';
            mat[18][2] = '*';
            mat[18][13] = '*';
            mat[19][14] = '*';
        }
        for (int i = 0;
                i < mat.length;
                i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != '*') {
                    mat[i][j] = ' ';
                }
            }
        }
        return mat;
    }

    public boolean validacionSuperposicion(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas) {
        char[][] mat = this.getMatriz();
        boolean seSuperpone = false;
        for (int i = filaInicial; i < filaInicial + cantidadFilas; i++) {
            for (int j = columnaInicial; j < columnaInicial + cantidadColumnas; j++) {
                if (mat[i][j] != ' ') {
                    seSuperpone = true;
                }
            }
        }
        return seSuperpone;
    }

    public boolean validacionAdyacente(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas) {
        char[][] mat = this.getMatriz();
        boolean esAdyacente = false;
        //un primer if que verifique si son adyacentes las filas o las columnas
        if (esAdyacente){
            //si este if me dice que si, necesito verificar que esta out of bounds
            if (this.chequearTamañoMatriz(filaInicial, columnaInicial, cantidadFilas, cantidadColumnas)){
                //puedo agregar la matriz
                esAdyacente = true;
            }
        }
         //si este if me dice que no, no necesito verificar si esta out of bounds
        return esAdyacente;
    }
    
    public boolean chequearTamañoMatriz(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas){
        boolean entra = true;
        
        return entra;
    }

    public void crearNuevaMatriz(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas) {
        char[][] mat = this.getMatriz();
        for (int i = filaInicial; i < filaInicial + cantidadFilas; i++) {
            for (int j = columnaInicial; j < columnaInicial + cantidadColumnas; j++) {
                mat[i][j] = this.getColor();
            }
        }
        this.setColor(this.getColor());
    }

}
