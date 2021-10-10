package BackEnd;

import java.util.*;

public class Rectangulo extends Juego {

    private char color;
    private int[] coordsMatrizAnterior;
    private char colorAnterior;

    public Rectangulo(Jugador j, char configuracion, String hora) {
        super(j, configuracion, hora);
        this.color = 'R';
        this.setMatriz(this.crearMatriz());
        this.colorAnterior = ' ';
        
        coordsMatrizAnterior = new int[4];
        for(int i=0; i<this.coordsMatrizAnterior.length; i++){
            this.coordsMatrizAnterior[i]=-1;
        }
    }

    public char getColorAnterior() {
        return colorAnterior;
    }

    public void setColorAnterior(char unColorAnterior) {
        
        this.colorAnterior = unColorAnterior;
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

    public int[] getCoordsMatrizAnterior() {
        return coordsMatrizAnterior;
    }
    
    public boolean outOfBounds(int filaInicial, int columnaInicial, int cantFilas, int cantColumnas){
        boolean outOfBounds=false;
        if(filaInicial+cantFilas>19 || columnaInicial+cantColumnas>19){
            outOfBounds=true;
        }
        return outOfBounds;
    }

    public void setCoordsMatrizAnterior(int filaInicialAnt, int columnaInicialAnt, int cantFilasAnt, int cantColumnasAnt) {
        this.getCoordsMatrizAnterior()[0] = filaInicialAnt;
        this.getCoordsMatrizAnterior()[1] = columnaInicialAnt;
        this.getCoordsMatrizAnterior()[2] = cantFilasAnt;
        this.getCoordsMatrizAnterior()[3] = cantColumnasAnt;
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

        boolean esAdyVertical = false;
        //un primer if que verifique si son adyacentes las filas o las columnas
        int filaIA = this.getCoordsMatrizAnterior()[0];
        int columnaIA = this.getCoordsMatrizAnterior()[1];
        int cantFilasA = this.getCoordsMatrizAnterior()[2];
        int cantColA = this.getCoordsMatrizAnterior()[3];

        if(filaIA==-1){
            esAdyacente=true;
        }else{
            boolean esAdyH = esAdyHorizontal(filaInicial, columnaInicial, cantidadFilas, cantidadColumnas);
            boolean esAdyV = esAdyVertical(filaInicial, columnaInicial, cantidadFilas, cantidadColumnas);
            if(esAdyH || esAdyV){
                esAdyacente=true;
            }
        }
        //si este if me dice que no, no necesito verificar si esta out of bounds
        return esAdyacente;
    }

    public boolean chequearTamañoMatriz(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas) {
        boolean entra = true;

        return entra;
    }
    public boolean esAdyacenteH2(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas){
        boolean h = true;
        /*
        for (int i=columnaInicial; i<=columnaFinal; i++){
                if (mat[filaInicial][i]==1){
                    puntaje+=rango;
                }
                if (mat[filaFinal][i]==1){
                    puntaje+=rango;
                }
            }
            for (int j=filaInicial+1; j<=filaFinal-1; j++){
                if (mat[j][columnaInicial]==1){
                    puntaje+=rango;
                }
                if (mat[j][columnaFinal]==1){
                    puntaje+=rango;
                }
                
            }*/
        return h;
    }
    
    public boolean esAdyHorizontal(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas) {
        boolean esAdyHorizontal = true;

        int filaIA = this.getCoordsMatrizAnterior()[0];
        int columnaIA = this.getCoordsMatrizAnterior()[1];
        int cantFilasA = this.getCoordsMatrizAnterior()[2];
        int cantColA = this.getCoordsMatrizAnterior()[3];

        if (columnaIA == 0) {
            if (columnaInicial == columnaIA + cantColA) {
                if (filaIA == 0) {
                    if (filaInicial >= filaIA + cantFilasA) {
                        esAdyHorizontal = false;
                    }

                } else {
                    if (filaInicial + cantidadFilas < filaIA || filaInicial >= filaIA + cantFilasA) {
                        esAdyHorizontal = false;
                    }
                }

            }
        } else {
            if ((columnaIA + cantColA) - 1 == 19) {
                if (columnaInicial + cantidadColumnas == columnaIA - 1) {
                    if (filaIA == 0) {
                        if (filaInicial >= filaIA + cantFilasA) {
                            esAdyHorizontal = false;
                        }

                    } else {
                        if (filaInicial + cantidadFilas < filaIA || filaInicial >= filaIA + cantFilasA) {
                            esAdyHorizontal = false;
                        }
                    }
                }

            } else {
                if (columnaInicial + cantidadColumnas == columnaIA - 1 || columnaInicial == columnaIA + cantColA) {
                    if ((columnaIA + cantColA) - 1 == 19) {
                        if (columnaInicial + cantidadColumnas == columnaIA - 1) {
                            if (filaIA == 0) {
                                if (filaInicial >= filaIA + cantFilasA) {
                                    esAdyHorizontal = false;
                                }

                            } else {
                                if (filaInicial + cantidadFilas < filaIA || filaInicial >= filaIA + cantFilasA) {
                                    esAdyHorizontal = false;
                                }
                            }

                        }
                    }
                }

            }
        }
        return esAdyHorizontal;
    }
    
    public boolean esAdyVertical(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas){
        boolean esAdyVertical = true;
        return esAdyVertical;
    }

    public void crearNuevaMatriz(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas) {
        char[][] mat = this.getMatriz();
        for (int i = filaInicial; i < filaInicial + cantidadFilas; i++) {
            for (int j = columnaInicial; j < columnaInicial + cantidadColumnas; j++) {
                mat[i][j] = this.getColor();
            }
        }
        this.setColorAnterior(this.getColor());
        this.setColor(this.getColor());
        this.setCoordsMatrizAnterior(filaInicial, columnaInicial, cantidadFilas, cantidadColumnas);

    }
   
    
   
}
