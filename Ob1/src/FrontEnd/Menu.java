package FrontEnd;

import BackEnd.Juego;
import BackEnd.Jugador;
import BackEnd.Rectangulo;
import BackEnd.Saltar;
import BackEnd.Sistema;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {

    public static void main(String[] args) {
        System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
        Sistema sistema = new Sistema();
        menuPrincipal(sistema);
    }

    public static void menuPrincipal(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("\u001B[31m" + "La opción ingresada no es correcta. Reingrese" + "\u001B[0m");
            }
        }

    }

    public static void opcion1(Sistema sistema) {
        boolean seguirIngresando = true;
        while (seguirIngresando) {
            System.out.println("REGISTRO DE NUEVO JUGADOR");
            Scanner in = new Scanner(System.in);
            System.out.println("Ingrese Nombre:");
            String nombre = validarString();
            System.out.println("Ingrese Edad:");
            int edad = manejarError();
            while (edad <= 0 || edad >= 100) {
                System.out.println("\u001B[31m" + "La edad ingresada está fuera de rango. Debe ser entre 1 y 100. Reingrese" + "\u001B[0m");
                edad = manejarError();
            }
            System.out.println("Ingrese Alias:");
            String alias = validarString();
            String aliasSinEspacios = "";
            for (int i = 0; i < alias.length(); i++) {
                if (alias.charAt(i) != ' ') {
                    aliasSinEspacios += String.valueOf(alias.charAt(i));
                }
            }
            Jugador jugadorAgregado = sistema.crearJugador(nombre, edad, aliasSinEspacios);
            boolean esta = sistema.aliasRepetido(jugadorAgregado);
            while (esta) {
                System.out.println("\u001B[31m" + "El alias elegido ya está en uso. Reingrese" + "\u001B[0m");
                alias = in.nextLine();
                aliasSinEspacios = "";
                for (int i = 0; i < alias.length(); i++) {
                    if (alias.charAt(i) != ' ') {
                        aliasSinEspacios += String.valueOf(alias.charAt(i));
                    }
                }
                jugadorAgregado = sistema.crearJugador(nombre, edad, aliasSinEspacios);
                esta = sistema.aliasRepetido(jugadorAgregado);
            }
            sistema.agregarJugador(nombre, edad, alias);
            System.out.println("");
            System.out.println("Jugador Registrado con éxito");
            System.out.println("¿Que desea hacer? \n 1) Registrar otro jugador \n 2) Volver al menú principal \n 3) Exit");
            int opcionIngresada = manejarError();
            while (opcionIngresada != 1 && opcionIngresada != 2 && opcionIngresada != 3) {
                System.out.println("\u001B[31m" + "La opcion ingresada esta fuera de rango. Reingrese." + "\u001B[0m");
                opcionIngresada = manejarError();

            }
            if (opcionIngresada == 2) {
                seguirIngresando = false;
            } else if (opcionIngresada == 3) {
                System.out.println("Nos vemos pronto!");
                System.exit(0);
            }
        }
        System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
        menuPrincipal(sistema);
    }

    public static String validarString() {
        Scanner lector = new Scanner(System.in);
        String string = lector.nextLine();
        boolean hayAlgo = false;
        while (!hayAlgo) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) != ' ') {
                    hayAlgo = true;
                }
            }
            if (!hayAlgo) {
                System.out.println("\u001B[31m" + "La informacion ingresada es un espacio en blanco. Reingrese" + "\u001B[0m");
                string = lector.nextLine();
            }
        }
        return string;
    }

    public static void opcion2(Sistema sistema) {
        /*primero mostramos la lista de jugadores A MODO DE MENU, le pedimos q elija uno, 
        chequeamos q este todo OK y entonces comienza el juego con ese jugador*/

 /*aca llamamos al metodo epico de la clase sistema 
        q nos conecta con los metodos en la clase SALTAR y arranca el juego*/
        ArrayList<Jugador> listaJugadores = sistema.getListaJugadores();
        if (listaJugadores.size() == 0) {
            System.out.println("\u001B[31m" + "No hay ningun jugador registrado en el sistema, porfavor seleccione la opción 1 del menu para hacerlo." + "\u001B[0m");
            menuPrincipal(sistema);
        } else {
            Scanner lector = new Scanner(System.in);
            System.out.println("BIENVENIDO AL JUEGO SALTAR");
            System.out.println("Lista de jugadores");
            listaJugadores = sistema.getListaJugadores();
            for (int i = 0; i < listaJugadores.size(); i++) {
                System.out.println((i + 1) + ": " + listaJugadores.get(i));
            }
            System.out.println("Seleccione un jugador de la lista para empezar: ");
            int nroJugador = manejarError();
            while (nroJugador < 0 || nroJugador > listaJugadores.size()) {
                System.out.println("\u001B[31m" + "El numero ingresado esta fuera del rango. Reingrese." + "\u001B[0m");
                nroJugador = manejarError();
            }

            System.out.println("");
            System.out.println("Ingrese A para configuracion AL AZAR o P para configuracion PREDETERMINADA");

            char configuracion = chequearConfiguracion();
            while (configuracion != 'A' && configuracion != 'a' && configuracion != 'P' && configuracion != 'p') {
                System.out.println("\u001B[31m" + "La letra ingresada no es valida, reingrese una P o una A." + "\u001B[0m");
                configuracion = chequearConfiguracion();
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

    }

    public static char chequearConfiguracion() {
        Scanner lector = new Scanner(System.in);
        String configuracion = lector.nextLine();
        boolean esCorrecto = false;
        char conf = ' ';
        while (!esCorrecto) {
            try {
                conf = configuracion.charAt(0);
                esCorrecto = true;
            } catch (Exception e) {
                System.out.println("\u001B[31m" + "El formato de la letra ingresada es incorrecto. Reingree" + "\u001B[0m");
                configuracion = lector.nextLine();
            }
        }
        return conf;
    }

    public static void jugarASaltar(Sistema sistema, Saltar s) {
        boolean volverAJugar = true;
        while (volverAJugar) {
            boolean seTermina = false;
            int cantidadMovimientos = 0;
            while (!seTermina) {
                char[][] mat = s.getMatriz();
                if (!(s.quedanDosFichas()) && s.quedanJugadasDisponibles()) {
                    //Se le muestra al jugador las columnas que puede mover para ese color
                    if (cantidadMovimientos != 4) {
                        imprimirMatrizSaltar(s);
                    }
                    cantidadMovimientos = 0;
                    int[] columnas = sistema.mostrarColumnasAUsuario(s);
                    String res = "Las columnas que se pueden mover para el color " + s.getColor() + " son: ";
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
                        hayXSaltar(columna, s, sistema);
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
                            System.out.println("\u001B[31m" + "Error, la columna ingresada no esta dentro de las opciones brindadas. Reingrese." + "\u001B[0m");
                            columna = manejarError();
                            hayXSaltar(columna, s, sistema);
                            for (int i = 0; i < columnas.length; i++) {
                                if (i == columna) {
                                    if (columnas[i] != 0) {
                                        validar = true;
                                        cantPosiciones = columnas[i];
                                    }
                                }
                            }

                        }
                        if (!seTermina) {
                            s.hacerMovida(columna, cantPosiciones);
                        }

                    } else {
                        System.out.println("\u001B[31m" + "No hay movimientos disponibles para el color " + s.getColor() + "\u001B[0m");
                        System.out.println("");
                        s.setColor(s.getColor());
                    }
                } else {
                    seTermina = true;
                }

            }
            imprimirMatrizSaltar(s);
            if (s.quedanDosFichas() && !s.quedanJugadasDisponibles()) {
                System.out.println("\u001B[35m" + "El juego se termino porque solo quedan dos fichas en el area base y porque no hay mas movimientos para realizar" + "\u001B[0m");
            } else {
                if (s.quedanDosFichas()) {
                    System.out.println("\u001B[35m" + "El juego se termino porque solo quedan dos fichas en el area base" + "\u001B[0m");
                } else {
                    if (!s.quedanJugadasDisponibles()) {
                        System.out.println("\u001B[35m" + "El juego se termino porque no hay mas movimientos para realizar" + "\u001B[0m");
                    }

                }
            }

            System.out.println("PUNTAJE FINAL: " + s.calcularPuntaje());
            boolean volverAlMenu = volverAMenu("volver a jugar a Saltar?", "S para volver a jugar", 's');
            if (volverAlMenu) {
                volverAJugar = false;
            } else {
                opcion2(sistema);
            }
        }

        System.out.println("");
        System.out.println("Se retornará al Menu Principal automaticamente");
        System.out.println("");
        System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
        menuPrincipal(sistema);
    }

    public static void hayXSaltar(int columna, Saltar s, Sistema sistema) {
        boolean hayX = false;
        if (columna == -1) {
            boolean deseaSalir = deseaSalir();
            if (deseaSalir) {
                System.out.println("PUNTAJE FINAL: " + s.calcularPuntaje());
                System.out.println("Se retornará al Menu Principal automaticamente");
                System.out.println("");
                System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
                menuPrincipal(sistema);
            } else {
                jugarASaltar(sistema, s);
            }
        }
    }

    public static void imprimirMatrizSaltar(Saltar s) {
        char[][] mat = s.getMatriz();
        String fila = "  +-+-+-+-+";
        String filaNums = "   1 2 3 4 ";
        int[] columna = {60, 40, 30, 20, 10};
        int p = 0;
        for (int i = 0; i < mat.length; i++) {
            System.out.println(fila);
            if (i > 4) {
                System.out.print("  ");
            } else {
                System.out.print(columna[p]);
            }
            for (int k = 0; k < mat[0].length; k++) {
                System.out.print("|");
                if (mat[i][k] == 'R') {
                    System.out.print("\u001B[31m" + '#' + "\u001B[0m");
                } else if (mat[i][k] == 'A') {
                    System.out.print("\u001B[34m" + '#' + "\u001B[0m");
                } else if (mat[i][k] == 'V') {
                    System.out.print("\u001B[32m" + '#' + "\u001B[0m");
                } else if (mat[i][k] == 'M') {
                    System.out.print("\u001B[33m" + '#' + "\u001B[0m");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("|");
            System.out.println("");
            p++;
        }
        System.out.println(fila);
        System.out.println(filaNums);
    }

    public static void opcion3(Sistema sistema) {
        ArrayList<Jugador> listaJugadores = sistema.getListaJugadores();
        if (listaJugadores.size() == 0) {
            System.out.println("\u001B[31m" + "No hay ningun jugador registrado en el sistema, porfavor seleccione la opción 1 del menu para hacerlo." + "\u001B[0m");
            menuPrincipal(sistema);
        } else {
            Scanner lector = new Scanner(System.in);
            System.out.println("BIENVENIDO AL JUEGO RECTANGULO");
            System.out.println("Lista de jugadores");
            listaJugadores = sistema.getListaJugadores();
            for (int i = 0; i < listaJugadores.size(); i++) {
                System.out.println((i + 1) + ": " + listaJugadores.get(i));
            }
            System.out.println("Seleccione un jugador de la lista para empezar: ");
            int nroJugador = manejarError();
            while (nroJugador < 0 || nroJugador > listaJugadores.size()) {
                System.out.println("\u001B[31m" + "El numero ingresado esta fuera del rango. Reingrese." + "\u001B[0m");
                nroJugador = manejarError();
            }

            System.out.println("");
            System.out.println("Ingrese A para configuracion AL AZAR o P para configuracion PREDETERMINADA");
            char configuracion = chequearConfiguracion();
            while (configuracion != 'A' && configuracion != 'a' && configuracion != 'P' && configuracion != 'p') {
                System.out.println("\u001B[31m" + "La letra ingresada no es valida, reingrese una P o una A." + "\u001B[0m");
                configuracion = chequearConfiguracion();
            }

            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
            String hora = hour.format(now) + "";

            System.out.println("");
            System.out.println("COMIENZA EL JUEGO RECTANGULO " + hora);
            Rectangulo r = sistema.crearRectangulo((nroJugador - 1), configuracion, hora);
            sistema.agregarJuego(r);
            jugarARectangulo(sistema, r);
        }
    }

    public static void imprimirMatrizRectangulo(char[][] mat) {
        String[] col1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "2"};
        String[] col2 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        System.out.print("    ");
        for (int i = 0; i < col1.length; i++) {
            System.out.print(col1[i] + " ");
        }
        System.out.println("");
        System.out.print("    ");
        for (int i = 0; i < col2.length; i++) {
            System.out.print(col2[i] + " ");
        }
        System.out.println("");
        for (int i = 0; i < mat.length; i++) {
            if (i < 9) {
                System.out.print("0" + (i + 1) + " ");
            } else {
                System.out.print((i + 1) + " ");
            }
            System.out.print(" ");
            for (int j = 0; j < mat[0].length; j++) {
                char charr = mat[i][j];
                switch (charr) {
                    case ' ':
                        System.out.print('_');
                        break;
                    case '*':
                        System.out.print('*');
                        break;
                    case 'R':
                        System.out.print("\u001B[31m" + '#' + "\u001B[0m");
                        break;
                    case 'A':
                        System.out.print("\u001B[34m" + '#' + "\u001B[0m");
                        break;
                    case 'V':
                        System.out.print("\u001B[32m" + '#' + "\u001B[0m");
                        break;
                    case 'M':
                        System.out.print("\u001B[33m" + '#' + "\u001B[0m");
                        break;
                    case 'B':
                        System.out.print("\u001B[35m" + '#' + "\u001B[0m");
                        break;
                    case 'G':
                        System.out.print("\033[34;1m" + '#' + "\u001B[0m");
                        break;
                    case 'H':
                        System.out.print("\033[35;2m" + '#' + "\u001B[0m");//4 1
                        break;
                    case 'I':
                        System.out.print("\033[36;1m" + '#' + "\u001B[0m");
                        break;
                    case 'E':
                        System.out.print("\033[37;1m" + '#' + "\u001B[0m");
                        break;
                    case 'P':
                        System.out.print("\033[36;2m" + '#' + "\u001B[0m");
                        break;
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void jugarARectangulo(Sistema s, Rectangulo r) {
        boolean volverAJugar = true;
        while (volverAJugar) {
            int contadorJugadas = 0;
            while (contadorJugadas < 10 && r.quedanJugadas()) {
                Scanner lector = new Scanner(System.in);
                char[][] mat = r.getMatriz();
                System.out.println("");
                imprimirMatrizRectangulo(mat);
                System.out.println("");
                System.out.println("Ingrese coordenadas de su rectangulo");
                String coordenadas = lector.nextLine();
                hayXRectangulo(coordenadas, r, s);
                int[] coords = recibirCoordenadas(coordenadas);
                hayX2Rectangulo(coords, r, s);
                int[] coordsCorrectas = coordCorrectas(coords, coordenadas, r, s);
                hayX2Rectangulo(coordsCorrectas, r, s);
                int[] coordsEntran = coordEntran(coordenadas, coords, coordsCorrectas, r, s);
                hayX2Rectangulo(coordsEntran, r, s);
                boolean seSuperpone = r.validacionSuperposicion((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), coordsCorrectas[2], coordsCorrectas[3]);
                boolean esAdyacente = r.esAdyacente((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), coordsCorrectas[2], coordsCorrectas[3]);
                while (seSuperpone || !esAdyacente) {
                    if (seSuperpone && !esAdyacente) {
                        System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con una posicion ya ocupada y no es adyacente a la matriz anterior" + "\u001B[0m");
                    } else {
                        if (seSuperpone && esAdyacente) {
                            System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con una posicion ya ocupada" + "\u001B[0m");
                        } else {
                            if (!seSuperpone && !esAdyacente) {
                                System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nNo es adyacente a la matriz anterior" + "\u001B[0m");
                            }
                        }
                    }
                    System.out.println("REINGRESE LAS COORDENADAS");
                    coordenadas = lector.nextLine();
                    coords = recibirCoordenadas(coordenadas);
                    hayX2Rectangulo(coords, r, s);
                    coordsCorrectas = coordCorrectas(coords, coordenadas, r, s);
                    hayX2Rectangulo(coordsCorrectas, r, s);
                    coordsEntran = coordEntran(coordenadas, coords, coordsCorrectas, r, s);
                    hayX2Rectangulo(coordsEntran, r, s);
                    seSuperpone = r.validacionSuperposicion((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), (coordsCorrectas[2]), (coordsCorrectas[3]));
                    esAdyacente = r.esAdyacente((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), (coordsCorrectas[2]), (coordsCorrectas[3]));
                }

                r.crearNuevaMatriz((coordsCorrectas[0]) - 1, (coordsCorrectas[1]) - 1, (coordsCorrectas[2]), (coordsCorrectas[3]));
                contadorJugadas++;
            }

            imprimirMatrizRectangulo(r.getMatriz());
            System.out.println("");

            if (contadorJugadas == 10 && !r.quedanJugadas()) {
                System.out.println("\u001B[35m" + "Ya se realizaron 10 jugadas y no quedan movimientos disponibles" + "\u001B[0m");
            } else {
                if (contadorJugadas < 10 && !r.quedanJugadas()) {
                    System.out.println("\u001B[35m" + "No quedan movimientos disponibles" + "\u001B[0m");
                } else {
                    if (contadorJugadas == 10 && r.quedanJugadas()) {
                        System.out.println("\u001B[35m" + "Ya se realizaron 10 jugadas" + "\u001B[0m");
                    }
                }
            }
            System.out.println("");
            System.out.println("PUNTAJE FINAL: " + r.getPuntaje());
            boolean volverAlMenu = volverAMenu("volver a jugar a Rectángulo?", "R para volver a jugar", 'r');
            if (volverAlMenu) {
                volverAJugar = false;
            } else {
                opcion3(s);
            }
        }
        System.out.println("");
        System.out.println("Se retornará al Menu Principal automaticamente");
        System.out.println("");
        System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
        menuPrincipal(s);

    }

    public static boolean validacionRangoCoords(int[] coords) {
        boolean correcto = true;
        for (int i = 0; i < coords.length; i++) {
            if (coords[i] < 1 || coords[i] > 20) {
                correcto = false;
            }
        }
        return correcto;
    }

    public static boolean deseaSalir() {
        Scanner lector = new Scanner(System.in);
        boolean salir = false;
        System.out.println("");
        System.out.println("\u001B[35m" + "Esta seguro de que desea salir del juego?" + "\u001B[0m");
        System.out.println("\u001B[35m" + "Ingrese S para salir o C para continuar" + "\u001B[0m");
        char opcion = chequearConfiguracion();
        while (opcion != 's' && opcion != 'S' && opcion != 'C' && opcion != 'c') {
            System.out.println("\u001B[31m" + "La opcion ingresada no es una S ni una C. Porfavor reingrese." + "\u001B[0m");
            opcion = chequearConfiguracion();
        }
        if (opcion == 'S' || opcion == 's') {
            salir = true;
        }
        return salir;
    }

    public static void hayX2Rectangulo(int[] coordenadas, Rectangulo r, Sistema s) {
        boolean hayX = false;
        for (int i = 0; i < coordenadas.length; i++) {
            if (coordenadas[0] == -1) {
                hayX = true;
            }
        }
        if (hayX) {
            boolean deseaSalir = deseaSalir();
            if (deseaSalir) {
                System.out.println("PUNTAJE FINAL: " + r.getPuntaje());
                System.out.println("Se retornará al Menu Principal automaticamente");
                System.out.println("");
                System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
                menuPrincipal(s);
            } else {
                jugarARectangulo(s, r);
            }
        }
    }

    public static void hayXRectangulo(String coordenadas, Rectangulo r, Sistema sistema) {
        boolean hayX = false;
        String s = coordenadas.trim();
        if (s.equalsIgnoreCase("x")) {
            hayX = true;
        }
        if (hayX) {
            boolean deseaSalir = deseaSalir();
            if (deseaSalir) {
                System.out.println("PUNTAJE FINAL: " + r.getPuntaje());
                System.out.println("Se retornará al Menu Principal automaticamente");
                System.out.println("");
                System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
                menuPrincipal(sistema);
            } else {
                jugarARectangulo(sistema, r);
            }
        }
    }

    public static boolean validarCantidadCoords(int[] coords) {
        boolean correcto = true;
        for (int i = 0; i < coords.length; i++) {
            if (coords[i] == 0) {
                correcto = false;
            }
        }
        return correcto;
    }

    public static int[] coordCorrectas(int[] coords, String coordenadas, Rectangulo r, Sistema s) {
        Scanner lector = new Scanner(System.in);
        boolean validarRango = validacionRangoCoords(coords);
        boolean validarCant = validarCantidadCoords(coords);
        while (!validarCant || !validarRango) {
            if (!validarCant) {
                System.out.println("\u001B[31m" + "La cantidad de coordenadas ingresadas no es suficiente. Reingrese" + "\u001B[0m");
                coordenadas = lector.nextLine();
                coords = recibirCoordenadas(coordenadas);
                hayX2Rectangulo(coords, r, s);
                validarCant = validarCantidadCoords(coords);
                validarRango = validacionRangoCoords(coords);

            } else if (!validarRango) {
                System.out.println("\u001B[31m" + "Las coordenadas ingresadas estan fuera de rango. Reingrese" + "\u001B[0m");
                coordenadas = lector.nextLine();
                coords = recibirCoordenadas(coordenadas);
                hayX2Rectangulo(coords, r, s);
                validarCant = validarCantidadCoords(coords);
                validarRango = validacionRangoCoords(coords);
            }

        }
        return coords;

    }

    public static int[] coordEntran(String coordenadas, int[] coords, int[] coordsCorrectas, Rectangulo r, Sistema s) {
        Scanner lector = new Scanner(System.in);
        boolean excedeTamano = r.outOfBounds((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), (coordsCorrectas[2]), (coordsCorrectas[3]));
        while (excedeTamano) {
            System.out.println("\u001B[31m" + "La matriz esta fuera de rango. Reingrese" + "\u001B[0m");
            coordenadas = lector.nextLine();
            hayXRectangulo(coordenadas, r, s);
            coords = recibirCoordenadas(coordenadas);
            coordsCorrectas = coordCorrectas(coords, coordenadas, r, s);
            excedeTamano = r.outOfBounds((coordsCorrectas[0]) - 1, (coordsCorrectas[1]) - 1, (coordsCorrectas[2]), (coordsCorrectas[3]));
        }

        return coordsCorrectas;
    }

    public static int[] recibirCoordenadas(String coordenadas) {
        Scanner lector = new Scanner(System.in);
        int[] coords = new int[4];
        int contador = 0;
        int aux = 0;
        boolean formatoEsCorrecto = false;
        boolean hayX = false;
        boolean hayMasDeCuatro = true;

        while (hayMasDeCuatro) {
            for (int i = 0; aux < coordenadas.length() && contador < 4 && !hayX; i++) {
                String res = "";
                if (coordenadas.charAt(aux) != ' ') {
                    res += coordenadas.charAt(aux);
                    while (aux < (coordenadas.length() - 1) && coordenadas.charAt(aux + 1) != ' ') {
                        res += coordenadas.charAt(aux + 1);
                        aux++;
                    }
                    if (res.equalsIgnoreCase("x")) {
                        coords[0] = -1;
                        hayX = true;
                    } else {
                        try {
                            int numero = Integer.parseInt(res);
                            formatoEsCorrecto = true;
                            coords[contador] = numero;
                            contador++;
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31m" + "Error en el formato del número." + "\u001B[0m");
//                            lector.nextLine();
                        }
                        if (!formatoEsCorrecto) {
                            System.out.println("Reingrese las coordenadas");
                            coordenadas = lector.nextLine();
                            System.out.println(coordenadas);
                            aux = 0;
                            contador = 0;
                        }
                    }

                }
                if (formatoEsCorrecto || coordenadas.charAt(aux) == ' ') {
                    formatoEsCorrecto = false;
                    aux++;
                }
            }

            if (aux < coordenadas.length() && coords[0] != -1) {
                System.out.println(aux);
                boolean seExcede = chequearMasCoords(coordenadas, aux);
                System.out.println(seExcede);
                if (!seExcede) {
                    hayMasDeCuatro = false;
                } else {
                    System.out.println("\u001B[31m" + "La cantidad de coordenadas es mayor que 4. Reingrese" + "\u001B[0m");
                    coordenadas = lector.nextLine();
                    aux = 0;
                    contador = 0;
                }
            } else {
                hayMasDeCuatro = false;
            }
        }
        return coords;
    }

    public static boolean chequearMasCoords(String stringCoords, int ultiPosi) {
        boolean seExcedenCords = false;
        for (int i = ultiPosi; i < stringCoords.length(); i++) {
            System.out.println(stringCoords.charAt(i));
            if (stringCoords.charAt(i) != ' ') {
                seExcedenCords = true;
            }
        }
        return seExcedenCords;
    }

    public static void opcion4(Sistema sistema) {
        Scanner lector = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            ArrayList<Juego> listaJuegos = sistema.getListaJuegos();
            if (listaJuegos.size() == 0) {
                System.out.println("\u001B[31m" + "No hay ningun juego registrado en el sistema, porfavor seleccione la opcion 2 o 3 del menu para hacerlo." + "\u001B[0m");
                System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
                menuPrincipal(sistema);
            } else {
                System.out.println("Seleccione orden de la lista :" + "\n" + "(A) Por alias creciente" + "\n" + "(B) Por puntaje decreciente");
                char opcion = chequearConfiguracion();
                while (opcion != 'A' && opcion != 'a' && opcion != 'B' && opcion != 'b') {
                    System.out.println("\u001B[31m" + "La letra ingresada no es valida, reingrese una A o una B." + "\u001B[0m");
                    opcion = chequearConfiguracion();
                }
                System.out.println("");
                if (opcion == 'A' && opcion == 'a') {
                    ArrayList<Juego> listaOrdenada = sistema.ordenarXAlias();
                    for (int i = 0; i < listaOrdenada.size(); i++) {
                        System.out.println((i + 1) + "-");
                        System.out.println(listaOrdenada.get(i));
                        System.out.println("");
                    }
                } else {
                    ArrayList<Juego> listaOrdenada = sistema.ordenarXPuntaje();
                    for (int i = 0; i < listaOrdenada.size(); i++) {
                        System.out.println((i + 1) + "-");
                        System.out.println(listaOrdenada.get(i));
                        System.out.println("");
                    }
                }
            }
            boolean volverAlMenu = volverAMenu("ver la bitacóra nuevamente?", "B para ver la bitácora", 'b');
            if (volverAlMenu) {
                System.out.println("");
                System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
                menuPrincipal(sistema);
                salir = true;
            }
        }

    }

    public static boolean volverAMenu(String s1, String s2, char letra) {
        boolean volverMenu = false;
        System.out.println("");
        System.out.println("¿Desea volver al menú o " + s1);
        System.out.println("Ingrese M para volver al menú o " + s2);
        char opcion = chequearConfiguracion();
        while (opcion != 'M' && opcion != 'm' && opcion != Character.toUpperCase(letra) && opcion != letra) {
            System.out.println("\u001B[31m" + "La letra ingresada no es valida, reingrese una de las opciones dictadas." + "\u001B[0m");
            opcion = chequearConfiguracion();

        }
        if (opcion == 'M' || opcion == 'm') {
            volverMenu = true;
        }
        return volverMenu;
    }

    public static int manejarError() {
        boolean esCorrecto = false;
        Scanner in = new Scanner(System.in);
        String opcionIngresada = in.nextLine();
        int resultado = 0;
        if (opcionIngresada.equalsIgnoreCase("x")) {
            resultado = -1;
        } else {
            while (!esCorrecto) {
                opcionIngresada = opcionIngresada.trim();
                if (opcionIngresada.equalsIgnoreCase("x")) {
                    resultado = -1;
                    esCorrecto = true;
                } else {
                    try {
                        resultado = Integer.parseInt(opcionIngresada);
                        if (resultado == -1) {
                            resultado = -2;
                        }
                        esCorrecto = true;
                    } catch (NumberFormatException e) {
                        System.out.println("\u001B[31m" + "Error en el formato del número. Reingrese" + "\u001B[0m");
                        opcionIngresada = in.nextLine();
                    }
                }
            }
        }
        return resultado;
    }

}
