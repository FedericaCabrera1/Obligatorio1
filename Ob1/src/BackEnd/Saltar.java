package BackEnd;

import java.util.*;

public class Saltar extends Juego {

    private char color;

    public Saltar(Jugador j, char configuracion, String hora) {
        super(j, configuracion, hora);
        this.color = 'R';
        this.setMatriz(this.crearMatriz());
    }

    public char getColor() {
        return color;
    }

    public void setColor(char unColor) {
        char[] colores = {'R', 'A', 'V', 'M'};
        for (int i = 0; i < colores.length; i++) {
            if (unColor == colores[i]) {
                this.color = colores[(i + 1) % colores.length];
            }
        }
    }

    public char[][] crearMatriz() {
        char mat[][] = new char[11][4];
        if (this.getConfiguracion() == 'A' || this.getConfiguracion() == 'a') {
            //Crear la matriz al azar
            char color = ' ';
            char[] filaTemporal = new char[4];
            int fila = 10;
            while (fila > 6) {
                boolean estaRepetido = true;
                while (estaRepetido) {
                    int posicion = 0;
                    while (posicion < filaTemporal.length) {
                        int numeroRandom = (int) ((Math.random()) * 4);
                        switch (numeroRandom) {
                            case 0:
                                color = 'R';
                                break;
                            case 1:
                                color = 'A';
                                break;
                            case 2:
                                color = 'V';
                                break;
                            case 3:
                                color = 'M';
                                break;
                        }
                        boolean repetido = false;
                        for (int k = 0; k < filaTemporal.length && !repetido; k++) {
                            if (filaTemporal[k] == color) {
                                repetido = true;
                            }
                        }
                        if (!repetido) {
                            filaTemporal[posicion] = color;
                            posicion++;
                        }
                    }
                    estaRepetido = validarFila(mat, filaTemporal);
                    if (estaRepetido) {
                        for (int i = 0; i < filaTemporal.length; i++) {
                            filaTemporal[i] = ' ';
                        }
                    }
                }
                for (int i = 0; i < mat[0].length; i++) {
                    mat[fila][i] = filaTemporal[i];
                }
                fila--;
                for (int i = 0; i < filaTemporal.length; i++) {
                    filaTemporal[i] = ' ';
                }
            }
        } else {
            //Crear matriz predeterminada
            mat[10][0] = 'M';
            mat[10][1] = 'V';
            mat[10][2] = 'R';
            mat[10][3] = 'A';
            mat[9][0] = 'V';
            mat[9][1] = 'M';
            mat[9][2] = 'A';
            mat[9][3] = 'R';
            mat[8][0] = 'A';
            mat[8][1] = 'R';
            mat[8][2] = 'M';
            mat[8][3] = 'V';
            mat[7][0] = 'R';
            mat[7][1] = 'A';
            mat[7][2] = 'V';
            mat[7][3] = 'M';
        }
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = ' ';
            }
        }
        return mat;

    }

    public boolean validarFila(char mat[][], char[] arr) {
        //Verifico que una letra no se repita en una misma columna 
        boolean algunRepetido = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < mat.length && !algunRepetido; j++) {
                if (mat[j][i] == arr[i]) {
                    algunRepetido = true;
                }
            }
        }
        return algunRepetido;
    }

    public void hacerMovida(int columna, int cantidadPosiciones) {
        //metodo que se llama una vez que la movida ya fue validada
        //llamar a los metodos this.getMatriz() y this.setMatriz(matriz cambiada);
        this.setMatriz(this.matrizNueva(columna, cantidadPosiciones));
        this.setColor(this.getColor());
    }

