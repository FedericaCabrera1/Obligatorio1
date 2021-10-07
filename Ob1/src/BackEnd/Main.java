package BackEnd;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Jugador n = new Jugador ("AAA", 19, "mm");
        Saltar j1 = new Saltar(n,'P', "08:55");
        char[][] mat = j1.getMatrizSaltar();
        for (int i=0; i<mat.length; i++){
            for (int j=0; j<mat[0].length; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
        int[] array = j1.indicarColumnasParaJugador();
        for (int i=0; i<array.length; i++){
            System.out.println(array[i]);
        }

    }

}
