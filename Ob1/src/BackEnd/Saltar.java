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

    public char setColor2(char unColor) {
        color = unColor;
        return color;
    }

    public boolean validarFila(char mat[][], char[] arr) {
        //Metodo que verifica que una letra no se repita en una misma columna 
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
        //Metodo que se llama una vez que la movida ya fue validada y realiza la movida
        this.setMatriz(this.matrizNueva(columna, cantidadPosiciones));
        this.setColor(this.getColor());
        System.out.println(this.getColor());
    }

    public int[] indicarColumnasParaJugador() {
        //Metodo que devuelve un array con las columnas que puede mover el jugador
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
        int[] colFichaAdelantada = fichaSola();
        if (colFichaAdelantada[0] != -1) {
            columnasQueSePuedenMover[colFichaAdelantada[0]] = 0;
        }
        return columnasQueSePuedenMover;
    }

    public int cantidadDePosicionesAMover(int fila) {
        //Metodo que calcula la cantidad de posiciones que se puede mover cada ficha de un determinado color
        char[][] mat = this.getMatriz();
        int contadorPosiciones = 0;
        for (int i = 0; i < 4; i++) {
            if (mat[fila][i] == 'R' || mat[fila][i] == 'A' || mat[fila][i] == 'V' || mat[fila][i] == 'M') {
                contadorPosiciones++;
            }
        }
        return contadorPosiciones;

    }

    public int[] fichaSola() {
        //Metodo que identifica y guarda la ficha mas adelantada de cada color para impedir que el usuario mueva esa
        int[] res = new int[2];
        int fila = -1;
        int contador = 0;
        char mat[][] = this.getMatriz();
        boolean encontre = false;
        boolean encontreColor = false;
        int columna = -1;
        for (int i = 0; i < mat.length && !encontreColor; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == this.getColor()) {
                    encontreColor = true;
                    columna = j;
                    fila = i;
                    for (int k = 0; k < mat[i].length; k++) {
                        if (mat[i][k] != ' ') {
                            contador++;
                        }
                    }
                }
            }

        }
        if (contador == 1 && encontreColor) {
            res[0] = columna;
            res[1] = fila;
        } else {
            res[0] = -1;
        }
        return res;
    }

    public boolean puedeMover(int cantidadPosiciones, int fila, int columna) {
        //Metodo que verifica que la posicion a la que se quiere llegar no este ocupada
        //Verifica tambien que, en caso de estar en el area base, en la fila a la que se quiere llegar no haya dos fichas del mismo color
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
                    //Chequea que no se repitan dos fichas de un mismo color
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
        //Metodo que calcula el puntaje total del juego Saltar
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
        //Metodo que genera la nueva matriz del juego, guardando la movida realizada
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

    public boolean quedanDosFichas() {
        //Metodo que verifica si en el area base quedan dos fichas para saber si terminar el juego o no
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

//    public boolean quedanJugadasDisponibles() {
//        Metodo que verifica si quedan jugadas disponibles en alguna ficha de algun color
//        char[][] mat = this.getMatriz();
//        boolean quedanJugadas = false;
//        int jugadasNoHechas = 0;
//        char color = this.getColor();
//        while (jugadasNoHechas != 4 && !quedanJugadas) {
//            Empieza con el color actual y se fija si puede hacer algun movimiento con el
//            int contadorColor = 0;
//            System.out.println("color: " + this.getColor());
//            for (int i = 0; i < mat.length; i++) {
//                for (int j = 0; j < mat[0].length; j++) {
//                    if (mat[i][j] == this.getColor()) {
//                        int cantidadPosiciones = this.cantidadDePosicionesAMover(i);
//                        boolean puedeConEsteColor = this.puedeMover(cantidadPosiciones, i, j);
//                        if (!puedeConEsteColor) {
//                            contadorColor++;
//                        }
//                    }
//                }
//            }
//            System.out.println(contadorColor);
//            if (fichaSola()[0] != -1) {
//                if (fichaSola()[1] != 0) {
//                    contadorColor++;
//                }
//            } else {
//                if (contadorColor == 4) {
//                    this.setColor(this.getColor());
//                    jugadasNoHechas++;
//                } else {
//                    quedanJugadas = true;
//                }
//                System.out.println("Contador " + this.getColor() + " : " + contadorColor);
//            }
//        }
//        if (quedanJugadas) {
//            this.setColor2(color);
//
//        }
//        System.out.println(quedanJugadas);
//        return quedanJugadas;
//
//    }
    
     public boolean quedanJugadasDisponibles() {
         boolean quedanJugadas = false;
         char color = this.getColor();
         int jugadasNoHechas = 0;
         while(!quedanJugadas && jugadasNoHechas!=4){
             int contadorColor = 0;
             int[] columnasDeUnColor = indicarColumnasParaJugador();
             for(int i=0; i<columnasDeUnColor.length; i++){
                 if(columnasDeUnColor[i]==0){
                     contadorColor++;    
                 }
             }
            
             if(contadorColor==4){
                 this.setColor(this.getColor());
                 jugadasNoHechas++;
             }
             else{
                 quedanJugadas = true;
             }
         }
         if(quedanJugadas){
             this.setColor2(color);
         }
         return quedanJugadas;
     }

    public String toString() {
        //Metodo que hereda el toString() de la clase Juego y le agrega informacion
        return super.toString() + "Saltar";
    }

}