//    public char[][] getMatrizSaltar() {
//        return this.getMatriz();
//    }
    public int[] indicarColumnasParaJugador() {
        //Devuelvo un array con las columnas que puede mover el jugador
        char[][] mat = this.getMatriz();
        int[] columnasQueSePuedenMover = new int[4];
        for (int i = mat.length - 1; i >= 0; i--) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == this.getColor()) {
                    int posiciones = cantidadDePosicionesAMover(i);
                    boolean sePuede = puedeMover(posiciones, i, j);
                    if (sePuede) {
                        columnasQueSePuedenMover[j] = posiciones;
                    }
                }
            }
        }
        return columnasQueSePuedenMover;
    }

    public int cantidadDePosicionesAMover(int fila) {
        char[][] mat = this.getMatriz();
        int contadorPosiciones = 0;
        for (int i = 0; i < 4; i++) {
            if (mat[fila][i] == 'R' || mat[fila][i] == 'A' || mat[fila][i] == 'V' || mat[fila][i] == 'M') {
                contadorPosiciones++;
            }
        }
        return contadorPosiciones;

    }

    public boolean puedeMover(int cantidadPosiciones, int fila, int columna) {
        //Chequear que la posicion no este ocupada
        char[][] mat = this.getMatriz();
        boolean sePuede = true;
        if (fila - cantidadPosiciones < 0) {
            sePuede = false;
        } else {
            if (fila - cantidadPosiciones < 5) {
                if (mat[fila - cantidadPosiciones][columna] == 'R' || mat[fila - cantidadPosiciones][columna] == 'A' || mat[fila - cantidadPosiciones][columna] == 'V' || mat[fila - cantidadPosiciones][columna] == 'M') {
                    sePuede = false;
                }

            } else {
                if (fila - cantidadPosiciones >= 5) {
                    //Chequeo que no se repitan dos fichas de un mismo color
                    fila = fila - cantidadPosiciones;
                    for (int i = 0; i < 4; i++) {
                        if (mat[fila][i] == this.getColor()) {
                            sePuede = false;
                        }
                    }
                    if (sePuede) {
                        if (mat[fila][columna] == 'R' || mat[fila][columna] == 'A' || mat[fila][columna] == 'V' || mat[fila][columna] == 'M') {
                            sePuede = false;
                        }
                    }
                }
            }
        }

        return sePuede;
    }

    public int calcularPuntaje() {
        char[][] mat = this.getMatriz();
        int puntaje = 0;
        int contador = 10;
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 'R' || mat[i][j] == 'A' || mat[i][j] == 'V' || mat[i][j] == 'M') {
                    puntaje += contador;
                }
            }
            if (i == 1) {
                contador += 20;
            } else {
                contador += 10;
            }

        }
        this.setPuntaje(puntaje);
        return puntaje;
    }

    public char[][] matrizNueva(int columna, int cantidadPosiciones) {
        char[][] mat = this.getMatriz();
        boolean colorMovido = false;
        for (int i = mat.length - 1; i >= 0 && !colorMovido; i--) {
            if (mat[i][columna] == this.getColor()) {
                mat[i - cantidadPosiciones][columna] = this.getColor();
                mat[i][columna] = ' ';
                colorMovido = true;
            }
        }
        return mat;
    }

//    public String mostrarMensajeAlJugador(boolean[] array) {
//        String res = "Las columnas que se pueden mover son: ";
//        int contador = 0;
//        for (int i = 0; i < array.length; i++) {
//            if (!array[i]) {
//                contador++;
//            } else {
//                res += array[i] + " ";
//            }
//        }
//        if (contador == 4) {
//            res = "No hay posibles movimientos para el color " + this.getColor();
//            this.setColor(this.getColor());
//        }
//        return res;
//    }
    public boolean quedanDosFichas() {
        char[][] mat = this.getMatriz();
        boolean seTermina = false;
        int contador = 0;
        for (int i = mat.length - 1; i >= 5; i--) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 'R' || mat[i][j] == 'A' || mat[i][j] == 'V' || mat[i][j] == 'M') {
                    contador++;
                }
            }
        }
        if (contador == 2) {
            seTermina = true;
        }
        return seTermina;
    }

    public boolean quedanJugadasDisponibles() {
        char[][] mat = this.getMatriz();
        boolean quedanJugadas = false;
        int jugadasNoHechas = 0;
        while (jugadasNoHechas != 4 && !quedanJugadas) {
            //Empiezo con el color que tengo y me fijo si puedo hacer algun movimiento con el
            int contadorColor = 0;
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    if (mat[i][j] == this.getColor()) {
                        int cantidadPosiciones = this.cantidadDePosicionesAMover(i);
                        boolean puedeConEsteColor = this.puedeMover(cantidadPosiciones, i, j);
                        if (!puedeConEsteColor) {
                            contadorColor++;
                        }

                    }
                }
            }
            if (contadorColor == 4) {
                this.setColor(this.getColor());
                jugadasNoHechas++;
            } else {
                quedanJugadas = true;
            }
        }
        return quedanJugadas;
    }

}
