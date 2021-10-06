package FrontEnd;

import BackEnd.Sistema;
import java.util.*;

public class Menu {

    public static void main(String[] args) {
        Sistema s = new Sistema();
        menuPrincipal(s);
    }

    public static void menuPrincipal(Sistema s) {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Exit");
            opcion = manejarError();
            switch (opcion) {
                case 1:
                    opcion1(s);
                    break;
                case 2:
                    opcion2(s);
                    break;
                case 3:
                    opcion3(s);
                    break;
                case 4:
                    System.out.println("Nos vemos pronto!");
                    break;
                default:
                    System.out.println("La opción ingresada no es correcta. Reingrese");
            }
        }

    }

    public static void opcion1(Sistema s) {
        boolean seguirIngresando = true;
        while (seguirIngresando) {
            System.out.println("REGISTRO DE NUEVO JUGADOR");
            Scanner in = new Scanner(System.in);
            System.out.println("Ingrese Nombre:");
            String nombre = in.nextLine();
            System.out.println("Ingrese Edad:");
            int edad = in.nextInt();
            System.out.println("Ingrese Alias:");
            in.nextLine();
            String alias = in.nextLine();
            s.agregarJugador(nombre, edad, alias);
            System.out.println("");
            System.out.println("Jugador Registrado con éxito");
            System.out.println("¿Que desea hacer? \n 1) Registrar otro jugador \n 2) Volver al menú principal \n 3) Exit");
            int opcionIngresada = manejarError();
            if (opcionIngresada == 2) {
                seguirIngresando = false;
            }
        }
        menuPrincipal(s);
    }

    public static void opcion2(Sistema s) {
        /*primero mostramos la lista de jugadores A MODO DE MENU, le pedimos q elija uno, 
        chequeamos q este todo OK y entonces comienza el juego con ese jugador*/
        
        /*aca llamamos al metodo epico de la clase sistema 
        q nos conecta con los metodos en la clase SALTAR y arranca el juego*/
        
        Scanner lector = new Scanner(System.in);
        System.out.println("BIENVENIDO AL JUEGO SALTAR");
        System.out.println("Lista de jugadores");
        ArrayList listaJugadores = s.getListaJugadores();
        for (int i=0; i<listaJugadores.size(); i++){
            System.out.println((i+1) + ": " + listaJugadores.get(i));
        }
        System.out.println("Seleccione un jugador de la lista para empezar: ");
        int jugador = lector.nextInt();
        
    }

    public static void opcion3(Sistema s) {
        /*aca llamamos al metodo epico de la clase sistema 
        q nos conecta con los metodos en la clase RECTANGULO y arranca el juego*/
     
        Scanner lector = new Scanner(System.in);
        System.out.println("BIENVENIDO AL JUEGO RECTANGULO");
        System.out.println("Lista de jugadores");
        ArrayList listaJugadores = s.getListaJugadores();
        for (int i=0; i<listaJugadores.size(); i++){
            System.out.println((i+1) + ": " + listaJugadores.get(i));
        }
        System.out.println("Seleccione un jugador de la lista para empezar: ");
        int jugador = lector.nextInt();
    }

    public static int manejarError() {
        boolean esCorrecto = false;
        Scanner in = new Scanner(System.in);
        int opcionIngresada = 0;
        while (!esCorrecto) {
            try {
                opcionIngresada = in.nextInt();
                esCorrecto = true;

            } catch (InputMismatchException e) {
                System.out.println("Error en el formato del número. Reingrese");
                in.nextLine();
            }
        }
        return opcionIngresada;
    }
}
