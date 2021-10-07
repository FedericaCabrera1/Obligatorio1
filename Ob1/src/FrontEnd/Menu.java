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
        menuPrincipal(sistema);
    }

    public static void menuPrincipal(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
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
                    opcion4(sistema);
                    break;
                case 5:
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

            /*Usar Hashmap
            Persona p1 = new Persona(200);
            p1.setNombre("Micaela");
            HashMap<String, Persona> map = new HashMap();
            
            map.put(p1.getNombre(), p1);
            
            Persona pAux=map.get("Micaela");
            map.containsKey("Micaela");
             */
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
        Saltar s = sistema.crearSaltar((nroJugador - 1), configuracion, hora);
        sistema.agregarJuego(s);
        jugarASaltar(sistema, s);
    }

    public static void jugarASaltar(Sistema sistema, Saltar s) {
        //while(noSeTermina) 
        boolean seTermina = false;
        while (!seTermina) {
            char[][] mat = s.getMatriz();
            imprimirMatrizSaltar(s);
            if (!(s.quedanDosFichas()) && s.quedanJugadasDisponibles()) {
                //Se le muestra al jugador las columnas que puede mover para ese color
                int[] columnas = sistema.mostrarColumnasAUsuario(s);
                String res = "Las columnas que se pueden mover para el color " + s.getColor() + " son: ";
                int cantidadMovimientos = 0;
                for (int i = 0; i < columnas.length; i++) {
                    if (columnas[i] != 0) {
                        res += "\n" + i + ": " + columnas[i];
                    } else {
                        cantidadMovimientos++;
                    }
                }
                if (cantidadMovimientos != 4) {
                    System.out.println(res);
                    System.out.println("Ingrese una columna de las opciones brindadas");
                    int columna = manejarError();
                    int cantPosiciones = 0;
                    boolean validar = false;
                    for (int i = 0; i < columnas.length; i++) {
                        if (i == columna) {
                            if (columnas[i] != 0) {
                                validar = true;
                                cantPosiciones = columnas[i];
                            }
                        }
                    }
                    while (!validar) {
                        System.out.println("Error, la columna ingresada no esta dentro de las opciones brindadas. Reingrese.");
                        columna = manejarError();
                        for (int i = 0; i < columnas.length; i++) {
                            if (i == columna) {
                                if (columnas[i] != 0) {
                                    validar = true;
                                    cantPosiciones = columnas[i];
                                }
                            }
                        }
                    }
                    s.hacerMovida(columna, cantPosiciones);
                } else {
                    System.out.println("No hay movimientos disponibles para el color " + s.getColor());
                    System.out.println("");
                    s.setColor(s.getColor());
                }
            } else {
                seTermina = true;
            }
        }

        if (s.quedanDosFichas() && !s.quedanJugadasDisponibles()) {
            System.out.println("El juego se termino porque solo quedan dos fichas en el area base y porque no hay mas movimientos para realizar");
        } else {
            if (s.quedanDosFichas()) {
                System.out.println("El juego se termino porque solo quedan dos fichas en el area base");
            } else {
                if (!s.quedanJugadasDisponibles()) {
                    System.out.println("El juego se termino porque no hay mas movimientos para realizar");
                }

            }
        }
        System.out.println("El juego finalizo.");
        System.out.println("PUNTAJE FINAL: " + s.calcularPuntaje());
        System.out.println("Se retornará al Menu Principal automaticamente");
        System.out.println("");
    }

    public static void imprimirMatrizSaltar(Saltar s) {
        char[][] mat = s.getMatriz();
        String fila = "+-+-+-+-+";
        for (int i = 0; i < mat.length; i++) {
            System.out.println(fila);
            for (int k = 0; k < mat[0].length; k++) {
                System.out.print("|" + mat[i][k]);
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println(fila);
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
    
    public static void opcion4(Sistema s){
        
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
