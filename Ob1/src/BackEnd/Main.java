package BackEnd;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Jugador n = new Jugador("AAA", 19, "mm");
//        Saltar j1 = new Saltar(n,'A', "08:55");
//        char[][] mat = j1.getMatriz();
//        for (int i=0; i<mat.length; i++){
//            for (int j=0; j<mat[0].length; j++){
//                System.out.print(mat[i][j] + " ");
//            }
//            System.out.println("");
//        }
//        int[] array = j1.indicarColumnasParaJugador();
//        for (int i=0; i<array.length; i++){
//            System.out.println(array[i]);
//        }
//        
//        j1.hacerMovida(1, 4);
//        System.out.println(j1.quedanJugadasDisponibles());
//        System.out.println(j1.quedanDosFichas());
//        
//        j1.hacerMovida(2,4);
//        System.out.println(j1.quedanJugadasDisponibles());
//        System.out.println(j1.quedanDosFichas());
        Sistema sistema = new Sistema();
        Rectangulo j2 = new Rectangulo(n, 'P', "9:50");
        char[][] mat2 = j2.getMatriz();
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                System.out.print(mat2[i][j] + " ");
            }
            System.out.println("");

        }
        j2.crearMatriz();

        for (int i = 0; i < 19; i++) {
            j2.getMatriz()[1][i] = 'R';
        }
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                System.out.print(mat2[i][j] + " ");
            }
            System.out.println("");

        }

        for (int i = 3; i < 8; i++) {
            j2.getMatriz()[2][i] = 'A';
        }
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                System.out.print(mat2[i][j] + " ");
            }
            System.out.println("");

        }
        System.out.println(j2.esAdyacente(2, 8, 1, 5));
        
        for (int i = 8; i < 14; i++) {
            j2.getMatriz()[2][i] = 'V';
        }
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                System.out.print(mat2[i][j] + " ");
            }
            System.out.println("");
        }

    }

    public static int[] recibirCoordenadas(String coordenadas) {
        int[] coords = new int[4];
        int contador = 0;
        for (int i = 0; i < coordenadas.length() && contador < 4; i++) {
            String res = "";
            if (coordenadas.charAt(i) != ' ') {
                res += coordenadas.charAt(i);
                if (i != coordenadas.length()) {
                    if (coordenadas.charAt(i + 1) != ' ') {
                        res += coordenadas.charAt(i + 1);
                    }
                }
                coords[contador] = Integer.parseInt(res);
                contador++;

            }
        }
        return coords;

    }

}
