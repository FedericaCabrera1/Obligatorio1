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
        //Metodo que genera el menu y recibe la opcion ingresada por el usuario
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
        //Metodo que se encarga de pedir los datos de cada jugador y registrarlo
        boolean seguirIngresando = true;
        while (seguirIngresando) {
            System.out.println("REGISTRO DE NUEVO JUGADOR");
            Scanner in = new Scanner(System.in);
            System.out.println("Ingrese Nombre:");
            String nombre = validarString();
            nombre = quitarEspacios(nombre);
            System.out.println("Ingrese Edad:");
            int edad = manejarError();
            while (edad <= 0 || edad >= 100) {
                System.out.println("\u001B[31m" + "La edad ingresada está fuera de rango. Debe ser entre 1 y 100. Reingrese" + "\u001B[0m");
                edad = manejarError();
            }
            System.out.println("Ingrese Alias:");
            String alias = validarString();
            alias = quitarEspacios(alias);
            Jugador jugadorAgregado = sistema.crearJugador(nombre, edad, alias);
            boolean esta = sistema.aliasRepetido(jugadorAgregado);
            while (esta) {
                System.out.println("\u001B[31m" + "El alias elegido ya está en uso. Reingrese" + "\u001B[0m");
                alias = validarString();
                alias = quitarEspacios(alias);
                jugadorAgregado = sistema.crearJugador(nombre, edad, alias);
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

    public static String quitarEspacios(String s) {
        String sinEspacios = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                sinEspacios += String.valueOf(s.charAt(i));
            }
        }
        return sinEspacios;
    }

    public static String validarString() {
        //Metodo que valida el ingreso del nombre y alias en el jugador
        //Verifica que tenga al menos una letra
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
        //Metodo que se encarga de pedir el ingreso de datos para Saltar
        ingresoDatosJuego(sistema, "SALTAR");
    }

    public static void opcion3(Sistema sistema) {
        //Metodo que se encarga de pedir el ingreso de datos para Rectangulo
        ingresoDatosJuego(sistema, "RECTÁNGULO");
    }

    public static void ingresoDatosJuego(Sistema sistema, String tipoDeJuego) {
        //Metodo que se encarga de pedir y recibir los datos de cada juego en general
        ArrayList<Jugador> listaJugadores = sistema.getListaJugadores();
        if (listaJugadores.size() == 0) {
            System.out.println("\u001B[31m" + "No hay ningun jugador registrado en el sistema, porfavor seleccione la opción 1 del menu para hacerlo." + "\u001B[0m");
            menuPrincipal(sistema);
        } else {
            Scanner lector = new Scanner(System.in);
            System.out.println("BIENVENIDO AL JUEGO " + tipoDeJuego);
            System.out.println("Lista de jugadores");
            listaJugadores = sistema.getListaJugadores();
            for (int i = 0; i < listaJugadores.size(); i++) {
                System.out.println((i + 1) + ": " + listaJugadores.get(i));
            }
            System.out.println("Seleccione un jugador de la lista para empezar: ");
            int nroJugador = manejarError();
            while (nroJugador <= 0 || nroJugador > listaJugadores.size()) {
                System.out.println("\u001B[31m" + "Jugador no existente. Reingrese." + "\u001B[0m");
                nroJugador = manejarError();
            }

            System.out.println("");
            System.out.println("Ingrese A para configuracion AL AZAR o P para configuracion PREDETERMINADA");

            char configuracion = chequearCaracter();
            while (configuracion != 'A' && configuracion != 'a' && configuracion != 'P' && configuracion != 'p') {
                System.out.println("\u001B[31m" + "La letra ingresada no es valida, reingrese una P o una A." + "\u001B[0m");
                configuracion = chequearCaracter();
            }

            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
            String hora = hour.format(now) + "";
            System.out.println("");
            System.out.println("COMIENZA EL JUEGO " + tipoDeJuego + " " + hora);
            if (tipoDeJuego.equalsIgnoreCase("saltar")) {
                Saltar s = sistema.crearSaltar((nroJugador - 1), configuracion, hora);
                sistema.agregarJuego(s);
                jugarASaltar(sistema, s);

            } else {
                Rectangulo r = sistema.crearRectangulo((nroJugador - 1), configuracion, hora);
                sistema.agregarJuego(r);
                jugarARectangulo(sistema, r, 0);
            }

        }

    }

    public static void jugarASaltar(Sistema sistema, Saltar s) {
        //Metodo que se encarga de realizar cada jugada del juego Saltar
        //Recibe los datos, los verifica y realiza la jugada
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
                            res += "\nColumna " + (i + 1) + ": " + columnas[i] + " posiciones";
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
                            if (i == (columna - 1)) {
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
                                if (i == (columna - 1)) {
                                    if (columnas[i] != 0) {
                                        validar = true;
                                        cantPosiciones = columnas[i];
                                    }
                                }
                            }
                        }
                        if (!seTermina) {
                            s.hacerMovida((columna - 1), cantPosiciones);
                        }

                    } else {
                        System.out.println("\u001B[31m" + "No hay movimientos disponibles para el color " + s.getColor() + "\u001B[0m");
                        s.setColor(s.getColor());
                    }
                } else {
                    seTermina = true;

                }
            }
            imprimirMatrizSaltar(s);
            if (!s.quedanJugadasDisponibles()) {
                System.out.println("\u001B[31m" + "No hay movimientos disponibles para el color R" + "\u001B[0m");
                System.out.println("\u001B[31m" + "No hay movimientos disponibles para el color A" + "\u001B[0m");
                System.out.println("\u001B[31m" + "No hay movimientos disponibles para el color V" + "\u001B[0m");
                System.out.println("\u001B[31m" + "No hay movimientos disponibles para el color M" + "\u001B[0m");
                System.out.println("");

            }
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
        //Metodo que verifica si se ingreso una x en lugar del numero de una columna en el juego Saltar
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

    public static void imprimirMatrizRectangulo(char[][] mat) {
        //Metodo que imprime la matriz rectangulo, generando los colores y demas
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

    public static void jugarARectangulo(Sistema s, Rectangulo r, int contadorJugadas) {
        //Método que realiza cada jugada de Rectangulo
        //Recibe las coordenadas, las verifica y realiza la jugada
        boolean volverAJugar = true;
        while (volverAJugar) {
            while (contadorJugadas < 10 && r.quedanJugadas()) {
                Scanner lector = new Scanner(System.in);
                char[][] mat = r.getMatriz();
                System.out.println("");
                imprimirMatrizRectangulo(mat);
                System.out.println("");
                System.out.println("Ingrese coordenadas de su rectangulo");
                String coordenadas = lector.nextLine();
                hayXRectangulo(coordenadas, r, s, contadorJugadas);
                int[] coords = recibirCoordenadas(coordenadas);
                hayX2Rectangulo(coords, r, s, contadorJugadas);
                int[] coordsCorrectas = coordCorrectas(coords, coordenadas, r, s, contadorJugadas);
                hayX2Rectangulo(coordsCorrectas, r, s, contadorJugadas);
                int[] coordsEntran = coordEntran(coordenadas, coords, coordsCorrectas, r, s, contadorJugadas);
                hayX2Rectangulo(coordsEntran, r, s, contadorJugadas);
                boolean seSuperponeAsterisco = r.validacionSuperposicionAsterisco((coordsEntran[0] - 1), (coordsEntran[1] - 1), coordsEntran[2], coordsEntran[3]);
                boolean seSuperponeRectangulo = r.validacionSuperposicionRectangulo((coordsEntran[0] - 1), (coordsEntran[1] - 1), (coordsEntran[2]), (coordsEntran[3]));
                boolean esAdyacente = r.esAdyacente((coordsEntran[0] - 1), (coordsEntran[1] - 1), coordsEntran[2], coordsEntran[3]);
                while (seSuperponeAsterisco || seSuperponeRectangulo || !esAdyacente) {
                    if (seSuperponeAsterisco && seSuperponeRectangulo && !esAdyacente) {
                        System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con algun asterisco y rectangulo anterior. Ademas no es adyacente a la matriz anterior" + "\u001B[0m");
                    } else {
                        if (seSuperponeAsterisco && !seSuperponeRectangulo && esAdyacente) {
                            System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con algun asterisco." + "\u001B[0m");
                        } else {
                            if (!seSuperponeAsterisco && !seSuperponeRectangulo && !esAdyacente) {
                                System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nNo es adyacente a la matriz anterior" + "\u001B[0m");
                            } else if (!seSuperponeAsterisco && seSuperponeRectangulo && esAdyacente) {
                                System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con un rectangulo anterior." + "\u001B[0m");
                            } else if (seSuperponeAsterisco && !seSuperponeRectangulo && !esAdyacente) {
                                System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con algun asterisco y no es adyacente a la matriz anterior." + "\u001B[0m");
                            } else if (!seSuperponeAsterisco && seSuperponeRectangulo && !esAdyacente) {
                                System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con un rectangulo anterior y no es adyacente a la matriz anterior." + "\u001B[0m");
                            } else if (seSuperponeAsterisco && seSuperponeRectangulo && esAdyacente) {
                                System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con un rectangulo anterior y con algun asterisco." + "\u001B[0m");

                            }
                        }
                    }
                    System.out.println("REINGRESE LAS COORDENADAS");
                    coordenadas = lector.nextLine();
                    coords = recibirCoordenadas(coordenadas);
                    hayX2Rectangulo(coords, r, s, contadorJugadas);
                    coordsCorrectas = coordCorrectas(coords, coordenadas, r, s, contadorJugadas);
                    hayX2Rectangulo(coordsCorrectas, r, s, contadorJugadas);
                    coordsEntran = coordEntran(coordenadas, coords, coordsCorrectas, r, s, contadorJugadas);
                    hayX2Rectangulo(coordsEntran, r, s, contadorJugadas);
                    seSuperponeAsterisco = r.validacionSuperposicionAsterisco((coordsEntran[0] - 1), (coordsEntran[1] - 1), (coordsEntran[2]), (coordsEntran[3]));
                    seSuperponeRectangulo = r.validacionSuperposicionRectangulo((coordsEntran[0] - 1), (coordsEntran[1] - 1), (coordsEntran[2]), (coordsEntran[3]));
                    esAdyacente = r.esAdyacente((coordsEntran[0] - 1), (coordsEntran[1] - 1), (coordsEntran[2]), (coordsEntran[3]));
                }

                r.crearNuevaMatriz((coordsEntran[0]) - 1, (coordsEntran[1]) - 1, (coordsEntran[2]), (coordsEntran[3]));
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

    public static void hayXRectangulo(String coordenadas, Rectangulo r, Sistema sistema, int contadorActualJugadas) {
        //Metodo que verifica si se ingreso una X como primer jugada, antes de ingresar las coordenadas
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
                jugarARectangulo(sistema, r, contadorActualJugadas);
            }
        }
    }

    public static void hayX2Rectangulo(int[] coordenadas, Rectangulo r, Sistema s, int contadorActualJugadas) {
        //Metodo que verifica si una x fue ingresada un vez que ya se hizo la primer jugada. 
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
                jugarARectangulo(s, r, contadorActualJugadas);
            }
        }
    }

    public static boolean deseaSalir() {
        //Método que, una vez ingresada una x, verifica si el usuario desea seguir jugando o no
        Scanner lector = new Scanner(System.in);
        boolean salir = false;
        System.out.println("");
        System.out.println("\u001B[35m" + "Esta seguro de que desea salir del juego?" + "\u001B[0m");
        System.out.println("\u001B[35m" + "Ingrese S para salir o C para continuar" + "\u001B[0m");
        char opcion = chequearCaracter();
        while (opcion != 's' && opcion != 'S' && opcion != 'C' && opcion != 'c') {
            System.out.println("\u001B[31m" + "La opcion ingresada no es una S ni una C. Porfavor reingrese." + "\u001B[0m");
            opcion = chequearCaracter();
        }
        if (opcion == 'S' || opcion == 's') {
            salir = true;
        }
        return salir;
    }

    public static int[] recibirCoordenadas(String coordenadas) {
        //Método que verifica el ingreso de las coordenadas
        //Verifica que no se ingresen letras o en el caso de recibir una x, la identifica
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
                            if (contador == 0 && numero == -1) {
                                //Si el numero ingresado es un -1, se cambia a -2 para no confundir con el ingreso de una x
                                numero = -2;
                            }
                            if (numero == 0) {
                                //Si el numero ingresado es un 0, se cambia a -3 para no confundir con la falta de coordenadas
                                numero = -3;
                            }
                            coords[contador] = numero;
                            contador++;
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31m" + "Error en el formato del número." + "\u001B[0m");
                        }
                        if (!formatoEsCorrecto) {
                            System.out.println("Reingrese las coordenadas");
                            coordenadas = lector.nextLine();
                            while (coordenadas.length() == 0) {
                                System.out.println("\u001B[31m" + "Error en el formato del número." + "\u001B[0m");
                                System.out.println("Reingrese las coordenadas");
                                coordenadas = lector.nextLine();
                            }
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
                boolean seExcede = chequearMasCoords(coordenadas, aux);
                if (!seExcede) {
                    hayMasDeCuatro = false;
                } else {
                    System.out.println("\u001B[31m" + "La cantidad de coordenadas es mayor que 4. Reingrese" + "\u001B[0m");
                    coordenadas = lector.nextLine();
                    while (coordenadas.length() == 0) {
                        System.out.println("\u001B[31m" + "Error en el formato del número." + "\u001B[0m");
                        System.out.println("Reingrese las coordenadas");
                        coordenadas = lector.nextLine();
                    }
                    aux = 0;
                    contador = 0;
                    coords = new int[4];
                }
            } else {
                if (aux < coordenadas.length() && coords[0] == -1) {
                    boolean haySoloX = cantidadCoordsX(coordenadas);
                    if (!haySoloX) {
                        System.out.println("\u001B[31m" + "Error en el formato del número." + "\u001B[0m");
                        System.out.println("Reingrese las coordenadas");
                        coordenadas = lector.nextLine();
                        while (coordenadas.length() == 0) {
                            System.out.println("\u001B[31m" + "Error en el formato del número." + "\u001B[0m");
                            System.out.println("Reingrese las coordenadas");
                            coordenadas = lector.nextLine();
                        }
                        aux = 0;
                        contador = 0;
                        coords = new int[4];
                        hayX = false;
                    } else {
                        hayMasDeCuatro = false;
                    }
                } else {
                    hayMasDeCuatro = false;
                }

            }
        }
        return coords;
    }

    public static boolean cantidadCoordsX(String s) {
        boolean tieneSoloX = true;
        int contadorX = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                if (s.charAt(i) == 'x' || s.charAt(i) == 'X') {
                    contadorX++;
                } else {
                    tieneSoloX = false;
                }
            }
        }
        if(tieneSoloX){
            if(contadorX>1){
                tieneSoloX = false;
            }
        }
        return tieneSoloX;
    }

    public static boolean chequearMasCoords(String stringCoords, int ultimaPosicion) {
        //Método que verifica si las coordenadas de Rectángulo son mas que 4
        boolean seExcedenCords = false;
        for (int i = ultimaPosicion; i < stringCoords.length(); i++) {
            if (stringCoords.charAt(i) != ' ') {
                seExcedenCords = true;
            }
        }
        return seExcedenCords;
    }

    public static int[] coordCorrectas(int[] coords, String coordenadas, Rectangulo r, Sistema s, int contadorActualJugadas) {
        //Método recibe las coordenadas con el formato adecuado
        //Verifica que esten entre 1 y 20
        //Verifica que no haya menos que 4 coordenadas
        Scanner lector = new Scanner(System.in);
        boolean validarRango = validacionRangoCoords(coords);
        boolean validarCant = validarCantidadCoords(coords);
        while (!validarCant || !validarRango) {
            if (!validarCant) {
                System.out.println("\u001B[31m" + "La cantidad de coordenadas ingresadas no es suficiente. Reingrese" + "\u001B[0m");
                coordenadas = lector.nextLine();
                coords = recibirCoordenadas(coordenadas);
                hayX2Rectangulo(coords, r, s, contadorActualJugadas);
                validarCant = validarCantidadCoords(coords);
                validarRango = validacionRangoCoords(coords);

            } else if (!validarRango) {
                System.out.println("\u001B[31m" + "Las coordenadas ingresadas estan fuera de rango. Reingrese" + "\u001B[0m");
                coordenadas = lector.nextLine();
                coords = recibirCoordenadas(coordenadas);
                hayX2Rectangulo(coords, r, s, contadorActualJugadas);
                validarCant = validarCantidadCoords(coords);
                validarRango = validacionRangoCoords(coords);
            }

        }
        return coords;

    }

    public static boolean validacionRangoCoords(int[] coords) {
        //Metodo que valida el rango (1-20) de las coordenadas ingresadas
        boolean correcto = true;
        for (int i = 0; i < coords.length; i++) {
            if (coords[i] < 1 || coords[i] > 20) {
                correcto = false;
            }
        }
        return correcto;
    }

    public static boolean validarCantidadCoords(int[] coords) {
        //Metodo que valida la cantidad de coordenadas ingresadas
        boolean correcto = true;
        for (int i = 0; i < coords.length; i++) {
            if (coords[i] == 0) {
                correcto = false;
            }
        }
        return correcto;
    }

    public static int[] coordEntran(String coordenadas, int[] coords, int[] coordsCorrectas, Rectangulo r, Sistema s, int contadorActualJugadas) {
        //Método que verifica que la matriz ingresada este dentro del rango de la matriz original 20x20
        Scanner lector = new Scanner(System.in);
        boolean excedeTamano = r.excedeRango((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), (coordsCorrectas[2]), (coordsCorrectas[3]));
        while (excedeTamano) {
            System.out.println("\u001B[31m" + "La matriz esta fuera de rango. Reingrese" + "\u001B[0m");
            coordenadas = lector.nextLine();
            hayXRectangulo(coordenadas, r, s, contadorActualJugadas);
            coords = recibirCoordenadas(coordenadas);
            coordsCorrectas = coordCorrectas(coords, coordenadas, r, s, contadorActualJugadas);
            excedeTamano = r.excedeRango((coordsCorrectas[0]) - 1, (coordsCorrectas[1]) - 1, (coordsCorrectas[2]), (coordsCorrectas[3]));
        }

        return coordsCorrectas;
    }

    public static void opcion4(Sistema sistema) {
        //Metodo que muestra la lista de juegos segun el orden seleccionado
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
                char opcion = chequearCaracter();
                while (opcion != 'A' && opcion != 'a' && opcion != 'B' && opcion != 'b') {
                    System.out.println("\u001B[31m" + "La letra ingresada no es valida, reingrese una A o una B." + "\u001B[0m");
                    opcion = chequearCaracter();
                }
                System.out.println("");
                if (opcion == 'A' || opcion == 'a') {
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
        //Metodo que se encarga de preguntarle al usuario si desea continuar jugando o volver al menu
        boolean volverMenu = false;
        System.out.println("");
        System.out.println("¿Desea volver al menú o " + s1);
        System.out.println("Ingrese M para volver al menú o " + s2);
        char opcion = chequearCaracter();
        while (opcion != 'M' && opcion != 'm' && opcion != Character.toUpperCase(letra) && opcion != letra) {
            System.out.println("\u001B[31m" + "La letra ingresada no es valida, reingrese una de las opciones dictadas." + "\u001B[0m");
            opcion = chequearCaracter();
        }
        if (opcion == 'M' || opcion == 'm') {
            volverMenu = true;
        }
        return volverMenu;
    }

    public static char chequearCaracter() {
        //Método que verifica el formato del ingreso de caracteres
        //Por ejemplo, en el ingreso de la configuración de los juegos
        Scanner lector = new Scanner(System.in);
        String configuracion = lector.nextLine();
        boolean esCorrecto = false;
        char conf = ' ';
        while (!esCorrecto) {
            try {
                conf = configuracion.charAt(0);
                boolean hayEspaciosEnBlanco = true;
                for (int i = 1; i < configuracion.length(); i++) {
                    if (configuracion.charAt(i) != ' ') {
                        hayEspaciosEnBlanco = false;
                    }
                }
                if (hayEspaciosEnBlanco) {
                    esCorrecto = true;
                } else {
                    System.out.println("\u001B[31m" + "La letra ingresada no es correcta, tiene que ser un único caracter. Reingree" + "\u001B[0m");
                    configuracion = lector.nextLine();
                }
            } catch (Exception e) {
                System.out.println("\u001B[31m" + "El formato de la letra ingresada es incorrecto. Reingree" + "\u001B[0m");
                configuracion = lector.nextLine();
            }
        }
        return conf;
    }

    public static int manejarError() {
        //Metodo que verifica el formato de un unico numero ingresado
        //Verifica que no sea una letra o en el caso de la x, la identifica
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
