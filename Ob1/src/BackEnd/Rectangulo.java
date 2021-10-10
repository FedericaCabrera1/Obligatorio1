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
        for (int i = 0; i < this.coordsMatrizAnterior.length; i++) {
            this.coordsMatrizAnterior[i] = -1;
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

    public boolean outOfBounds(int filaInicial, int columnaInicial, int cantFilas, int cantColumnas) {
        boolean outOfBounds = false;
        if (filaInicial + (cantFilas - 1) > 19 || columnaInicial + (cantColumnas - 1) > 19) {
            outOfBounds = true;
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
        for (int i = filaInicial; i <= filaInicial + (cantidadFilas - 1); i++) {
            for (int j = columnaInicial; j <= columnaInicial + (cantidadColumnas - 1); j++) {
                if (mat[i][j] != ' ') {
                    seSuperpone = true;
                }
            }
        }
        return seSuperpone;
    }

    public boolean esAdyacente(int filaInicial, int columnaInicial, int cantidadFilas, int cantidadColumnas) {
        boolean esAdy = false;
        int filaFinal = (filaInicial + cantidadFilas) - 1;
        int columnaFinal = (columnaInicial + cantidadColumnas) - 1;
        int filaIA = this.getCoordsMatrizAnterior()[0];
        if (filaIA == -1) {
            esAdy = true;
        } else {
            for (int i = columnaInicial; i <= columnaFinal; i++) {
                if (filaInicial != 0 && filaFinal != 19) {
                    if (this.getMatriz()[filaInicial - 1][i] == this.getColorAnterior() || this.getMatriz()[filaFinal + 1][i] == this.getColorAnterior()) {
                        esAdy = true;
                    }
                } else {
                    if (filaInicial == 0) {
                        if (this.getMatriz()[filaFinal + 1][i] == this.getColorAnterior()) {
                            esAdy = true;
                        }
                    } else {
                        if (filaFinal == 19) {
                            if (this.getMatriz()[filaInicial - 1][i] == this.getColorAnterior()) {
                                esAdy = true;
                            }
                        }
                    }

                }

                for (int j = filaInicial; j <= filaFinal; j++) {
                    if (columnaInicial != 0 && columnaFinal != 19) {
                        if (this.getMatriz()[j][columnaInicial - 1] == this.getColorAnterior() || this.getMatriz()[j][columnaFinal + 1] == this.getColorAnterior()) {
                            esAdy = true;
                        }
                    } else {
                        if (columnaInicial == 0) {
                            if (this.getMatriz()[j][columnaFinal + 1] == this.getColorAnterior()) {
                                esAdy = true;
                            } else {
                                if (columnaFinal == 19) {
                                    if (this.getMatriz()[j][columnaInicial - 1] == this.getColorAnterior()) {
                                        esAdy = true;
                                    }
                                }
                            }

                        }
                    }

                }
            }

        }
        return esAdy;
    }

    public boolean quedanJugadas() {
        boolean quedanJugadas = false;

        int filaInicial = this.getCoordsMatrizAnterior()[0];
        int columnaInicial = this.getCoordsMatrizAnterior()[1];
        int cantFilas = this.getCoordsMatrizAnterior()[2];
        int cantColumnas = this.getCoordsMatrizAnterior()[3];
        int filaFinal = filaInicial + (cantFilas - 1);
        int columnaFinal = columnaInicial + (cantColumnas - 1);

        if (filaInicial == -1) {
            quedanJugadas = true;
        }

        for (int i = columnaInicial; i <= columnaFinal; i++) {
            if (filaInicial != 0 && filaFinal != 19) {
                if (this.getMatriz()[filaInicial - 1][i] == ' ' || this.getMatriz()[filaFinal + 1][i] == ' ') {
                    quedanJugadas = true;
                }
            } else {
                if (filaInicial == 0 && filaFinal != 19) {
                    if (this.getMatriz()[filaFinal + 1][i] == ' ') {
                        quedanJugadas = true;
                    }
                } else {
                    if (filaFinal == 19 && filaInicial != 0) {
                        if (this.getMatriz()[filaInicial - 1][i] == ' ') {
                            quedanJugadas = true;
                        }
                    }
                }

            }

            for (int j = filaInicial; j <= filaFinal; j++) {
                if (columnaInicial != 0 && columnaFinal != 19) {
                    if (this.getMatriz()[j][columnaInicial - 1] == ' ' || this.getMatriz()[j][columnaFinal + 1] == ' ') {
                        quedanJugadas = true;
                    }
                } else {
                    if (columnaInicial == 0 && columnaFinal != 19) {
                        if (this.getMatriz()[j][columnaFinal + 1] == ' ') {
                            quedanJugadas = true;
                        } else {
                            if (columnaFinal == 19 && columnaInicial != 0) {
                                if (this.getMatriz()[j][columnaInicial - 1] == ' ') {
                                    quedanJugadas = true;
                                }
                            }
                        }

                    }
                }

            }
        }
        return quedanJugadas;
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
        this.setPuntaje(this.getPuntaje() + (cantidadFilas * cantidadColumnas));

    }

}
