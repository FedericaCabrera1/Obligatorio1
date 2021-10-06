package FrontEnd;

import BackEnd.Juego;
import BackEnd.Jugador;
import BackEnd.Saltar;
import BackEnd.Sistema;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        menuPrincipal(s);
    }

    public static void menuPrincipal(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Exit");
            opcion = manejarError();
            switch (opcion) {
                case 1:
                    opcion1(sistema);
                    break;
                case 2:
                    opcion2(sistema);
                    break;
                case 3:
                    opcion3(sistema);
                    break;
                case 4:
                    System.out.println("Nos vemos pronto!");
                    break;
                default:
                    System.out.println("La opción ingresada no es correcta. Reingrese");
            }
        }

    }

    public static void opcion1(Sistema sistema) {
        boolean seguirIngresando = true;
        while (seguirIngresando) {
            System.out.println("REGISTRO DE NUEVO JUGADOR");
            Scanner in = new Scanner(System.in);
            System.out.println("Ingrese Nombre:");
            String nombre = in.nextLine();
            System.out.println("Ingrese Edad:");
            int edad = manejarError();
            System.out.println("Ingrese Alias:");
            String alias = in.nextLine();
            sistema.agregarJugador(nombre, edad, alias);
            System.out.println("");
            System.out.println("Jugador Registrado con éxito");
            System.out.println("¿Que desea hacer? \n 1) Registrar otro jugador \n 2) Volver al menú principal \n 3) Exit");
            int opcionIngresada = manejarError();
            if (opcionIngresada == 2) {
                seguirIngresando = false;
            }
        }
        menuPrincipal(sistema);
    }

    public static void opcion2(Sistema sistema) {
        /*primero mostramos la lista de jugadores A MODO DE MENU, le pedimos q elija uno, 
        chequeamos q este todo OK y entonces comienza el juego con ese jugador*/

 /*aca llamamos al metodo epico de la clase sistema 
        q nos conecta con los metodos en la clase SALTAR y arranca el juego*/
        Scanner lector = new Scanner(System.in);
        System.out.println("BIENVENIDO AL JUEGO SALTAR");
        System.out.println("Lista de jugadores");
        ArrayList listaJugadores = sistema.getListaJugadores();
        for (int i = 0; i < listaJugadores.size(); i++) {
            System.out.println((i + 1) + ": " + listaJugadores.get(i));
        }
        System.out.println("Seleccione un jugador de la lista para empezar: ");
        int nroJugador = manejarError();
        while (nroJugador < 0 || nroJugador > listaJugadores.size()) {
            System.out.println("El numero ingresado esta fuera del rango. Reingrese.");
            nroJugador = manejarError();
        }

        System.out.println("");
        System.out.println("Ingrese A para configuracion AL AZAR o P para configuracion PREDETERMINADA");
        char configuracion = (lector.nextLine()).charAt(0);
        while (configuracion != 'A' && configuracion != 'a' && configuracion != 'P' && configuracion != 'p') {
            System.out.println("La letra ingresada no es valida, reingrese una P o una A.");
            configuracion = (lector.nextLine()).charAt(0);
        }

        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        String hora = hour.format(now) + "";

        System.out.println("");
        System.out.println("COMIENZA EL JUEGO SALTAR " + hora);
        sistema.agregarJuego((nroJugador - 1), configuracion, hora, "saltar");
        jugarASaltar(sistema, hora);
    }
    
    public static void jugarASaltar(Sistema sistema, String hora){
        Juego j = sistema.buscarJuegoPorHora(hora);
        //while(noSeTermina)
        //hacer cada movida que se valida 
        char[][] mat = j.getMatriz();
        String fila = "+-+-+-+-+";
        for (int i=0; i<mat.length; i++){
            System.out.println(fila);
            for (int k=0; k<mat[0].length; k++){
                System.out.print("|" + mat[i][k]);
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println(fila);
        
        String movida = sistema.mostrarColumnasAUsuario(s);
        System.out.println(movida);   
        System.out.println("Ingrese una columna de las opciones brindadas");
        int columna = manejarError();
        boolean validar = movida.contains(columna+"");
        while (!validar){
            System.out.println("Error, la columna ingresada no esta dentro de las opciones brindadas. Reingrese.");
            columna = manejarError();
            validar = movida.contains(columna+"");   
        }
        
        
        
        //hacer la movida
    }

    public static void opcion3(Sistema s) {
        /*aca llamamos al metodo epico de la clase sistema 
        q nos conecta con los metodos en la clase RECTANGULO y arranca el juego*/

        Scanner lector = new Scanner(System.in);
        System.out.println("BIENVENIDO AL JUEGO RECTANGULO");
        System.out.println("Lista de jugadores");
        ArrayList listaJugadores = s.getListaJugadores();
        for (int i = 0; i < listaJugadores.size(); i++) {
            System.out.println((i + 1) + ": " + listaJugadores.get(i));
        }
        System.out.println("Seleccione un jugador de la lista para empezar: ");
        int jugador = lector.nextInt();

        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        String hora = hour + "";
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
